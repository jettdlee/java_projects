/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Adventure Game Application
	Warehouse Class 

	Stores the overall structure of the current warehouse and the room objects associated.
	
	Created by Jet-Tsyn Lee 04/12/17
	Last updated v0.1 06/12/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class Warehouse
{
	// #####  INSTANCE VARIABLES  #####
	private int whSize; 		// Size of the warehouse
	private Room whRoom[][];	// Array containing the number of rooms in the warehouse
	
	// #####  WAREHOUSE CONSTRUCTOR  #####
	public Warehouse(int sizeInt)
	{
		// Create the individual room objects in the warehouse
		whSize = sizeInt;
		whRoom = new Room[whSize][whSize];
		for (int yRow = 0; yRow < sizeInt; yRow++)
		{
			for (int xCol = 0; xCol < sizeInt; xCol++)
			{
				whRoom[yRow][xCol] = new Room(yRow,xCol);
			}
		}
		
		// Set random doors in warehouse
		for (Room[] yRowArr : this.whRoom)
		{
			for (Room iRoom : yRowArr)
			{
				if (iRoom.sumDoors() < 2)
				{
					// Get random internal door position
					int setDirect = this.randomDoor(iRoom);
					
					// Install door
					iRoom.setDoor(setDirect);

					// Update neighbouring rooms 
					this.connectRooms(iRoom);
				}
			}
		}
	}


	// #####  METHODS  #####
	public int getWhSize()
	{
		return whSize;
	}
	
	public Room getWHRoom(int row, int col)
	{
		return whRoom[row][col];
	}
		
	
	// Return a integer representing the door position to be placed in the room
	private int randomDoor(Room r1)
	{
		boolean randCheck;
		int doorRand;	
		int yRoomLength = whRoom[r1.getYRow()].length;
		// Loop random values until a valid position is identified in the room
		do
		{
			randCheck = true;
			doorRand = (int) Math.floor(Math.random() * 4);
			
			// Check if random door position is either on a external wall, or already set
			if (doorRand == Room.WEST && 
					(r1.getXCol() == 0 || r1.isThereADoor(Room.WEST) == true))
			{	
				randCheck = false;
			}
			else if (doorRand == Room.EAST && 
						(r1.getXCol() == yRoomLength-1 || r1.isThereADoor(Room.EAST) == true))
			{	
				randCheck = false;
			}	
			else if (doorRand == Room.NORTH && 
						(r1.getYRow() == 0 || r1.isThereADoor(Room.NORTH) == true))
			{	
				randCheck = false;
			}
			else if (doorRand == Room.SOUTH && 
						(r1.getYRow() == yRoomLength-1 || r1.isThereADoor(Room.SOUTH) == true))
			{	
				randCheck = false;
			}
			
		} while ( randCheck == false );
		
		return doorRand;
	}
	

	// Update room of the corresponding position of the door and add the opposing door to connect rooms
	private void connectRooms(Room r1)
	{
		// Get reletive position of current room
		int yRow = r1.getYRow();
		int xCol = r1.getXCol();
		
		// Check neighbouring rooms for all sides and add connecting door, if required
		if (r1.isThereADoor(Room.NORTH) == true && (yRow-1 >= 0))
		{
			whRoom[yRow-1][xCol].setDoor(Room.SOUTH);
		}
		
		if (r1.isThereADoor(Room.EAST) == true && (xCol+1 < whSize))
		{
			whRoom[yRow][xCol+1].setDoor(Room.WEST);
		}
		
		if (r1.isThereADoor(Room.SOUTH) == true && (yRow+1 < whSize))
		{
			whRoom[yRow+1][xCol].setDoor(Room.NORTH);
		}
		
		if (r1.isThereADoor(Room.WEST) ==true && (xCol-1 >= 0))
		{
			whRoom[yRow][xCol-1].setDoor(Room.EAST);
		}
	}
		
	
	// ~~~~~  Warehouse Floorplan  ~~~~~
	// Return the new warehouse draw string with the added room object
	
	public String showWarehouse(boolean hasItemDet)
	{
		String whString = "";
		
		for (Room[] yRowArr : whRoom)
		{
			// Add new lines, \n, for next y row
			for (int iWall = 1; iWall <= Room.ROOMSIZE; iWall++)
			{
				whString += "\n";
			}
			
			// Add each room in current row to floor plan
			for (Room iRoom : yRowArr)
			{
				
				if (hasItemDet == true)
					iRoom.updateItem(true);
				else
					iRoom.updateItem(false);
				
				whString = addRoom(whString, iRoom);
			}
			
		}
		
		return whString;
		
	}
	
	// Return the Full string of warehouse with the added room string
	private static String addRoom(String whString, Room addRoom)
	{
		String tempStr = whString;
		int roomSize = Room.ROOMSIZE;
		int yRoom = addRoom.getYRow();	// Get room row position in WH
		
		// Loop process for all walls
		for (int iWall = 1; iWall <= roomSize; iWall++)
		{
			int vRoomPos = ( yRoom * roomSize ) + iWall;	// Get the row number of '\n'
			int RowPos = rowSearch(tempStr,vRoomPos);
			
			// Split string into two variables to insert new room string
			String iniString = tempStr.substring(0,RowPos);
			String endString = tempStr.substring(RowPos,tempStr.length());
			
			// Get wall string for the current room
			String s1 = addRoom.toString();
			int addPos = rowSearch(s1,iWall);
			String addWall = s1.substring(addPos-Room.ROOMSIZE,addPos);

			// Add string to the Warehouse draw string
			tempStr = iniString + addWall + endString;
		}
		
		return tempStr;
	}
	
	// Find the string position where the new line,\n, is for the method to add room strings
	// depending on the row number
	private static int rowSearch(String whStr, int row)
	{   
		String lineWord = "\n";
		int line = 1;
        int strPos = whStr.indexOf(lineWord);
        while (line < row)
        {   
			strPos = whStr.indexOf(lineWord, strPos + 1);	// Find next occurrence
			line++;
        }

        return strPos; 			// Return as a String
	}
	
}