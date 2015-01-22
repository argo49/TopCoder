package SRM146.DIV1;

public class Driver {
	public static void main (String [] args) { 
		RectangularGrid r = new RectangularGrid();
		
		System.out.println(r.countRectangles(592, 964));
		
		Masterbrain m = new Masterbrain();
		
		String [] guesses = {"1575"};
		String [] results = {"4b 0w"};
		
		System.out.println(m.possibleSecrets(guesses, results));
	}
}
