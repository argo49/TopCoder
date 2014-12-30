/*
 *  Problem Statement
 *   
	You work for an electric company, and the power goes out in a rather large apartment complex with a lot of irate tenants. You isolate the problem to a 
	network of sewers underneath the complex with a step-up transformer at every junction in the maze of ducts. Before the power can be restored, every 
	transformer must be checked for proper operation and fixed if necessary. To make things worse, the sewer ducts are arranged as a tree with the root of 
	the tree at the entrance to the network of sewers. This means that in order to get from one transformer to the next, there will be a lot of 
	backtracking through the long and claustrophobic ducts because there are no shortcuts between junctions. Furthermore, it's a Sunday; you only have one 
	available technician on duty to search the sewer network for the bad transformers. Your supervisor wants to know how quickly you can get the power back 
	on; he's so impatient that he wants the power back on the moment the technician okays the last transformer, without even waiting for the technician to 
	exit the sewers first.
	You will be given three int[]'s: fromJunction, toJunction, and ductLength that represents each sewer duct. Duct i starts at junction (fromJunction[i]) 
	and leads to junction (toJunction[i]). ductlength[i] represents the amount of minutes it takes for the technician to traverse the duct connecting 
	fromJunction[i] and toJunction[i]. Consider the amount of time it takes for your technician to check/repair the transformer to be instantaneous. Your 
	technician will start at junction 0 which is the root of the sewer system. Your goal is to calculate the minimum number of minutes it will take for 
	your technician to check all of the transformers. You will return an int that represents this minimum number of minutes.
	
	
	Solution: 
	
	The key here is to know that every path must be traveled twice, except the longest path. The man only needs to get to the farthest node, but doesn't 
	need to come all the way back out again. As such the equation that solves this should be (allPaths * 2) - longestPath. This is the total length of 
	all ducts, but since he doesn't need to travel back we can subtract that last journey back out of the sewers. 
	
	
 */

package SRM144.DIV2;

public class PowerOutage {
	public int estimateTimeOut (int[] fromJunction, int[] toJunction, int[] ductLength) {
		
		int total = 0;
		
		for (int time : ductLength) {
			// You have to go there AND back
			total += time * 2;
		}
		
		int maxDepth = 0, depth = 0, temp = 0;
		
		/*
		 * This finds the length back up to the root for each node in the tree and saves
		 * the longest path. Each node in the tree can only have one parent so we don't 
		 * need to worry about duplicates in the toJunction array.
		 */
		for	(int i = 0; i < toJunction.length; i++) {
			depth = 0;
			temp = i;
			while (fromJunction[i] != 0) {
				depth += ductLength[i];
				
				// find the path back up
				for (int j = 0; j < toJunction.length; j++) {
					if (toJunction[j] == fromJunction[i]) {
						i = j;
						j = toJunction.length;
					}
				}
			}
			depth += ductLength[i];
			
			// Restore the counter back to where it was
			i = temp;
			
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		
		return total - maxDepth;
	}
	 
}
