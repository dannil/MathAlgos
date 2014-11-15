package org.dannil.mathalgos.algorithm.helper;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberHelper {

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

	/**
	 * Calculates the square root of the supplied BigInteger.
	 * 
	 * @param x 
	 * 				  - The value to be calculated
	 * 
	 * @return The square root of value
	 */
	public static BigInteger sqrt(BigInteger x) {
		BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
		BigInteger div2 = div;
		// Loop until we hit the same value twice in a row, or wind
		// up alternating.
		for (;;) {
			BigInteger y = div.add(x.divide(div)).shiftRight(1);
			if (y.equals(div) || y.equals(div2))
				return y;
			div2 = div;
			div = y;
		}
	}

}
