// This is a stripped down version of the keys.sbt found in the main framework.
// Its almost like the one from the framework but without some tasks like 'deploy' or 'gui' that aren't used by plugin developers.

// ONLY these two assignments is unique to the plugin dev environment:
// This task has to be overwritten, because sbt will only look in source files for main classes, but the class is in a jar.
Compile / discoveredMainClasses := Seq("org.codeoverflow.chatoverflow.Launcher")
unmanagedBase := file("bin")

// Settings
pluginBuildFileName := "plugins.sbt"
pluginFolderNames := List("plugins-public", "plugins-private")
pluginTargetFolderNames := List("plugins", s"target/scala-${scalaVersion.value.split('.').dropRight(1).mkString(".")}/plugins")
apiProjectPath := "api"



// Plugin framework setting keys
lazy val pluginBuildFileName = settingKey[String]("The filename of the plugin build file. Remember to gitignore it!")
lazy val pluginFolderNames = settingKey[List[String]]("The folder names of all plugin source directories. Remember to gitignore it!")
lazy val pluginTargetFolderNames = settingKey[List[String]]("The folder names of compiled and packaged plugins. Remember to gitignore these!")
lazy val apiProjectPath = settingKey[String]("The path to the api sub project. Remember to gitignore it!")

// Plugin framework task keys
lazy val create = TaskKey[Unit]("create", "Creates a new plugin. Interactive command using the console.")
lazy val fetch = TaskKey[Unit]("fetch", "Searches for plugins in plugin directories, builds the plugin build file.")
lazy val copy = TaskKey[Unit]("copy", "Copies all packaged plugin jars to the target plugin folder.")



// Tasks

import org.codeoverflow.chatoverflow.build.BuildUtils.scalaMajorVersion
import org.codeoverflow.chatoverflow.build.plugins.{PluginCreateWizard, PluginUtility}

create := new PluginCreateWizard(streams.value.log).createPluginTask(pluginFolderNames.value, PluginCreateWizard.getApiVersion.value)
fetch := new PluginUtility(streams.value.log).fetchPluginsTask(pluginFolderNames.value, pluginBuildFileName.value,
  pluginTargetFolderNames.value, apiProjectPath.value)
copy := new PluginUtility(streams.value.log).copyPluginsTask(pluginFolderNames.value, pluginTargetFolderNames.value, scalaMajorVersion.value)
