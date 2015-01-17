/*
 * 
 * 
 *  Problem Statement
    
	Note that in the following problem statement, all quotes and angle brackets are for clarity
	A certain vending machine delves out its goods from a rotating cylinder, which can rotate 
	around in both clockwise and counter-clockwise directions. The cylinder has a number of 
	shelves on it, and each shelf is divided into a number of columns. On the front of the 
	machine, there is a panel of doors that extends the entire height of the column. There is one 
	door for each shelf, which is the width of one column. When a purchase is made, the user uses 
	two buttons to rotate the cylinder so their purchase is located at a door. They make their 
	purchase by sliding the appropriate door open, and removing the item (there can only be one 
	item per column on a particular shelf). The cylinder can rotate in a complete circle, and so 
	there are always two ways to get from a particular column to another column.
	Because the vending machine company wants to sell the most expensive items possible, and the 
	machine can only show one column at a time, the machine will always try to put forth the most 
	expensive column available. The price of a column is calculated by adding up all the prices 
	of the remaining items in that column. The most expensive column is defined to be the one 
	with the maximum price. If 5 minutes have elapsed since the last purchase was made, the 
	machine rotates the cylinder to the most expensive column. If, however, another purchase has 
	been made before the 5 minutes are up, the rotation does not occur, and the 5 minute timer is 
	reset.
	Recently, some machines' rotating motors have been failing early, and the company wants to 
	see if it is because the machines rotate to show their expensive column too often. To 
	determine this, they have hired you to simulate purchases and see how long the motor is 
	running.
	You will be given the prices of all the items in the vending machine in a String[]. Each 
	element of prices will be a single-space separated list of integers, which are the prices (in 
	cents) of the items. The Nth integer in the Mth element of prices represents the price of the 
	Nth column in the Mth shelf in the cylinder. You will also be given a String[] purchases. 
	Each element in purchases will be in the format: "<shelf>,<column>:<time>" <shelf> is a 0-
	based integer which identifies the shelf that the item was purchased from. <column> is a 0-
	based integer which identifies the column the item was purchased from. <time> is an integer 
	which represents the time, in minutes, since the machine was turned on.
	In the simulation, the motor needs to run for 1 second in order to rotate to an adjacent 
	column. When the machine is turned on, column 0 is facing out, and it immediately rotates to 
	the most expensive column, even if the first purchase is at time 0. The machine also rotates 
	to the most expensive column at the end of the simulation, after the last purchase. Note that 
	when an item is purchased, its price is no longer used in calculating the price of the column 
	it is in. When the machine rotates to the most expensive column, or when a user rotates the 
	cylinder, the rotation is in the direction which takes the least amount of time. For example, 
	in a 4-column cylinder, if column 0 is displayed, and the cylinder is rotated to column 3, it 
	can be rotated backwards, which takes 1 second, versus rotating forwards which takes 3 
	seconds.
	If a user tries to purchase an item that was already purchased, this is an incorrect 
	simulation, and your method should return -1. Otherwise, your method should return how long 
	the motor was running, in seconds.
 */

package SRM145.DIV1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VendingMachine {
	
	private int timeSpentSpinning = 0;
	private int timeElapsed       = 0;
	private int currCol           = 0;
	private ArrayList<Integer> columnPrices = new ArrayList<Integer>();
	private ArrayList<Integer[]> rows;
	
	
	public int motorUse (String [] prices, String [] purchases) {
		// prices: each string represents one row, columns are denoted by spaces in each string
		
		rows = new ArrayList<Integer[]>(prices.length);
		
		// Transform strings to rows arraylist 
		for (int i = 0; i < prices.length; i++) {
			// Get the number of prices in one row
			String [] splitRow = prices[i].split(" ");
			rows.add(new Integer [splitRow.length]);
			
			// Add them to the rows arraylist
			for (int j = 0; j < splitRow.length; j++) {
				rows.get(i)
				[j] = Integer.parseInt(splitRow[j]);
			}
		}
		
		columnPrices = updateColumnPrices();
		
		// Very first thing it does
		rotateToMostExpensive();
		
		for (int i = 0; i < purchases.length; i++) {
			makeSale(purchases[i]);
		}
		
		// It will have to move eventually after all purchases have been made
		rotateToMostExpensive();
		
		if (hasDoubleSales()) {
			timeSpentSpinning = -1;
		}
		
		return timeSpentSpinning;
	}
	
	public boolean hasDoubleSales () {
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < rows.get(i).length; j++) {
				if (rows.get(i)[j] == -1) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void makeSale (String sale) {
		int row, col, time;
		
		row  = Integer.parseInt(sale.split(",")[0]);
		col  = Integer.parseInt(sale.split(",")[1].split(":")[0]);
		time = Integer.parseInt(sale.split(",")[1].split(":")[1]);
		
		if (time - timeElapsed >= 5) {
			rotateToMostExpensive();
		}
		timeElapsed += time - timeElapsed;
		
		spinTo(col);
		
		purchaseItem(row, col);
		
	}
	
	public void purchaseItem (int row, int col) {
		int updateVal = rows.get(row)[col] == 0 ? -1 : 0;
		rows.get(row)[col] = updateVal;
		updateColumnPrices();
	}
	
	public void spinTo (int to) {
		timeSpentSpinning += getMinSpins(currCol, to);
		currCol = to;
	}
	
	public void rotateToMostExpensive () {
		updateColumnPrices();
		int toCol = columnPrices.indexOf(Collections.max(columnPrices));
		spinTo(toCol);
	}
	
	public int getMinSpins (int from, int to) {
		int between = Math.abs(from - to);
		
		// Bad juju. Logic depends on which comes first in the row
		int around  = from < to ? Math.abs(from + (rows.get(0).length - to)) : Math.abs(to + (rows.get(0).length - from));
		
		return between > around ? around : between;
	}

	public ArrayList<Integer> updateColumnPrices () {
		int numOfColumns = rows.get(0).length;
		columnPrices = new ArrayList<Integer>();

		int colPrice;
		for (int i = 0; i < numOfColumns; i++) {
			colPrice = 0;
			for (int j = 0; j < rows.size(); j++) {
				colPrice += rows.get(j)[i];
			}
			columnPrices.add(colPrice);
		}
		
		return columnPrices;
		
	}
	
	public int getColumnPrice (int col) {
		return 0;
	}
	
	public void print() {
		System.out.print("\nCurrent Column: " + currCol);
		for (int i = 0; i < rows.size(); i++) {
			System.out.println("");
			for (int j = 0; j < rows.get(i).length; j++) {
				System.out.print(rows.get(i)[j] + ", ");
			}
		}
		System.out.println("Total Spins: " + timeSpentSpinning);
	}
}
