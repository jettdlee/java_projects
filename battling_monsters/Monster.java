/* 	Battling Monsters Script
*	Monster Class
*
*	Allows the user to input Monster stat values. 
*	Stores all information for the monster object and all round results for the battlequeen object to return for the fight.
*
*	Created by Jet-Tsyn Lee 04/10/2017
*	Version 0.4, last updated 13/10/2017
*/

import java.util.Scanner;

public class Monster
{
	Scanner in = new Scanner(System.in);
	
	//#######  VARIABLES  #######
	private String name;	//Monster Name
	private int strength;	//Monster Strength
	private int wrath;		//Monster Wrath
	
	public int winNo = 0;
	public int lossNo = 0;
	public int drawNo = 0;
	
	
	//#######  CONTRUCTOR  #######
	//Set Monster info (CONSTRUCTOR), user is askes to input the monsters strength and wrath values
	public Monster(int monsterCount, String monsterName)	//Monster name determined from BattleQueen class
	{	
		//NAME
		name = monsterName;
		
		//User inputs the strength and wrath values, limited to integers 1-10
		//STRENGTH
		System.out.print(name + " (Monster " + monsterCount + ") Strength:");
		int strInput = in.nextInt();
		strength = inpErr(strInput);
		
		//WRATH
		System.out.print(name + " (Monster " + monsterCount + ") Wrath:");
		int wraInput = in.nextInt();
		wrath = inpErr(wraInput);
		
		//Display Monster stat inputs for testing
		System.out.println(name + " (Monster " + monsterCount + ") Strength:" + strength + ", Wrath:" + wrath);
		
		System.out.println();
	}
		
	
	//#######  METHODS  #######
	//Input value for strength and wrath is checked to restrict user input to 1-10, end program if number is invalid
	private int inpErr(int inputVal)
	{	if (inputVal > 10 || inputVal < 1)
		{	System.out.println("Invalid input value, Please try again");
			System.exit(0);
		}
		
		return inputVal;
	}
	
	
	//Return Monster stat information to the battlequeen class for fights
	public String getName()	//Name
	{	return name;
	}
	public int getStrength()		//Strength
	{	return strength;
	}
	public int getWrath()		//Wrath
	{	return wrath;
	}
	
	
	/*	Monster tracks the Win/Loss/Draw results from fights in the BattleQueen Class
	*	in the fight method, monsters will be given a result integer from the battlequeen 
	*	class to determine their battle results and stored.
	*/
	public void setRound(int Result)
	{
		//Get the round results integer from the battlequeen and increase the score
		switch(Result)
		{	case BattleQueen.WIN:	winNo++;
					break;
			case BattleQueen.LOSS:	lossNo++;
					break;
			case BattleQueen.DRAW:	drawNo++;
					break;
			default: 	System.out.print("An error has occured, Ending Code");
						System.exit(0);
		}
			
	}
	
	


	
}	
	
