import sbt.file

lazy val acceptancePluginProject = hexagonalPluginsProject("acceptance", Dependencies.acceptancePluginProject, Scala3.settings)
lazy val layeredPluginProject = hexagonalPluginsProject("layered", Dependencies.layeredPluginProject, Scala3.settings)
lazy val libraryPluginProject = hexagonalPluginsProject("library", Dependencies.libraryPluginProject, Scala3.settings)
lazy val protobufsPluginProject = hexagonalPluginsProject("protobufs", Dependencies.protobufsPluginProject, Scala3.settings)
lazy val structuredPluginProject = hexagonalPluginsProject("projectstructure", Dependencies.structuredPluginProject, Scala3.settings)

lazy val hexagonalPlugins = (project in file("."))
  .aggregate(
    acceptancePluginProject,
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
      Test / parallelExecution := false
    )
    .settings(additionalSettings *)
}
