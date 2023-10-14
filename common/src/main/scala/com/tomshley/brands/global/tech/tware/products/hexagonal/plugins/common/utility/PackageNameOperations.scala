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
package common
package utility

object PackageNameOperations {
  def appNameToPackageName(appName: String): String = {
    appName
      .replace("_", ".")
      .replace("-", ".")
  }

  def appNameToArtifactName(appName: String): String = {
    appName
      .replace(".", "_")
      .replace("\\s", "-")
  }

  def appNameToFileName(appName: String): String = {
    appName
      .split('-')
      .map(_.capitalize)
      .mkString
      .split('_')
      .map(_.capitalize)
      .mkString
      .split('.')
      .map(_.capitalize)
      .mkString
  }

  def packageNameToFilepath(packageName: String): String = {
    packageName.replace(".", "/")
  }
}
