package org.dannil.mathalgos.math;

import java.math.BigDecimal;

public class BigDecimalMath {

	private static final BigDecimal TWO = new BigDecimal(2);
	private static final BigDecimal TEN = new BigDecimal(10);
	private static final BigDecimal LOGTEN = new BigDecimal(Math.log(10));
	private static final BigDecimal LNTWO = new BigDecimal(Math.log(2));
	private static final BigDecimal MAXDOUBLE = new BigDecimal(Double.MAX_VALUE);

	public static BigDecimal log10(BigDecimal v) {
		if (v.compareTo(MAXDOUBLE) > 0) {
			return v.divide(TEN).add(LOGTEN);
		} else {
			return new BigDecimal(Math.log10(v.doubleValue()));
		}
	}

}
