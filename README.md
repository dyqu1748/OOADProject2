# OOAD-Project2

### Members:
Anna Nuggehalli, Dylan Quach, Casey Tran

## Assumptions Made:
- User is running the program on IntelliJ IDEA or in a terminal.
- JDK is version 8 or newer.
- Every hour passed in the zoo is .1 seconds in real life.
- Multiple tasks can be performed in a single hour (ZooFoodServer can serve food and Zookeeper can feed animals immediately after).

## Issues During Dev:
- Writing all of the actions performed by the animals/employees to a file. Was resolved with the discovery of System.setOut(), but required modification of old code.
- Figuring out how to keep track of time. Initally explored Java's Clock class, but Timer proved easier to work with.
- Figuring out how to properly implement delegation. Had to do additional research to understand.

## Instructions to Run Application:
#### Instructions for Running Application in a Terminal: ####
- Open the terminal for your respective device (Command Prompt on Windows, Terminal on mac or Linux).
- Change the current directory to the location of ZooProgramV2.jar (i.e. cd pathToZooProgramV2/ZooProgramV2/out/artifacts/ZooProgramV2_jar).
- Type in and enter java -jar ZooProgramV2.jar into the terminal to run the program.
- The program will execute and display a message saying it is running. After it has completed running, a "dayatthezoo.out" file will be created in the artifacts folder above the executable.
#### Instructions for Running Application in IntelliJ IDEA: ####
- Open the project in IntelliJ by right-clicking the OOADProject2 folder and selecting "Open Folder as IntelliJ IDEA Community Edition Project".
- Locate ZooProgramV2/out/artifacts/ZooProgramV2_jar/ZooProgramV2.jar in the "Project" tab.
- Right-click on the ZooProgramV2.jar file and select "Run 'ZooProgramV2.jar'".
- The program will begin running and will print out a message in the console to notify you when it has completed. After completion, a "dayatthezoo.out" file will be created in the OOADProject2 folder.
