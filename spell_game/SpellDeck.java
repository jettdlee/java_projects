/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Spell Game Application
	SpellDeck Class

	For the Spell game a deck is created containing 4 elements, FIRE, WATER, EARTH, AIR, with each element having a
	potency of a range between 1-12, resulting in 48 cards total.
	
	The SpellDeck Class stores all the values and methods regarding the games deck, including orders, Element/Potency
	stats, and methods to show an shuffle the deck.
	
	Created by Jet-Tsyn Lee 06/11/17
	Last updated v0.1 09/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class SpellDeck
{
	// #####  INSTANCE VARIABLES  #####
	private String elementArr[] = {"FIRE","WATER","EARTH","AIR"};	
	
	private final int MINPOTENCY = 1;
	private final int MAXPOTENCY = 12;
	private int maxCards = (MAXPOTENCY - MINPOTENCY + 1) * elementArr.length;
	
	private SpellCard deckOrder[] = new SpellCard[maxCards];;	// Current Order of cards, note 0 = Top of deck
		
		
	// #####  CONSTRUCTOR  #####
	public SpellDeck()
	{
		System.out.println("Creating spell cards ...");
		System.out.println();
		initializeCards();
	}
		
	
	// #####  METHODS  #####
	private void initializeCards()
	{
		// Initialize card orders
		int count = 0;
		int spellCount = elementArr.length;
		
		for (int iElement = 0; iElement < spellCount; iElement++)
		{
			for (int iPot = MINPOTENCY; iPot <= MAXPOTENCY; iPot++)
			{	
				deckOrder[count] = new SpellCard(elementArr[iElement], iPot);
				count++;
			}
		}		
	}	
	
	
	// Show current order of deck
	public void show()
	{
		// If cards remaining in deck, Show card
		if (deckOrder[0] != null)
		{
			System.out.println("The cards are:");
			int deckCount = deckOrder.length;
			for (int i = 0; i < deckCount; i++)
			{
				if (deckOrder[i] != null)
				{	System.out.println(deckOrder[i]);
				}
			}	
		} 
		else
		{
			return;
		}

	}
	
	// Shuffle cards in deck - based of the Riffle Shuffle
	public void shuffle() 
	{
		System.out.println("Shuffling cards ...");
		System.out.println();
		
		int cardMax = deckOrder.length;
		int loop = 100;
		
		// Create a copy of the deck
		SpellCard mainH[] = new SpellCard[cardMax];
		System.arraycopy(deckOrder, 0, mainH, 0, cardMax);
	
		
		for (int iLoop = 0; iLoop < loop; iLoop++)
		{
			SpellCard leftH[] = new SpellCard[cardMax];
			SpellCard rightH[] = new SpellCard[cardMax];
			System.arraycopy(mainH, 0, rightH, 0, cardMax);	// Move all cards to right hand
			
			// Get random cut position
			int iCut = (int)Math.round(Math.random() * (cardMax-1));
						
			// Cut the deck to split between left and right hands
			for (int i=0; i < iCut; i++)
			{
				leftH[i] = rightH[0];
				removeCard(rightH,1);
			}
			
			// Put cards back into deck in a random order
			for (int i = 0 ; i < cardMax; i++)
			{
				// Randomly select which hand to insert to main deck, 0 = Right, 1 = Left
				int rand = (int)Math.round(Math.random());
				
				if( rand == 0 && rightH[0] != null)
				{	mainH[i] = rightH[0];
					removeCard(rightH,1);
					
				}	else {
					// If left is selected but no cards remain, use right hand
					if (leftH[0] != null)
					{	mainH[i] = leftH[0];
						removeCard(leftH,1);
					} else {
						mainH[i] = rightH[0];
						removeCard(rightH,1);
					}
				}
			
			}
			
		};
		
		// Return array to deck
		System.arraycopy(mainH, 0, deckOrder, 0, cardMax);
		
	}
	
	// Remove n amount of cards from deck array and shift cards by position n
	public void removeCard(SpellCard[] array, int del) {
		// Copy the array to itself shifting the values by one and reduces the overall length
		SpellCard newArr[] = new SpellCard[array.length];
		int count = 0;
		for (int i = del; i < array.length; i++)
		{	newArr[count]=array[i];
			count++;
		}
		System.arraycopy(newArr, 0, array, 0, array.length);
	}

	// Getter Methods
	public SpellCard[] getDeckOrder()
	{
		return deckOrder;
	}
	
	public SpellCard getDeckValue(int value)
	{
		return deckOrder[value];
	}
	
}




