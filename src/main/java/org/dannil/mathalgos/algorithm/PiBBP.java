package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class PiBBP {

	private static final RoundingMode roundingMode = RoundingMode.HALF_EVEN;

	private static final int scale = 20;

	static BigInteger MINUS_ONE = new BigInteger("-1");
	static BigInteger TWO = new BigInteger("2");
	static BigInteger FOUR = new BigInteger("4");
	static BigInteger FIVE = new BigInteger("5");
	static BigInteger SIX = new BigInteger("6");
	static BigInteger EIGHT = new BigInteger("8");
	static BigInteger SIXTEEN = new BigInteger("16");

	static BigInteger r;
	static BigInteger k;
	static BigInteger pow;

	static BigDecimal t;
	static BigDecimal exp;
	static BigDecimal newt;

	static BigDecimal s;

	public static void debug() {
		// calculateSum(new BigInteger("1"), new BigInteger("10"));
		computePi(new BigInteger("0"));
	}

	public static String computePi(BigInteger decimals) {
		BigInteger n = decimals.subtract(BigInteger.ONE);
		BigDecimal first = new BigDecimal(FOUR).multiply(calculateSum(BigInteger.ONE, n));
		BigDecimal second = new BigDecimal(TWO).multiply(calculateSum(FOUR, n));
		BigDecimal third = calculateSum(FIVE, n);
		BigDecimal fourth = calculateSum(SIX, n);
		BigDecimal x = first.subtract(second).subtract(third).subtract(fourth).remainder(BigDecimal.ONE);
		System.out.println(x);
		return x.toString();
	}

	public static BigDecimal calculateSum(final BigInteger j, final BigInteger n) {
		// Left sum
		s = new BigDecimal("0");

		for (k = new BigInteger("0"); k.compareTo(n) <= 0; k = k.add(BigInteger.ONE)) {
			r = EIGHT.multiply(k).add(j);
			System.out.println("R: " + r);

			pow = SIXTEEN.pow(n.intValue() - k.intValue()).mod(r);
			System.out.println("pow: " + pow);

			s = new BigDecimal(pow).divide(new BigDecimal(r), scale, roundingMode).add(s).remainder(BigDecimal.ONE);
			System.out.println("S: " + s);
		}
		System.out.println("K: " + k);

		// Right sum
		t = new BigDecimal("0.0");
		k = n.add(BigInteger.ONE);
		System.out.println("New k: " + k);
		while (true) {
			System.out.println("N: " + n);
			System.out.println("K: " + k);

			// exp = pow(16, n - k)
			// As BigInteger.pow() doesn't accept negative exponents, we need to
			// refactor the expression pow(16, n - k) to 1/(16^(k - n)
			BigInteger divider = BigInteger.valueOf(k.intValue() - n.intValue());
			System.out.println("divider: " + divider);
			exp = BigDecimal.ONE.divide(new BigDecimal(SIXTEEN.pow(divider.intValue())), scale, roundingMode);
			System.out.println("t: " + t);
			System.out.println("exp: " + exp);

			// newt = t + pow(16, n - k) / (8 * k + j)
			newt = exp.divide(new BigDecimal(EIGHT.multiply(k).add(j)), scale, roundingMode).add(t);
			System.out.println("newt: " + newt);

			if (t.compareTo(newt) == 0) {
				break;
			} else {
				t = newt;
			}
			k = k.add(BigInteger.ONE);
		}

		System.out.println("s: " + s);
		System.out.println("t: " + t);

		BigDecimal abc = s.add(t);
		System.out.println("abc: " + abc);

		return s.add(t);
	}
}