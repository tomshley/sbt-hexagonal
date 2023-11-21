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

protected[this] object BaseProjectSettingsPlugin extends AutoPlugin {

  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends CommonProjectSettingsKeys

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[?]] = {
    super.projectSettings ++ baseSettings3
  }
}
object ProjectsHelperPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = BaseProjectSettingsPlugin

  object autoImport extends ProjectsHelperKeys with CommonProjectSettingsKeys

  import autoImport.*
}
object LibProjectPlugin extends AutoPlugin {
  override val requires: Plugins = BaseProjectSettingsPlugin

  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.javaProject ++
    ProjectSettingsDefs.jsonProject ++
    ProjectSettingsDefs.libProject ++
    ProjectSettingsDefs.scala3CrossVersions
}
object LibProjectAkkaPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.akkaProject
}
object LibProjectAkkaGrpcPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectAkkaPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.akkaGRPCProject
}
object LibProjectAkkaHttpPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectAkkaPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.akkaHTTPProject
}
object LibManagedProjectPlugin extends AutoPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.scala3CrossVersions ++
    ProjectSettingsDefs.hexagonalProject
}
object LibUnmanagedProjectPlugin extends AutoPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.scala3CrossVersions ++
    ProjectSettingsDefs.unmanagedProject ++
    ProjectSettingsDefs.hexagonalProject
}

object CoreProjectPlugin extends AutoPlugin {

  override val requires: Plugins = LibProjectAkkaGrpcPlugin && LibManagedProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.hexagonalAkkaGrpcProject
}
object ValueAddProjectPlugin extends AutoPlugin {

  override val requires: Plugins = LibProjectAkkaHttpPlugin && LibProjectAkkaGrpcPlugin && LibManagedProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    ProjectSettingsDefs.hexagonalAkkaGrpcProject ++
    ProjectSettingsDefs.hexagonalAkkaHttpProject
}
object EdgeProjectPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectAkkaHttpPlugin && LibManagedProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.hexagonalAkkaHttpProject
}
