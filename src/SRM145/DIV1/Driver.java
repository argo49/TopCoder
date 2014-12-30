package SRM145.DIV1;

public class Driver {
	public static void main (String [] args) { 
		Bonuses b = new Bonuses();
		
		// int [] pnts  = {1,2,3,4,5}; => Returns: { 6,  13,  20,  27,  34 }
		// int [] pnts  = {5,5,5,5,5,5}; => Returns: { 17,  17,  17,  17,  16,  16 }
		int [] pnts  = {485, 324, 263, 143, 470, 292, 304, 188, 100, 254, 296,
				 255, 360, 231, 311, 275,  93, 463, 115, 366, 197, 470};
		int [] bnuse = b.getDivision(pnts);
		
		System.out.println("Result: ");
		for (int i = 0; i < bnuse.length; i++) {
			System.out.print(bnuse[i] + ", ");
		}
	}
}

