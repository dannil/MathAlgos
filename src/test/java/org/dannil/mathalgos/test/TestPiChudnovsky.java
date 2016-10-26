package org.dannil.mathalgos.test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.test.utility.FileUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public  class TestPiChudnovsky {

	private  static Logger LOGGER = Logger.getLogger(TestPiChudnovsky.class.getName());

	private String expected;
	private String actual;

	public TestPiChudnovsky( String expected,  String actual) {
		this.expected = expected;
		this.actual = actual;
	}

	@Parameters
	public static Collection<String[]> generatedPi() {
		return Arrays.asList(new String[][] { { FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=1000]/value"), PiChudnovsky.computePiStringPresentation(new BigInteger("1000")) },
				{ FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=10000]/value"), PiChudnovsky.computePiStringPresentation(new BigInteger("10000")) },
				{ FileUtility.getPiXmlValue("pi.xml", "/pilist/pi[@length=100000]/value"), PiChudnovsky.computePiStringPresentation(new BigInteger("100000")) } });
	}

	// ------ PI ------ //

	@Test
	public  void calculatePiWithChudnovsky() {
		Assert.assertEquals(this.expected, this.actual);
	}

}