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

package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.model.ResourceTypes
import sbt.{File, file}

import java.net.URL
case class UnmanagedResource(unmanagedName: String,
                             targetName: String,
                             buildBaseDirectory: File,
                             unmanagedResourceTypeOption: Option[ResourceTypes.EnumType] = None) {

  lazy val targetFile: File = {
    file(Seq(buildBaseDirectory, targetName).mkString("/"))
  }

  lazy val unmanagedResourceURL: URL = getClass.getClassLoader.getResource(
    unmanagedName
  )

  lazy val fileExtension:String = {
    unmanagedResourceTypeOption.fold(ifEmpty = ResourceTypes.Untyped)(unmanagedResourceType => unmanagedResourceTypeOption.get).toString
  }
}
