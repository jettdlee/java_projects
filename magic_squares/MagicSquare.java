/*	Magic Square Application
*	Magic Square class (required SquareMaster.java)
*
*	Entry will be granted when supplied a Magic Square – 3X3 square matrix of distinct integers, and the sum of values in each row,column and diagonal is the same. 
*	The Square must be different on each visit.
*	The SquareMaster (class), will generate a Magic Square for you when given suitable Magic Keys – a set of (distinct) integers satisfying certain arcane properties.
*	SquareMaster class to produce 3x3 Magic Squares to take three integers (read from the keyboard) and sort into ascending order (a, b, c), respectivly. 
*	It should then check the following conditions:
*		0 < a < b < (c – a) 
*		b /= 2a
*	If these conditions hold, the SquareMaster will conjure up a Magic Square for you 
*
*	Created by Jet-Tsyn Lee 17/10/2017
*	Version 0.1, last updated 19/10/2017
*/

import java.util.Scanner;

public class MagicSquare
{
	//Main Application using the SquareMaster.java class to generate the Keys a/b/c, and create square is applicable.
	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);
		
		//User input 3 integers for the key values
		System.out.println("Please enter 3 magic keys:");
		int key1 = in.nextInt();
		int key2 = in.nextInt();
		int key3 = in.nextInt();
	
	
		SquareMaster sqrMaster = new SquareMaster(key1, key2, key3);
		
		//System.out.println("keys given: " + key1 + " " + key2 + " " + key3);
		//Key values are sorted in SquareMaster obejct and printed
		System.out.println();
		System.out.println("This is the SquareMaster!");
		System.out.println("You have given me the following Keys: " + sqrMaster.keyA + " " + sqrMaster.keyB + " " + sqrMaster.keyC);
		System.out.println();
		
		//Values are checked against condition and calculated for square cube (methods in SquareMaster class)
		sqrMaster.valueCheck(sqrMaster.keyA, sqrMaster.keyB, sqrMaster.keyC);
		sqrMaster.squareInputs(sqrMaster.keyA, sqrMaster.keyB, sqrMaster.keyC);
		
			
	}
		
}





