package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt

import sbt.{File, Project, file}

protected[plugins] trait SbtProjectHelper {
  val projectName: String
  val projectBaseOption: Option[File]

  /*
   * Example usage:
   * private def getProjectType[T : WeakTypeTag] = weakTypeOf[T] match {
   *
   *   case t if t =:= weakTypeOf[String] => "String matched"
   *   case t if t <:< weakTypeOf[HexagonalPart] => "Some other type matched"
   * }
   */

  lazy val sbtProject: Project = {
    Project(
      id = projectName,
      base = projectBaseOption.fold(ifEmpty = file(projectName))(projectFile => projectBaseOption.get)
    )
  }
}
