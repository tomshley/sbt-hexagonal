package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings.vendor

import sbt.*

object PekkoProjectSettings {
  object Versions {
    val PekkoVersion = "1.1.0-M1"
    val PekkoManagementVersion = "1.0.0"
    val PekkoKafkaConnector = "1.0.0+66-68d085b8-SNAPSHOT"
    val PekkoHttpVersion = "1.1.0-M1"
    val KafkaStreamsVersion = "5.2.1"
    val KafkaAvroVersion = "6.2.0"
    val Avro4sVersion = "5.0.13"
    val LogbackVersion = "1.5.6"
    val Json4sVersion = "4.0.7"
    val TestContainers = "1.20.0"
    val ScalaTest = "3.2.19"
  }
  
  object Resolvers {
    val pekkoResolvers: Seq[MavenRepository] = Seq(
      Resolver.ApacheMavenSnapshotsRepo
    )
    val pekkoKafkaResolvers: Seq[MavenRepository] = Seq(
      "Confluent Maven Repository" at "https://packages.confluent.io/maven/"
    )
  }

  object Libraries {
    val basicLoggingLibraries: Seq[ModuleID] = Seq(
      "ch.qos.logback" % "logback-classic" % PekkoProjectSettings.Versions.LogbackVersion
    )
    val basicSerializationLibraries: Seq[ModuleID] = Seq(
      "org.json4s" %% "json4s-jackson" % PekkoProjectSettings.Versions.Json4sVersion
    )
    val basicTestLibraries: Seq[ModuleID] = Seq(
      "org.scalatest" %% "scalatest" % PekkoProjectSettings.Versions.ScalaTest % Test
    )
    val pekkoActorLibraries: Seq[ModuleID] = Seq(
      "org.apache.pekko" %% "pekko-stream" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-actor-typed" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-management" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-management-cluster-http" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-discovery-kubernetes-api" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-discovery" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-management-cluster-bootstrap" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-http" % PekkoProjectSettings.Versions.PekkoHttpVersion,
      "org.apache.pekko" %% "pekko-http-spray-json" % PekkoProjectSettings.Versions.PekkoHttpVersion,
      "org.apache.pekko" %% "pekko-serialization-jackson" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-slf4j" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-cluster-sharding-typed" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-actor-testkit-typed" % PekkoProjectSettings.Versions.PekkoVersion % Test,
      "org.apache.pekko" %% "pekko-stream-testkit" % PekkoProjectSettings.Versions.PekkoVersion % Test
    )

    val pekkoPersistenceLibraries: Seq[ModuleID] = Seq(
      "org.apache.pekko" %% "pekko-persistence-r2dbc" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-persistence-typed" % PekkoProjectSettings.Versions.PekkoVersion,
      "org.apache.pekko" %% "pekko-persistence-testkit" % PekkoProjectSettings.Versions.PekkoVersion % Test
    )

    val pekkoProjectionLibraries: Seq[ModuleID] = Seq(
      "org.apache.pekko" %% "pekko-projection-core" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-projection-r2dbc" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-projection-eventsourced" % PekkoProjectSettings.Versions.PekkoManagementVersion,
      "org.apache.pekko" %% "pekko-persistence-query" % PekkoProjectSettings.Versions.PekkoVersion
    )

    val pekkoKafkaLibraries: Seq[ModuleID] = Seq(
      "com.sksamuel.avro4s" %% "avro4s-core" % PekkoProjectSettings.Versions.Avro4sVersion,
      "io.confluent" % "kafka-streams-avro-serde" % PekkoProjectSettings.Versions.KafkaStreamsVersion,
      "io.confluent" % "kafka-avro-serializer" % PekkoProjectSettings.Versions.KafkaAvroVersion,
      "org.apache.pekko" %% "pekko-connectors-kafka" % PekkoProjectSettings.Versions.PekkoKafkaConnector,
      "org.apache.pekko" %% "pekko-connectors-kafka-cluster-sharding" % PekkoProjectSettings.Versions.PekkoKafkaConnector,

      "org.testcontainers" % "kafka" % PekkoProjectSettings.Versions.TestContainers % Test
    )
  }
}
