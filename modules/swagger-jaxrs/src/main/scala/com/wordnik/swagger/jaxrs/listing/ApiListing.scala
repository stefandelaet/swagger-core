package com.wordnik.swagger.jaxrs.listing

import javax.ws
import ws.rs.core.Response
import ws.rs.core.Response.Status
import ws.rs.{PathParam, Path, GET}
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.core.{DocumentationEndPoint, Documentation}
import scala.collection.JavaConverters._
import com.wordnik.swagger.jaxrs.JaxrsApiReader
import ApiListing._

object ApiListing {
  //TODO Revisit how these are loaded (from annotation?)
  JaxrsApiReader.setFormatString("")
  val apiVersion = "0.2"
  val swaggerVersion = "1.1"
  val basePath = ""
}

//TODO: Need to add in the proper slf4j logger trait
class ApiListing extends ApiLocator {

  @GET
  def resourceListing(): Response = {
    val doc = new Documentation(apiVersion, swaggerVersion, basePath, null)
    doc.setApis(loadApiRoutes().asJava)
    Response.ok(doc).build()
  }

  @GET
  //TODO Dropped out the odd {.format}, still needed?
  @Path("/{route}")
  def apiListing(@PathParam("route") route: String): Response = {
    createDocForRoute(route) match {
      case Some(doc) => Response.ok.entity(doc).build
      case None => Response.status(Status.NOT_FOUND).build
    }
  }

  protected def loadApiRoutes(): List[DocumentationEndPoint] = {
    val listingRoot = determineListingRoot()
    //TODO: The following is hard to read
    val apis = for ((route, value) <- resolveApiRoutes) yield {
      createDocForRoute(route) match {
        case Some(doc) if validDoc(doc) => Some(new DocumentationEndPoint(listingRoot + doc.resourcePath, ""))
        case _ => None
      }
    }
    apis.flatten.toList
  }

  protected def determineListingRoot() = if (getClass.isAnnotationPresent(classOf[Api])) getClass.getAnnotation(classOf[Api]).value else "/"
  protected def validDoc(doc: Documentation): Boolean = doc.getApis != null && doc.getApis.size > 0

  protected def createDocForRoute(route: String): Option[Documentation] = {
    val routes = resolveApiRoutes
    if (routes.contains(route)) Some(createDoc(routes(route))) else None
  }

  protected def createDoc(apiClass: Class[_]): Documentation = {
    //TODO: Using the reflections lib, the following will always work, but not so sure with the jax-rs App class
    val apiPath = apiClass.getAnnotation(classOf[Api]).value
    JaxrsApiReader.read(apiClass, apiVersion, swaggerVersion, basePath, apiPath)
  }
}