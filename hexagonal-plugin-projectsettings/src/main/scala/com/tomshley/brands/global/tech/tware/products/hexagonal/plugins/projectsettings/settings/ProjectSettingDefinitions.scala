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

package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings.settings

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings.vendor.{AkkaProjectSettings, PekkoProjectSettings}
import com.typesafe.sbt.packager.Keys.{dockerBaseImage, dockerRepository, dockerUpdateLatest, dockerUsername}
import sbt.{Def, *}
import sbt.Keys.*


sealed trait ProjectSettingsVersions {
  lazy val jetbrainsAnnotationsVersion = "24.0.1"
  lazy val apacheCommonsLang3Version = "3.12.0"
  lazy val jodaTimeVersion = "2.12.5"
  lazy val googleGuavaVersion = "23.0"
  lazy val jacksonVersion = "2.15.2"
  lazy val slf4jVersion = "2.0.5"
  lazy val apacheCommonsIOVersion = "20030203.000550"
  lazy val apacheCommonsDigester = "3.2"
  lazy val scalaTestVersion = "3.2.15"
  lazy val json4sVersion = "4.1.0-M3"
  val Scala212: String = "2.12.18"
  val Scala3 = "3.4.2"
  val Scala213 = "2.13.14"
  val Scala2Versions: Seq[String] = Seq(Scala213)
  val ScalaVersions: Seq[String] = Scala2Versions :+ Scala3
}
protected[projectsettings] object ProjectSettingsDefs extends ProjectSettingsVersions {
  lazy val dockerPublishProject: Seq[Def.Setting[? >: String & Option[String] & Boolean]] = Seq(
    dockerBaseImage := "docker.io/library/eclipse-temurin:17.0.3_7-jre-jammy",
    dockerUsername := sys.props.get("docker.username"),
    dockerRepository := sys.props.get("docker.registry"),
    dockerUpdateLatest := true
  )

  lazy val scala213Settings: Seq[Def.Setting[? >: Seq[String] & String <: Object]] = Seq(
    crossScalaVersions := Scala2Versions,
    scalaVersion := Scala213
  )

  lazy val scala3Settings: Seq[Def.Setting[? >: Seq[String] & String <: Object]] = Seq(
    crossScalaVersions := ScalaVersions,
    scalaVersion := Scala3
  )

  lazy val hexagonalProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
/* UNDER CONSTRUCTION */
//        "com.tomshley.brands.global.tech.tware.products.hexagonal.lib" % "hexagonal-lib_3" % "0.1.0-SNAPSHOT"
      )
  )

  lazy val akkaProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= AkkaProjectSettings.Libraries.akkaActorProjectSettings
  )
  lazy val akkaGRPCProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= AkkaProjectSettings.Libraries.akkaActorProjectSettings
  )
  lazy val akkaHTTPProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= AkkaProjectSettings.Libraries.akkaActorProjectSettings
  )

  lazy val pekkoProject: Seq[Def.Setting[? >: Seq[Resolver] & Seq[ModuleID] <: Seq[Serializable]]] = Seq(
    resolvers ++= PekkoProjectSettings.Resolvers.pekkoResolvers,
    libraryDependencies ++= PekkoProjectSettings.Libraries.pekkoActorLibraries
  )
  lazy val pekkoPersistenceProject: Seq[Def.Setting[? >: Seq[Resolver] & Seq[ModuleID] <: Seq[Serializable]]] = Seq(
    resolvers ++= PekkoProjectSettings.Resolvers.pekkoResolvers,
    libraryDependencies ++= PekkoProjectSettings.Libraries.pekkoPersistenceLibraries
  )
  lazy val pekkoProjectionProject: Seq[Def.Setting[? >: Seq[Resolver] & Seq[ModuleID] <: Seq[Serializable]]] = Seq(
    resolvers ++= PekkoProjectSettings.Resolvers.pekkoResolvers,
    libraryDependencies ++= PekkoProjectSettings.Libraries.pekkoProjectionLibraries
  )
  lazy val pekkoKafkaProject: Seq[Def.Setting[? >: Seq[Resolver] & Seq[ModuleID] <: Seq[Serializable]]] = Seq(
    resolvers ++= PekkoProjectSettings.Resolvers.pekkoResolvers ++ PekkoProjectSettings.Resolvers.pekkoKafkaResolvers,
    libraryDependencies ++= PekkoProjectSettings.Libraries.pekkoKafkaLibraries
  )

  lazy val jsonProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "org.json4s" %% "json4s-native" % json4sVersion,
        "org.json4s" %% "json4s-jackson" % json4sVersion
      )
  )

  lazy val javaProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "org.apache.commons" % "commons-digester3" % apacheCommonsDigester,
        "commons-io" % "commons-io" % apacheCommonsIOVersion,
        "org.slf4j" % "slf4j-api" % slf4jVersion,
        "org.slf4j" % "slf4j-simple" % slf4jVersion,
        "org.jetbrains" % "annotations" % jetbrainsAnnotationsVersion,
        "org.apache.commons" % "commons-lang3" % apacheCommonsLang3Version,
        "joda-time" % "joda-time" % jodaTimeVersion,
        "com.google.guava" % "guava" % googleGuavaVersion,
        "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
      )
  )
  lazy val libProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "com.github.nscala-time" %% "nscala-time" % "2.32.0",
        "com.typesafe" % "config" % "1.4.2",
        "org.scalatest" %% "scalatest" % scalaTestVersion % Test
      )
  )
  lazy val unmanagedProject: Seq[Def.Setting[?]] = Seq(
    Test / unmanagedResourceDirectories += baseDirectory.value / "src" / "main" / "resources",
    Compile / unmanagedResourceDirectories += baseDirectory.value / "src" / "main" / "resources"
  )
}
