/*
 * Copyright 2023 Tomshley LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Thomas Schena @sgoggles <https://github.com/sgoggles> | <https://gitlab.com/sgoggles>
 */

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
