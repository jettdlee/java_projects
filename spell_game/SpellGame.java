/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Spell Game Application
	SpellGame class and main (Reqires SpellDeck.java, SpellCard.java, Player.java)

	In the game, each player is given a set of spells, based on 4 elements, with a potency between 1-12, no two cards are the same.
	Deck will be presented and cards shuffled, to be distributed across all players evenly.
	
	The Spell Game Class controls the game process by calling methods to setup the game, controlling the deck, 
	and dealing cards to the player.
	
	Created by Jet-Tsyn Lee 06/11/17
	Last updated v0.1 09/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class SpellGame
{
	// #####  INSTANCE VARIABLES  #####
	private final int PLAYERNO = 4;							// Number of players a game
	private Player playerArr[] = new Player[PLAYERNO + 1];	//add 1 to exclude player 0
	
	
	// #####  MAIN  #####
	public static void main(String[] args)
	{
		SpellGame game = new SpellGame();
		SpellDeck deck = new SpellDeck();
		
		// Show initial order of deck
		deck.show();
		System.out.println();
		
		// Shuffle and show new deck order		
		deck.shuffle();

		deck.show();
		System.out.println();

		
		// Create player objects stored in array
		for (int i = 1; i < game.playerArr.length; i++)
		{
			game.playerArr[i] = new Player("PLAYER " + i, deck.getDeckOrder().length);
		}		
				
		// Deal n cards to players
		game.dealCards(deck);
		
		// Show hand for all players
		game.showPlayerHand();
		
		// Show deck if any cards are remaining
		deck.show();
		
	}
	
	
	// #####  METHODS  #####
	// Deal all cards to the players hand
	private void dealCards(SpellDeck deck)
	{
		int playerCount = playerArr.length;
		
		// amount of cards dealt to players, any excess cards remain in deck, minus 1 as position 0 ignored
		int cardCount = (int)Math.floor(deck.getDeckOrder().length / (playerCount - 1));	
		
		System.out.println("Dealing cards ...");
		System.out.println();
		
		for (int iDeal = 0; iDeal < cardCount; iDeal++)
		{
			for (int iPlayer = 1; iPlayer < playerCount; iPlayer++)
			{
				// Check if there are any cards remaining in deck
				if (deck.getDeckValue(0) != null)
				{
					// Give card to player and remove from deck
					playerArr[iPlayer].setHand(iDeal, deck.getDeckValue(0));
					deck.removeCard(deck.getDeckOrder(),1);
				}
				else
				{	
					System.out.println(playerArr[iPlayer].getName() + " unable to draw card, No cards remaining in deck.");
				}
				
			}
		}	
	}
	
	private void showPlayerHand()
	{
		System.out.println("The hands are:");
		System.out.println();
		for (int i = 1; i < playerArr.length; i++)
		{
			playerArr[i].showHand();
		}
	}
	
	
	
}