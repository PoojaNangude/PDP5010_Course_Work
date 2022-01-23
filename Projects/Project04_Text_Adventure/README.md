# ABOUT/ OVERVIEW
The problem statement expects to previously built model to add monsters and arrows in the dungeon and to give the ability to the player to try and slay the monsters.
The player will be equipped with a certain number of arrows initially and can search for more monsters, and try to slay them while navigating through the dugeon.

# LIST OF FEATURES
1. User can move from location to another by specifying the direction to be travelled in (East, West, North, South).
2. User can get a sense of pungent smell if there are monsters in the nearby locations.
3. User can also shoot arrows in all 4 directions if there is a path available in the direction.
4. User can pick up treasure and arrows if he finds them while navigating the dungeon.

# HOW TO RUN THE PROGRAM
1. Download the .zip and unzip it.
2. Open IntelliJ IDEA and on the task bar click on File --> New --> Project from Existing Sources
3. Locate the unzipped folder and click on open.
4. Keep going forward with the default settings by clicking on Next and in the end click Finish.
5. Go to the terminal and navigate to /CS5010_PDP_Project4_AdventureGame/res/JAR
6. For Deterministic dungeon:
   -> navigate to /Deterministic_Dungeon
   ->Run the jar file by using the following commands (Input values given are just an example) (input parameters sequence - wrapping type, number of rows, number of columns, degree of interconnectivity, percentage of caves with treasure, percentage of caves with monsters):java -jar CS5010_PDP_Project4_AdventureGame.jar W 6 6 13 30 40
7. Similarly, for Random Dungeon:
   -> navigate to /Random_Dungeon
   ->Run the jar file by using the following commands (Input values given are just an example) (input parameters sequence - wrapping type, number of rows, number of columns, degree of interconnectivity, percentage of caves with treasure, percentage of caves with monsters):java -jar CS5010_PDP_Project4_AdventureGame.jar W 6 6 13 30 40

# HOW TO USE THE PROGRAM
1. The instructions on how to use the program is given above on how to run the program.
2. To execute the jar files follow the instructions given in 'How To Run The Program'.
3. You can give the input paramters of your choice and a dungeon will be created and game will start provided the parameter values are valid.
4. To run the driver file:
   -> Navigate to /CS5010_PDP_Project4_AdventureGame/src
   -> javac GameDriver.java
   -> java GameDriver.java W 6 6 13 30 40 (Input values given are just an example) (input parameters sequence - wrapping type, number of rows, number of columns, degree of interconnectivity, percentage of caves with treasure, percentage of caves with monsters)

# DESCRIPTION OF EXAMPLES
1. In /res/DriverPrograms_ExampleRuns/Deterministic_Dungeon, there are the following runs
   a) Player navigating through the dungeon, Player picking up treasure, Player picking up arrows, Player killing an Otyugh, Player winning the game by reaching the end
   b) Player navigating through the dungeon, Player entering a cave with an injured monster and escaping, Player getting eaten by a monster which is in full health, Moving player in all 4 directions (North, South, East, West), if there exists a path.
   c) 1. Player navigating through the dungeon, User getting defeated by an injured Otyugh , User trying to shoot when he is not equipped with/ out of arrows
2. In /res/DriverPrograms_ExampleRuns/Random_Dungeon, there are the following runs
   a) Player navigating through the dungeon, Player picking up the treasure, Player killing an Otyugh, Player winning the game by reaching the end
   b) Player navigating through the dungeon, Player killing an Otyugh, Player getting defeated by an injured Otyugh

# DESIGN/ MODEL CHANGES
1. Initially all the methods after creation of dungeon were being called separately, after dungeon creation. Now everything from creation of dungeon to adding treasure, arrows and monsters is done in the constructor itself.
2. Also, I have added a new data type using a class Pair to store each nodes' neighbour location ID and the neighbours' direction from the present location.

# ASSUMPTIONS
When the player enters a location with an injured monster, the survival of the player depends on luck factor which is evaluated randomly.

# LIMITATIONS
The survival of the player, when he enters a location with an injured monster is based on luck factor, and there is no mechanism to give the player a chance to save himself from the injured monster.

# CITATIONS
I have done the coding logic on entirely on my own, so I haven't added any citations. I used online resources only to look up built-in functions but did not copy a part of some code snippet. So I have not mentioned citations.