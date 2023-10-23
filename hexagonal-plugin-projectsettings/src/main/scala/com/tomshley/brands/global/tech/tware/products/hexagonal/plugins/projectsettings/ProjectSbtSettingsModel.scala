package com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.projectsettings

import com.tomshley.brands.global.tech.tware.products.hexagonal.plugins.common.sbt.SbtProjectHelper
import sbt.File

case class ProjectSbtSettingsModel(projectName: String, projectBaseOption: Option[File] = None) extends SbtProjectHelper
