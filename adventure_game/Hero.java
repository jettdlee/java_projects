/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Adventure Game Application
	Hero class 

	Hero class stores all attributes and actions that can be performed by the hero.
	Hero contains the inventory size (default 50) that allows the hero to carry a number of weighted
	items until the set weight. Method allows the hero to pickup/drop items in the current room,
	or traverse the different rooms in the warehouse.

	Created by Jet-Tsyn Lee 04/12/17
	Last updated v0.1 06/12/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

import java.util.*;
import java.io.*;
public class Hero
{
	
	// #######  INSTANCE VARIABLES  #######
	private int inventorySize = 50;		// not final as future changes may increase amount
	private int carrySize = 0;
	private int carryValue = 0;
	LinkedList<Item> inventory = new LinkedList<Item>();
	private int itemCount = 0;
	private int xPos, yPos;		// Hero position
	
	
	// #######  CONSTRUCTOR  #######
	// Set the initial position of hero
	public Hero(int xCol, int yRow)
	{
		xPos = xCol;
		yPos = yRow;	
	}
	
		
	// #######  METHODS  #######
	
	// ~~~~~  PASSING AND CHECKING VALUES  ~~~~~
	// Returns the variable values and checks for the process
	
	// Remain public as future gameplay features may use methods outside of class
	public void addItem(Item itemRef)
	{
		carrySize += itemRef.getSize();
		carryValue += itemRef.getValue();
		
		// Maintain inventory order by finding the position to add the new item
		// Organised by size ascending, if equal, value is compared
		int itemPos = 0;
		for (Item i : inventory)
		{
			if(itemRef.getSize() < i.getSize())
				break;
			else if(itemRef.getSize() == i.getSize())
			{
				if(itemRef.getValue() < i.getValue())
					break;
			}
			else
				itemPos++;
		}
		inventory.add(itemPos, itemRef);
		itemCount++;
	}
	
	public void removeItem(int input)
	{
		Item itemRef = inventory.get(input);
		carrySize -= itemRef.getSize();
		carryValue -= itemRef.getValue();
		inventory.remove(input);
		itemCount--;
	}
	
	public Item getItem(int input)
	{
		return inventory.get(input);	
	}
	
	public int getValue()
	{
		return carryValue;
	}
		
	public int getXPos()
	{
		return xPos;
	}
	public int getYPos()
	{
		return yPos;
	}
	
	
	// ~~~~~  HERO INFORMATION  ~~~~~
	// Returns the heroes information to be shown to user
	
	public void showInventory()
	{
		if (itemCount > 0)
		{
			int count = 1;
			System.out.println("Inventory:");
			for (Item i1 : inventory)
			{
				System.out.println(count + ": " + i1.getName() + " (size " + i1.getSize() + ",value " + i1.getValue() + ")");
				count++;
			}
		}
		else
		{
			System.out.println("~~You have no items in your inventory.");
		}
	}
	
	public void showStatus()
	{
		System.out.println("You are carrying " + itemCount + " items");
		if (itemCount > 0)
		{
			System.out.println("Total inventory value: " + carryValue);
			System.out.println("Current Carry weight: " + carrySize);
		}
		System.out.println("Remaining space: " + (inventorySize-carrySize));
	}
	
	
	
	// ~~~~~  ACTIONS  ~~~~~
	// Contains the actions that can be performed by the hero
	
	public void pickupItem(Room roomRef)
	{
		Scanner in = new Scanner(System.in);
		boolean endPickup = false;
		int input;
		do
		{	
			// Shows items in room (if no item, end action)
			System.out.println();
			roomRef.showItems();
			if (roomRef.getItemCount() <= 0) // End if there are no items
				return;
			
			try{
				// Gets user input to pick up item
				System.out.println();
				System.out.println("What item to pick up (0 to cancel): ");
				input = in.nextInt();
				int itemNo = input - 1;
				
				// Ends if user cancels action (input zero '0')
				if (input == 0)
					endPickup = true;
				
				// Check input within range of room list
				else if (input > 0 && input <= roomRef.getItemCount())
				{
					Item itemRef = roomRef.getItem(itemNo);
					
					// Check hero inventory size
					if (carrySize + itemRef.getSize() < inventorySize)
					{	
						// Add item to hero and remove from room
						addItem(itemRef);
						roomRef.removeItem(itemNo);
					}
					else
					{
						System.out.println("~~inventory limit reached, cannot pickup item.");
					}
				}
				
				// If user inputs value out of bounds (confirmed by function), return error
				else
				{
					System.out.println("~~Input out of bounds, please try again.");
				}
			}
			
			// Catch exception if user inputs a non integer value
			catch (InputMismatchException ime)
			{
				System.out.println("~~Invalid input, please try again.");
			}
			
			String line = in.nextLine(); // Passes next value
		} while (endPickup == false);
			
	}
	
	
	public void dropItem(Room roomRef)
	{
		
		// Show hero inventory and return false if empty
		Scanner in = new Scanner(System.in);
		boolean endDrop = false;
		do
		{
			// Checks if there are items to drop and show inventory
			System.out.println();
			showInventory();
			if (itemCount <= 0)
				return;
			
			try{
				// Get user input
				System.out.println();
				System.out.println("What item to drop up (0 to cancel): ");
				int input = in.nextInt();
				int itemNo = input - 1;
				
				// Ends if user cancels action (input zero '0')
				if (input == 0)
					endDrop = true;
				
				// Check input is within range
				else if (input > 0 && input <= itemCount)
				{
					// Remove item from hero inventory and add to room list
					Item itemRef = getItem(itemNo);
					removeItem(itemNo);
					roomRef.addItem(itemRef);
				}
				
				else
				{
					System.out.println("~~Input out of bounds, please try again.");
				}
				
			}
			
			// Catch exception if user inputs a non integer value
			catch (InputMismatchException ime)
			{
				System.out.println("~~Invalid input, please try again.");
			}
			String line = in.nextLine();
		} while (endDrop == false);
			
	}	
	
	
	// Show the warehouse floorplan if hero has a 'Map' item, also shows item location
	// if hero has a Item Detector.
	public void showMap(Warehouse whRef)
	{
		// Check if map avaliable
		boolean mapCheck = false;
		boolean detectorCheck = false;
		
		// Loop through items in inventory to check for a MAP/ITEM DETECTOR
		for (Item i1 : inventory)
		{
			if (i1.getMap() == true)
				mapCheck = true;
			
			if (i1.getDetector() == true)
				detectorCheck = true;
			
		}
		
		// If player has the item then print map
		if (mapCheck == true)
		{
			// LEGEND
			System.out.println("H - Hero");
			if (detectorCheck == true)
				System.out.println("i - item");
			
			// Print warehouse
			System.out.print(whRef.showWarehouse(detectorCheck));
		}
		else
		{
			System.out.println("~~You do not have a map");
		}
		
	}
	
	
	// Move Actions
	// Move heroes to room by i amount of spaces in a row/col direction
	public void moveRoomRow(int moveAmt)
	{
		yPos += moveAmt;
	}
	public void moveRoomCol(int moveAmt)
	{
		xPos += moveAmt;
	}
	

}




