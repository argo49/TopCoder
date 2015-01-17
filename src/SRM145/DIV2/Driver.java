package SRM145.DIV2;

import java.util.Arrays;

public class Driver {
	public static void main (String [] args) { 
		ImageDithering id = new ImageDithering();
		
		String [] screen = {"AAAAAAAA",
						 "ABWBWBWA",
						 "AWBWBWBA",
						 "ABWBWBWA",
						 "AWBWBWBA",
						 "AAAAAAAA"};
		System.out.println(id.count("BW", screen));
	}
}


