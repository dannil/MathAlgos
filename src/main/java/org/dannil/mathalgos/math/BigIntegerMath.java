package org.dannil.mathalgos.math;

import java.math.BigInteger;

public class BigIntegerMath {

	private static final BigInteger MINUS_ONE = new BigInteger("-1");
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger FOUR = new BigInteger("4");
	private static final BigInteger SIXTEEN = new BigInteger("16");

	/**
	 * Calculates the square root of the supplied BigInteger.
	 * 
	 * @param x 
	 * 			- The value to be calculated
	 * 
	 * @return The square root of x
	 */
	public static final BigInteger sqrt(BigInteger x) {
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

	public static final BigInteger taylorSqrt(BigInteger x) {
		BigInteger result = new BigInteger("0");
		BigInteger n = x.subtract(BigInteger.ONE);

		for (BigInteger k = new BigInteger("1"); k.compareTo(x) < 0; k = k.add(BigInteger.ONE)) {
			System.out.println("factorial: " + factorial(k));
			System.out.println("loop");
			BigInteger top = MINUS_ONE.pow(k.intValue()).multiply(factorial(k.multiply(TWO)));
			BigInteger bottom = SIXTEEN.pow(k.intValue()).multiply(factorial(k).pow(2));
			System.out.println("top: " + top);
			System.out.println("bottom: " + bottom);
			System.out.println("top/bottom: " + (top.divide(bottom)));
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
	public static final BigInteger log10(final BigInteger x) {
		return new BigInteger(Integer.toString(x.toString().length() - 1));
	}

	public static final BigInteger factorial(final BigInteger x) {
		if (x.equals(BigInteger.ZERO)) {
			return BigInteger.ONE;
		}

		BigInteger n = x;
		for (BigInteger i = x.subtract(BigInteger.ONE); i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
			n = n.multiply(i);
		}
		return n;
	}

}
