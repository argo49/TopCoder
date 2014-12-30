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
			print();
			System.out.println("Sale to be made: " + purchases[i]);
			makeSale(purchases[i]);
		}
		
		System.out.println("CUUR:" + currCol);
		// It will have to move eventually after all purchases have been made
		rotateToMostExpensive();
		System.out.println("CUUR:" + currCol);
		
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
		System.out.println("FROM: " + from + " TO: " + to + " LENGTH: " + rows.get(0).length);
		int between = Math.abs(from - to);
		
		// Bad juju. Logic depends on which comes first in the row
		int around  = from < to ? Math.abs(from + (rows.get(0).length - to)) : Math.abs(to + (rows.get(0).length - from));
		
		System.out.println("Min Spins: " + between + " ar: " + around);
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
