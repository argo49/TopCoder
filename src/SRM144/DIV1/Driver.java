package SRM144.DIV1;

public class Driver {
	public static void main (String [] args) {
		BinaryCode bc = new BinaryCode();
		
		System.out.println(bc.decode("123210122")[1]);
		
		Lottery l = new Lottery();
		
		l.calculateOdds("Lotto649: 20 2 T T");
	}
}
