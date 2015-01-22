package SRM146.DIV1;

import java.util.ArrayList;
import java.util.Collections;

public class BridgeCrossing {
	public int minTime (int [] times) {
		
		ArrayList<Integer> leftSide  = new ArrayList<Integer>();
		ArrayList<Integer> rightSide = new ArrayList<Integer>();
		
		boolean flashlightOnLeft = true;
		
		// Init left side array
		for (int i = 0; i < times.length; i++) {
			leftSide.add(times[i]);
		}
		
		int time = 0;
		
		while (leftSide.size() > 0) {
			
			if (flashlightOnLeft) {
				
				// things get done in pairs, either 2mins or 2maxes
				Integer extreme1, extreme2;
				
				if (rightSide.size() == 0) {
					// Send a runner to the other side (two minimums)
					
					extreme1 = removeMin(leftSide);
					rightSide.add(extreme1);
					if (leftSide.size() > 0) {
						extreme2 = removeMin(leftSide);
						rightSide.add(extreme2);
						time += extreme1 > extreme2 ? extreme1 : extreme2;
					} else {
						time += extreme1;
					}
					
				} else {
					// Send maximums over
					
					extreme1 = removeMax(leftSide);
					rightSide.add(extreme1);
					time += extreme1;
					
					if (leftSide.size() > 0)
						rightSide.add(removeMax(leftSide));
					
				}
				
				flashlightOnLeft = false;
			} else {
				// Send smallest person back
				int backtrack = removeMin(rightSide);
				leftSide.add(backtrack);
				time += backtrack;
				flashlightOnLeft = true;
			}
			
		}	
		
		return time;
	}
	
	public int removeMin (ArrayList<Integer> a) {
		return a.remove(a.indexOf(Collections.min(a)));
	}
	
	public int removeMax (ArrayList<Integer> a) {
		return a.remove(a.indexOf(Collections.max(a)));
	}
}
