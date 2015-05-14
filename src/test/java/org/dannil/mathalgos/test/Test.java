package org.dannil.mathalgos.test;

import java.math.BigInteger;

import org.dannil.mathalgos.algorithm.PiBBP;

public class Test {

	public static void main(String[] args) {
		String s = PiBBP.computePi(new BigInteger("1000000000000"));
		System.out.println(s);
	}

}
