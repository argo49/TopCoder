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
		
		// Get mins
		int globalMin       = leftSide.remove(leftSide.indexOf(Collections.min(leftSide)));
		
		int globalSecondMin = -1;
		if (leftSide.size() > 1)
			globalSecondMin = leftSide.get(leftSide.indexOf(Collections.min(leftSide)));
		
		leftSide.add(globalMin);
		
		while (leftSide.size() > 0) {
			
			System.out.println("\n");
			
			for (int i = 0; i < leftSide.size(); i++) {
				System.out.print(leftSide.get(i));
			}
			
			System.out.println("\n|");
			System.out.println("v");
			
			for (int i = 0; i < rightSide.size(); i++) {
				System.out.print(rightSide.get(i));
			}
			
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
					if (leftSide.indexOf(globalMin) > -1 && globalSecondMin > 0 && leftSide.indexOf(globalSecondMin) > -1) {
						extreme1 = removeMin(leftSide);
					} else {
						extreme1 = removeMax(leftSide);
					}
					
					rightSide.add(extreme1);
					if (leftSide.size() > 0) {
						extreme2 = removeMax(leftSide);
						rightSide.add(extreme2);
						time += extreme1 > extreme2 ? extreme1 : extreme2; 
					} else {
						time += extreme1;
					}
					
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
