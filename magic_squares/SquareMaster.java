/*	Magic Square Application
*	SquareMaster class
*
*	The SquareMaster (class), read the values inputted and sort the keys to acending order a/b/c respectivly.
*	the 3 keys will be checked against the following conditions:
*	0 < a < b < (c – a) 
*	b /= 2a
*	If these conditions hold, the SquareMaster will conjure up a Magic Square with each row column and diagonal equal the same with difference cell combinations.
*
*	Created by Jet-Tsyn Lee 17/10/2017
*	Version 0.1, last updated 19/10/2017
*/

import java.util.Scanner;
public class SquareMaster
{	
	int keyA;
	int keyB;
	int keyC;
	
	//#######  SQUARE MASTER CONSTRUCTOR  #######
	public SquareMaster(int key1, int key2, int key3)
	{
		//Keys A/B/C are allocated values based on the key sort method below
		keyA = sortKeyA(key1, key2, key3);
		keyB = sortKeyB(key1, key2, key3);
		keyC = sortKeyC(key1, key2, key3);
	
	}

	
	
	//#######  SORT KEYS  ########
	//the 3 user key inputs will be sorted and allocated to the key values A/B/C respectivly, depending on the value
	
	//Key A - Lowest value key
	private int retIntA;	
	public int sortKeyA(int keyI, int keyJ, int keyK)
	{	//Check that the selected key value is minimum to allocate to key A
		if ((keyI < keyJ) && (keyI < keyK))
		{	retIntA = keyI;}
		else if ((keyJ < keyI) && (keyJ < keyK))
		{	retIntA = keyJ;}
		else if ((keyK < keyI) && (keyK < keyJ))
		{	retIntA = keyK;}
		else
		{	retIntA = equalCheck(keyI, keyJ, keyK);	//if two of the keys have equal value, run method below to allocate value to key A
		}
		
		return retIntA;
	}
	
	//Key B - Middle value key
	private int retIntB;	
	public int sortKeyB(int keyI, int keyJ, int keyK)
	{	//Check that the selected key value is between the two other keys
		if (((keyI > keyJ) && (keyI < keyK)) || ((keyI < keyJ) && (keyI > keyK)))
		{	retIntB = keyI;}
		else if (((keyJ > keyI) && (keyJ < keyK)) || ((keyJ < keyI) && (keyJ > keyK)))
		{	retIntB = keyJ;}
		else if (((keyK > keyI) && (keyK < keyJ)) || ((keyK < keyI && keyK > keyJ)))
		{	retIntB = keyK;}
		else
		{	retIntB = equalCheck(keyI, keyJ, keyK);}
		
		return retIntB;
	}
	
	//Key C - Highest value key
	private int retIntC;	
	public int sortKeyC(int keyI, int keyJ, int keyK)
	{	//Check that the selected key value is maximum to allocate to key C
		if ((keyI > keyJ) && (keyI > keyK))
		{	retIntC = keyI;}
		else if ((keyJ > keyI) && (keyJ > keyK))
		{	retIntC = keyJ;}
		else if ((keyK > keyI) && (keyK > keyJ))
		{	retIntC = keyK;}
		else
		{	retIntC = equalCheck(keyI, keyJ, keyK);
		}
		return retIntC;
	}
		
	
	//checks keys values if they are equal, and returns to code to be set for order
	private int retInt;
	public int equalCheck(int keyI, int keyJ, int keyK)
	{
		if (keyI == keyJ || keyI == keyK)
			retInt = keyI;
		else if (keyJ == keyI || keyJ == keyK)
			retInt = keyJ;
		else if (keyK == keyI || keyK == keyJ)
			retInt = keyK;
		
		return retInt;
	}
	
	
	
	//#######  CHECK CONDITIONS  #######
	/*	the keys will be checked against the following conditions:
	*	0 < a < b < (c – a) 
	*	b /= 2a
	*	if conditions do not match, then application will end
	*/
	
	public void valueCheck(int keyI, int keyJ, int keyK)
	{	//		0<A 			A<B					B<(C-A) 					b!=2A
		if( (!(0 < keyI) ||  !(keyI < keyJ)  || !(keyJ < (keyK - keyI)) ) || keyJ == (2 * keyI) )
		{	System.out.println("The Keys are not appropriate, Begone!");
			System.exit(0);
		};
		
	}
	
	
	//Magic Square will be given if keys match the condition above
	/*Square follows:	c – b, c + (a + b), c – a
						c – (a – b), c, c + (a – b)
						c + a, c – (a + b), c + b
	*/
	public void squareInputs(int keyI, int keyJ, int keyK)
	{
		System.out.println("The Keys are appropriate. Below is your Magic Square. Use it wisely!");
		System.out.println( (keyK - keyJ) + "\t" + (keyK + (keyI + keyJ)) + "\t" + (keyK - keyI) );
		System.out.println( (keyK - (keyI - keyJ)) + "\t" + (keyK) + "\t" + (keyK + (keyI - keyJ)) );
		System.out.println( (keyK + keyI) + "\t" + (keyK - (keyI + keyJ)) + "\t" + (keyK + keyJ) );
	
	}

	
	
}