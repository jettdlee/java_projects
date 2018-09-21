/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Spell Game Application
	SpellCard Class

	The SpellCard class storing the values of a card (element, potency) which is returned in the game 
	
	Created by Jet-Tsyn Lee 06/11/17
	Last updated v0.1 09/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/


public class SpellCard
{
	// #####  INSTANCE VARIABLES  #####
	private String element;
	private int potency;
		
		
	// ##### CONSTRUCTOR #####
	public SpellCard(String iElement, int iPotency)
	{
		element = iElement;
		potency = iPotency;
	}
	
	
	// #####  METHODS  #####
	public String toString()
	{
		return element + " " + potency;
	}

}





