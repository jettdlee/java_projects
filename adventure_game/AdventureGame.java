/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Adventure Game Application
	AdventureGame class and main 

	Create a game that allows a hero to traverse rooms in a warehouse picking up items.
	The goal is to pick up items totaling to the maximum value (Knapsack problem) with a limited capacity.
	
	AdventureGame class creates the game and controls the rules of the overall gameplay.
	Create 8x8 warehouse and randomly scatter 50 items with	each item given name, 
	size(int) 5-15 and value (int) 1-20.	
	Hero with backpack size 50 that can requrest user inputs as actions:
		n, e, s, w : head north, east, south or west, assuming there is a door allowing it
		i : inventory – list all items in your backpack
		p : pick up item
		d : drop item
		m : map (added)

	Created by Jet-Tsyn Lee 04/12/17
	Last updated v0.1 06/12/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

import java.util.*;
import java.io.*;

public class AdventureGame
{
	
	// #####  MAIN  #####
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		// #######  GAME SETUP  #######
		// Create initial warehouse and setup random doors for each room
		Warehouse wh1 = new Warehouse(8);

		// Read item file and place items in random rooms in warehouse using method
		// if file failed to load, end game
		boolean itemSetup = randObj(wh1, "ItemList.txt");
		if (itemSetup == false)
			System.exit(1);
		
		// Create Hero and place in random room in warehouse
		int whSize = wh1.getWhSize();
		Hero hero1 = new Hero(randomNumber(whSize),randomNumber(whSize));	
		
		
		// #######  PLAY  #######
		boolean inpCheck;
		boolean exit = false;
		String inpAction;
		
		// Loop the gameplay until user requires the game to end
		do
		{
			// Get object for current room and update with hero position
			Room curRoom = wh1.getWHRoom(hero1.getYPos(), hero1.getXPos());
			curRoom.updateRoom(true);	// Update room for heroes position
			curRoom.showRoom();			// Show room contents
			
			// Get user input and validate
			do
			{	
				inpCheck = true;
				try
				{
					System.out.println();
					System.out.print("What would you like to do(type 'help' for actions):");
					inpAction = in.nextLine().toLowerCase();	// Set as lowercase to ensure that action input is not case sensetive
				}
				catch(InputMismatchException e)
				{	
					System.out.println("~~Invalid input, please try again.");
					inpAction = in.nextLine(); // Passes incorrect input
					inpCheck = false;
				}

			} while (inpCheck == false);
			System.out.println();
			
			
			// =======  ACTIONS  =======
						
			// ~~~~~~~  HERO Actions  ~~~~~~~
			// Pickup items in room
			if (inpAction.matches("p|pickup"))
			{
				hero1.pickupItem(curRoom);
			}
			
			// Drop items in heroes inventory
			else if (inpAction.matches("d|drop"))
			{
				hero1.dropItem(curRoom);
			}
			
			// Show items in heroes inventory
			else if (inpAction.matches("i|inventory"))
			{
				hero1.showInventory();
				System.out.println();
				hero1.showStatus();
			}
			
			// Show map of warehouse if aquired (and items if detector aquired)
			else if (inpAction.matches("m|map"))
			{
				hero1.showMap(wh1);
			}
			
			
			// ~~~~~~~  MOVE Actions  ~~~~~~~
			// Checks if input is a valid direction input
			else if (inpAction.matches("n|e|s|w|north|east|south|west"))
			{
				// Checks that there is a door avaliable and move to next room
				if (inpAction.matches("n|north") && curRoom.isThereADoor(Room.NORTH) == true)
					hero1.moveRoomRow(-1);
				else if (inpAction.matches("e|east") && curRoom.isThereADoor(Room.EAST) == true)
					hero1.moveRoomCol(1);
				else if (inpAction.matches("s|south") && curRoom.isThereADoor(Room.SOUTH) == true)
					hero1.moveRoomRow(1);
				else if (inpAction.matches("w|west") && curRoom.isThereADoor(Room.WEST) == true)
					hero1.moveRoomCol(-1);
				
				else
					System.out.println("~~Cannot Move in that direction");

				// Remove hero check from room
				curRoom.updateRoom(false);
			}
			
			
			// ~~~~~~~  MISC Actions  ~~~~~~~
			// End Game
			else if (inpAction.equals("exit"))
			{
				exit = true;
				System.out.println("Thank You for playing");
				System.out.println("Total value of items:" + hero1.getValue());
			}
			
			// Show Game help
			else if (inpAction.equals("help"))
			{
				System.out.println("p or pickup - pick up item in room");
				System.out.println("d or drop - drop item in inventory");
				System.out.println("i or inventory - show inventory and status");
				System.out.println("m or map - show map (if obtained map item)");
				System.out.println("n/e/s/w or north/east/south/west - move rooms in input direction");
				System.out.println("exit - end game");
			}

			// Input incorrect
			else
				System.out.println("~~Invalid input");
						
			
		} while(exit == false);
	
		
	}
	
	
	// #######  METHODS  #######
	
	// Randomly places a number of items in the Warehouse with names taken from a file
	// Returns a true/false to confirm if the file was loaded successfully and continue with game
	private static boolean randObj(Warehouse wh, String fileName)
	{
		Scanner data;

		// Check for file
		File itemFile = new File(fileName);
		try
		{
			if (!itemFile.canRead())
			{
				System.out.println("~~File not readable: " + fileName);
				return false;
			}
			data = new Scanner(itemFile);
		}
		catch (FileNotFoundException e)
		{  
			System.out.println("~~Cannot find data file");
			return false;
		}
		
		
		// Loop each line in text file for default 50 items
		
		int whSize = wh.getWhSize();
		String itemName;
		int maxItems = 50;
		for(int i = 1; i <= maxItems; i++)
		{  
			// If file contians less lines than required max, end loop and continue with placed items
			try
			{
				itemName = data.nextLine();
			}
			catch(NoSuchElementException e)
			{
				System.out.println("~~No remaining item names in file.");
				break;
			}
			
			// Give the variable a random value for Size (5-15) and Value (1-20) 
			int randSize = 5 + randomNumber(11);
			int randValue = 1 + randomNumber(20);
			
			// Create new Item object and place in random room in warehouse
			Item i1 = new Item(itemName, randSize, randValue);
			Room randRoom = wh.getWHRoom(randomNumber(whSize),randomNumber(whSize));
			randRoom.addItem(i1);
			
		}
		return true;
	}
	
	
	// Random number generator used to for several game elements, item location, player location
	private static int randomNumber(int maxNo)
	{
		int randNo = (int) Math.floor(Math.random() * maxNo);
		return randNo;
	}
		
}