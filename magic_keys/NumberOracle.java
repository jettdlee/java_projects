/*	MagicKeys Application
	NumberOracle class

	Number Oracle will store the key values given from the magic key class,
	calculate the Divisor sums, to return stored values back to the main class for check
	
	Created by Jet-Tsyn Lee 24/10/17
	Version 0.2 last update 26/10/17
*/	


public class NumberOracle
{	
	int keyStore;
	int divTotal = 0;
	boolean errorVal = false;
	
	//Set key variable
	public void setKey(int keyVal)
	{	keyStore = keyVal;
	}
	
	//Reset Oracle variable for the process to loop in a new instance
	public void resetOracle()
	{	divTotal = 0;
		errorVal = false;
	}
	
	//identify the divisors of a value by looping througn all numbers from 0 to n-1(excluding itself).
	//divisor is identified if modulus of the key = 0, if true, add loop value to sum
	public void checkDivisors(int keyVal)
	{	for (int iKey = 1 ; iKey < keyStore; iKey++)
		{	if (keyVal % iKey == 0)
			{	
				//System.out.println("Div: " + iKey);	//print proper divisors (testing)
				divTotal = divTotal + iKey;
				
				//if divisor reaches max integer limit, sum resets to negative
				//set as true which will trigger the error message in the check method
				if ( divTotal < 0 )
				{	errorVal = true;
				}	
				
			}
		}
	}
	
	//Return Values
	public int getKey()
	{	return keyStore;
	}
	public int getDiv()
	{	return divTotal;
	}	
	
}