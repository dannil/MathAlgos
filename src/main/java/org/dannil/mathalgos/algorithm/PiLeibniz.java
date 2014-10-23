package org.dannil.mathalgos.algorithm;

public class PiLeibniz {

	/**
	 * Approximate pi using the Leibniz formula (optimized version)
	 * 
	 * @return An approximation of pi
	 */
	public static double pi(int iterations) {
		double pi = 0;
		for (int i = 1; i < iterations; i += 4) {
			pi += 8.0 / (i * (i + 2L));
		}
		return pi;
	}
}
