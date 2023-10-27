package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt

import sbt.Keys.baseDirectory
import sbt.{File, file}

import java.net.URL

case class UnmanagedResource(unmanagedName: String,
                             targetName: String,
                             buildBaseDirectory: File,
                             extension: String = ".tpl"
                             ) {
  private lazy val fileName: String = {
    file(unmanagedName).getName
  }

  lazy val targetFile: File = {
    file(Seq(buildBaseDirectory, targetName).mkString("/"))
  }

  lazy val cleanManagedFileName: String = fileName.substring(0, fileName.length - extension.length)

  lazy val unmanagedResourceURL: URL = getClass.getClassLoader.getResource(
    unmanagedName
  )
}
