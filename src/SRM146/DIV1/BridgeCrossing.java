package SRM146.DIV1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BridgeCrossing {
		
	public int minTime (int [] times) {
		
		if (times.length == 1)
			return times[0];
			
		ArrayList<Integer> leftSide  = arraylistize(times);
		ArrayList<Integer> rightSide = new ArrayList<Integer>(times.length);
		
		int time = 0;
		
		print(leftSide, rightSide);
		
		int min1 = removeMin(leftSide);
		int min2 = removeMin(leftSide);
		
		rightSide.add(min1);
		rightSide.add(min2);
		
		time += Math.max(min1, min2);
		
		int extreme1, extreme2;
		boolean torchOnLeft = false;
		
		while (leftSide.size() > 0) {
			
			print(leftSide, rightSide);
			
			if (torchOnLeft) {
							
				if (leftSide.indexOf(min1) > -1 && leftSide.indexOf(min2) > -1 && leftSide.size() < 5) {
					extreme1 = removeMin(leftSide);
					extreme2 = removeMin(leftSide);
				} else if (leftSide.indexOf(min1) > -1 && leftSide.indexOf(min2) > -1) {
					extreme1 = removeMin(leftSide);
					extreme2 = removeMax(leftSide);
				} else {
					extreme1 = removeMax(leftSide);
					extreme2 = removeMax(leftSide);
				}
				
				rightSide.add(extreme1);
				rightSide.add(extreme2);
				
				time += Math.max(extreme1, extreme2);
				
			} else {
				int min = removeMin(rightSide);
				time += min;
				leftSide.add(min);
			}
			
			torchOnLeft = !torchOnLeft;
		}
		
		return time;
	}
	
	public void print (ArrayList<Integer> a, ArrayList<Integer> b) {
		
		StringBuilder bob = new StringBuilder();
		
		for (int i = 0; i < a.size(); i++) {
			bob.append(a.get(i) + " ");
		}
		
		bob.append(" --> ");
		
		for (int i = 0; i < b.size(); i++) {
			bob.append(b.get(i) + " ");
		}
		
		System.out.println(bob);
	}
	
	public ArrayList<Integer> arraylistize (int [] a) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			al.add(a[i]);
		}
		return al;
	}
	
	public int removeMin (ArrayList<Integer> a) {
		return a.remove(a.indexOf(Collections.min(a)));
	}
	
	public int removeMax (ArrayList<Integer> a) {
		return a.remove(a.indexOf(Collections.max(a)));
	}
}
