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

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings.keys.{CommonProjectKeys, ProjectTypeKeys}
import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings.settings.ProjectSettingsDefs
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.docker.DockerPlugin
import org.apache.pekko.grpc.sbt.PekkoGrpcPlugin
import play.twirl.sbt.SbtTwirl
import sbt.{Def, *}

protected[this] object BaseProjectSettingsPlugin extends AutoPlugin {

  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends CommonProjectKeys

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[?]] = {
    super.projectSettings ++ baseSettings3
  }
}
object ProjectsHelperPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = BaseProjectSettingsPlugin

  object autoImport extends ProjectTypeKeys with CommonProjectKeys
}
object LibProjectPlugin extends AutoPlugin {
  override val requires: Plugins = BaseProjectSettingsPlugin

  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.javaProject ++
    ProjectSettingsDefs.jsonProject ++
    ProjectSettingsDefs.libProject
}
object ProtoOnlyPekkoProjectPlugin extends AutoPlugin {
  override val requires: Plugins = PekkoGrpcPlugin

  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++ ProjectSettingsDefs.scala3Settings
}
object LibProjectPekkoPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.pekkoProject
}
object LibProjectPekkoPersistencePlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.pekkoProject ++
    ProjectSettingsDefs.pekkoPersistenceProject
}
object LibProjectPekkoProjectionPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.pekkoProject ++
    ProjectSettingsDefs.pekkoProjectionProject
}
object LibProjectPekkoKafkaPlugin extends AutoPlugin {
  override val requires: Plugins = LibProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.pekkoKafkaProject
}

object LibManagedProjectPlugin extends AutoPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings
}

object LibUnmanagedProjectPlugin extends AutoPlugin {
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.unmanagedProject
}

object DockerPublishPlugin extends AutoPlugin {
  override def requires = JavaAppPackaging && 
    DockerPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings ++
    ProjectSettingsDefs.dockerPublishProject
}

object CoreProjectPlugin extends AutoPlugin {

  override val requires: Plugins = DockerPublishPlugin && 
    PekkoGrpcPlugin && 
    LibProjectPekkoPlugin && 
    LibManagedProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings
//    ProjectSettingsDefs.hexagonalAkkaGrpcProject
}
object ValueAddProjectPlugin extends AutoPlugin {

  override val requires
  : Plugins = DockerPublishPlugin && 
    PekkoGrpcPlugin && 
    LibProjectPekkoPlugin && 
    LibProjectPekkoPersistencePlugin && 
    LibProjectPekkoProjectionPlugin && 
    LibProjectPekkoKafkaPlugin && 
    LibManagedProjectPlugin

  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings
}

object EdgeProjectPlugin extends AutoPlugin {
  override val requires: Plugins = DockerPublishPlugin &&
    DockerPublishPlugin &&
    SbtTwirl &&
    PekkoGrpcPlugin &&
    LibProjectPekkoPlugin &&
    LibProjectPekkoPersistencePlugin &&
    LibProjectPekkoProjectionPlugin &&
    LibProjectPekkoKafkaPlugin &&
    LibManagedProjectPlugin
  override def projectSettings: Seq[Def.Setting[?]] =
    super.projectSettings
}
