package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.LinkedList;

import org.dannil.mathalgos.algorithm.helper.NumberHelper;

public class PiChudnovsky {

	/* Magic numbers used for making the calculation work */
	private static final BigInteger TENTHOUSHAND = new BigInteger("10005");
	private static final BigInteger C = new BigInteger("640320");
	private static final BigInteger FOURHUNDREDTHOUSAND = new BigInteger("426880");
	private static final BigInteger THIRTEENMILLION = new BigInteger("13591409");
	private static final BigInteger FIVEHUNDREDFOURTYFIVEMILLION = new BigInteger("545140134");

	private static final BigInteger MINUS_ONE = new BigInteger("-1");
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger FIVE = new BigInteger("5");
	private static final BigInteger SIX = new BigInteger("6");
	private static final BigInteger TWENTYFOUR = new BigInteger("24");

	private static final BigInteger C3_OVER_24 = C.pow(3).divide(TWENTYFOUR);

	private static BigDecimal P;
	private static BigDecimal Q;
	private static BigDecimal T;

	private static BigDecimal Pab;
	private static BigDecimal Qab;
	private static BigDecimal Tab;

	private static BigDecimal Pam;
	private static BigDecimal Qam;
	private static BigDecimal Tam;

	private static BigDecimal Pmb;
	private static BigDecimal Qmb;
	private static BigDecimal Tmb;

	public static BigDecimal debug() {
		System.out.println("C3_OVER_24: " + C3_OVER_24);
		return computePi(new BigInteger("27"));
	}

	/**
	 * Computes Pi to the specified amount of decimals
	 * 
	 * @param decimals The amount of decimals to calculate
	 * @return A BigDecimal containing truncated Pi
	 */
	public static BigDecimal computePi(BigInteger decimals) {
		final double DIGITS_PER_TERM = Math.log10(C3_OVER_24.doubleValue() / 6 / 2 / 6);
		System.out.println("DIGITS_PER_TERM: " + DIGITS_PER_TERM);

		final BigInteger N = BigInteger.valueOf((long) (decimals.doubleValue() / DIGITS_PER_TERM + 1));
		System.out.println("N: " + N);

		LinkedList<BigDecimal> list = null;
		try {
			list = calculateTerms(BigInteger.ZERO, N);
			list.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		P = list.get(0);
		Q = list.get(1);
		T = list.get(2);

		BigDecimal ONE_SQUARED = BigDecimal.TEN.pow(2 * decimals.intValue());
		System.out.println("One squared: " + ONE_SQUARED);
		BigDecimal SQRT_C = NumberHelper.bigSqrt(new BigDecimal(TENTHOUSHAND).multiply(ONE_SQUARED));
		System.out.println("SQRT_C: " + SQRT_C);

		System.out.println("Q: " + Q);
		BigDecimal FINAL_DECIMAL = Q.multiply(new BigDecimal(FOURHUNDREDTHOUSAND)).multiply(SQRT_C).divide(T, RoundingMode.FLOOR);
		FINAL_DECIMAL = FINAL_DECIMAL.setScale(0, RoundingMode.DOWN);
		System.out.println(FINAL_DECIMAL);
		return null;
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
	private static LinkedList<BigDecimal> calculateTerms(BigInteger a, BigInteger b) throws Exception {
		System.out.println("B - A: " + b.subtract(a));
		if (b.subtract(a).equals(BigInteger.ONE)) {
			System.out.println("Equals 1");
			// Directly compute P(a,a+1), Q(a,a+1) and T(a,a+1)
			if (a.equals(BigInteger.ZERO)) {
				System.out.println("Equals 0");
				BigDecimal tmp1 = BigDecimal.valueOf(1);
				Pab = tmp1;
				Qab = tmp1;
				System.out.println("Pab: " + Pab);
			} else {
				BigDecimal tmp1 = new BigDecimal(a.multiply(SIX).subtract(FIVE));
				BigDecimal tmp2 = new BigDecimal(a.multiply(TWO).subtract(BigInteger.ONE));
				BigDecimal tmp3 = new BigDecimal(a.multiply(SIX).subtract(BigInteger.ONE));
				Pab = tmp1.multiply(tmp2).multiply(tmp3);
				System.out.println("Pab in else: " + Pab);

				Qab = new BigDecimal(C3_OVER_24.multiply(a).multiply(a).multiply(a));
				System.out.println("Qab in else: " + Qab);
			}

			// a(a) * p(a)
			Tab = Pab.multiply(new BigDecimal(a.multiply(FIVEHUNDREDFOURTYFIVEMILLION).add(THIRTEENMILLION)));
			System.out.println("Tab: " + Tab);

			System.out.println("Bitcount: " + a.bitCount());
			// UNSURE
			Integer bitCount = a.bitCount();
			if (bitCount.equals(1)) {
				System.out.println("bitshift");
				Tab = Tab.multiply(new BigDecimal(MINUS_ONE));
				System.out.println("Tab bitshift: " + Tab);
			}
		} else {
			System.out.println("Inside else");

			// Recursively compute P(a,b), Q(a,b) and T(a,b)
			// m is the midpoint of a and b
			BigInteger m = a.add(b).divide(TWO);
			System.out.println("M: " + m);

			// Recursively calculate P(a,m), Q(a,m) and T(a,m)
			LinkedList<BigDecimal> list = calculateTerms(a, m);
			Pam = list.get(0);
			Qam = list.get(1);
			Tam = list.get(2);

			// Recursively calculate P(m,b), Q(m,b) and T(m,b)
			LinkedList<BigDecimal> list2 = calculateTerms(m, b);
			Pmb = list2.get(0);
			Qmb = list2.get(1);
			Tmb = list2.get(2);

			// Now combine
			Pab = Pam.multiply(Pmb);
			Qab = Qam.multiply(Qmb);
			Tab = (Qmb.multiply(Tam)).add((Pam.multiply(Tmb)));
		}

		// Return Pab, Qab and Tab in a list
		LinkedList<BigDecimal> list = new LinkedList<BigDecimal>();
		list.add(Pab);
		list.add(Qab);
		list.add(Tab);

		return list;
	}
}
