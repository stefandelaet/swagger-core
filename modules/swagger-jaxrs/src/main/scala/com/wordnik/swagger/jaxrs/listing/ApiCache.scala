package com.wordnik.swagger.jaxrs.listing

import scala.collection.Map

//TODO: Needed? Useful for testing...
trait ApiCache {
  def setCache(values: Option[Map[String, Class[_]]]): Unit
  def getCache(): Option[Map[String, Class[_]]]
}

trait BasicApiCache extends ApiCache {
  override def setCache(values: Option[Map[String, Class[_]]]): Unit = BasicApiCache.cache = values
  override def getCache(): Option[Map[String, Class[_]]] = BasicApiCache.cache
}

object BasicApiCache {
  private var cache: Option[Map[String, Class[_]]] = None
}

trait NoCache extends ApiCache {
  override def setCache(values: Option[Map[String, Class[_]]]): Unit = {}
  override def getCache(): Option[Map[String, Class[_]]] = None
}