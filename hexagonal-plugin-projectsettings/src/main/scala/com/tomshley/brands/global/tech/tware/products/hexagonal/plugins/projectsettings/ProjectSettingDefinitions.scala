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
  lazy val json4sVersion = "4.1.0-M3"
  val Scala213: String = "2.13.12"
  val Scala212: String = "2.12.18"
  val Scala3: String = "3.3.1"
  val Scala2Versions: Seq[String] = Seq(Scala213, Scala212)
  val ScalaVersions: Seq[String] = Scala2Versions :+ Scala3
}
protected[projectsettings] object ProjectSettingsDefs extends ProjectSettingsVersions {
  lazy val scala3CrossVersions: Seq[Def.Setting[Seq[String]]] = Seq(crossScalaVersions := ScalaVersions)

  lazy val akkaProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
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
