package org.dannil.mathalgos.algorithm.helper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class IntegerHelper {

	/***
	 * Splits a number into its digits.
	 * 
	 * @param t
	 *            - The number to split
	 *            
	 * @return The digits of the splitted number
	 */
	public static List<Integer> splitIntoDigits(int t) {
		// System.out.println("split");
		List<Integer> temp = new LinkedList<Integer>();
		int number = t;
		while (number > 0) {
			// System.out.println(number);
			temp.add(number % 10);
			number = number / 10;
		}
		Collections.reverse(temp);
		return temp;
	}

}
