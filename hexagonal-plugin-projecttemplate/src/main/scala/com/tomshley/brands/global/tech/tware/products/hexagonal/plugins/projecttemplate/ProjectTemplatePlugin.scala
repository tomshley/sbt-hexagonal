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

import sbt.Keys.{baseDirectory, sLog}
import sbt.{Def, *}

import java.nio.charset.Charset
import java.nio.file.Files
import scala.io.BufferedSource
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
    case class TemplateSource(name: String, extension: String = ".tpl", targetDestination: File = baseDirectory.value) {
      def bufferedFromTemplateFile: BufferedSource = {
        scala.io.Source.fromFile(name)
      }

      lazy val fileName: String = {
        file(name).getName
      }

      lazy val targetFile: File = {
        file(Seq(targetDestination, cleanFileName).mkString("/"))
      }

      lazy val cleanFileName: String = fileName.substring(0, fileName.length - extension.length)
    }

    lazy val gitignoreSource: TemplateSource = TemplateSource("src/main/resources/.gitignore.tpl")
    lazy val jvmoptsSource: TemplateSource = TemplateSource("src/main/resources/.jvmopts.tpl")
    lazy val scalafmtSource: TemplateSource = TemplateSource("src/main/resources/.scalafmt.tpl")
    lazy val licenseSource: TemplateSource = TemplateSource("src/main/resources/LICENSE.tpl")
    lazy val versionSource: TemplateSource = TemplateSource("src/main/resources/VERSION.tpl")

    lazy val allTemplates = Seq(gitignoreSource, jvmoptsSource, scalafmtSource, licenseSource, versionSource)

    allTemplates
      .filterNot { s =>
        s.targetFile.exists()
      }
      .map(s => {
        Using(Files.newBufferedWriter(s.targetFile.toPath, Charset.forName("UTF-8"))) { writer =>
          s.bufferedFromTemplateFile.getLines().foreach(line => writer.write(line))
        }
      })
  }
}
