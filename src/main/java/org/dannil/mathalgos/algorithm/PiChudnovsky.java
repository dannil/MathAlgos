package org.dannil.mathalgos.algorithm;

import java.math.BigInteger;
import java.util.LinkedList;

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
	private static final BigInteger C = new BigInteger("640320");
	private static final BigInteger FOURHUNDREDTHOUSAND = new BigInteger("426880");
	private static final BigInteger THIRTEENMILLION = new BigInteger("13591409");
	private static final BigInteger FIVEHUNDREDFOURTYFIVEMILLION = new BigInteger("545140134");
	private static final BigInteger C3_OVER_24 = C.pow(3).divide(TWENTYFOUR);

	/**
	 * Computes Pi to the specified amount of decimals and returns it
	 * as a string with correct delimite
	 * 
	 * @param decimals The amount of decimals to calculate
	 * @return A String representation of a BigInteger containing truncated pi
	 */
	public static String computePiStringPresentation(BigInteger decimals) {
		String piString = computePi(decimals).toString();
		return new StringBuffer(piString).insert(1, ".").toString();
	}

	/**
	 * Computes Pi to the specified amount of decimals
	 * 
	 * @param decimals The amount of decimals to calculate
	 * @return A BigInteger containing truncated pi
	 */
	public static BigInteger computePi(BigInteger decimals) {
		final double DIGITS_PER_TERM = Math.log10(C3_OVER_24.doubleValue() / 6 / 2 / 6);
		System.out.println("DIGITS_PER_TERM: " + DIGITS_PER_TERM);

		final BigInteger N = BigInteger.valueOf((long) (decimals.doubleValue() / DIGITS_PER_TERM + 1));
		System.out.println("N: " + N);

		LinkedList<BigInteger> list = calculateTerms(BigInteger.ZERO, N);

		BigInteger P = list.get(0);
		BigInteger Q = list.get(1);
		BigInteger T = list.get(2);

		System.out.println("Q: " + Q);

		BigInteger ONE_SQUARED = BigInteger.TEN.pow(2 * decimals.intValue());
		System.out.println("One squared: " + ONE_SQUARED);

		BigInteger SQRT_C = NumberHelper.bigIntSqRootCeil(TENTHOUSHAND.multiply(ONE_SQUARED));
		System.out.println("SQRT_C: " + SQRT_C);

		BigInteger FINAL_DECIMAL = Q.multiply(FOURHUNDREDTHOUSAND).multiply(SQRT_C).divide(T);
		System.out.println(FINAL_DECIMAL);
		return FINAL_DECIMAL;
	}

	/**
	 * <p>Computes the terms for binary splitting the Chudnovsky infinite series</p>
	 *
	 * - a(a) = +/- (13591409 + 545140134*a)<br>
	 * - p(a) = (6*a-5)*(2*a-1)*(6*a-1)<br>
	 * - b(a) = 1<br>
	 * - q(a) = a*a*a*C3_OVER_24<br>
	 * <br>
	 *
	 * @param a
	 *            Start value (default BigInteger.ZERO)
	 * @param b
	 *            End value (the number of decimals to calculate)
	 * @return P(a,b), Q(a,b) and T(a,b)
	 * @throws Exception
	 */
	private static LinkedList<BigInteger> calculateTerms(BigInteger a, BigInteger b) {
		BigInteger Pab;
		BigInteger Qab;
		BigInteger Tab;
		System.out.println("B - A: " + b.subtract(a));
		if (b.subtract(a).equals(BigInteger.ONE)) {
			System.out.println("Equals 1");
			// Directly compute P(a,a+1), Q(a,a+1) and T(a,a+1)
			if (a.equals(BigInteger.ZERO)) {
				System.out.println("Equals 0");
				BigInteger tmp1 = new BigInteger("1");
				Pab = tmp1;
				Qab = tmp1;
				System.out.println("Pab: " + Pab);
			} else {
				BigInteger tmp1 = a.multiply(SIX).subtract(FIVE);
				BigInteger tmp2 = a.multiply(TWO).subtract(BigInteger.ONE);
				BigInteger tmp3 = a.multiply(SIX).subtract(BigInteger.ONE);
				Pab = tmp1.multiply(tmp2).multiply(tmp3);
				System.out.println("Pab in else: " + Pab);

				Qab = C3_OVER_24.multiply(a).multiply(a).multiply(a);
				System.out.println("Qab in else: " + Qab);
			}

			// a(a) * p(a)
			Tab = Pab.multiply(a.multiply(FIVEHUNDREDFOURTYFIVEMILLION).add(THIRTEENMILLION));
			System.out.println("Tab: " + Tab);

			// Unsure, seems to work; needs more testing
			BigInteger bi = a.mod(TWO);
			System.out.println("bi: " + bi);
			if (bi.equals(BigInteger.ONE)) {
				System.out.println("bitshift");
				System.out.println("Tab before bitshift: " + Tab);
				Tab = Tab.multiply(MINUS_ONE);
				System.out.println("Tab bitshift: " + Tab);
			}
		} else {
			System.out.println("Inside else 2");

			// Recursively compute P(a,b), Q(a,b) and T(a,b)
			// m is the midpoint of a and b
			BigInteger m = a.add(b).divide(TWO);
			System.out.println("M: " + m);

			// Recursively calculate P(a,m), Q(a,m) and T(a,m)
			LinkedList<BigInteger> list_am = calculateTerms(a, m);
			BigInteger Pam = list_am.get(0);
			BigInteger Qam = list_am.get(1);
			BigInteger Tam = list_am.get(2);

			// Recursively calculate P(m,b), Q(m,b) and T(m,b)
			LinkedList<BigInteger> list_mb = calculateTerms(m, b);
			BigInteger Pmb = list_mb.get(0);
			BigInteger Qmb = list_mb.get(1);
			BigInteger Tmb = list_mb.get(2);

			System.out.println("Pam in else 2: " + Pam);
			System.out.println("Qam in else 2: " + Qam);
			System.out.println("Tam in else 2: " + Tam);
			System.out.println("Pmb in else 2: " + Pmb);
			System.out.println("Qmb in else 2: " + Qmb);
			System.out.println("Tmb in else 2: " + Tmb);

			// Now combine
			Pab = Pam.multiply(Pmb);
			Qab = Qam.multiply(Qmb);
			Tab = (Qmb.multiply(Tam)).add((Pam.multiply(Tmb)));

			System.out.println("Pab in else 2: " + Pab);
			System.out.println("Qab in else 2: " + Qab);
			System.out.println("Tab in else 2: " + Tab);
		}

		// Return Pab, Qab and Tab in a list
		LinkedList<BigInteger> list = new LinkedList<BigInteger>();
		list.add(Pab);
		list.add(Qab);
		list.add(Tab);

		return list;
	}
}
