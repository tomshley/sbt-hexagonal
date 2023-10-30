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
package projecttemplate

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.model.ResourceTypes
import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt.UnmanagedResource
import sbt.*
import sbt.Keys.{baseDirectory, sLog}

import java.nio.charset.Charset
import java.nio.file.Files
import scala.util.Using

object ProjectTemplatePlugin extends AutoPlugin {

  override val trigger: PluginTrigger = noTrigger

  override val requires: Plugins = plugins.JvmPlugin

  object autoImport extends ProjectTemplateKeys

  import autoImport.*

  override lazy val projectSettings: Seq[Setting[?]] = Seq(
    generateTemplateContent := generateTemplateContentTask.value
  )

  private def generateTemplateContentTask: Def.Initialize[Task[Unit]] = Def.task {
    val log = sLog.value

    log.info("Enforcing file structure for a hexagonal project...")

    lazy val gitignoreSource: UnmanagedResource = UnmanagedResource("templates/gitignore.tpl", ".gitignore", baseDirectory.value, unmanagedResourceTypeOption=Some(ResourceTypes.Template))
    lazy val jvmoptsSource: UnmanagedResource = UnmanagedResource("templates/jvmopts.tpl", ".jvmopts", baseDirectory.value, unmanagedResourceTypeOption=Some(ResourceTypes.Template))
    lazy val scalafmtSource: UnmanagedResource = UnmanagedResource("templates/scalafmt.tpl", ".scalafmt", baseDirectory.value, unmanagedResourceTypeOption=Some(ResourceTypes.Template))
    lazy val licenseSource: UnmanagedResource = UnmanagedResource("templates/license.tpl", "LICENSE", baseDirectory.value, unmanagedResourceTypeOption=Some(ResourceTypes.Template))
    lazy val versionSource: UnmanagedResource = UnmanagedResource("templates/version.tpl", "VERSION", baseDirectory.value, unmanagedResourceTypeOption=Some(ResourceTypes.Template))

    lazy val allTemplates = Seq(gitignoreSource, jvmoptsSource, scalafmtSource, licenseSource, versionSource)

    allTemplates
      .filterNot { s =>
        s.targetFile.exists()
      }
      .foreach(t => {

        Using(
          scala.io.Source.createBufferedSource(t.unmanagedResourceURL.openStream())
        ) { inputFile =>
          Using(Files.newBufferedWriter(t.targetFile.toPath, Charset.forName("UTF-8"))) { outputFile =>
            for (line <- inputFile.getLines) {
              outputFile.write(line + "\n")
            }
          }
        }
      })
  }
}
