package com.wordnik.swagger.jaxrs.listing

import collection.{mutable, Map}
import com.wordnik.swagger.annotations.Api

trait ApiLocator extends ReflectionsApiAnnotationLoader with BasicApiCache  {
  protected def resolveApiRoutes(): Map[String, Class[_]] = {
    getCache match {
      case Some(cache) => cache;
      case None => {
        val resources = locateApiAnnotations()
        setCache(Some(resources))
        resources
      }
    }
  }

  protected def locateApiAnnotations(): mutable.HashMap[String, Class[_]] = {
    val apiClasses = new mutable.HashMap[String, Class[_]]
    load().foreach(resource => {
      val path = apiPath(resource.getAnnotation(classOf[Api]).value)
      apiClasses.put(path, resource)
    })
    apiClasses
  }

  protected def apiPath(str: String) = if (str.startsWith("/")) str.substring(1) else str
}