package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt.BasicSbtSettingsKeys
import sbt.Keys.{licenses, scalaVersion, scalacOptions, version}
import sbt.url

protected[projectsettings] trait CommonProjectSettingsKeys extends BasicSbtSettingsKeys {
  lazy val baseSettings3: sbt.Def.SettingsDefinition = Seq(
    licenses := {
      val tagOrBranch =
        if (version.value.endsWith("SNAPSHOT")) "main"
        else "v" + version.value
      Seq(
        ("APACHE-2.0",
          url("https://raw.githubusercontent.com/tomshley/hexagonal-plugins-sbt/" + tagOrBranch + "/LICENSE"))
      )
    },
    scalacOptions += "-Wconf:cat=deprecation&msg=.*JavaConverters.*:s",
    scalaVersion := ProjectSettingsDefs.Scala3
  )
}
