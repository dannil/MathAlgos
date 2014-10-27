package org.dannil.mathalgos.algorithm.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberHelper {

	/***
	 * Splits a number into its digits.
	 * 
	 * @param t
	 *            - The number to split.
	 * @return The digits of the splitted number.
	 */
	public static List<Integer> splitIntoDigits(int t) {
		// System.out.println("split");
		List<Integer> temp = new LinkedList<Integer>();
		int number = t;
		while (number > 0) {
			// System.out.println(number);
			temp.add(number % 10);
			number = number / 10;
		}
		Collections.reverse(temp);
		return temp;
	}

	/**
	 * Calculates the square root of the supplied BigDecimal.
	 * 
	 * @param value 
	 * 				  - The value to be calculated
	 * @return The square root of value
	 */
	public static BigDecimal sqrt(BigDecimal value) {
		BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
		return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}

	private static final BigDecimal SQRT_DIG = new BigDecimal(150);
	private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

	private static BigDecimal sqrtNewtonRaphson(BigDecimal c, BigDecimal xn, BigDecimal precision) {
		BigDecimal fx = xn.pow(2).add(c.negate());
		BigDecimal fpx = xn.multiply(new BigDecimal(2));
		BigDecimal xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
		xn1 = xn.add(xn1.negate());
		// ----
		BigDecimal currentSquare = xn1.pow(2);
		BigDecimal currentPrecision = currentSquare.subtract(c);
		currentPrecision = currentPrecision.abs();
		if (currentPrecision.compareTo(precision) <= -1) {
			return xn1;
		}
		return sqrtNewtonRaphson(c, xn1, precision);
	}

	public static BigDecimal bigSqrt(BigDecimal c) {
		return sqrtNewtonRaphson(c, new BigDecimal(1), new BigDecimal(1).divide(SQRT_PRE));
	}

}
