package SRM144.DIV2;

public class Driver {
	public static void main (String [] args) { 
		Time t = new Time();
		System.out.println(t.whatTime(0));
		System.out.println(t.whatTime(3661));
		System.out.println(t.whatTime(5436));
		System.out.println(t.whatTime(86399));
		
		PowerOutage po = new PowerOutage();
		
		int [] from  = {0, 0, 13, 9, 3, 4, 5, 1, 5, 3, 2, 0, 0, 0, 4, 13, 0};
		int [] to    = {9, 4, 15, 11, 16, 14, 7, 6, 12, 13, 10, 2, 1, 5, 8, 17, 3};
		int [] ducts = {1046279, 1311183, 429331, 1140774, 1468021, 49084, 791438, 304381, 1675432, 1353849, 889525, 391384, 1018989, 170090, 53233, 996444, 194951};
		/*
		int [] from  = {0,  0,  0,   1,  4, 4, 6, 7,   7, 7,  20};
		int [] to    = {1,  3,  4,   2,  5, 6, 7, 20,  9, 10, 31};
		int [] ducts = {10, 10, 100, 10, 5, 1, 1, 100, 1, 1,  5};
		*/
		System.out.println(po.estimateTimeOut(from, to, ducts));
	}
}
