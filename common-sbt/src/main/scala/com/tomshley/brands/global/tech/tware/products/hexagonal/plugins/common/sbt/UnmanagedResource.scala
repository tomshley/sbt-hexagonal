package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.model.ResourceTypes
import sbt.{File, file}

import java.net.URL
case class UnmanagedResource(unmanagedName: String,
                             targetName: String,
                             buildBaseDirectory: File,
                             unmanagedResourceTypeOption: Option[ResourceTypes.EnumType] = None) {

  lazy val targetFile: File = {
    file(Seq(buildBaseDirectory, targetName).mkString("/"))
  }

  lazy val unmanagedResourceURL: URL = getClass.getClassLoader.getResource(
    unmanagedName
  )

  lazy val fileExtension:String = {
    unmanagedResourceTypeOption.fold(ifEmpty = ResourceTypes.Untyped)(unmanagedResourceType => unmanagedResourceTypeOption.get).toString
  }
}
