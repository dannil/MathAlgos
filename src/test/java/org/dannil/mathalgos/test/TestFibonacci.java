package org.dannil.mathalgos.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.dannil.mathalgos.algorithm.Fibonacci;

public class TestFibonacci {

	public static void main(String[] args) {
		System.out.println(Fibonacci.fib(0));
		System.out.println(Fibonacci.fib(1));
		System.out.println(Fibonacci.fib(2));
		System.out.println(Fibonacci.fib(3));
		System.out.println(Fibonacci.fib(4));
		System.out.println(Fibonacci.fib(5));
		System.out.println(Fibonacci.fib(6));
		System.out.println(Fibonacci.fib(7));
		System.out.println(Fibonacci.fib(48));

		System.out.println("------------");

		System.out.println(Fibonacci.fibBinet(0));
		System.out.println(Fibonacci.fibBinet(1));
		System.out.println(Fibonacci.fibBinet(2));
		System.out.println(Fibonacci.fibBinet(3));
		System.out.println(Fibonacci.fibBinet(4));
		System.out.println(Fibonacci.fibBinet(5));
		System.out.println(Fibonacci.fibBinet(6));
		System.out.println(Fibonacci.fibBinet(7));
		System.out.println(Fibonacci.fibBinet(90));

		System.out.println("------------");

		BigDecimal decimal = new BigDecimal("1.61803").pow(60).setScale(1, RoundingMode.HALF_EVEN);
		System.out.println(decimal);
	}

}
