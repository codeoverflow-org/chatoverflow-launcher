# chatoverflow-launcher
This repository contains the launcher that is used for all end-user deployments of [chatoverflow](https://github.com/codeoverflow-org/chatoverflow). 

The launcher resided in the main repository till prealpha 2.1 and has been moved here with prealpha 3.

## Modules

It currently consists of two modules:

### Bootstrap

The bootstrap module is responsible for downloading of the dependencies and then actually starting Chat Overflow and for performing a simple integrity check.

To do this it reads the `dependencies.pom` inside of the framework jar. This file is produced by the [sbt PomInclusionPlugin](https://github.com/codeoverflow-org/chatoverflow/blob/develop/build/src/main/scala/org/codeoverflow/chatoverflow/build/PomInclusionPlugin.scala). The file is parsed and dependencies get resolved and downloaded using [coursier](https://get-coursier.io). After that it just starts Chat Overflow with all jars and the dependencies on the classpath.

### Updater

The updater checks for updates and installs them if the user accepts it. It is contained in the `ChatOverflow.jar` inside the deployment.

To do this it firsts figures out the current version that is stored in the `version.txt` file in the jar of the bootstrapper (located at `bin/ChatOverflow-Launcher.jar`). It then searches for this release on GitHub and, if found, checks if a newer release than the current used on is available. If yes it asks the user if the installation should be upgraded. If no tty is attached, because the launcher was started from systemd or similar, it will just print a note and start Chat Overflow normally. To upgrade the installation the updater will download the newer deployment zip file to a temporary file and extract it to the directory of the current installation, overwriting old jars and files. After the update check it starts the bootstrap module of the launcher.

Note that the updater can update itself only on on *nix systems because on Windows a file lock is being held on the currently executed updater jar. If the updater has changed and your are running windows/it can't self update it will prompt you to download the zip yourself and overwrite the old updater with the new one, but the rest will be updated and also may still work completely fine depending on the changes that were being made to the updater, we just can't guarantee that it will still work.