package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class PiNilakantha {

	private static final RoundingMode roundingMode = RoundingMode.HALF_EVEN;

	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigInteger FOUR = new BigInteger("4");

	private static BigInteger multiplications;
	private static BigDecimal divisions;

	public static final void debug() {
		System.out.println(computePi(new BigInteger("1000000")));
	}

	private static final BigDecimal computePi(final BigInteger iterations) {
		BigDecimal pi = new BigDecimal("3.0");
		Integer decimalPlaces = (int) Math.pow(iterations.toString().length() - 1, 2);
		System.out.println(decimalPlaces);
		boolean plus = true;

		for (BigInteger k = THREE; k.compareTo(iterations) < 0; k = k.add(TWO)) {
			multiplications = (k.subtract(BigInteger.ONE).multiply(k).multiply(k.add(BigInteger.ONE)));
			divisions = new BigDecimal(FOUR).divide(new BigDecimal(multiplications), decimalPlaces, roundingMode);
			if (plus) {
				pi = pi.add(divisions);
			} else {
				pi = pi.subtract(divisions);
			}
			plus = !plus;
		}

		return pi;
	}
}
