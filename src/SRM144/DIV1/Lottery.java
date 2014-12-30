/* Problem Statement
 * 
 * In most states, gamblers can choose from a wide variety of different lottery games. The rules of a lottery are defined by two integers (choices and 
 * blanks) and two boolean variables (sorted and unique). choices represents the highest valid number that you may use on your lottery ticket. (All 
 * integers between 1 and choices, inclusive, are valid and can appear on your ticket.) blanks represents the number of spots on your ticket where 
 * numbers can be written.
 *
 * The sorted and unique variables indicate restrictions on the tickets you can create. If sorted is set to true, then the numbers on your ticket must 
 * be written in non-descending order. If sorted is set to false, then the numbers may be written in any order. Likewise, if unique is set to true,
 * then each number you write on your ticket must be distinct. If unique is set to false, then repeats are allowed.
 * 
 * Here are some example lottery tickets, where choices = 15 and blanks = 4:
 * 
 *   {3, 7, 12, 14} -- this ticket is unconditionally valid.
 *   {13, 4, 1, 9} -- because the numbers are not in nondescending order, this ticket is valid only if sorted = false.
 *   {8, 8, 8, 15} -- because there are repeated numbers, this ticket is valid only if unique = false.
 *   {11, 6, 2, 6} -- this ticket is valid only if sorted = false and unique = false.
 *   
 * Given a list of lotteries and their corresponding rules, 
 * return a list of lottery names sorted by how easy they are 
 * to win. The probability that you will win a lottery is 
 * equal to (1 / (number of valid lottery tickets for that 
 * game)). The easiest lottery to win should appear at the 
 * front of the list. Ties should be broken alphabetically (see example 1).
 */

package SRM144.DIV1;

public class Lottery {
	public String [] SortByOdds (String [] lotteries) {
		double [] odds = new double [lotteries.length];
		
		for (int i = 0; i < lotteries.length; i++) {
			odds[i] = calculateOdds(lotteries[i]);
		}
		
		return new String [1];
	}
	
	public double calculateOdds (String game) {
		Game g = new Game(game);
		
		
		
		return choose(g.getChoices(), g.getBlanks());
		
	}
	
	public double choose (int top, int bottom) {
		double total = 1;
		double bottomd = (double)bottom;
		
		for (int i = 0; i < bottom; i++) {
			total *= (top/bottomd);
			--bottomd;
			--top;
		}
				
		return total;
	}
	
	class Game {
		boolean sorted, unique;
		int choices, blanks;
		double odds;
		
		public Game (String gameOptions) {
			String [] gameRules = gameOptions.split(" ");
			
			choices = Integer.parseInt(gameRules[1]);
			blanks  = Integer.parseInt(gameRules[2]);
			sorted  = gameRules[3] == "T" ? true : false;
			unique  = gameRules[4] == "T" ? true : false;
			
			setOdds();
		}
		
		public void setOdds () {
			double rawChance = choose(choices, blanks);
			
			
			
		}
		
		public boolean shouldBeSorted() {
			return sorted;
		}

		public void setSorted(boolean sorted) {
			this.sorted = sorted;
		}

		public boolean shouldBeUnique() {
			return unique;
		}

		public void setUnique(boolean unique) {
			this.unique = unique;
		}

		public int getChoices() {
			return choices;
		}

		public void setChoices(int choices) {
			this.choices = choices;
		}

		public int getBlanks() {
			return blanks;
		}

		public void setBlanks(int blanks) {
			this.blanks = blanks;
		}
	}
}
