// This is a stripped down version of the build.sbt found in the main framework.
// Its almost like the one from the framework but without some sbt tasks like 'deploy' that aren't needed for plugin developers.

// Main project config
name := "ChatOverflow"
version := "3.0.0"

// Main class and sub projects
mainClass := Some("org.codeoverflow.chatoverflow.Launcher")

// Settings
inThisBuild(List(scalaVersion := "2.12.5"))

fork in run := true // Start ChatOverflow in it's own java process when starting it with 'sbt run'