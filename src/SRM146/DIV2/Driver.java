package SRM146.DIV2;

import SRM146.DIV1.BridgeCrossing;

public class Driver {
	public static void main (String [] args) { 
		YahtzeeScore y = new YahtzeeScore();
		
		int [] toss = {6,4,1,1,3};
		
		System.out.println(y.maxPoints(toss));
		
		BridgeCrossing b = new BridgeCrossing();
		
		int [] times = {1,2,3,4,5};
		
		System.out.println(b.minTime(times));
	}
}

