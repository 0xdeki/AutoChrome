# AutoChrome
### This repo currently only hosts files for win64, if you are using any other platform (linux, mac, win32) or want to use a different version of Chromium download the correct files from [here](https://github.com/smac89/java-cef-build/releases) or compile them yourself from [here](https://bitbucket.org/chromiumembedded/java-cef/src/master/)
Web bot written in Java based on JCEF, based on CEF, a wrapper for Chromium.

Currently running Chromium 78.

## Requirements
[IntelliJ IDEA](https://www.jetbrains.com/idea/) (preferrably)

Java JDK 8+

libcef.dll from [here](https://github.com/0xdeki/AutoChrome/releases/download/libcef78/libcef.dll)

## Command line arguments
*-script scriptName*: Start a script immediately when the client loads
*-proxy ip:port*: Load the client with a proxy
*-proxyUsername username*: Set a username to authenticate the proxy
*-proxyPassword password*: Set a password to authenticate the proxy

## Installation
1. Clone the repository to your IDE of choice (IntelliJ recommended!)
2. Place libcef.dll in /lib/win64 
3. Build!

## Making scripts
To make scripts simply add classes to the scripts module and package the artifact with the premade configuration (scripts.jar). If you want to do so in a different project or IDE, import client.jar as a library. Put the packaged scripts jar in the same path as the client jar.

## Packaging/running
1. Package the artifact with the premade configuration (client.jar)
2. Copy /lib/win64 to the path of the jar file
3. Run with `java -jar -Djava.library.path=./win64 client.jar`
