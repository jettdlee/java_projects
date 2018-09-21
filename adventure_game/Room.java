/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Adventure Game Application
	Room Class
	
	Stores all infomation regeerding the individual room and the internal contents (item, hero etc.).
	Methods set to create the initial room, storing all the door positions, and the contents and 
	describes to the game.

	Created by Jet-Tsyn Lee 04/12/17
	Last updated v0.1 06/12/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

import java.util.*;
import java.io.*;

public class Room
{
	// #####  INSTANCE VARIABLES  #####	
	// Room Scale
	static final int ROOMSIZE = 5;
	private int lowDoor, upDoor;
	
	// Set the direction as integer variables to refer to values when determining the direction,
	// also related to the door position array
	static final int NORTH = 0;
	static final int EAST = 1;	
	static final int SOUTH = 2;	
	static final int WEST = 3;
	
	// Warehouse symbols
	private static final char VWALL = '-';
	private static final char HWALL = '|';
	private static final char DSYMB = ' ';
	
	// Store the door position in the room object
	private boolean doorArr[] = {false, false, false, false};
	// Position of room reletive to the warehouse
	private int xPos, yPos;
	
	private LinkedList<Item> itemList = new LinkedList<Item> ();
	private int itemCount = 0;
	private boolean isHeroInRoom = false;
	private boolean heroHasItemDet = false;
		
	
	// #####  CONTRUCTOR  #####
	public Room(int yRow, int xCol)
	{	
		// Set the spaces where the door symbol will be positioned
		if (ROOMSIZE % 2 == 0) // if even, use 2 spaces from middle
		{	
			lowDoor = ROOMSIZE/2;
			upDoor = lowDoor + 1;
		}
		else
		{	
			lowDoor = ROOMSIZE/2 + 1;	//if odd, use middle space, always rounds down
			upDoor = lowDoor;
		};
		
		// Room position relative to the warehouse
		yPos = yRow;
		xPos = xCol;
	}
	
	
	// #####  METHODS  #####
		
	// ~~~~~~~  ITEMS  ~~~~~~~
	public void addItem(Item itemRef)
	{
		itemList.addLast(itemRef);
		itemCount++;
	}
	
	public void removeItem(int itemNo)
	{
		itemList.remove(itemNo);
		itemCount--;
	}
	
	public Item getItem(int itemNo)
	{
		return itemList.get(itemNo);	
	}
	
	public int getItemCount()
	{
		return itemCount;
	}
	
	// Update the active contents in room (hero)
	public void updateRoom(boolean heroIn)
	{
		isHeroInRoom = heroIn;	
	}

	// Checks if items can bee seen by hero
	public void updateItem(boolean hasItemDet)
	{
		heroHasItemDet = hasItemDet;
	}
	
	public void showItems()
	{
		// Checks if there is an item in the room
		
		if (itemCount > 0)
		{
			int count = 1;
			System.out.println("Here are the items you can pick up:");
			for (Item i1 : itemList)
			{
				System.out.println(count + ": " + i1.getName() + " (size " + i1.getSize() + ",value " + i1.getValue() + ")");
				count++;
			}
		}
		else
		{
			System.out.println("~~There are no items in the room.");
		}

	}
	
	
	
	
	// Show the contents of the current room
	public void showRoom()
	{
		System.out.println();
		// +1 due to zero array
		System.out.println("You are in a room (" + (xPos + 1) + ", " + (yPos + 1) + ")");
		if (doorArr[NORTH] == true)
			System.out.println("There is a door going north");
		if (doorArr[EAST] == true)
			System.out.println("There is a door going east");
		if (doorArr[SOUTH] == true)
			System.out.println("There is a door going south");
		if (doorArr[WEST] == true)
			System.out.println("There is a door going west");
		
		
		if (itemCount > 0)
		{
			System.out.println("There are items in the room:");
			for (Item i1 : itemList)
			{
				System.out.println("\t" + i1.getName() + " (size " + i1.getSize() + ",value " + i1.getValue() + ")");
			}
		}
		else
		{
			System.out.println("There no items in the room.");
		}
		
	}
		
		
	// Return Room position in the warehouse
	public int getXCol()
	{
		return xPos;
	}
	
	public int getYRow()
	{
		return yPos;
	}	
	
	
		
	
	
	// Given the integer value representing the direction, the door will be installed
	public void setDoor(int doorPos) 
	{	
		doorArr[doorPos] = true;
	}

	public int sumDoors()
	{
		int countDoor = 0;
		for (boolean i : doorArr)
		{
			if (i)
			{
				countDoor++;
			}
		}
		return countDoor;
	}

	
	public boolean isThereADoor(int direction)
	{
		return doorArr[direction];
	}

	
		
	
	// ~~~~~~~  ROOM DRAW  ~~~~~~~
	
	public String toString()
	{
		String output = "";
		
		for (int iRow = 1; iRow <= ROOMSIZE; iRow++)
		{
			if (iRow == 1)
				output += createVWalls(NORTH);
			else if (iRow == lowDoor || iRow == upDoor)
				output += createEWWalls();
			else if (iRow == ROOMSIZE)
				output += createVWalls(SOUTH);
			else
				output += createHWalls();
		}

		return output;
	}
	
	// Construct the room string to be used in the toString method, depends on room scale
	private static String createHWalls()	// Misc walls '|   |'
	{
		String output = "";
		for (int i=1; i <= ROOMSIZE; i++)
		{
			if ( i == 1 || i == ROOMSIZE)
				output += HWALL;
			else
				output += " ";
		}
		return output + "\n";
		
	}
	
	private String createVWalls(int doorPos)	// Vertical wall, for north/south
	{
		String output = "";
		for (int i=1; i <= ROOMSIZE; i++)
		{
			if (doorArr[doorPos] == true && (i == lowDoor || i == upDoor))
				output += DSYMB;
			else
				output += VWALL;
		}
		return output + "\n";
	}
	
	private String createEWWalls()	// Horizontal wall, for east/west
	{
		String output = "";
		
		//West Door
		if(doorArr[WEST] == true)
			output += DSYMB;
		else
			output += HWALL;
	
		
		for (int i = 2; i < ROOMSIZE; i++)
		{			
			//Space
			if (!(i == 1 || i == ROOMSIZE || i == lowDoor || i == upDoor))
				output += " ";
			
			
			// Add hero position
			if (i == lowDoor || i == upDoor)
			{	
				if (isHeroInRoom == true)
					output += "H";
				else if (heroHasItemDet == true && itemCount > 0)
					output += "i";
				else
					output += " ";
			}
		}
		
		// East Door
		if(doorArr[EAST] == true)
			output += DSYMB;
		else
			output += HWALL;

		return output + "\n";
	}

}

