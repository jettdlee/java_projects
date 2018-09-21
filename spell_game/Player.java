/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Spell Game Application
	Player Class

	The Player class storing the values of the player name and an array of the players card hand which can be returned
	
	Created by Jet-Tsyn Lee 06/11/17
	Last updated v0.1 09/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
public class Player
{
	// #####  INSTANCE VARIABLES  #####
	private String playerName;
	private SpellCard playerHand[];	// Array of cards in the current players hand
	
	
	// ##### CONSTRUCTOR #####
	public Player(String name, int handCount)
	{
		playerName = name;
		playerHand = new SpellCard[handCount];
	}
	
	// #####  METHODS  #####
	public void showHand()
	{
		System.out.println(playerName);
		System.out.println("==============");
		for (int iHand = 0; iHand < playerHand.length; iHand++)
		{
			if (playerHand[iHand]!=null)
			{
				System.out.println(playerHand[iHand]);
			}
		}
		System.out.println();
	}
	
	public String getName()
	{
		return playerName;
	}
	
	public void setHand(int handPos, SpellCard spell)
	{
		playerHand[handPos] = spell;
	}
	
}



