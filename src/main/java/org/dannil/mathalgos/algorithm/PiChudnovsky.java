package org.dannil.mathalgos.algorithm;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.helper.NumberHelper;

/**
 * <p>Class that implements the Chudnovsky brothers solution of computing pi
 * to a specified amount of decimal places</p>
 * 
 * </p>Credits to Nick Craig-Wood for showing a Python implementation which I used
 * as a basis for this class (see http://www.craig-wood.com/nick/articles/pi-chudnovsky/)</p>
 * 
 * @author Daniel Nilsson
 *
 */
public class PiChudnovsky {

	/* Magic numbers used for making the calculation work */
	private static final BigInteger MINUS_ONE = new BigInteger("-1");
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger FIVE = new BigInteger("5");
	private static final BigInteger SIX = new BigInteger("6");
	private static final BigInteger TWENTYFOUR = new BigInteger("24");
	private static final BigInteger TENTHOUSHAND = new BigInteger("10005");
	private static final BigInteger SIXHUNDREDFOURTYTHOUSAND = new BigInteger("640320");
	private static final BigInteger FOURHUNDREDTHOUSAND = new BigInteger("426880");
	private static final BigInteger THIRTEENMILLION = new BigInteger("13591409");
	private static final BigInteger FIVEHUNDREDFOURTYFIVEMILLION = new BigInteger("545140134");
	private static final BigInteger SIXHUNDREDFOURTYTHOUSAND_RAISED_3_OVER_24 = SIXHUNDREDFOURTYTHOUSAND.pow(3).divide(TWENTYFOUR);

	private static BigInteger Pab;
	private static BigInteger Qab;
	private static BigInteger Tab;

	/**
	 * <p>Computes pi to the specified amount of decimals and returns it
	 * as a string with correct delimiter. Note that this method transforms 
	 * pi into a string and performs operations on it, so this method isn't suited
	 * for benchmarking purposes solely to measure the time to calculate pi.</p><p>If you need
	 * this method for benchmarking, please use the underlying computePi(BigInteger) method
	 * and transform the result into a string after the benchmark.<p>
	 * 
	 * @param decimals 
	 * 					- The amount of decimals to calculate
	 * @return A string presentation of a BigInteger containing pi truncated to the amount
	 * 		   of supplied decimals
	 */
	public static final String computePiStringPresentation(final BigInteger decimals) {
		return new StringBuffer(computePi(decimals).toString()).insert(1, ".").toString();
	}

	/**
	 * <p>Computes Pi to the specified amount of decimals.</p>
	 * 
	 * @param decimals 
	 * 					- The amount of decimals to calculate
	 * @return A BigInteger containing truncated pi
	 */
	public static final BigInteger computePi(final BigInteger decimals) {
		final BigInteger DIGITS_PER_TERM = NumberHelper.log10(SIXHUNDREDFOURTYTHOUSAND_RAISED_3_OVER_24.divide(SIX).divide(TWO).divide(SIX));

		final BigInteger N = decimals.divide(DIGITS_PER_TERM).add(BigInteger.ONE);

		final BigInteger[] array = calculateTerms(BigInteger.ZERO, N);

		final BigInteger Q = array[1];
		// System.out.println("Q: " + Q);
		final BigInteger T = array[2];
		// System.out.println("T: " + T);

		// THE THREE LINES BELOW CONTRIBUTE TO THE MAJOR RUNNING TIME
		// (ESPECIALLY LINE 2); THIS NEEDS TO BE OPTIMIZED TO REDUCE THE RUNNING
		// TIME SIGNIFICANTLY
		final BigInteger ONE_SQUARED = BigInteger.TEN.pow(2 * decimals.intValue());
		// System.out.println("ONE_SQUARED: " + ONE_SQUARED);

		final BigInteger SQRT_C = NumberHelper.sqrt(TENTHOUSHAND.multiply(ONE_SQUARED));
		// System.out.println("SQRT_C: " + SQRT_C);

		return Q.multiply(FOURHUNDREDTHOUSAND).multiply(SQRT_C).divide(T);
	}

	/**
	 * <p>Computes the terms for binary splitting the Chudnovsky infinite series.</p>
	 *
	 * - a(a) = +/- (13591409 + 545140134*a)<br>
	 * - p(a) = (6*a-5)*(2*a-1)*(6*a-1)<br>
	 * - b(a) = 1<br>
	 * - q(a) = SIXHUNDREDFOURTYTHOUSAND_RAISED_3_OVER_24*a*a*a<br>
	 * <br>
	 *
	 * @param a
	 *            - Start value (default BigInteger.ZERO)
	 * @param b
	 *            - End value (the number of decimals to calculate)
	 * @return P(a,b), Q(a,b) and T(a,b)
	 */
	private static final BigInteger[] calculateTerms(final BigInteger a, final BigInteger b) {
		if (b.subtract(a).equals(BigInteger.ONE)) {
			// Directly compute P(a,a+1), Q(a,a+1) and T(a,a+1)
			if (a.equals(BigInteger.ZERO)) {
				Pab = BigInteger.ONE;
				Qab = BigInteger.ONE;
			} else {
				Pab = a.multiply(SIX).subtract(FIVE).multiply(a.multiply(TWO).subtract(BigInteger.ONE).multiply(a.multiply(SIX).subtract(BigInteger.ONE)));

				Qab = SIXHUNDREDFOURTYTHOUSAND_RAISED_3_OVER_24.multiply(a).multiply(a).multiply(a);
			}

			// a(a) * p(a)
			Tab = Pab.multiply(a.multiply(FIVEHUNDREDFOURTYFIVEMILLION).add(THIRTEENMILLION));

			if (a.mod(TWO).equals(BigInteger.ONE)) {
				Tab = Tab.multiply(MINUS_ONE);
			}
		} else {
			// Recursively compute P(a,b), Q(a,b) and T(a,b)
			// m is the midpoint of a and b
			final BigInteger m = a.add(b).divide(TWO);

			// Recursively calculate P(a,m), Q(a,m) and T(a,m)
			final BigInteger[] array_am = calculateTerms(a, m);
			final BigInteger Pam = array_am[0];
			final BigInteger Qam = array_am[1];
			final BigInteger Tam = array_am[2];

			// Recursively calculate P(m,b), Q(m,b) and T(m,b)
			final BigInteger[] array_mb = calculateTerms(m, b);
			final BigInteger Pmb = array_mb[0];
			final BigInteger Qmb = array_mb[1];
			final BigInteger Tmb = array_mb[2];

			// Now combine
			Pab = Pam.multiply(Pmb);
			Qab = Qam.multiply(Qmb);
			Tab = (Qmb.multiply(Tam)).add((Pam.multiply(Tmb)));
		}

		// Return Pab, Qab and Tab in an array
		final BigInteger[] array = new BigInteger[3];
		array[0] = Pab;
		array[1] = Qab;
		array[2] = Tab;

		return array;
	}
}
