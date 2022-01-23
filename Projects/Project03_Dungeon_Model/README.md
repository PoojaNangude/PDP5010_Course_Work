# ABOUT/ OVERVIEW
The problem statement expects to design a model which will be able to create a dungeon 2D dungeon using Kruskal's algorithm and create a player who will move around the dungeon.
The basic operations expected to work are moving the player around and also options for the player to pick up the treasure is he finds treasure in the way while exploring the dungeon.

# LIST OF FEATURES
1. User will have the option to move around in the neighbouring tunnels/caves if there is a path to it.
2. User can pickup the treasure in a cave if he finds it.
3. The player description can be shown whihc will have the treasure collected and present location.
4. The description of the location where the player is presently can be shown, so that th eplayer knows the possible moves and if there is treasure in the cave in which he is presently.

# HOW TO RUN THE PROGRAM
1. Download the .zip and unzip it.
2. Open IntelliJ IDEA and on the task bar click on File --> New --> Project from Existing Sources
3. Locate the unzipped folder and click on open.
4. Keep going forward with the default settings by clicking on Next and in the end click Finish.
5. Go to the terminal and navigate to /CS5010_Project3_Dungeon/src
6. For GameDriver1: 
    -> javac GameDriver1.java
    -> java GameDriver1.java  NW 5 6 8 20 (Make sure you enter the same inputs in the commandline arguments, as the player moves have been hard coded in the driver class)
7. Similarly, for GameDriver2:
    -> javac GameDriver2.java
    -> java GameDriver2.java  W 5 6 8 30 ((Make sure you enter the same inputs in the commandline arguments, as the player moves have been hard coded in the driver class))
8. To execute the jar files
    For Project3_Dungeon_Game_Driver1_JAR:
    -> navigate to /res/Project3_Dungeon_Game_Driver1_JAR
    -> Enter this command to run the jar file: java -jar CS5010_Project3_Dungeon.jar NW 5 6 8 20

    For Project3_Dungeon_Game_Driver2_JAR:
    -> navigate to /res/Project3_Dungeon_Game_Driver2_JAR
    -> Enter this command to run the jar file: java -jar CS5010_Project3_Dungeon.jar W 5 6 8 30

# HOW TO USE THE PROGRAM
1. The instructions on how to use the program is given above on how to run the program. Make sure you hard core the same inputs as the player movements and collecting pleasure is hard coded in the driver class.
2. To do this copy the commands given in 'How To Run The Program' as they are to execute the driver programs(point 7).
3. To execute the jar files follow the instructions given in 'How To Run The Program' - point 8.

# DESCRIPTION OF EXAMPLES
1. GameDriver1 covers the following cases: Non- wrapping and player starts from start point and reaches destination.
2. GameDriver2 covers the following cases: wrapping and player visits all the nodes.

NOTE: In both the drivers the player description and the description of the location in which the player is printed after every move.

# DESIGN/ MODEL CHANGES
I had previously created a separate function called createDungeon() to create the dungeon. But I hace changed that and how the dungeon gets constructed in the constructor itself, to avoid any invalid dungeon creation. 

# ASSUMPTIONS
1. I am showing the player the neighbour nodes instead of the directions, as once user is in the dungeon, the directions may become relative to him.
2. So instead of north, south, east and west I show the neighbours and display the dungeon and the player's location in the dungeon at every move so that player will know where he is going if he chooses a particular neighbour.
3. While assigning the treasure to the caves I have randomly chosen any 2 type of treasures and assigned 1 of each of the 2 types to the cave. This was my design decision to add any 2 types of treasure to a cave while assigning.

# LIMITATIONS
Instead of north, south, east and west I have shown neighbours of the location at which the player is so that the player can choose the next move, as once inside the dungeon the directions will relative the player.

# CITATIONS
I have done the coding logic on entirely on my own, so I haven't added any citations. I used online resources only to look up built-in functions but did not copy a part of some code snippet. So I have not mentioned citations.