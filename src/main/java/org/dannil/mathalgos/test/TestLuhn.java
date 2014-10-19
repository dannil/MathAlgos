package org.dannil.mathalgos.test;

import org.dannil.mathalgos.algorithm.LuhnAlgorithm;

public class TestLuhn {
	public static void main(String[] args) {
		int startRange = 1;
		int[] endRange = { 1000, 10000, 100000, 1000000, 10000000 };

		for (int i = 0; i < endRange.length; i++) {
			System.out.println("Testing range of " + startRange + " to " + endRange[i]);
			double current_millis = System.currentTimeMillis();
			for (int j = startRange; j < endRange[i]; j++) {
				LuhnAlgorithm.calculate(j);
			}
			System.out.println("Old:\t" + (System.currentTimeMillis() - current_millis) / 1000);

			current_millis = System.currentTimeMillis();
			for (int j = startRange; j < endRange[i]; j++) {
				LuhnAlgorithm.calculate_new(j);
			}
			System.out.println("New:\t" + (System.currentTimeMillis() - current_millis) / 1000);
			System.out.println();
		}
	}
}
