package SRM146.DIV2;

public class Driver {
	public static void main (String [] args) { 
		YahtzeeScore y = new YahtzeeScore();
		
		int [] toss = {6,4,1,1,3};
		
		System.out.println(y.maxPoints(toss));
	}
}

