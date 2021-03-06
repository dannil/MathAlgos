package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.dannil.mathalgos.math.BigDecimalMath;
import org.dannil.mathalgos.math.BigIntegerMath;

/**
 * <p>Class that implements the Chudnovsky brothers solution of computing pi
 * to a specified amount of decimal places.</p>
 * 
 * </p>Credits to Nick Craig-Wood for showing a Python implementation which I used
 * as a basis for this class (see <url>http://www.craig-wood.com/nick/articles/pi-chudnovsky/</url>).</p>
 * 
 * @author Daniel Nilsson
 *
 */
public class PiChudnovsky {

	private static final RoundingMode roundingMode = RoundingMode.HALF_EVEN;
	private static final int roundingScale = 20;

	/* Magic numbers used for making the calculation work */
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
	 * 					the amount of decimals to calculate
	 * 
	 * @return a string presentation of a BigInteger containing pi truncated 
	 * 		   to the specified amount of decimals
	 */
	public static final String computePiStringPresentation(final BigInteger decimals) {
		return new StringBuffer(computePi(decimals).toString()).insert(1, ".").toString();
	}

	/**
	 * <p>Computes Pi to the specified amount of decimals.</p>
	 * 
	 * @param decimals 
	 * 					the amount of decimals to calculate
	 * 
	 * @return a BigInteger containing truncated pi
	 */
	public static final BigInteger computePi(final BigInteger decimals) {
		final BigDecimal DIGITS_PER_TERM = BigDecimalMath.log10(new BigDecimal(SIXHUNDREDFOURTYTHOUSAND_RAISED_3_OVER_24.divide(SIX).divide(TWO).divide(SIX))).setScale(roundingScale, roundingMode);

		final BigInteger N = new BigDecimal(decimals).divide(DIGITS_PER_TERM, DIGITS_PER_TERM.setScale(0, roundingMode).toBigInteger().intValue(), roundingMode).add(BigDecimal.ONE).toBigInteger();

		final BigInteger[] array = calculateTerms(BigInteger.ZERO, N);

		final BigInteger Q = array[1];
		// System.out.println("Q: " + Q);
		final BigInteger T = array[2];
		// System.out.println("T: " + T);

		// THE TWO LINES BELOW CONTRIBUTE TO THE MAJOR RUNNING TIME
		// (ESPECIALLY LINE 1 SQUARE ROOTING OPERATION); THIS NEEDS TO BE
		// OPTIMIZED TO REDUCE THE RUNNING TIME SIGNIFICANTLY
		final BigInteger SQRT_C = BigIntegerMath.sqrt(TENTHOUSHAND.multiply(BigInteger.TEN.pow(2 * decimals.intValue())));
		// System.out.println("SQRT_C: " + SQRT_C);

		return Q.multiply(FOURHUNDREDTHOUSAND).multiply(SQRT_C).divide(T);
	}

	/**
	 * <p>Computes the terms for binary splitting the Chudnovsky infinite series.</p>
	 *
	 * <pre>
	 * - a(a) = +/- (13591409 + 545140134*a)
	 * - p(a) = (6*a-5)*(2*a-1)*(6*a-1)
	 * - b(a) = 1
	 * - q(a) = SIXHUNDREDFOURTYTHOUSAND_RAISED_3_OVER_24*a*a*a
	 * </pre>
	 *
	 * @param a
	 *            start value (default BigInteger.ZERO)
	 * @param b
	 *            end value (the number of decimals to calculate)
	 *            
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
				Tab = Tab.negate();
			}
		} else {
			// Recursively compute P(a,b), Q(a,b) and T(a,b)
			// m is the midpoint of a and b
			final BigInteger midpoint = a.add(b).divide(TWO);

			// Recursively calculate P(a,m), Q(a,m) and T(a,m)
			final BigInteger[] lower_half = calculateTerms(a, midpoint);
			final BigInteger Pam = lower_half[0];
			final BigInteger Qam = lower_half[1];
			final BigInteger Tam = lower_half[2];

			// Recursively calculate P(m,b), Q(m,b) and T(m,b)
			final BigInteger[] upper_half = calculateTerms(midpoint, b);
			final BigInteger Pmb = upper_half[0];
			final BigInteger Qmb = upper_half[1];
			final BigInteger Tmb = upper_half[2];

			// Now combine
			Pab = Pam.multiply(Pmb);
			Qab = Qam.multiply(Qmb);
			Tab = (Qmb.multiply(Tam)).add((Pam.multiply(Tmb)));
		}

		// Return Pab, Qab and Tab in an array
		return new BigInteger[] { Pab, Qab, Tab };
	}
}
