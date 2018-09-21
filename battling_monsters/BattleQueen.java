/* 	Battling Monsters Script
*	BattleQueen Application Class (required Monster.java class)
*
*	BattleQueen application, 
*	Battlequeen, gets the Monster info and organise all monsters to compete in a tournament.
*	Each monster object will be created with user input stats, and fight against each other, over 6 rounds.
*	Fights are determined by Strength or Wrath advantage and round results are stored in monster class.
*	At the end, fight results will be shown for each round, and individual monster results will be announced by BattleQueen.
*
*	Created by Jet-Tsyn Lee 04/10/2017
*	Version 0.4, last updated 13/10/2017
*/

public class BattleQueen
{	
	//Result integers to determine round results.
	public static final int WIN = 1;
	public static final int LOSS = 2;
	public static final int DRAW = 3;
	
	
	//#######  BATTLEQUEEN  #######
	//Gets all the monster data and places each in a fight against each other.
	//Final results for each monster will be shown.
	public BattleQueen(Monster mon1, Monster mon2, Monster mon3, Monster mon4)
	{
		//#######  FIGHT  #######
		//Each monster competes against each other over 6 rounds, fight method (below) is used for battle.
		System.out.println("Battle Start:");
		fight (1, mon1, mon2);	//1v2
		fight (2, mon3, mon4);	//3v4
		fight (3, mon1, mon3);	//1v3
		fight (4, mon2, mon4);	//2v4
		fight (5, mon1, mon4);	//1v4
		fight (6, mon2, mon3);	//2v3		
		
		//#######  RESULTS  #######
		//Prints the Final results for each monster.
		System.out.println();
		System.out.println("Final Results (W/L/D):");
		System.out.println(mon1.getName() + ": " + getResults(mon1));
		System.out.println(mon2.getName() + ": " + getResults(mon2));
		System.out.println(mon3.getName() + ": " + getResults(mon3));
		System.out.println(mon4.getName() + ": " + getResults(mon4));
		
	}
	
	//Return the Win/Loss/Draw results of the set monster to the battlequeen class for final results.
	public String getResults(Monster monsterNo)
	{	return monsterNo.winNo + "/" + monsterNo.lossNo + "/" + monsterNo.drawNo;
	}
		
	
	/* #######  FIGHT METHOD  #######
	*Two monster objects compete against each other (Red vs Blue) and stats are compared to determine a winner.
	*Winner is based on Str advantage, if equal, Wrath is used, else Draw.
	*Fight results are set in the monster class to return at end.
	*/
	public static void fight(int roundCount, Monster redMon, Monster blueMon)
	{
		//#######  VARIABLES  #######
		//Get Monster stats and set to variables.
		//Red Monster stats
		String redMonName = redMon.getName();
		int redMonStr = redMon.getStrength(); 
		int redMonWra = redMon.getWrath();
		
		//Blue Monster Stats
		String blueMonName = blueMon.getName();
		int blueMonStr = blueMon.getStrength();	 
		int blueMonWra = blueMon.getWrath(); 
		
		//Round information
		System.out.println("Round " + roundCount + " - " + redMonName + " vs. " + blueMonName + ": ");
		
		
		//#######  FIGHT  #######
		//Compare stats against the 2 set monsters to determine the winner
		//Highest strength wins, on draw, highest wrath wins, else round draw
		//Round results will be set in monster object
		if (redMonStr == blueMonStr)	//Strength Draw
		{
			if (redMonWra == blueMonWra) //Wrath Draw
			{	System.out.println("Round Draw");
				redMon.setRound(DRAW);
				blueMon.setRound(DRAW);
			}
			else if (redMonWra > blueMonWra) //Wrath redMon advantage
			{	System.out.println(redMonName + " wins with Wrath " + redMonWra);
				redMon.setRound(WIN);
				blueMon.setRound(LOSS);
			}
			else 	//Wrath blueMon advantage
			{	System.out.println(blueMonName + " wins with Wrath " + blueMonWra);
				redMon.setRound(LOSS);
				blueMon.setRound(WIN);
			}
		}
		
		//Strength Advantage
		else if (redMonStr > blueMonStr)	//Strength redMon advantage
		{	System.out.println(redMonName + " wins with Strength " + redMonStr);
			redMon.setRound(WIN);
			blueMon.setRound(LOSS);
		}
		else		//Strength blueMon advantage
		{	System.out.println(blueMonName + " wins with Strength " + blueMonStr);
			redMon.setRound(LOSS);
			blueMon.setRound(WIN);
		}
	
	}
	
	
	//#######  MAIN  #######
	//Main method containing the creation of monster and battlequeen object class which will run the battling script.
	public static void main(String[] args)
	{			
		//#######  MONSTER OBJECT  #######
		//Create 4 Monster objects, Input Values for Strength and Wrath located in Monster.java
		System.out.println("Please input stat values (1 to 10): ");
		Monster monster1 = new Monster(1, "Orc");
		Monster monster2 = new Monster(2, "Werewolf");
		Monster monster3 = new Monster(3, "Kill Bot v2.34");
		Monster monster4 = new Monster(4, "Gargoyle");
		
		//BattleQueen Object
		BattleQueen battlequeen = new BattleQueen(monster1, monster2, monster3, monster4);
	}	
	
	
	
}



