package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectstructure

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.model.HexagonalPart
import sbt.*

trait ProjectStructureKeys {
//  lazy val sourceZipDir = settingKey[File]("source directory to generate zip from.")
//  lazy val targetZipDir = settingKey[File]("target directory to store generated zip.")
//  lazy val zip = taskKey[Unit]("Generates zip file which includes all files from sourceZipDir")
  lazy val enforceProjectstructure = taskKey[Unit]("Generates hexagonal project structure")
  lazy val hexagonalPart = settingKey[HexagonalPart]("the hexagonal architecture part")
}
