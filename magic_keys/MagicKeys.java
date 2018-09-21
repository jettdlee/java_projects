/*	MagicKeys Application
	MagicKey class and main (required NumberOracle.java)

	Given a positive integer, magic oracle will declare if the number is is abundant, deficient, or perfect 
	depending on the divisors of the number.
		divisors - number divide to an integer, 10 & 1, 2 & 5 (EXCLUDING the input number itself)
		abundant - sum of all divisors > number input
		deficient - sum of all divisors < number input
		perfect - sum of all divisors = number input
	
	
	Created by Jet-Tsyn Lee 24/10/17
	Version 0.2 last update 26/10/17
*/	



import java.util.Scanner;

public class MagicKeys
{
	
	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);
				
		MagicKeys magKeys = new MagicKeys();
		NumberOracle numOracle = new NumberOracle();
		
		int closeOracle = 0;
		int keyInput = 1;

		
		//Loops program until user inputs a value lower than 'closeOracle' to exit the program
		while (keyInput > closeOracle)
		{
			//Reset div value in object to loop the 
			numOracle.resetOracle();
			
			System.out.print("Please enter your number:");
			keyInput = in.nextInt();

			numOracle.setKey(keyInput);
			
			System.out.println();
			
			//Check if the number if the number is valid
			if (keyInput > closeOracle)
			{	System.out.println("Consulting the Oracle ...");
				System.out.println();
				
				numOracle.checkDivisors(keyInput);
				magKeys.checkKey(numOracle);
			
			}
			else
			{	System.out.println("The Oracle is closing for business now");			
			}
			
			System.out.println();
			
		}
		
	}
	
	
	//#######  COMPARE DIVISOR  #######
	//Check the Divisor sum against the key input and return if the number is Abundant, deficient, or perfect
	
	private final int abundant = 1;
	private final int deficient = 2;
	private final int perfect = 3;
	private final int error = 4;
	private int keyResult;
	private void checkKey(NumberOracle nOracle)
	{
		
		int divVal = nOracle.getDiv();
		int keyVal = nOracle.getKey();
		
		if (divVal > keyVal) 
		{	keyResult = abundant;}
	
		else if (divVal < keyVal)
		{	keyResult = deficient;}
	
		else if (divVal == keyVal)	//perfect number = (2^(n-1))*((2^n) -1)
		{	keyResult = perfect;}
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (nOracle.errorVal == true)
		{	keyResult = error;}
		
		switch (keyResult)
		{	case abundant:
				System.out.println("The number " + keyVal + " is abundant!");
				System.out.println("It has a abundancy of " + (divVal - keyVal) );
				break;
			case deficient:
				System.out.println("The number " + keyVal + " is deficient!");
				System.out.println("It has a deficiency of " + (keyVal - divVal) );
				break;
			case perfect:
				System.out.println("You have achieved perfection with the number " + keyVal + "!!");
				break;
			case error:
				System.out.println("The number " + keyVal + " is abundant!");
				System.out.println("Key has exceeded the limit, cannot provide an answer");
				break;
			default:
				System.out.print("An error has occured, key not valid");
				break;
		}	
	
	}
	
}