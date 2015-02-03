package org.dannil.mathalgos.test;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.test.utility.BenchmarkUtility;
import org.dannil.mathalgos.test.utility.FileUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class TestPiChudnovsky {

	private final static Logger LOGGER = Logger.getLogger(TestPiChudnovsky.class.getName());

	private BenchmarkUtility benchmarkUtility;

	// ------ PI ------ //

	@Before
	public final void initialize() {
		this.benchmarkUtility = new BenchmarkUtility();
	}

	@Test
	public final void chudnovskyTo1000Places() {
		final String length = "1000";

		String s = FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=" + length + "]/value");

		this.benchmarkUtility.startBench();
		Assert.assertEquals(s, PiChudnovsky.computePiStringPresentation(new BigInteger(length)));
		this.benchmarkUtility.stopBench();

		LOGGER.info(this.benchmarkUtility.toString());
	}

	@Test
	public final void chudnovskyTo10000Places() {
		final String length = "10000";

		String s = FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=" + length + "]/value");

		this.benchmarkUtility.startBench();
		Assert.assertEquals(s, PiChudnovsky.computePiStringPresentation(new BigInteger(length)));
		this.benchmarkUtility.stopBench();

		LOGGER.info(this.benchmarkUtility.toString());
	}

	@Test
	public final void chudnovskyTo100000Places() {
		final String length = "100000";

		String s = FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=" + length + "]/value");

		this.benchmarkUtility.startBench();
		Assert.assertEquals(s, PiChudnovsky.computePiStringPresentation(new BigInteger(length)));
		this.benchmarkUtility.stopBench();

		LOGGER.info(this.benchmarkUtility.toString());
	}

	// @Test
	public final void chudnovskyTo1000000Places() {
		final String length = "1000000";

		String s = FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=" + length + "]/value");

		this.benchmarkUtility.startBench();
		Assert.assertEquals(s, PiChudnovsky.computePiStringPresentation(new BigInteger(length)));
		this.benchmarkUtility.stopBench();

		LOGGER.info(this.benchmarkUtility.toString());
	}
}
