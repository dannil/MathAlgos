package org.dannil.mathalgos.debug;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.test.utility.BenchmarkUtility;

public class DebugPi {
	public static void main(String[] args) {
		BigInteger big = new BigInteger("10000");

		BenchmarkUtility bench = new BenchmarkUtility();

		bench.startBench();
		System.out.println(PiChudnovsky.computePi(big));
		bench.stopBench();
		System.out.println("Time (sec): " + bench.getBenchTimeInSeconds());

		// PiNilakantha.debug();
	}
}
