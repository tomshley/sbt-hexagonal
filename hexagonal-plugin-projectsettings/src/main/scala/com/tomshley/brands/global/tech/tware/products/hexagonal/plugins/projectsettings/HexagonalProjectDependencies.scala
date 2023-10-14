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

import sbt.{Def, *}
import Keys.*

object HexagonalProjectDependencies {
  val Scala213 = "2.13.12"
  val Scala212 = "2.12.18"
  val Scala3 = "3.3.1"
  val Scala2Versions: Seq[String] = Seq(Scala213, Scala212)
  val ScalaVersions: Seq[String] = HexagonalProjectDependencies.Scala2Versions :+ HexagonalProjectDependencies.Scala3

  lazy val springBootVersion = "3.1.4"
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

  val akkaProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
    )
  )
  val jsonProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % json4sVersion,
      "org.json4s" %% "json4s-jackson" % json4sVersion
    )
  )
  val javaProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
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
  val libProject: Seq[Def.Setting[Seq[ModuleID]]] = Seq(
    libraryDependencies ++= Seq(
      "com.github.nscala-time" %% "nscala-time" % "2.32.0",
      "com.typesafe" % "config" % "1.4.2"
    )
  )
}
