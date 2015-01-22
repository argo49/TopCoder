/*
 *     

	Problem Statement
	
	Masterbrain is a two player board game in which one player decides on
	a secret combination of digits, while the other must figure it out in
	10 guesses or less. The game differs from Mastermind in that the player
	making the secret combination is allowed to lie once.
	
	The game consists of one player making a sequence of guesses about what 
	the secret combination is, and the other player giving him or her certain 
	information about the quality of the guess. The following is how each guess 
	is analyzed: if a digit is in the correct position then a black peg is given. 
	If a digit is in the guess but in the wrong position then a white peg is given. 
	For all other cases no pegs are given.
	
	For example, if guess = "1234", secret = "2335". Analyzing the guess digit by
	 digit; the '1' is not in secret - no pegs given. The '2' is in secret but not 
	 in the right place - white peg given. The '3' is in secret and in the right
	  place - black peg given. The '4' is not in secret - no pegs given. Result 
	  should be "1b 1w", meaning one black peg and one white peg. Now, if guess 
	  is "2334" and secret is "3224", we have the following: '2' is in secret, 
	  but not in the right place - white peg given. The first '3' is in secret, 
	  but not in the right place - white peg given. Since the '3' in secret has
	   been used, the second '3' in guess should return no pegs. The '4' is in 
	   secret and in the right place - black peg given. Result should be "1b 2w".
	Given a String[] of guesses and a String[] of results for those guesses, 
	return the total number of possible secret combinations, assuming that exactly 
	one of the results is incorrect. Each element of results will be formatted as 
	"<x>b <y>w", where <x> and <y> are the number of black and white pegs respectively.


	None = not in secret
	White = wrong position
	Black = nailed it

 */

package SRM146.DIV1;

public class Masterbrain {
	
	private final int CODE_LENGTH  = 4;
	private final int GUESS_MAX = 7;
	private final int COMBINATIONS = (int) Math.pow(GUESS_MAX, CODE_LENGTH);
	
	public int possibleSecrets (String [] guesses, String [] results) {
		
		int possibleCombos = COMBINATIONS;
		int numOfGuesses   = guesses.length;
		
		int blacks = getBlacks(results[0]);
		int whites = getWhites(results[0]);
				
		if (numOfGuesses > 1) {
			
		}
		
		numOfGuesses--;
		
		return possibleCombos;
	}
	
	private int getWhites (String validation) {
		String [] results = validation.split(" ");
		return Integer.parseInt(results[1].split("w")[0]);
	}
	
	private int getBlacks (String validation) {
		String [] results = validation.split(" ");
		return Integer.parseInt(results[0].split("b")[0]);
	}
}
