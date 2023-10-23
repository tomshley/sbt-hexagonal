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

sealed trait BaseProjectSettingsPlugin extends AutoPlugin {

  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends CommonProjectSettingsKeys

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[?]] = {
    super.projectSettings ++ baseSettings3
  }
}
object ProjectsHelperPlugin extends AutoPlugin{
  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends ProjectsHelperKeys

  import autoImport.*
}
object HexagonalLibProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] = {
    super.projectSettings ++ ProjectSettingsDefs.javaProject ++ ProjectSettingsDefs.jsonProject ++ ProjectSettingsDefs.akkaProject ++ ProjectSettingsDefs.libProject ++ ProjectSettingsDefs.scala3CrossVersions
  }
}
object HexagonalCoreProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] = super.projectSettings ++ ProjectSettingsDefs.scala3CrossVersions
}
object HexagonalValueAddProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] = super.projectSettings ++ ProjectSettingsDefs.scala3CrossVersions
}
object HexagonalEdgeProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] = super.projectSettings ++ ProjectSettingsDefs.scala3CrossVersions
}
