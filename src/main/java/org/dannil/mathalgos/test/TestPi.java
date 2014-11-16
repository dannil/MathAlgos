package org.dannil.mathalgos.test;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.PiChudnovsky;
import org.dannil.mathalgos.algorithm.helper.BenchmarkHelper;

public class TestPi {
	public static void main(String[] args) {
		BenchmarkHelper bench = new BenchmarkHelper();

		bench.startBench();
		System.out.println(PiChudnovsky.computePiStringPresentation(new BigInteger("100000")));
		bench.stopBench();
		System.out.println("Time (sec): " + bench.getBenchTimeInSeconds());
	}
}
