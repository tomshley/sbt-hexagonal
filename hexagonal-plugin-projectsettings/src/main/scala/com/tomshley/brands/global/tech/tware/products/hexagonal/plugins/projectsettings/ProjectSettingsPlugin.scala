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

package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins
package projectsettings

import sbt.{Def, *}

/*
 * WARNING - TODO - Under Construction!
 */

sealed trait ProjectSettingsPlugin extends AutoPlugin {

  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends ProjectSettingsKeys

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[?]] = {
    println("hit the base settings")
    super.projectSettings ++ baseSettings3
  }
}
object ProjectHelperPlugin extends ProjectSettingsPlugin {}
object HexagonalLibProjectPlugin extends ProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] = {
    println("hit the specific settings (lib)")
    super.projectSettings ++ ProjectSettingsDefs.javaProject ++ ProjectSettingsDefs.jsonProject ++ ProjectSettingsDefs.akkaProject ++ ProjectSettingsDefs.libProject ++ ProjectSettingsDefs.scala3CrossVersions
  }
}
object HexagonalCoreProjectPlugin extends ProjectSettingsPlugin {}
object HexagonalValueAddProjectPlugin extends ProjectSettingsPlugin {}
object HexagonalEdgeProjectPlugin extends ProjectSettingsPlugin {}
