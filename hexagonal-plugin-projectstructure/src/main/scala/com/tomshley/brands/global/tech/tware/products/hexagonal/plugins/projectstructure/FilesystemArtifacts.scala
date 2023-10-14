/*
 * copyright 2023 tomshley llc
 *
 * licensed under the apache license, version 2.0 (the "license");
 * you may not use this file except in compliance with the license.
 * you may obtain a copy of the license at
 *
 * http://www.apache.org/licenses/license-2.0
 *
 * unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "as is" basis,
 * without warranties or conditions of any kind, either express or implied.
 * see the license for the specific language governing permissions and
 * limitations under the license.
 *
 * @author thomas schena @sgoggles <https://github.com/sgoggles> | <https://gitlab.com/sgoggles>
 *
 */

package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins
package projectstructure

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.utility.PackageNameOperations.{appNameToPackageName, packageNameToFilepath}
import sbt.*

protected[projectstructure] class FilesystemArtifacts(val fqdnPath: String) extends FilesystemArtifactsDef

protected[projectstructure] object FilesystemArtifacts {
  def apply(projectName: String, orgSpace: String, dir: Option[File]): Unit = {

    lazy val src = dir.getOrElse(file(".")).getAbsolutePath + "/src"
    lazy val srcMain = src + "/main"
    lazy val srcMainScala = srcMain + "/scala"
    lazy val fqdnPath = srcMainScala + "/" + packageNameToFilepath(
        Seq(
          orgSpace,
          appNameToPackageName(projectName)
        ).mkString(".")
      )

    new FilesystemArtifacts(fqdnPath).enforce()
  }
}

/**
 */
sealed trait FilesystemArtifactsDef {
  private[this] lazy val filePaths: List[String] = List(
    fqdnPath,
    fqdnPath + "/app",
    fqdnPath + "/core",
    fqdnPath + "/core/models",
    fqdnPath + "/core/ports",
    fqdnPath + "/core/ports/incoming",
    fqdnPath + "/core/ports/outgoing",
    fqdnPath + "/infrastructure",
    fqdnPath + "/infrastructure/config",
    fqdnPath + "/infrastructure/adapters",
    fqdnPath + "/infrastructure/adapters"
  )

  private[this] lazy val files: List[File] = {
    for (fp <- filePaths)
      yield new File(fp)
  }

  private[this] lazy val readmeFiles: List[File] = {
    for (fp <- filePaths)
      yield new File(fp + "/README.md")
  }
  val fqdnPath: String

  def enforce(): Unit = {
    files
      .filter(!_.exists())
      .foreach(f => {

        f.mkdirs()
      })

    readmeFiles
      .filter(!_.exists())
      .foreach(f => {
        lazy val readmeParent = f.getParentFile

        if (readmeParent.isDirectory) {
          lazy val readmeSiblingsNoExist = readmeParent.listFiles().forall(_.isDirectory)

          if (readmeSiblingsNoExist) {
            f.createNewFile()
          }
        }
      })
  }

  override def toString: String = {
    filePaths.toString()
  }
}
