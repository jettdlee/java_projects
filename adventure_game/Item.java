/*	
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	Adventure Game Application
	Item Class
	
	Item class stores the attributes of the single item and any unique features of the item
	that allows unique actions to be performed.
	
	Created by Jet-Tsyn Lee 04/12/17
	Last updated v0.1 06/12/17
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
import java.util.*;
public class Item
{
	// #######  INSTANCE VARIABLES  #######
	private String name;
	private int size;
	private int value;
	
	// Unique item boolean to enable special actions
	private boolean isMap = false;
	private boolean isDetector = false;


	// #######  CONSTRUCTOR  #######
	public Item(String itemName, int sizeInt, int valInt)
	{
		name = itemName;
		size = sizeInt;
		value = valInt;
		
		//System.out.println("Item name: " + name + ", Item Size: " + size + ", Item Value: " + value); // Debug and testing
		
		// Confirms item type if name matches
		if (itemName.equals("Map"))
			isMap = true;
		else if (itemName.equals("Item Detector"))
			isDetector = true;
	}
	
	
	// #######  METHODS  #######
	public String getName()
	{
		return name;
	}
	
	public int getSize()
	{
		return size;
	}
	public int getValue()
	{
		return value;
	}
	
	public boolean getMap()
	{
		return isMap;
	}
	public boolean getDetector()
	{
		return isDetector;
	}
	
}