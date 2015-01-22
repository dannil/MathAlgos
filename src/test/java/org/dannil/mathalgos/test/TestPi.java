package org.dannil.mathalgos.test;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.test.constant.Pi;
import org.junit.Assert;
import org.junit.Test;

public final class TestPi {

	@Test
	public void chudnovskyTo10000Places() {
		Assert.assertEquals(Pi.PI_TENTHOUSAND, PiChudnovsky.computePi(new BigInteger("10000")));
	}

}
