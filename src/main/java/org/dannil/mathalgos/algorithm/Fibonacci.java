package org.dannil.mathalgos.algorithm;

import java.math.BigDecimal;

public class Fibonacci {

	public static int fib(int n) {
		int[] vector = { 0, 1 };

		for (int i = 0; i < n; i++) {
			int temp0 = vector[0];
			vector[0] = vector[1] + vector[0];
			vector[1] = temp0;
		}

		return vector[0];
	}

	public static int fibBinet(int n) {
		BigDecimal sqrtOfFive = new BigDecimal("2.236067977499789");
		BigDecimal phi = BigDecimal.ONE.add(sqrtOfFive).divide(new BigDecimal("2"));
		System.out.println("Phi: " + phi);

		double phi1 = (1 + Math.sqrt(5)) / 2;
		System.out.println("Phi1: " + phi1);

		BigDecimal pow = phi.pow(n);
		System.out.println("Pow: " + pow);

		double pow1 = Math.pow(phi1, n);
		System.out.println("Pow1: " + pow1);

		return ((int) ((Math.pow(phi1, n) - Math.pow(1 - phi1, n)) / Math.sqrt(5)));
	}
}
