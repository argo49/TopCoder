/* Problem Statement
 * ����
 * Let's say you have a binary string such as the following:
 * 	011100011
 * One way to encrypt this string is to add to each digit the sum of its 
 * adjacent digits. For example, the above string would become:
 * 	123210122
 * In particular, if P is the original string, and Q is the encrypted string, 
 * then Q[i] = P[i-1] + P[i] + P[i+1] for all digit positions i. Characters 
 * off the left and right edges of the string are treated as zeroes.
 * An encrypted string given to you in this format can be decoded as follows (
 * using 123210122 as an example):
 *  1. Assume P[0] = 0.
 *  2. Because Q[0] = P[0] + P[1] = 0 + P[1] = 1, we know that P[1] = 1.
 *  3. Because Q[1] = P[0] + P[1] + P[2] = 0 + 1 + P[2] = 2, we know that P[2] = 1.
 *  4. Because Q[2] = P[1] + P[2] + P[3] = 1 + 1 + P[3] = 3, we know that P[3] = 1.
 *  5. Repeating these steps gives us P[4] = 0, P[5] = 0, P[6] = 0, P[7] = 1, and P[8] = 1.
 *  6. We check our work by noting that Q[8] = P[7] + P[8] = 1 + 1 = 2. Since 
 *     this equation works out, we are finished, and we have recovered one 
 *     possible original string.
 *
 * Now we repeat the process, assuming the opposite about P[0]:
 * 
 * 1. Assume P[0] = 1.
 * 2. Because Q[0] = P[0] + P[1] = 1 + P[1] = 1, we know that P[1] = 0.
 * 3. Because Q[1] = P[0] + P[1] + P[2] = 1 + 0 + P[2] = 2, we know that P[2] = 1.
 * 4. Now note that Q[2] = P[1] + P[2] + P[3] = 0 + 1 + P[3] = 3, which leads us 
 *    to the conclusion that P[3] = 2. However, this violates the fact that each 
 *    character in the original string must be '0' or '1'. Therefore, there 
 *    exists no such original string P where the first digit is '1'.
 *
 * Note that this algorithm produces at most two decodings for any given 
 * encrypted string. There can never be more than one possible way to decode 
 * a string once the first binary digit is set.
 * Given a String message, containing the encrypted string, return a String[] 
 * with exactly two elements. The first element should contain the decrypted 
 * string assuming the first character is '0'; the second element should 
 * assume the first character is '1'. If one of the tests fails, return the 
 * string "NONE" in its place. For the above example, you should return 
 * {"011100011", "NONE"}.
 */
package SRM144.DIV1;

public class BinaryCode {
	public String [] decode (String message) {
		int [] Q = stringToIntArray(message);
		
		// Contains the two possible results
		String [] decodedOptions = new String [2];
		
		decodedOptions[0] = solveEncoding(0, Q);
		decodedOptions[1] = solveEncoding(1, Q);
		
		return decodedOptions;
	}
	
	public String solveEncoding (int start, int [] Q) {
		int [] P = new int [Q.length];
		
		P[0] = start;
		
		for (int i = 0; i < Q.length; i++) {
			// This value should be the sum of P[i - 1] + P[i] + P[i + 1]
			int curr = Q[i];
			int before, at, after;
			
			at = P[i];
			
			// No before element
			if (i == 0) {
				before = 0;
			} else {
				before = P[i - 1];
			}
				
			after = curr - (before + at);
			
			if (after > 1 || after < 0) {
				return "NONE";
			}
			
			// No further elements
			if (i == Q.length - 1) {
				if (after != 0) {
					return "NONE";
				}
			} else {
				P[i + 1] = after;
			}
			
		}
		
		return intArrayToString(P);
		
	}
	
	public String intArrayToString (int [] integers) {
		String result = "";
		
		for (int i = 0; i < integers.length; i++) {
			result += integers[i];
		}
		
		return result;
		
	}
	
	public int[] stringToIntArray (String str) {
		
		String [] letters = str.split("");
		int [] individualInts = new int [letters.length];
		
		for (int i = 0; i < individualInts.length; i++) {
			individualInts[i] = Integer.parseInt(letters[i]);
		}
		
		return individualInts;
		
	}
}
