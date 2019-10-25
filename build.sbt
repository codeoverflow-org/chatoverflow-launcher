name := "chatoverflow-launcher"

lazy val launcherBootstrapProject = project in file("bootstrap")
lazy val launcherUpdaterProject = project in file("updater")

lazy val launcherProject = (project in file("."))
  .aggregate(launcherBootstrapProject, launcherUpdaterProject)