import sbt.file

lazy val acceptancePluginProject = hexagonalPluginsProject("acceptance", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val cicdPluginProject = hexagonalPluginsProject("cicd", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val layeredPluginProject = hexagonalPluginsProject("layered", Dependencies.layeredPluginProject, Scala3.settings)
lazy val libraryPluginProject = hexagonalPluginsProject("library", Dependencies.libraryPluginProject, Scala3.settings)
lazy val protobufsPluginProject = hexagonalPluginsProject("protobufs", Dependencies.protobufsPluginProject, Scala3.settings)
lazy val structuredPluginProject = hexagonalPluginsProject("projectstructure", Dependencies.structuredPluginProject, Scala3.settings)

lazy val hexagonalPlugins = (project in file("."))
  .aggregate(
    acceptancePluginProject,
    cicdPluginProject,
    layeredPluginProject,
    libraryPluginProject,
    protobufsPluginProject,
    structuredPluginProject
  )
def hexagonalPluginsProject(projectShortName: String, additionalSettings: sbt.Def.SettingsDefinition*): Project = {
  val hexagonalProjectName = "hexagonal-plugin-" + projectShortName
  Project(id = hexagonalProjectName, base = file(hexagonalProjectName))
    .settings(
      organization := "com.tomshley.brands.global.tech.tware.products.hexagonal.plugins",
      name := hexagonalProjectName,
      licenses := {
        val tagOrBranch =
          if (version.value.endsWith("SNAPSHOT")) "main"
          else "v" + version.value
        Seq(("APACHE-2.0", url("https://raw.githubusercontent.com/tomshley/hexagonal-plugins-sbt/" + tagOrBranch + "/LICENSE")))
      },
      scalacOptions += "-Wconf:cat=deprecation&msg=.*JavaConverters.*:s",
      sbtPlugin := true,
      scriptedLaunchOpts += ("-Dplugin.version=" + version.value),
      scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
        a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
      ),
      scriptedBufferLog := false
    )
    .settings(additionalSettings *)
}
