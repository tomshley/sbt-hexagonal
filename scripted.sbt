enablePlugins(SbtPlugin)

scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++
    Seq("-Xmx1024M", "-Dplugin.version=" + version.value, "-Dsbt.io.implicit.relative.glob.conversion=allow")
}

scriptedBufferLog := false
