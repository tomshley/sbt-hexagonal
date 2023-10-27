package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt

import sbt.{File, file}

import java.net.URL

case class UnmanagedResource(unmanagedName: String,
                             targetName: String,
                             buildBaseDirectory: File,
                             extension: String = ".tpl"
                             ) {

  lazy val targetFile: File = {
    file(Seq(buildBaseDirectory, targetName).mkString("/"))
  }

  lazy val unmanagedResourceURL: URL = getClass.getClassLoader.getResource(
    unmanagedName
  )
}
