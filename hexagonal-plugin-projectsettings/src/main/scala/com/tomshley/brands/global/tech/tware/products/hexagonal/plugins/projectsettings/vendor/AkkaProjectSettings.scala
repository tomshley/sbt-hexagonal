package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings.vendor

import sbt.*
import sbt.Keys.crossScalaVersions

object AkkaProjectSettings {
  val Scala213 = "2.13.14"
  val Scala3 = "3.4.2"
  val Scala212 = "2.12.18"
  val Scala2Versions: Seq[String] = Seq(Scala213, Scala212)
  val ScalaVersions: Seq[String] = AkkaProjectSettings.Scala2Versions :+ AkkaProjectSettings.Scala3

  object Scala3Settings {
    val settings: Seq[Def.Setting[Seq[String]]] = Seq(crossScalaVersions := AkkaProjectSettings.ScalaVersions)
  }

  object Versions {

    val AkkaVersion = "2.9.0"
    val AkkaHttpVersion = "10.6.0"
    val AkkaManagementVersion = "1.5.0"
    val AkkaPersistenceJdbcVersion = "5.3.0"
    val AlpakkaKafkaVersion = "5.0.0"
    val AkkaProjectionVersion = "1.5.0"
    val AkkaDiagnosticsVersion = "2.1.0"
    val JetbrainsAnnotationsVersion = "24.0.1"
    val ApacheCommonsLang3Version = "3.12.0"
    val JodaTimeVersion = "2.12.5"
    val GoogleGuavaVersion = "23.0"
    val JacksonVersion = "2.15.2"
    val Slf4jVersion = "2.0.5"
    val ApacheCommonsIOVersion = "20030203.000550"
    val ApacheCommonsDigester = "3.2"
    val AkkaKafkaConnector = "1.0.0+66-68d085b8-SNAPSHOT"
    val IoGrpc = "1.59.0"
    val ScalaPBVersion = "0.11.13"
    val ScalaTestVersion = "3.2.15"
    val Json4sVersion = "4.1.0-M3"
    val KafkaStreamsVersion = "5.2.1"
    val KafkaAvroVersion = "6.2.0"
    val Avro4sVersion = "4.1.2"
    val LogbackVersion = "1.5.6"
    val TestContainers = "1.20.0"
    val ScalaTest = "3.2.19"
  }

  object Resolvers {
    val akkaResolvers: Seq[MavenRepository] = Seq(
      "Akka library repository".at("https://repo.akka.io/maven")
    )
    val akkaKafkaResolvers: Seq[MavenRepository] = Seq(
      "Confluent Maven Repository" at "https://packages.confluent.io/maven/"
    )
  }

  object Libraries {
    val basicLoggingProjectSettings: Seq[ModuleID] = Seq(
      "ch.qos.logback" % "logback-classic" % AkkaProjectSettings.Versions.LogbackVersion
    )
    val basicSerializationProjectSettings: Seq[ModuleID] = Seq(
      //      "org.json4s" %% "json4s-native" % AkkaProjectSettings.Versions.Json4sVersion,
      "org.json4s" %% "json4s-jackson" % AkkaProjectSettings.Versions.Json4sVersion
    )
    val basicTestProjectSettings: Seq[ModuleID] = Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    )
    val akkaActorProjectSettings: Seq[ModuleID] = Seq(
      "com.typesafe.akka" %% "akka-stream" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.lightbend.akka.management" %% "akka-management" % AkkaProjectSettings.Versions.AkkaManagementVersion,
      "com.lightbend.akka.management" %% "akka-management-cluster-http" % AkkaProjectSettings.Versions.AkkaManagementVersion,
      "com.lightbend.akka.discovery" %% "akka-discovery-kubernetes-api" % AkkaProjectSettings.Versions.AkkaManagementVersion,
      "com.typesafe.akka" %% "akka-discovery" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.lightbend.akka.management" %% "akka-management-cluster-bootstrap" % AkkaProjectSettings.Versions.AkkaManagementVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaProjectSettings.Versions.AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaProjectSettings.Versions.AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.typesafe.akka" %% "akka-cluster-sharding-typed" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.lightbend.akka" %% "akka-diagnostics" % AkkaProjectSettings.Versions.AkkaDiagnosticsVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaProjectSettings.Versions.AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaProjectSettings.Versions.AkkaVersion % Test
    )

    val akkaPersistenceProjectSettings: Seq[ModuleID] = Seq(
      "com.typesafe.akka" %% "akka-persistence-r2dbc" % AkkaProjectSettings.Versions.AkkaManagementVersion,
      "com.typesafe.akka" %% "akka-persistence-typed" % AkkaProjectSettings.Versions.AkkaVersion,
      "com.typesafe.akka" %% "akka-persistence-testkit" % AkkaProjectSettings.Versions.AkkaVersion % Test
    )

    val akkaProjectionProjectSettings: Seq[ModuleID] = Seq(
      "com.lightbend.akka" %% "akka-projection-core" % AkkaProjectSettings.Versions.AkkaProjectionVersion,
      "com.lightbend.akka" %% "akka-projection-r2dbc" % AkkaProjectSettings.Versions.AkkaProjectionVersion,
      "com.lightbend.akka" %% "akka-projection-eventsourced" % AkkaProjectSettings.Versions.AkkaProjectionVersion,
      "com.typesafe.akka" %% "akka-persistence-query" % AkkaProjectSettings.Versions.AkkaVersion
    )

    val akkaKafkaProjectSettings: Seq[ModuleID] = Seq(
      "com.sksamuel.avro4s" %% "avro4s-core" % AkkaProjectSettings.Versions.Avro4sVersion,
      "io.confluent" % "kafka-streams-avro-serde" % "5.2.1",
      "io.confluent" % "kafka-avro-serializer" % AkkaProjectSettings.Versions.KafkaAvroVersion,
      "com.typesafe.akka" %% "akka-stream-kafka" % AkkaProjectSettings.Versions.AlpakkaKafkaVersion,
      "org.testcontainers" % "kafka" % "1.20.0" % Test
    )
  }
}
