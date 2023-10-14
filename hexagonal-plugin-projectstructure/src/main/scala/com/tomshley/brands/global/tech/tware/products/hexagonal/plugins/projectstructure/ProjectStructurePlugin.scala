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
package projectstructure

import sbt.Keys.{baseDirectory, name, organization, sLog}
import sbt.{Def, *}

object ProjectStructurePlugin extends AutoPlugin {

  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends ProjectStructureKeys

  import autoImport.*

  override lazy val projectSettings: Seq[Setting[?]] = Seq(
    enforceProjectStructure := enforceProjectStructureTask.value
  )

  private def enforceProjectStructureTask: Def.Initialize[Task[Unit]] = Def.task {
    val log = sLog.value

    log.info("Enforcing file structure for a hexagonal project...")
    log.info(Seq("Hexagonal Part", hexagonalPart.value).mkString(":"))

    lazy val filesystemArtifacts: Unit = FilesystemArtifacts.apply(
      name.value,
      organization.value,
      Some(baseDirectory.value)
    )

    log.info(filesystemArtifacts.toString)

    filesystemArtifacts
  }
}
