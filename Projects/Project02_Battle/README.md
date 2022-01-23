# ABOUT/ OVERVIEW
The problem statement expects to design a model which can create a battle arena and let the players compete. The result of the match is based on their abilities and a bit on luck depending upon the gears they are equipped with and the weapons they have.
The model is uses the Player(class), Gear(interface) and Weapon(interface) in the Battle class to create players and assign equipments/gears and weapons and compete in the battle arena until one gets knocked out or the game is a draw.

# LIST OF FEATURES
1. Creates player with basic abilities.
2. Assigns equipments/gears to players and add all the gears on to the player unless they cant be used in combination.
3. Updates the abilities of the player depending upon the gears put on them.
4. Assigns the weapon to the player in the beginning of the game.
5. Battles between two players with two possible outcomes - victory or draw.
6. Players can have a rematch. In this case all their gears and weapons are kept as it is and only their health is revived.

# HOW TO RUN THE PROGRAM
1. Download the .zip and unzip it.
2. Open IntelliJ IDEA and on the task bar click on File --> New --> Project from Existing Sources
3. Locate the unzipped folder and click on open.
4. Keep going forward with the default settings by clicking on Next and in the end click Finish.
5. In the res/ folder open the CS5010_PDP_Project2_Battle_jar folder.
6. There will be .jar file under this folder name as CS5010_PDP_Project2_Battle_jar.jar
7. Right click on this .jar file and click on 'Run CS5010_PDP_Project2_Battle_jar.jar'
8. The .jar file will execute all the code of the driver file BattleDriver which is situated in src/battle/BattleDriver.


# HOW TO USE THE PROGRAM
1. The driver class execute the battle between 2 players. At the end of the game : draw or one player wins, the user can choose to have a rematch between the same players.
2. If user wants a rematch, 'y' is to be entered, otherwise 'n' is to be added.

# DESCRIPTION OF EXAMPLES
An example run of the project can be found in res/DriverProgram_Output/DriverProgram_Output.txt.
The example run shows both the player abilities with the affect of the gears they are using on their abilities.
Then the example run shows the result of every round and if the striking player was successful in hitting the opponent.
The example run shows kinds of output: One in which one of the player wins the match and one which result in a draw.

# DESIGN/ MODEL CHANGES
1. In the old design I was creating the armoury and equipment bag in the Player class which would be assigned to the Player. I changed this to Battle class as armoury and equipment bag is associated with the battle and not one particular player.
2. Added a new class BattleRoundResult which would be used to create an object to store the details of result of every round during the battle to make the printing of information easier.
3. At first, I had decided to continue the battle until of the player wins. But now after 60 hits are done if after 60 hits there are 20 subsequent hits with no damage to each other, the gaem will be declared as a draw.

# ASSUMPTIONS
1. Once the player has been assigned gears, the effect of the gears on the abilities will stay until the match is over.
2. As it is not mentioned specifically that the equipment bag need to be created by randomly assigning elements to it until all the minimum requirements are satisfied I have assumed that we can create an equipment with the same content always and have mad sure that the requirement of 25% gears having a negative affect is satisfied.
3. As the effect of the gears on the abilities is left as a design decision, I have assumed the following effects of each gear:
Headgear - increases constitution of player by 2.
Potions - increase strength and constitution by 2 and decrease charisma by 3.
Footware - increase dexterity by 2.
Small belts - increase constitution and dexterity by 1.
Medium belts - increase constitution and dexterity by 2.
Large belts - increase constitution and dexterity by 3.
    

# LIMITATIONS
As the affect of the gears has not been specified by Jumptastic Games the effect the gears have on the abilities have been specified in the model itself. If later on Jumptastic Games wishes to have these value in their control this would not be possible and model would require changes.

# CITATIONS
I have done the coding logic on entirely on my own, so I haven't added any citations. I used online resources only to look up built-in functions but did not copy a part of some code snippet. So I have not mentioned citations.