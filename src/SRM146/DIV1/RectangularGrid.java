/*
 * 

	Problem Statement
	
	Given the width and height of a rectangular grid, return the total number 
	of rectangles (NOT counting squares) that can be found on this grid.
	For example, width = 3, height = 3 (see diagram below):
	 __ __ __
	|__|__|__|
	|__|__|__|
	|__|__|__|
	
	In this grid, there are 4 2x3 rectangles, 6 1x3 rectangles and 12 1x2 rectangles. 
	Thus there is a total of 4 + 6 + 12 = 22 rectangles. 
	Note we don't count 1x1, 2x2 and 3x3 rectangles because they are squares.
	
 */

package SRM146.DIV1;

public class RectangularGrid {
	public long countRectangles (int width, int height) {
		
		long rectangles = 0;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i != j) {
					
					int rectWidth  = i + 1;
					int rectHeight = j + 1;
					
					int rectanglesHorizontally = width - rectWidth + 1;
					int rectanglesVertically   = height - rectHeight + 1;
					
					rectangles += rectanglesHorizontally * rectanglesVertically;
					
				}
			}
		}
		
		return rectangles;
	}
}
