/* GoldCube volume and price calculator
*	created by Jet-Tsyn Lee 27/09/17
*	Version 0.2 02/10/17
*/

import java.util.Scanner;

public class Treasurer
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		
		//CUBE 1~~~~~~~~~
		System.out.print("Please input cube 1 length (CC):");
		double LengthValue1 = in.nextDouble();
		System.out.print("Please input cube 1 price (per CC):");
		double PriceValue1 = in.nextDouble();

		//sets value as 0 if user has inputted a negative value
		if (LengthValue1 < 0 ) 
			LengthValue1 = 0;
		if (PriceValue1 < 0) 
			PriceValue1 = 0;

		GoldCube Cube1 = new GoldCube(LengthValue1, PriceValue1);
		
		
		//CUBE 2~~~~~~~~~
		System.out.print("Please input cube 2 length (CC):");
		double LengthValue2 = in.nextDouble();
		System.out.print("Please input cube 2 price (per CC):");
		double PriceValue2 = in.nextDouble();

		if (LengthValue2 < 0 ) 
			LengthValue2 = 0;
		if (PriceValue2 < 0) 
			PriceValue2 = 0;

		GoldCube Cube2 = new GoldCube(LengthValue2, PriceValue2);
		
		
		//CUBE 3~~~~~~~~~
		System.out.print("Please input cube 3 length (CC):");
		double LengthValue3 = in.nextDouble();
		System.out.print("Please input cube 3 price (per CC):");
		double PriceValue3 = in.nextDouble();

		if (LengthValue3 < 0 ) 
			LengthValue3 = 0;
		if (PriceValue3 < 0) 
			PriceValue3 = 0;
		
		GoldCube Cube3 = new GoldCube(LengthValue3, PriceValue3);
		
			
		//MESSAGE~~~~~~~~~
		System.out.println("Cube 1 Volume: " + Cube1.calculateVolume());
		System.out.println("Cube 1 Price: " + Cube1.calculatePrice());
		System.out.println("Cube 2 Volume: " + Cube2.calculateVolume());
		System.out.println("Cube 2 Price: " + Cube2.calculatePrice());
		System.out.println("Cube 3 Volume: " + Cube3.calculateVolume());
		System.out.println("Cube 3 Price: " + Cube3.calculatePrice());
	}
}	
		
		
		