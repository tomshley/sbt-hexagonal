package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins
package projectsettings
import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.models.HexagonalPart
import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.keys.BasicHexagonalSettings
import sbt.*

trait ProejctSettingsKeys extends BasicHexagonalSettings{
  lazy val hexagonalPart = settingKey[HexagonalPart]("the hexagonal architecture part")
}
