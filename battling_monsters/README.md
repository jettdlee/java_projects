# Battling Monsters
Design a tournament where monster objects battle. The monster contains the following attributes: 
* the monster’s name (String), 
* strength (integer in the range 1 to 10)
* wrath (integer in the range 1 to 10)

BattleQueen object organizes tournaments, passing it references to the four monsters, and ensure that monster fights each of the others with rules:
* Highest strength wins. 
* If the strength values are equal, the one with the greatest wrath wins.
* If these are also equal, the result is a tie.
At the end of each fight, the BattleQueen will inform the competitors of the result, and each monster should keep count of its wins, losses and draws. At the end of the tournament, the BattleQueen will ask each monster to output details of its fight record.

Used to create efficient objects and reference to the main application, and to be able to call variables to use in if statements.
Compile and run 'BattleQueen.java'
