# ABOUT/ OVERVIEW
The Jungles Friends Primate Sanctuary has to replace all their paper records, with computer records where they can keep a track of all monkeys in isolations and enclosures.
This implementation includes a Sanctuary interface which creates housings of 2 types: isolations and enclosures. There are various functions implemented by which the Sanctuary will be able to maintain and keep a track of all monkeys housed in the Sanctuary.

# LIST OF FEATURES
1. Sanctuary is initially configured to have n isolation rooms and m enclosures.
2. We can increase the number of isolations and enclosures later on if sanctuary receives enough funding and is able to expand and build more isolations and enclosures.
3. We can add new monkeys to isolation depending upon the availability of isolations.
4. We can also move a monkey which is in enclosure to isolation if it has to be given treatment.
5. Enclosure can house monkeys of the same species. The consumed area is updated according to the size of the monkey which is added to the enclosure.
6. Monkey can be moved from isolation to enclosure depending upon the availability of enclosures and enclosure capacity.
7. We can also print all the species housed in the sanctuary in alphabetical order along with thier location in the sanctuary.
8. We can also look up for a particular species with species name.
9. We can get the sign of a particular enclosure based on the enclosure id. It will give the name, sex and favourite food of every monkey in the enclosure.
10. We can also get a list of all monkeys housed in the sanctuary by their name and their location in the sanctuary.
11. Produce shopping list for the entire sanctuary depending upon the favourite food and size of the monkeys housed.

# HOW TO RUN THE PROGRAM
1. Download the .zip and unzip it.
2. Open IntelliJ IDEA and on the task bar click on File --> New --> Project from Existing Sources
3. Locate the unzipped folder and click on open.
4. Keep going forward with the default settings by clicking on Next and in the end click Finish.
5. In the res/ folder open the CS5010_PDP_Project1_Primates_jar folder.
6. There will be .jar file under this folder name as CS5010_PDP_Project1_Primates.jar
7. Right click on thi =s .jar file and click on 'Run CS5010_PDP_Project1_Primates.jar'
8. The .jar file will execute all the code of the driver file SanctuaryDriver which is situated in src/sanctuary.

# DESCRIPTION OF EXAMPLES
Open the /res folder and then the SanctuaryDriver_Output text file to see the example inputs and outputs of the different functionalities.

# DESIGN/ MODEL CHANGES
The major changes which were made in the design after the design meeting are as follows:
1. The addMonkeyToEnclosure which was first added in Isolation class was shifted to the Sanctuary class to avoid coupling between Isolation and Enclosure class.
2. Added an additional field in the Monkey called uniqueId to assign a unique identification number every time a monkey record is created.
3. Removed the abstract class AbstractSanctuary and added a class Sanctuary which implements the interface. This change was made mainly because all the functionalities to be implemented were to be done in the Sanctuary class and the Isolation and Enclosure class were mainly a means of providing a structure.
4. Added a few more functions in the Monkey class like getting the monkey size and the area it would require based on the monkey size which would be helpful while moving monkeys from isolation to enclosure and vice-versa.
5. Added 2 more enums type data types for monkey size and medical status of the monkey.

# ASSUMPTIONS

SPECIES NAME:
As new species can be discovered anytime, we cannot maintain a list of species for it and limit it only to the species which we know so far.
For this the datatype of species name has been kept as String. The implementation of the any functionality which is look up species that there is a consistency in the way species are names.
For example: If you are adding a species 'ape' and you search for 'apes' it won't show the appropriate results. But if there is a species 'tamarin' and you search for 'Tamarin' that will work as I have ignored the cases.

# LIMITATIONS
Mainly when creating a sanctuary or extending the sanctuary size, when we are adding enclosures, at first the enclosure class variables are given the default values with the help of default constructor. To assign the species it can house and the area we have to set it separately.