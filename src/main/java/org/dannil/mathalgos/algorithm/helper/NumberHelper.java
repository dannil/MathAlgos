package org.dannil.mathalgos.algorithm.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberHelper {

	/***
	 * Splits a number into its digits.
	 * 
	 * @param t
	 *            - The number to split.
	 * @return The digits of the splitted number.
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
	 * Calculates the square root of the supplied BigDecimal.
	 * 
	 * @param value 
	 * 				  - The value to be calculated
	 * @return The square root of value
	 */
	public static BigDecimal sqrt(BigDecimal value) {
		BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
		return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}

	public static BigInteger bigIntSqRootFloor(BigInteger x) throws IllegalArgumentException {
		if (x.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Negative argument.");
		}
		// square roots of 0 and 1 are trivial and
		// y == 0 will cause a divide-by-zero exception
		if (x == BigInteger.ZERO || x == BigInteger.ONE) {
			return x;
		} // end if
		BigInteger two = BigInteger.valueOf(2L);
		BigInteger y;
		// starting with y = x / 2 avoids magnitude issues with x squared
		for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y)).add(y)).divide(two))
			;
		return y;
	} // end bigIntSqRootFloor

	public static BigInteger bigIntSqRootCeil(BigInteger x) throws IllegalArgumentException {
		if (x.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Negative argument.");
		}
		// square roots of 0 and 1 are trivial and
		// y == 0 will cause a divide-by-zero exception
		if (x == BigInteger.ZERO || x == BigInteger.ONE) {
			return x;
		} // end if
		BigInteger two = BigInteger.valueOf(2L);
		BigInteger y;
		// starting with y = x / 2 avoids magnitude issues with x squared
		for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y)).add(y)).divide(two))
			;
		if (x.compareTo(y.multiply(y)) == 0) {
			return y;
		} else {
			return y.add(BigInteger.ONE);
		}
	} // end bigIntSqRootCeil

}
