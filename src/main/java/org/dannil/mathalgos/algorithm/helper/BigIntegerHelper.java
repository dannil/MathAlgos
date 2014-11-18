package org.dannil.mathalgos.algorithm.helper;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberHelper {

	private static final BigInteger MINUS_ONE = new BigInteger("-1");
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger FOUR = new BigInteger("4");

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
	 * 			- The value to be calculated
	 * 
	 * @return The square root of x
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

	public static BigInteger taylorSqrt(BigInteger x) {
		final BigInteger MINUS_ONE = new BigInteger("-1");

		BigInteger result = new BigInteger("0");
		BigInteger n = x.subtract(BigInteger.ONE);

		for (BigInteger k = new BigInteger("1"); k.compareTo(n) < 0; k = k.add(BigInteger.ONE)) {
			System.out.println("loop");
			BigInteger top = MINUS_ONE.pow(k.intValue()).multiply(factorial(k.multiply(TWO)));
			BigInteger bottom_left = factorial(FOUR.pow(k.intValue()).multiply(k)).pow(2);
			BigInteger bottom_right = BigInteger.ONE.subtract(k.multiply(TWO));
			BigInteger bottom = bottom_left.multiply(bottom_right);
			System.out.println("top: " + top);
			System.out.println("bottom: " + bottom);
			result = result.add(top.divide(bottom).multiply(n.pow(k.intValue())));
		}
		return result;
	}

	/**
	 * Calculates the 10 logarithm for the supplied BigInteger.
	 * 
	 * @param x
	 * 			- The value to be calculated
	 * 
	 * @return The logarithm for x
	 */
	public static BigInteger log10(final BigInteger x) {
		return new BigInteger(Integer.toString(x.toString().length() - 1));
	}

	public static BigInteger factorial(final BigInteger x) {
		if (x.equals(BigInteger.ZERO))
			return new BigInteger("1");

		BigInteger i = x.subtract(BigInteger.ONE);
		BigInteger n = x;
		while (i.compareTo(BigInteger.ZERO) > 0) {
			n = n.multiply(i);
			i = i.subtract(BigInteger.ONE);
		}
		return n;
	}

}
