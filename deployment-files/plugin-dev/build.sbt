// This is a stripped down version of the build.sbt found in the main framework.
// Its almost like the one from the framework but without some sbt tasks like 'deploy' that aren't needed for plugin developers.

// Main project config
name := "ChatOverflow"
version := "4.0.0"

// Main class and sub projects
mainClass := Some("org.codeoverflow.chatoverflow.Launcher")

// Settings
inThisBuild(List(scalaVersion := "2.12.5"))

import org.codeoverflow.chatoverflow.build.BuildUtils

Global / javacOptions ++= BuildUtils.getJava8CrossOptions
Global / scalacOptions ++= Seq("-deprecation", "-feature")