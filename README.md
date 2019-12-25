# AutoChrome
Web bot written in Java based on JCEF, based on CEF, a wrapper for Chromium.

Currently running Chromium 78.

## Requirements
Java JDK 8+

libcef.dll from [here](https://github.com/0xdeki/AutoChrome/releases/download/libcef78/libcef.dll)

## Installation
1. Clone the repository to your IDE of choice (IntelliJ recommended!)
2. Place libcef.dll in /lib/win64 
3. Build!

## Packaging/running
1. Package the artifact with the premade configuration
2. Copy /lib/win64 to the path of the jar file
3. Run with `java -jar -Djava.library.path=./win64 AutoChrome.jar`
