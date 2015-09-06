package org.dannil.mathalgos.algorithm;

public class Fibonacci {

	public static int Fib(int n) {
		int[] vector = { 1, 1 };

		for (int i = 0; i < n - 2; i++) {
			int temp1 = vector[1];
			vector[1] = vector[0] + vector[1];
			vector[0] = temp1;
		}

		return vector[1];
	}

}
