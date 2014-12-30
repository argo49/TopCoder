/*
 *  Problem Statement
    
	You have a certain amount of money to give out as a bonus to 
	employees. The trouble is, who do you pick to receive what 
	bonus? You decide to assign a number of points to each 
	employee, which corresponds to how much they helped the 
	company in the last year. You are given an int[] points, where 
	each element contains the points earned by the corresponding 
	employee (i.e. points[0] is the number of points awarded to 
	employee 0). Using this, you are to calculate the bonuses as 
	follows:
	- First, add up all the points, this is the pool of total 
	points awarded. 
	- Each employee gets a percentage of the bonus 
	money, equal to the percentage of the point pool that the 
	employee got. 
	- Employees can only receive whole percentage 
	amounts, so if an employee's cut of the bonus has a fractional 
	percentage, truncate it. 
	- There may be some percentage of 
	bonus left over (because of the fractional truncation). If n% 
	of the bonus is left over, give the top n employees 1% of the 
	bonus. There will be no more bonus left after this. If two or 
	more employees with the same number of points qualify for this 
	"extra" bonus, but not enough bonus is left to give them all 
	an extra 1%, give it to the employees that come first in 
	points.
	The return value should be a int[], one element per employee 
	in the order they were passed in. Each element should be the 
	percent of the bonus that the employee gets.
	
	
 */

package SRM145.DIV1;

import java.util.Arrays;

public class Bonuses {
	public int[] getDivision(int[] points) {
		int totalPoints = 0, totalBonus = 0, percentageLeftOver = 0;
		int [] bonus = new int [points.length];
		int [] sorted = Arrays.copyOf(points, points.length);
		
		Arrays.sort(sorted);
		
		// Get the total points
		for (int i = 0; i < points.length; i++) {
			totalPoints += points[i];
		}
		
		// Calculate the percentage for each
		for (int i = 0; i < points.length; i++) {
			bonus[i] = (int)Math.floor(((double)points[i] / totalPoints) * 100);
		}
		
		// Get the total bonus dished out
		for (int i = 0; i < bonus.length; i++) {
			totalBonus += bonus[i];
		}
		
		percentageLeftOver = 100 - totalBonus;
		
		// Distribute the leftovers to the best
		for (int i = 0; i < percentageLeftOver; i++) {
			for (int j = 0; j < points.length; j++) {
				if (points[j] == sorted[(sorted.length - 1 - i)]) {
					bonus[j]++;
					
					// This destroys the original data but is needed to
					// differentiate between those that have gotten the
					// extra 1% bonus and those who haven't
					points[j]++;
					
					j = points.length;
				}
			}
		}
		
		return bonus;
		
	}
}
