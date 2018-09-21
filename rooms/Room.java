/*	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Room Application
	Create Room class and main 

	Draw maps and buildings where rooms will vary in appearance, and containing items/monsters
	Build multiple 5x5 room, setting symbol string as north or south, and another for e & w
	ask user to input symbols and position of door
	give room object a method that returns string representation of its appearance
	string can be used externally
	rooms must have a door on the east if connected (2 doors)

	Created by Jet-Tsyn Lee 30/10/17
	last updated v0.1 01/11/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
import java.util.Scanner;

public class Room
{
	//#####  OBJECT VARIABLES  #####
	private final int ROOMCOUNT = 2;		//number of rooms
	private final int ROOMSIZE = 5;			//room size NxN
	private String seperator = ";:";		//seperator string, at least 2 different char to differentiate between user inputs
	
	//Symbol and door positions set as strings to be extracted from
	private String vertString = new String();
	private String horzString = new String();
	private String doorString = new String();
	
	
	//#######  MAIN  #######
	public static void main(String[] args)
	{
		Room roomBuild = new Room();
		int rCount = roomBuild.ROOMCOUNT;
		String strSep = roomBuild.seperator;

		
		if (rCount > 0 && roomBuild.ROOMSIZE > 0 && strSep.length() > 1)
		{	
			StringBuffer vertStr = new StringBuffer();
			StringBuffer horzStr = new StringBuffer();
			StringBuffer doorStr = new StringBuffer();		
		
			//Loop for the number of rooms required, for the user to input wall symbols and door position strings
			for (int iRoom = 1; iRoom <= rCount; iRoom++)
			{
				System.out.print("Please enter character for top and bottom of room " + iRoom + ": ");			
				vertStr.append(strSep + roomBuild.wallInput());
				//System.out.println("Vertical Wall: " + vertStr.toString());	//Print inputs for testing
				
				System.out.print("Please enter character for sides of room " + iRoom + ": ");
				horzStr.append(strSep + roomBuild.wallInput());
				//System.out.println("Horizontal Wall: " + horzStr.toString());	//Print inputs for testing
				
				System.out.print("Please enter position of door (NSEW) in room " + iRoom + ":");
				doorStr.append(strSep + roomBuild.doorPost());
				//System.out.println("Door Position: " + doorStr.toString());	//Print inputs for testing
				
				System.out.println();
				
			};
	
			//Store strings to object to be passed to the createRoom method
			roomBuild.vertString = vertStr.toString();
			roomBuild.horzString = horzStr.toString();
			roomBuild.doorString = doorStr.toString();
		
			roomBuild.createRoom(roomBuild);
		}
		else
		{	System.out.println("No rooms can be created as specifications invalid, Ending application.");
		}
	}
	
	
	//#######  METHODS  #######
	
	//Users to input Wall symbols and checks inputs are valid (Symbol only)
	private static String wallInput()
	{	Scanner in = new Scanner(System.in);
		boolean wallCheck;
		String wallChar;
		
		do //Loop until a valid symbol is inputted
		{	wallChar = in.nextLine();
			wallCheck = true;
						
			//if input is over one character, result error
			if (wallChar.length() != 1)
			{	System.out.print("Invalid character limit, Please try again: ");
				wallCheck = false;
			}
			//restrict any characters and only allow symbols
			else if ( wallChar.matches("[^A-Za-z0-9 ]") == false)
			{	System.out.print("Invalid symbol input, Please try again: ");
				wallCheck = false;
			}
				
		} while (wallCheck != true);
		
		return wallChar;
	}
	
	
	//User inputs the Door position which is checked and formatted
	private String doorPost()
	{	Scanner in = new Scanner(System.in);
		boolean doorCheck;
		String upChar;
		do //Loop until a direction is set (NESW)
		{	
			String doorChar = in.nextLine();
			upChar = doorChar.toUpperCase();
			doorCheck = true;
						
			if (!("N".equals(upChar)) && !("E".equals(upChar)) && !("S".equals(upChar)) && !("W".equals(upChar)))
			{	System.out.print("Invalid door input, Please try again: ");
				doorCheck = false;
			};
			
		} while (doorCheck != true);
		
		return upChar;
	}
	

	//Create the string representation of the rooms using the object string variables
	private static void createRoom(Room objRoom)
	{
		//~~~~~  VARIABLES  ~~~~~
		int rCount = objRoom.ROOMCOUNT;
		int rSize = objRoom.ROOMSIZE;
		String strSep = objRoom.seperator;
		String vStr = objRoom.vertString;
		String hStr = objRoom.horzString;
		String dStr = objRoom.doorString;

		StringBuffer nWall = new StringBuffer();
		StringBuffer sWall = new StringBuffer();
		String wallSym = new String();
		String doorPos = new String();
		int lowDoor;
		int upDoor;
		
		//Find door location depending on room scale
		if (rSize % 2 == 0) // if even, use 2 spaces from middle
		{	lowDoor = rSize/2;
			upDoor = lowDoor + 1;
		}
		else
		{	lowDoor = rSize/2 + 1;	//if odd, use middle space, always rounds down
			upDoor = lowDoor;
		};
			
			
		//~~~~~  VERTICAL WALL  ~~~~~
		//Create strings for the North and South Walls to be printed
		for (int iCount = 1; iCount <= rCount; iCount++)	//Loop for the number of rooms
		{	//Get symbol and door positions
			wallSym = objRoom.wordSearch(vStr, strSep, iCount);
			doorPos = objRoom.wordSearch(dStr, strSep, iCount);
			
			//loop for to add the symbols depending on the room scale
			for (int iWall = 1; iWall <= rSize; iWall++)
			{	
				//NORTH
				if (doorPos.equals("N") && (iWall == lowDoor || iWall == upDoor))	//if door is set, then exclude symbol
				{	nWall.append(" ");
				}
				else
				{	nWall.append(wallSym);
				};
				
				//SOUTH
				if (doorPos.equals("S") && (iWall == lowDoor || iWall == upDoor))
				{	sWall.append(" ");	
				}
				else
				{	sWall.append(wallSym);
				};
			};
		};
		System.out.println(nWall.toString());	//Print North wall
		
		
		//~~~~~  HORIZONTAL WALL  ~~~~~
		//Each wall will be created and door positions checked for each room
		String eastDoor = new String();
		String westDoor = new String();
		
		//Start on second wall and end before last as this is created on the north and south walls
		for (int iWall = 2; iWall <= (rSize - 1); iWall++)
		{	StringBuffer hWall = new StringBuffer();
			
			//Loop for the number of rooms
			for (int iCount = 1; iCount <= rCount; iCount++)
			{
				wallSym = objRoom.wordSearch(hStr, strSep, iCount);
				doorPos = objRoom.wordSearch(dStr, strSep, iCount);
				
				//Get door positions for the rooms east and west of the current selected room
				//used to include additional room connection
				eastDoor = "";
				westDoor = "";
				if (!(iCount - 1 < 1)) 
				{	eastDoor = objRoom.wordSearch(dStr, strSep, iCount - 1);
				}
				if (!(iCount + 1 > rCount))
				{	westDoor = objRoom.wordSearch(dStr, strSep, iCount + 1);
				}
				
				//WEST DOOR - if door position west in current room or door position east for the room to the west
				if ((doorPos.equals("W") || eastDoor.equals("E")) && (iWall == lowDoor || iWall == upDoor))
				{	hWall.append(" ");
				}
				else
				{	hWall.append(wallSym);
				};
				
				//SPACE - scaled to room size
				for (int iSpace = 2; iSpace <= (rSize - 1); iSpace++)
				{	hWall.append(" ");
				};				
				
				//EAST DOOR
				if ((doorPos.equals("E") || westDoor.equals("W")) && (iWall == lowDoor || iWall == upDoor))
				{	hWall.append(" ");
				}
				else
				{	hWall.append(wallSym);
				};
					
			};
			System.out.println(hWall.toString());	//Print each horizontal wall
		};
		
		System.out.println(sWall.toString());	//Print South wall
	}

	
	//Identify the room symbol or door position from the string and room number
	private String wordSearch(String strVarb, String strSepr, int cntRoom)
	{
		int sepLength = strSepr.length();
		int inxKey = strVarb.indexOf(strSepr);
		int count = 1;	//Start room 1
		
		while (count < cntRoom)	//Loop through each count until selected Room is found
		{	++count;
			inxKey = strVarb.indexOf(strSepr, inxKey + 1);
		}
		
		//find Character for room and return, assuming only 1 charachter is allowed
		int srtChar = inxKey + sepLength;
		int endChar = srtChar + 1;
		return strVarb.substring(srtChar,endChar);
		
	}
	
	
}