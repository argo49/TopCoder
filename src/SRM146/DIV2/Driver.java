package SRM146.DIV2;

import SRM146.DIV1.BridgeCrossing;

public class Driver {
	public static void main (String [] args) { 
		YahtzeeScore y = new YahtzeeScore();
		
		int [] toss = {6,4,1,1,3};
		
		System.out.println(y.maxPoints(toss));
		
		BridgeCrossing b = new BridgeCrossing();
		
		// int [] times = {44, 63, 30, 1, 9, 53}; // 154
		// int [] times = {1,2,5,10}; // 17
		
		int [] times = {99, 13, 67, 32, 5, 17}; // 202
		
		
		System.out.println("Minumum Time: " + b.minTime(times));
		
	}
}

