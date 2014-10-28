package org.dannil.mathalgos.algorithm.helper;

public class BenchmarkHelper {

	private double startMillis;
	private double endMillis;

	public BenchmarkHelper() {

	}

	public void startBench() {
		this.startMillis = System.currentTimeMillis();
	}

	public void stopBench() {
		this.endMillis = System.currentTimeMillis();
	}

	public double getBenchTimeInSeconds() {
		return (this.endMillis - this.startMillis) / 1000;
	}

	public double getStartMillis() {
		return this.startMillis;
	}

	public double getEndMillis() {
		return this.endMillis;
	}

}
