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
object ProjectsHelperPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends ProjectsHelperKeys with CommonProjectSettingsKeys

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[?]] = {
    super.projectSettings ++ baseSettings3
  }
}
object LibProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.javaProject ++
    ProjectSettingsDefs.jsonProject ++
    ProjectSettingsDefs.libProject ++
    ProjectSettingsDefs.scala3CrossVersions
}
object LibProjectAkkaPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.javaProject ++
    ProjectSettingsDefs.jsonProject ++
    ProjectSettingsDefs.akkaProject ++
    ProjectSettingsDefs.libProject ++
    ProjectSettingsDefs.scala3CrossVersions
}
object LibProjectAkkaGrpcPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.javaProject ++
    ProjectSettingsDefs.jsonProject ++
    ProjectSettingsDefs.akkaProject ++
    ProjectSettingsDefs.akkaProject ++
    ProjectSettingsDefs.akkaGRPCProject ++
    ProjectSettingsDefs.libProject ++
    ProjectSettingsDefs.scala3CrossVersions
}
object LibProjectAkkaHttpPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.javaProject ++
    ProjectSettingsDefs.jsonProject ++
    ProjectSettingsDefs.akkaProject ++
    ProjectSettingsDefs.akkaProject ++
    ProjectSettingsDefs.akkaHTTPProject ++
    ProjectSettingsDefs.libProject ++
    ProjectSettingsDefs.scala3CrossVersions
}
object LibUnmanagedProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.scala3CrossVersions ++
    ProjectSettingsDefs.unmanagedProject ++
    ProjectSettingsDefs.hexagonalProject
}
object CoreProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.scala3CrossVersions ++
    ProjectSettingsDefs.hexagonalProject ++
    ProjectSettingsDefs.hexagonalAkkaGrpcProject ++
    ProjectSettingsDefs.akkaGRPCProject
}
object ValueAddProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.scala3CrossVersions ++
    ProjectSettingsDefs.hexagonalProject
}
object EdgeProjectPlugin extends BaseProjectSettingsPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.scala3CrossVersions ++
    ProjectSettingsDefs.hexagonalProject ++
    ProjectSettingsDefs.hexagonalAkkaHttpProject ++
    ProjectSettingsDefs.akkaHTTPProject
}
