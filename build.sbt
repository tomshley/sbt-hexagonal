import sbt.{addDependencyTreePlugin, addSbtPlugin, file}

lazy val commonProject = hexagonalProject("common", Dependencies.commonProject, Scala3.settings)
lazy val commonSbtProject = hexagonalSbtProject("common-sbt", Dependencies.commonProject, Scala3.settings)
lazy val acceptancePluginProject =
  hexagonalScriptedPluginProject("acceptance", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val cicdPluginProject =
  hexagonalScriptedPluginProject("cicd", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val layeredPluginProject =
  hexagonalScriptedPluginProject("layered", Dependencies.layeredPluginProject, Scala3.settings)
lazy val libraryPluginProject =
  hexagonalScriptedPluginProject("library", Dependencies.libraryPluginProject, Scala3.settings)
lazy val protobufsPluginProject =
  hexagonalScriptedPluginProject("protobufs", Dependencies.protobufsPluginProject, Scala3.settings)
lazy val settingsPluginProject =
  hexagonalScriptedPluginProject(
    "projectsettings",
    Dependencies.settingsPluginProject,
    Scala3.settings,
    Seq(
      addSbtPlugin("org.apache.pekko" % "pekko-grpc-sbt-plugin" % "1.1.0-M1"),
      addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2"),
      addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.9.13"),
      addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.1.1"),
      addSbtPlugin("org.playframework.twirl" % "sbt-twirl" % "2.0.1"),
      addDependencyTreePlugin
    )
  )
lazy val structuredPluginProject = hexagonalScriptedPluginProject(
  "projectstructure",
  Dependencies.structuredPluginProject,
  Scala3.settings
)
lazy val templatePluginProject =
  hexagonalScriptedPluginProject("projecttemplate", Dependencies.templatePluginProject, Scala3.settings)

lazy val hexagonalPlugins = (project in file("."))
  .enablePlugins(AssemblyPlugin)
  .aggregate(
    commonProject,
    commonSbtProject,
    acceptancePluginProject,
    cicdPluginProject,
    layeredPluginProject,
    libraryPluginProject,
    protobufsPluginProject,
    settingsPluginProject,
    structuredPluginProject,
    templatePluginProject
  )
  .settings(
    publish / skip := true
  )
def hexagonalProject(projectName: String, additionalSettings: sbt.Def.SettingsDefinition*): Project = {
  Project(id = projectName, base = file(projectName))
    .enablePlugins(AssemblyPlugin)
    .settings(
      organization := "com.tomshley.brands.global.tech.tware.products.hexagonal.plugins",
      name := projectName,
      licenses := {
        val tagOrBranch =
          if (version.value.endsWith("SNAPSHOT")) "main"
          else "v" + version.value
        Seq(
          ("APACHE-2.0",
           url("https://raw.githubusercontent.com/tomshley/hexagonal-plugins-sbt/" + tagOrBranch + "/LICENSE"))
        )
      },
      scalacOptions += "-Wconf:cat=deprecation&msg=.*JavaConverters.*:s"
    )
    .settings(additionalSettings *)
}
def hexagonalSbtProject(projectName: String, additionalSettings: sbt.Def.SettingsDefinition*): Project = {
  hexagonalProject(projectName, additionalSettings *)
    .settings(
      sbtPlugin := true
    )
    .dependsOn(commonProject)
}
def hexagonalScriptedPluginProject(pluginProjectName: String,
                                   additionalSettings: sbt.Def.SettingsDefinition*): Project = {
  val hexagonalProjectName = "hexagonal-plugin-" + pluginProjectName
  hexagonalSbtProject(hexagonalProjectName, additionalSettings *)
    .settings(
      scriptedLaunchOpts += ("-Dplugin.version=" + version.value),
//      scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
//        a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
//      ),
      scriptedBufferLog := false
    )
    .dependsOn(commonProject, commonSbtProject)
}
