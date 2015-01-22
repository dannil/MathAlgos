package org.dannil.mathalgos.debug;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.algorithm.helper.BenchmarkHelper;

public class DebugPi {
	public static void main(String[] args) {
		BigInteger big = new BigInteger("10000");

		BenchmarkHelper bench = new BenchmarkHelper();

		bench.startBench();
		System.out.println(PiChudnovsky.computePi(big));
		bench.stopBench();
		System.out.println("Time (sec): " + bench.getBenchTimeInSeconds());

		// PiNilakantha.debug();
	}
}
