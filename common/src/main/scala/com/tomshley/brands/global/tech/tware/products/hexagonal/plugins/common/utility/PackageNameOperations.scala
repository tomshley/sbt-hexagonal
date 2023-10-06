package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.utility

object PackageNameOperations {
  def appNameToPackageName(appName: String): String = {
    appName.replace("_", ".")
      .replace("-", ".")
  }

  def appNameToArtifactName(appName: String): String = {
    appName.replace(".", "_")
      .replace("\\s", "-")
  }

  def appNameToFileName(appName: String): String = {
    appName.split('-').map(_.capitalize).mkString
      .split('_').map(_.capitalize).mkString
      .split('.').map(_.capitalize).mkString
  }

  def packageNameToFilepath(packageName: String): String = {
    packageName.replace(".", "/")
  }
}
