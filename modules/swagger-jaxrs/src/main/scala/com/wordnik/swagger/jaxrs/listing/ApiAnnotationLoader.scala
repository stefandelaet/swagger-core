package com.wordnik.swagger.jaxrs.listing

import collection.mutable
import com.wordnik.swagger.annotations.Api

//TODO Add in the old-style jax-rs Application class as a loader
trait ApiAnnotationLoader {
  def load(): mutable.Set[Class[_]]
}

trait ReflectionsApiAnnotationLoader extends ApiAnnotationLoader {
  import org.reflections.util.{FilterBuilder, ClasspathHelper, ConfigurationBuilder}
  import org.reflections.scanners.{SubTypesScanner, TypeAnnotationsScanner}
  import org.reflections.Reflections
  import scala.collection.JavaConverters._

  override def load(): mutable.Set[Class[_]] = {
    //TODO: Should this try and use the webinf lib and webinfclasses?
    val config = new ConfigurationBuilder()
      .setUrls(ClasspathHelper.forClassLoader())
      .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner())
      //TODO: Locate the proper package to use in filter
      .filterInputsBy(new FilterBuilder.Exclude(FilterBuilder.prefix("com.wordnik.swagger.jaxrs")))
    new Reflections(config).getTypesAnnotatedWith(classOf[Api]).asScala
  }
}