package org.dannil.mathalgos.algorithm;

import java.util.LinkedList;
import java.util.List;

import org.dannil.mathalgos.algorithm.helper.NumberHelper;

public class LuhnAlgorithm {

	/***
	 * Calculates the result of performing the Luhn algorithm on a given
	 * integer.
	 * 
	 * @param t
	 *            - The number to calculate.
	 * @return An integer which has been calculated by the algorithm.
	 */
	public static final int calculate(int t) {
		List<Integer> digits = new LinkedList<Integer>();
		List<Integer> result = new LinkedList<Integer>();

		// Step 1: Split the number into it's digits and store them in a list
		digits = NumberHelper.splitIntoDigits(t);

		// Step 2: For every digit, multiply the digits which has even
		// positions with 2 and the digits which has uneven positions with 1
		// Save this new sequence of numbers into a separate list
		for (int x = 0; x < digits.size(); x++) {
			int res = 0;
			if (x % 2 == 0) {
				res = 2 * digits.get(x);
			} else {
				res = digits.get(x);
			}

			// Step 3: Similar to step 1; if we detect that the number is equal
			// to or greater than 10, we split that number into it's digits
			// and remove the detected number from the list. We then insert our
			// new numbers we got after the split into the old numbers position
			if (res >= 10) {
				List<Integer> temp = NumberHelper.splitIntoDigits(res);
				result.addAll(temp);
			} else {
				result.add(res);
			}
		}

		// Step 4: Sum up all the numbers in the list and return the result
		int sumToReturn = 0;
		for (int y = 0; y < result.size(); y++) {
			sumToReturn += result.get(y);
		}
		return sumToReturn;
	}
}
