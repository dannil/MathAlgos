package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PiLeibniz {

	private static final int scale = 15;

	private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;

	/**
	 * Approximate pi using the Leibniz formula (optimized version)
	 * 
	 * @return An approximation of pi
	 */
	public static BigDecimal compute(final BigInteger iterations) {
		final BigDecimal TWO = new BigDecimal("2");
		final BigDecimal FOUR = new BigDecimal("4");
		final BigDecimal ITERATIONS = new BigDecimal(iterations);

		BigDecimal EIGHT = new BigDecimal("8");
		EIGHT = EIGHT.setScale(scale, roundingMode);

		BigDecimal aa = new BigDecimal("0");
		aa = aa.setScale(scale, roundingMode);

		BigDecimal bb = new BigDecimal("0");
		aa = aa.setScale(scale, roundingMode);

		BigDecimal cc = new BigDecimal("0");
		aa = aa.setScale(scale, roundingMode);

		BigDecimal pi = new BigDecimal("0");
		pi = pi.setScale(scale, roundingMode);

		for (BigDecimal bi = new BigDecimal("1"); bi.compareTo(ITERATIONS) < 0; bi = bi.add(FOUR)) {
			aa = bi.add(TWO);
			// System.out.println("AA: " + aa);

			bb = bi.multiply(aa);
			// System.out.println("BB: " + bb);

			cc = EIGHT.divide(bb, roundingMode);
			// System.out.println("CC: " + cc);

			pi = pi.add(cc);
			// System.out.println("PI: " + pi);
		}
		return pi;
	}

	public static double pi(int iterations) {
		double pi = 0;
		for (int i = 1; i < iterations; i += 4) {
			double a = i + 2L;

			double b = i * a;

			double c = 8.0 / b;

			pi += c;
		}
		return pi;
	}
}
