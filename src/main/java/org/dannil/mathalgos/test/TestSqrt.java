package org.dannil.mathalgos.test;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.helper.BenchmarkHelper;
import org.dannil.mathalgos.algorithm.helper.NumberHelper;

public class TestSqrt {

	public static void main(String[] args) {
		BenchmarkHelper bench = new BenchmarkHelper();

		bench.startBench();
		System.out.println(Math.sqrt(Long.MAX_VALUE));
		bench.stopBench();
		System.out.println(bench.getBenchTimeInMilliseconds());

		System.out.println();

		bench.startBench();
		System.out.println(NumberHelper.sqrt(BigInteger.valueOf(Long.MAX_VALUE)));
		bench.stopBench();
		System.out.println(bench.getBenchTimeInMilliseconds());
	}

}
