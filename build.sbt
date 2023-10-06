import sbt.file

lazy val commonProject = hexagonalProject("common", Dependencies.commonProject, Scala3.settings)
lazy val acceptancePluginProject = hexagonalPluginProject("acceptance", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val cicdPluginProject = hexagonalPluginProject("cicd", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val layeredPluginProject = hexagonalPluginProject("layered", Dependencies.layeredPluginProject, Scala3.settings)
lazy val libraryPluginProject = hexagonalPluginProject("library", Dependencies.libraryPluginProject, Scala3.settings)
lazy val protobufsPluginProject = hexagonalPluginProject("protobufs", Dependencies.protobufsPluginProject, Scala3.settings)
lazy val structuredPluginProject = hexagonalPluginProject("projectstructure", Dependencies.structuredPluginProject, Scala3.settings)

lazy val hexagonalPlugins = (project in file("."))
  .aggregate(
    commonProject,
    acceptancePluginProject,
    cicdPluginProject,
    layeredPluginProject,
    libraryPluginProject,
    protobufsPluginProject,
    structuredPluginProject
  )
def hexagonalProject(projectName: String, additionalSettings: sbt.Def.SettingsDefinition*): Project = {
  Project(id = projectName, base = file(projectName))
    .settings(
      organization := "com.tomshley.brands.global.tech.tware.products.hexagonal.plugins",
      name := projectName,
      licenses := {
        val tagOrBranch =
          if (version.value.endsWith("SNAPSHOT")) "main"
          else "v" + version.value
        Seq(("APACHE-2.0", url("https://raw.githubusercontent.com/tomshley/hexagonal-plugins-sbt/" + tagOrBranch + "/LICENSE")))
      },
      scalacOptions += "-Wconf:cat=deprecation&msg=.*JavaConverters.*:s"
    )
    .settings(additionalSettings*)
}
def hexagonalPluginProject(pluginProjectName: String, additionalSettings: sbt.Def.SettingsDefinition*): Project = {
  val hexagonalProjectName = "hexagonal-plugin-" + pluginProjectName
  hexagonalProject(hexagonalProjectName, additionalSettings*)
    .settings(
      sbtPlugin := true,
      scriptedLaunchOpts += ("-Dplugin.version=" + version.value),
//      scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
//        a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
//      ),
      scriptedBufferLog := false
    )
    .dependsOn(commonProject)
}
