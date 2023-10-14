package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins
package projectstructure

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.model.HexagonalPart
import sbt.*

trait ProjectStructureKeys {
  lazy val enforceProjectstructure = taskKey[Unit]("Generates hexagonal project structure")
  lazy val hexagonalPart = settingKey[HexagonalPart]("the hexagonal architecture part")
}
