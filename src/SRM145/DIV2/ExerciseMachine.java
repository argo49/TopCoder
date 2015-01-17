/*

	Problem Statement
	
	You are writing firmware for an exercise machine. Each second, a routine 
	in your firmware is called which decides whether it should display the 
	percentage of the workout completed. The display does not have any ability 
	to show decimal points, so the routine should only display a percentage if 
	the second it is called results in a whole percentage of the total workout.
	Given a String time representing how long the workout lasts, in the format 
	"hours:minutes:seconds", return the number of times a percentage will be 
	displayed by the routine. The machine should never display 0% or 100%.

 */

package SRM145.DIV2;

public class ExerciseMachine {
	
	public int getPercentages (String time) {

		String [] timeSegments = time.split(":");
		
		// Assuming valid input
		String hours = timeSegments[0];
		String mins  = timeSegments[1];
		String secs  = timeSegments[2];
		
		int timeInSeconds = Integer.parseInt(secs) + (Integer.parseInt(mins) * 60) + (Integer.parseInt(hours) * 3600);
		
		// Need to find shortest interval over which we get a whole number
		int cycles = 1;
		while (cycles < timeInSeconds) {
			
			// If we find that there is a percentage without decimal part, we know 
			// it is a multiple of the whole so when we find it for the first time we
			// know how often it will repeat
			if ((((double)cycles / timeInSeconds) * 100) % 1 == 0) {
				break;
			}
			
			cycles++;
		}
		
		
		// -1 comes from the fact that we don't display 100%
		return (timeInSeconds / cycles) - 1;
	}

}
