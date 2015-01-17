/*
 *  Problem Statement
 *  
 *  A hiker has set out to conquer a hill. The trail guide for the hill lists information known 
	about the hill. First, it lists how tall the hill is, and how far it is to the other side of the 
	hill. Next, it gives a list of landmarks that will be encountered while hiking the hill. The 
	only things that are known about these landmarks are their height, and the order in which they 
	appear along the trail. Finally, on this hill, there are three types of terrain:
	     _____
	    /:   :\
	   / :   : \
	  /  :   :  \
	 / 1 : 2 : 3 \
	  * 
	Type 1: rising terrain. In this type of terrain, the elevation of the hill rises one meter 
			vertically for every meter that is traveled horizontally.
	Type 2: level terrain. In this type of terrain, the elevation of the hill remains constant.
	Type 3: falling terrain. This terrain's elevation falls one meter vertically for every meter 
			that is traveled horizontally.
			
	All three types of terrain can last for only multiples of one horizontal meter.
	You will be given an int maxHeight (the maximum height of the hill, assuming the hill starts and 
	ends at height 0), an int distance (how far horizontally one must travel to hike over the top 
	and reach the bottom on the other side), and a int[] landmarks, which contains the heights of 
	all the landmarks present on the trail. The order of the elements in landmarks is the order in 
	which they will be encountered on the trail. All landmarks must be at least one horizontal meter 
	apart.
	Given all of this information, you must return how many different valid paths that the hiker 
	could be facing. A path on the hill is a sequence consisting only of the three types of terrain 
	for the entire distance of the hill. Two paths are different if and only if for at least one 
	horizontal meter, the terrain type of one path is not the same as the terrain type of another 
	path. A path is valid if and only if the path: 1. Starts at height 0 and ends at height 0 2. Has 
	no other locations with height 0, or height below 0 (otherwise it would be two hills, or a 
	valley) 3. Has at least one point where the height is equal to maxHeight (otherwise, the hill 
	would be smaller) 4. Does not go above maxHeight (otherwise, it would be taller) 5. Is laid out 
	such that the landmarks could be placed in the order given at points on the trail. Note that two 
	landmarks cannot appear at the same point on the trail, even if they are at the same height. 
	they must be at least 1 meter apart
 */

package SRM145.DIV1;

public class HillHike {
	
	public long numPaths (int distance, int maxHeight, int [] landmarks) {
		
		// Number of possible landmarks at each level
		int [] levels = new int [maxHeight];
		
		// The number of different ways to place type 2 extras 
		double perms = choose((distance - maxHeight), (distance - (2 * maxHeight)));
		
		// Need to have enough distance to go up and down
		if (maxHeight * 2 > distance || landmarks.length == 0) {
			perms = 0;
		}
		
		System.out.println(new Mountain(distance, maxHeight));
		
		return (int)perms;
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
	
	class Mountain {
		int [][] grid;
		int maxHeight;
		int distance;
		
		public Mountain (int distance, int maxHeight) {
			this.distance = distance;
			this.maxHeight = maxHeight;
			grid = new int [maxHeight][distance];
			
			// Mountain needs to start and end by going up
			grid[maxHeight-1][0] = 1;
			grid[maxHeight-1][distance-1] = 3;
			
			perm();
		}
		
		public void perm () {
			
		}
		
		public String toString () {
			StringBuilder bob = new StringBuilder();
			
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					int type = grid[i][j];
					if (type == 1) {
						bob.append("/");
					} else if (type == 2) {
						bob.append("_");
					} else if (type == 3) {
						bob.append("\\");
					} else {
						bob.append(" ");
					}
				}
				bob.append("\n");
			}
			
			return bob.toString();
		}
		
	}
}
