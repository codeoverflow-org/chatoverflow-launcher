name := "chatoverflow-launcher-updater"
version := "3.0.0" // Currently not used anywhere
assemblyJarName in assembly := "ChatOverflow.jar"

// JSON Lib to read the json provided by GitHub Releases
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.7"

// jansi is used to check if the application is running with a tty in a interactive session
libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.18"

// Progressbar is used to display progress of update zip download
libraryDependencies += "me.tongfei" % "progressbar" % "0.8.0"

// Command Line Parsing
libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.1"

packageBin / includePom := false
