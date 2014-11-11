package org.dannil.mathalgos.constant;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.algorithm.PiMachin;

/**
 * Constant class for Pi, which includes reference points 
 * and methods performed on and with Pi
 * 
 * @author Daniel
 *
 */
public class Pi {

	public static Double pi = Math.PI;

	/**
	 * Calculate Pi with the Chudnovsky borthers' formula
	 * 
	 * @param decimals
	 * 					- The amount of decimals to retrieve
	 * 
	 * @return The digits of Pi wrapped as a BigInteger
	 */
	public static final BigInteger calculateWithChudnovsky(final BigInteger decimals) {
		return PiChudnovsky.computePi(decimals);
	}

	/**
	 * Calculate Pi with the Machin formula
	 * 
	 * @param decimals
	 * 					- The amount of decimals to retrieve
	 * 
	 * @return The digits of Pi wrapped as a BigDecimal
	 */
	public static final BigDecimal calculateWithMachin(final int decimals) {
		return PiMachin.computePi(decimals);
	}

}
