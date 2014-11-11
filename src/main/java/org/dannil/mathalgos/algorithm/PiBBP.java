package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PiBBP {

	static BigInteger EIGHT = new BigInteger("8");
	static BigInteger SIXTEEN = new BigInteger("16");

	static BigInteger r;
	static BigInteger k;

	static BigDecimal s;

	public static String computePi(BigInteger decimals) {
		return null;
	}

	public static BigDecimal calculateSum(final BigInteger j, final BigInteger n) {
		s = new BigDecimal("0");
		k = new BigInteger("0");

		while (k.compareTo(n) <= 0) {
			r = EIGHT.multiply(k).add(j);
			// s =
		}
		return null;
	}

}