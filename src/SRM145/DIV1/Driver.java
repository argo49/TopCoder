package SRM145.DIV1;

import java.util.Arrays;

public class Driver {
	public static void main (String [] args) { 
		Bonuses b = new Bonuses();
		
		// int [] pnts  = {1,2,3,4,5}; => Returns: { 6,  13,  20,  27,  34 }
		// int [] pnts  = {5,5,5,5,5,5}; => Returns: { 17,  17,  17,  17,  16,  16 }
		int [] pnts  = {485, 324, 263, 143, 470, 292, 304, 188, 100, 254, 296,
				 255, 360, 231, 311, 275,  93, 463, 115, 366, 197, 470};
		int [] bnuse = b.getDivision(pnts);
		System.out.println(Arrays.toString(bnuse));
		
		VendingMachine vm = new VendingMachine();
		/* 4
		String [] prices    = {"100 100 100"};
		String [] purchases = {"0,0:0", "0,2:5", "0,1:10"};
		*/
		/* 11
		String [] prices    = {"100 200 300 400 500 600"};
		String [] purchases = {"0,2:0", "0,3:4", "0,1:8", "0,4:12"};
		*/
		// 6
		String [] prices    = {"100 200 300", "600 500 400"};
		String [] purchases = {"0,0:0", "1,1:10", "1,2:20", "0,1:21", "1,0:22", "0,2:35"};
		
		System.out.println(vm.motorUse(prices, purchases));
		
	}
}

