/*	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Warehouse Application
	Warehouse Class and main 

	Create square warehouse (n x n), asking the user to input the size of the warehouse.
	Initailly starting with no doors, give each room a random door, and connecting the
	opposing room with a door to connect the two rooms. Door selection is restricted to:
		- Door can only be selected if there are less than 2 doors in the room
		- Cannot be placed in the external wall of the warehouse
		- Cannot overwrite a door already in the position
	Connecting doors can still be placed in room with 2+ doors.
			
	After doors are placed, the full warehouse will be drawn representing all rooms and
	the connecting doors. User is then asked for the starting room position, where the
	room contents will be described.	

	Created by Jet-Tsyn Lee 15/11/17
	last updated v0.1 21/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class Warehouse
{
	// #####  INSTANCE VARIABLES  #####
	private int whSize; 		// Size of the warehouse
	private Room whRoom[][];	// Array containing the number of rooms in the warehouse
	private String whDraw = "";	// Warehouse Floor Plan

	
	// #####  WAREHOUSE CONSTRUCTOR  #####
	public Warehouse(int sizeInt)
	{
		whSize = sizeInt;
		whRoom = new Room[whSize][whSize];
	}

	
	// #####  MAIN  #####
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		// Ask user to input the size of the warehouse (n x n)
		boolean roomCheck;
		int roomCount = 0;
		
		// Repeat input request until user input a valid input
		do
		{
			System.out.print("Please enter the size of the warehouse (n x n): ");
			roomCheck = true;
			try
			{
				roomCount = in.nextInt();
				
				// Check input, we restrict size >1 as we are not allowed to add doors 
				// to external walls which will cause an inf loop when identifying door position.
				if (roomCount < 2)
				{
					System.out.println("Invalid warehouse size, please try again.");
					roomCheck = false;
				}
				
			}
			catch (InputMismatchException e)
			{
				System.out.println("Invalid warehouse input, please try again.");
				String line = in.nextLine(); // Passes incorrect input
				roomCheck = false;
			}
			
		} while (roomCheck == false);
		
		
		// Create initial warehouse and room objects
		Warehouse wh1 = new Warehouse(roomCount);
		for (int yRow = 0; yRow < wh1.whSize; yRow++)
		{
			for (int xCol = 0; xCol < wh1.whSize; xCol++)
			{
				wh1.whRoom[yRow][xCol] = new Room(yRow,xCol);
			}
		}
		
		
		// Add  random doors to all rooms in warehouse, if door count in room is less than 2
		for (Room[] yRowArr : wh1.whRoom)
		{
			for (Room iRoom : yRowArr)
			{
				if (iRoom.sumDoors() < 2)
				{
					// Get random internal door position
					int setDirect = wh1.randomDoor(iRoom);
					
					// Install door
					iRoom.setDoor(setDirect);

					// Update neighbouring rooms 
					wh1.connectRooms(iRoom);
				}
			}
		}
		
		
		// Set rooms in warehouse to a string and print
		for (Room[] yRowArr : wh1.whRoom)
		{
			// Add new lines, \n, for next y row
			for (int iWall = 1; iWall <= Room.ROOMSIZE; iWall++)
			{
				wh1.whDraw += "\n";
			}
			
			// Add each room in current row to floor plan
			for (Room iRoom : yRowArr)
			{
				wh1.whDraw = addRoom(wh1.whDraw, iRoom);
			}
		}
		System.out.println(wh1.whDraw);
				
				
		// Ask user to enter starting position and return the room details
		// Loop if user input is incorrect
		boolean err;
		do
		{
			err = true;
			try{
				System.out.print("Enter starting position (row, col): ");
				int colPos = in.nextInt();
				int rowPos = in.nextInt();
				
				// Return room description, -1 on position due to zero value array vs user input
				wh1.whRoom[colPos-1][rowPos-1].showRoom();
			}
			
			// Catch exception if user inputs a non integer value
			catch (InputMismatchException ime)
			{
				System.out.println("Invalid position input, please try again.");
				String line = in.nextLine();
				err = false;
			}
			
			// Catch exception if user inputs values over/under the warehouse size
			catch (ArrayIndexOutOfBoundsException aioobe)
			{
				System.out.println("Starting position out of bounds, please try again.");
				String line = in.nextLine();
				err = false;
			}
		} while (err == false);

	}

	
	// #####  METHODS  #####
	
	
	/*
		~~~~~  Random Door  ~~~~~
		Randomly select the door in a room, returning a integer value representing the door direction.
		Door selection is restricted by:
	 	Cannot select door if placed on a external wall of the warehouse
	 	Cannot select door if already exists in room
	*/
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
			if (doorRand == Room.WEST && (r1.getXCol() == 0 || 
						r1.isThereADoor(Room.WEST) == true))
			{	
				randCheck = false;
			}
			else if (doorRand == Room.EAST && (r1.getXCol() == yRoomLength-1 || 
						r1.isThereADoor(Room.EAST) == true))
			{	
				randCheck = false;
			}	
			else if (doorRand == Room.NORTH && (r1.getYRow() == 0 || 
						r1.isThereADoor(Room.NORTH) == true))
			{	
				randCheck = false;
			}
			else if(doorRand == Room.SOUTH && (r1.getYRow() == yRoomLength-1 ||  
						r1.isThereADoor(Room.SOUTH) == true))
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
	
	
	/*
		~~~~~  Warehouse Floorplan  ~~~~~
		Return the new warehouse draw string with the added room object
	*/
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


