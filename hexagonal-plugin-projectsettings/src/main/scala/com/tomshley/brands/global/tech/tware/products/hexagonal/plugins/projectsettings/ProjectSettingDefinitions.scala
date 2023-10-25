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

package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings

import sbt.Keys.{crossScalaVersions, libraryDependencies}
import sbt.{Def, *}

sealed trait ProjectSettingsVersions {
  lazy val jetbrainsAnnotationsVersion = "24.0.1"
  lazy val apacheCommonsLang3Version = "3.12.0"
  lazy val jodaTimeVersion = "2.12.5"
  lazy val googleGuavaVersion = "23.0"
  lazy val jacksonVersion = "2.15.2"
  lazy val slf4jVersion = "2.0.5"
  lazy val apacheCommonsIOVersion = "20030203.000550"
  lazy val apacheCommonsDigester = "3.2"
  lazy val akkaVersion = "2.9.0-M2"
  lazy val akkaHttpVersion = "10.6.0-M1"
  lazy val ioGrpc = "1.59.0"
  lazy val scalaPBVersion = "0.11.13"
  lazy val scalaTestVersion = "3.2.15"
  lazy val json4sVersion = "4.1.0-M3"
  val Scala213: String = "2.13.12"
  val Scala212: String = "2.12.18"
  val Scala3: String = "3.3.1"
  val Scala2Versions: Seq[String] = Seq(Scala213, Scala212)
  val ScalaVersions: Seq[String] = Scala2Versions :+ Scala3
}
protected[projectsettings] object ProjectSettingsDefs extends ProjectSettingsVersions {
  lazy val scala3CrossVersions: Seq[Def.Setting[Seq[String]]] = Seq(crossScalaVersions := ScalaVersions)

  lazy val hexagonalProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "com.tomshley.brands.global.tech.tware.products.hexagonal.lib" %% "hexagonal-lib" % "0.1.0-SNAPSHOT"
      )
  )
  lazy val akkaProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
      )
  )
  lazy val akkaGRPCProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "io.grpc" % "grpc-netty" % ioGrpc,
//       Note: the compiler plugin needs to be added to plugins.sbt "com.thesamet.scalapb" %% "compilerplugin" % scalaPBVersion,
        "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalaPBVersion,
        "com.typesafe.akka" %% "akka-protobuf-v3" % akkaVersion
      )
  )
  lazy val akkaHTTPProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
        "com.typesafe.akka" %% "akka-stream" % akkaVersion,
        "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
        "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
        "org.scalatest" %% "scalatest" % scalaTestVersion % Test
      )
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
        "com.typesafe" % "config" % "1.4.2"
      )
  )
}
