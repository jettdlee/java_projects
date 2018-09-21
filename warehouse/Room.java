/*	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Warehouse Application
	Room class

	Room class storing all information regarding the current room object used for the Warehouse
	class. The object will also return all the nessessary information of the room using methods
	which is used in the main module.

	Created by Jet-Tsyn Lee 15/11/17
	last updated v0.1 21/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Room
{
	// #####  INSTANCE VARIABLES  #####	
	// Room Scale
	static final int ROOMSIZE = 5;
	private int lowDoor;
	private int upDoor;
	
	// Set the direction as integer variables to refer to values when determining the direction,
	// also related to the door position array
	static final int NORTH = 0;
	static final int EAST = 1;	
	static final int SOUTH = 2;	
	static final int WEST = 3;
	
	// Warehouse symbols
	private static final char VWALL = '-';
	private static final char HWALL = '|';
	private static final char DSYMB = '#';
	
	// Store the door position in the room object
	private boolean doorArr[] = {false, false, false, false};
	// Position of room reletive to the warehouse
	private int xPos;
	private int yPos;
	
	
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
		
		yPos = yRow;
		xPos = xCol;
	}
	
	
	// #####  METHODS  #####
	
	// Given the integer value representing the direction, the door will be installed
	public void setDoor( int doorPos ) 
	{	
		if ( doorPos == NORTH ) 
		{
			doorArr[NORTH] = true;
		}
		else if ( doorPos == EAST )
		{
			doorArr[EAST] = true;
		}
		else if ( doorPos == SOUTH )
		{
			doorArr[SOUTH] = true;
		}	
		else if ( doorPos == WEST )
		{
			doorArr[WEST] = true;
		}
	}

	public int sumDoors()
	{
		int countDoor = 0;
		for (boolean i : doorArr)
		{
			if (i == true)
			{
				countDoor++;
			}
		}
		return countDoor;
	}

	// Show the contents of the current room
	public void showRoom()
	{
		System.out.println();
		System.out.println("You are in a room");
		if (doorArr[NORTH] == true)
			System.out.println("There is a door going north");
		if (doorArr[EAST] == true)
			System.out.println("There is a door going east");
		if (doorArr[SOUTH] == true)
			System.out.println("There is a door going south");
		if (doorArr[WEST] == true)
			System.out.println("There is a door going west");
	}
	
	
	public boolean isThereADoor(int direction)
	{
		return doorArr[direction];
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
	
	
	// Return Room String
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
		for (int i = 1; i <= ROOMSIZE; i++)
		{
			//West Door
			if ( i == 1 )
			{
				if(doorArr[WEST] == true)
					output += DSYMB;
				else
					output += HWALL;
			}
			
			//Space
			if (!(i == 1 || i == ROOMSIZE))
				output += " ";
			
			// East Door
			if ( i == ROOMSIZE)
			{
				if(doorArr[EAST] == true)
					output += DSYMB;
				else
					output += HWALL;
			}
		}
		
		return output + "\n";
	}

}

