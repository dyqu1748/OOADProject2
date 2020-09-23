# OOAD-Project2

### Members:
Anna Nuggehalli, Dylan Quach, Casey Tran

## Assumptions Made:
- User is running the program on IntelliJ IDEA.
- JDK is version 8 or newer.
- Every hour passed in the zoo is .5 seconds in real life.
- Multiple tasks can be performed in a single hour (ZooFoodServer can serve food and Zookeeper can feed animals immediately after).

## Issues During Dev:
- Writing all of the actions performed by the animals/employees to a file. Was resolved with the discovery of System.setOut(), but required modification of old code.
- Figuring out how to keep track of time. Initally explored Java's Clock class, but Timer proved easier to work with.
- Figuring out how to properly implement delegation. Had to do additional research to understand.

## Instructions to Run Application:
!!NEED TO MODIFY INSTRUCTIONS!!
- Open the project in IntelliJ by right-clicking the OOADProject1 folder and selecting "Open Folder as IntelliJ IDEA Community Edition Project".
- Locate FinalZooProgram/out/artifacts/FinalZooProgram_jar/FinalZooProgram.jar in the "Project" tab.
- Right-click on the FinalZooProgram.jar file and select "Run 'FinalZooProgram.jar'".
- The program will execute, and a "dayatthezoo.out" file will be created in the OOADProject1 folder.
