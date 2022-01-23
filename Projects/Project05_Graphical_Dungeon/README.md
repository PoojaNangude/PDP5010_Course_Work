# ABOUT/ OVERVIEW
The problem statement expects to build up the GUI for the dungeon game for which we have previously built the model and controller. The GUI is expected to be made using Java Swing.

# LIST OF FEATURES
1. User can move from location to another by specifying the direction to be travelled in (East, West, North, South).
2. User can get a sense of pungent smell if there are monsters in the nearby locations.
3. User can also shoot arrows in all 4 directions if there is a path available in the direction.
4. User can pick up treasure and arrows if he finds them while navigating the dungeon.
5. User can die by 2 ways - eaten by monster or by falling into a pit.
6. User wins when be will reach the end point without being killed.

# HOW TO RUN THE PROGRAM
1. Download the .zip and unzip it.
2. Open IntelliJ IDEA and on the task bar click on File --> New --> Project from Existing Sources
3. Locate the unzipped folder and click on open.
4. Keep going forward with the default settings by clicking on Next and in the end click Finish.
5. To execute the Game Driver:
   -> Navigate to src/GameDriver.java
   -> Compile the driver file using the command: javac GameDriver.java
   -> To run the graphical based Adventure Game use the command: java GameDriver.java
   -> Tu run the text based adventure game use the same command followed by the dungeon parameters. Example: java GameDriver.java W 6 6 13 30 40
6. To execute the JAR file (I have included the JAR file in the main directory as reading the images is not happening otherwise)
   ->Go to the terminal and navigate to /CS5010_PDP_Project4_AdventureGame/
   -> Run the command java -jar CS5010_PDP_Project5_Graphical_Adventure_Game.jar

# HOW TO USE THE PROGRAM
1. In the graphical bases game the player can enter the parameters of the dungeon like number of rows, columns, interconnectivity, percentage of caves with monsters and percentage of caves with treasure.
2. Player can also reset the game, reuse the game and exit the given through the menu on the top of the game.
3. Player can move north,south,east,west using the four direction keys.
4. Player can pick up treasure using the key 't' and pick up the arrow using the key 'a';
5. Player can shoot by pressing 's', then one the four direction keys and then a number between 1 to 9;

# DESCRIPTION OF EXAMPLES
In /res/Program_Execution_Screenshots
1. One screenshot shows the player winning by reaching the end point.
2. One screenshot shows the player loosing by being eaten by a monster.
3. One screenshot shows the player loosing by falling into a pit.
4. One screenshot shows the player being looted by a thief, when he encounters one.

# DESIGN/ MODEL CHANGES
1. Initially all the methods after creation of dungeon were being called separately, after dungeon creation. Now everything from creation of dungeon to adding treasure, arrows and monsters is done in the constructor itself.
2. Also, I have added a new data type using a class Pair to store each nodes' neighbour location ID and the neighbours' direction from the present location.
3. I have added a thief who can rob the player's collected treasure.
4. I have also added pits in which player can fall and loose the game.

# ASSUMPTIONS
1. When the player enters a location with an injured monster, the survival of the player depends on luck factor which is evaluated randomly.
2. When the player is near a pit, I have assumed that the pit is a king of a whirpool which will suck anything that falls into it, so the locations near the pits will be windy.

# LIMITATIONS
The survival of the player, when he enters a location with an injured monster is based on luck factor, and there is no mechanism to give the player a chance to save himself from the injured monster.

# CITATIONS
I have done the coding logic on entirely on my own, so I haven't added any citations. I used online resources only to look up built-in functions but did not copy a part of some code snippet. So I have not mentioned citations.