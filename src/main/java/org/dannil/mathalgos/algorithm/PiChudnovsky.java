package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;

public class PiChudnovsky {

	/* Magic numbers used for making the calculation work */
	private static final BigInteger TENTHOUSHAND = new BigInteger("10005");
	private static final BigInteger C = new BigInteger("640320");
	private static final BigInteger FOURHUNDREDHOUSAND = new BigInteger("426880");
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

	public static void debug() {
		computePi(new BigInteger("1"));
		System.out.println("C3_OVER_24: " + C3_OVER_24);

	}

	public static void computePi(BigInteger decimals) {
		final double DIGITS_PER_TERM = Math.log10(C3_OVER_24.doubleValue() / 6 / 2 / 6);
		System.out.println("DIGITS_PER_TERM: " + DIGITS_PER_TERM);

		final BigInteger N = BigInteger.valueOf((long) (decimals.doubleValue() / DIGITS_PER_TERM + 1));
		System.out.println("N: " + N);

		try {
			calculateTerms(BigInteger.ZERO, N);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static LinkedList<BigDecimal> calculateTerms(BigInteger a, BigInteger b) throws Exception {
		System.out.println("calculating terms...");
		if (b.subtract(a).equals(1)) {
			// Directly compute P(a,a+1), Q(a,a+1) and T(a,a+1)
			System.out.println("First if");
			if (a.equals(0)) {
				BigDecimal tmp1 = BigDecimal.valueOf(1);
				Pab = tmp1;
				Qab = tmp1;
			} else {
				BigDecimal tmp1 = new BigDecimal(a.multiply(SIX).subtract(FIVE));
				BigDecimal tmp2 = new BigDecimal(a.multiply(TWO).subtract(BigInteger.ONE));
				BigDecimal tmp3 = new BigDecimal(a.multiply(SIX).subtract(BigInteger.ONE));
				Pab = tmp1.multiply(tmp2).multiply(tmp3);

				Qab = new BigDecimal(C3_OVER_24.multiply(a).multiply(a).multiply(a));
				System.out.println("Qab: " + Qab);
			}

			// a(a) * p(a)
			Tab = Pab.multiply(new BigDecimal(a.multiply(THIRTEENMILLION.add(FIVEHUNDREDFOURTYFIVEMILLION))));

			// UNSURE
			if (a.testBit(1)) {
				Tab = Tab.multiply(new BigDecimal(MINUS_ONE));
			}
		} else {
			// Recursively compute P(a,b), Q(a,b) and T(a,b)
			// m is the midpoint of a and b
			BigInteger m = a.add(b).divide(TWO);

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
		// LinkedList<BigDecimal> list = new LinkedList<BigDecimal>();
		// list.add(Pab);
		// list.add(Qab);
		// list.add(Tab);

		return null;
	}
}
