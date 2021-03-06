package org.dannil.mathalgos.test.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BenchmarkUtility {

	private long startMillis;
	private long endMillis;

	private Date startMillisAsDate;
	private Date endMillisAsDate;

	public BenchmarkUtility() {

	}

	public void startBench() {
		this.startMillis = System.currentTimeMillis();
		this.startMillisAsDate = new Date(this.startMillis);
	}

	public void stopBench() {
		this.endMillis = System.currentTimeMillis();
		this.endMillisAsDate = new Date(this.endMillis);
	}

	public double getBenchTimeInMilliseconds() {
		return (this.endMillis - this.startMillis);
	}

	public double getBenchTimeInSeconds() {
		return getBenchTimeInMilliseconds() / 1000;
	}

	public double getBenchTimeInMinutes() {
		return getBenchTimeInSeconds() / 60;
	}

	public double getBenchTimeInHours() {
		return getBenchTimeInMinutes() / 60;
	}

	public String getStartMillisAsDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(this.startMillisAsDate);
	}

	public String getEndMillisAsDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(this.endMillisAsDate);
	}

	public double getStartMillis() {
		return this.startMillis;
	}

	public double getEndMillis() {
		return this.endMillis;
	}

	@Override
	public final String toString() {
		final StringBuilder result = new StringBuilder();

		result.append("Start date: " + this.startMillisAsDate + ", End date: " + this.endMillisAsDate + ", Duration: " + this.getBenchTimeInSeconds() + " seconds (" + this.getBenchTimeInMinutes()
				+ " minutes");

		return result.toString();
	}
}
