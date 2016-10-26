package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Sqrt {

	public static void main(String[] args) {
		String value = "2747696798167813768278267893578235987238921456460657849651298516847455897193678525894126237859456745344718576219784017984115";
		System.out.println("res big: " + babylonianSqrt(new BigDecimal(value)));
	}

	public static BigDecimal babylonianSqrt(BigDecimal n) {
		int scale = 30;
		RoundingMode roundingMode = RoundingMode.HALF_DOWN;

		final BigDecimal initialGuess = BigDecimal.valueOf(n.stripTrailingZeros().precision()
				- n.stripTrailingZeros().scale());

		final BigDecimal TWO = BigDecimal.valueOf(2);

		BigDecimal guess = n.divide(initialGuess, roundingMode).setScale(scale, roundingMode);
		BigDecimal y = BigDecimal.ONE;

		MathContext mc = new MathContext(scale, roundingMode);
		BigDecimal e = new BigDecimal(BigInteger.ONE, scale, mc);

		while (guess.subtract(y).compareTo(e) > 0) {
			guess = (guess.add(y)).divide(TWO);
			y = n.divide(guess, scale, roundingMode);
		}
		return guess;
	}

}
