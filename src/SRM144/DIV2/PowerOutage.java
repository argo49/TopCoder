package SRM144.DIV2;

public class PowerOutage {
	public int estimateTimeOut (int[] fromJunction, int[] toJunction, int[] ductLength) {
		
		int total = 0;
		
		for (int time : ductLength) {
			// You have to go there AND back
			total += time * 2;
		}
		
		int maxDepth = 0, depth = 0, temp = 0;
		
		for	(int i = 0; i < toJunction.length; i++) {
			depth = 0;
			temp = i;
			while (fromJunction[i] != 0) {
				depth += ductLength[i];
				
				// find the link
				for (int j = 0; j < toJunction.length; j++) {
					if (toJunction[j] == fromJunction[i]) {
						i = j;
						j = toJunction.length;
					}
				}
			}
			depth += ductLength[i];
			
			i = temp;
			
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		return total - maxDepth;
	}
	 
}
