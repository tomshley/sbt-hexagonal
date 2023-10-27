import sbt.{Def, *}
import Keys.*

object Dependencies {
  val Scala213 = "2.13.12"
  val Scala212 = "2.12.18"
  val Scala3 = "3.3.1"
  val Scala2Versions: Seq[String] = Seq(Scala213, Scala212)
  val ScalaVersions: Seq[String] = Dependencies.Scala2Versions :+ Dependencies.Scala3

  val commonProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val acceptancePluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val cicdPluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val layeredPluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val libraryPluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val protobufsPluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val settingsPluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val structuredPluginProject: Seq[Def.Setting[?]] = Seq(
    libraryDependencies ++= Seq(

    )
  )
  val templatePluginProject: Seq[Def.Setting[?]] = Seq(
    Compile / unmanagedResourceDirectories := {
      Seq(baseDirectory.value / "src/main/resources")
    },
//    Compile / target := {
//      baseDirectory.value / "sbt-stuff"
//    },
//    Compile / classDirectory := {
//      baseDirectory.value / "target"
//    },
    libraryDependencies ++= Seq(

    )
  )
}
