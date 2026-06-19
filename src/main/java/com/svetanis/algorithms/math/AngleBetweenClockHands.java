package com.svetanis.algorithms.math;

// 1344. Angle Between Hands of a Clock

public final class AngleBetweenClockHands {

	public static double angleClock(int hour, int min) {
		double minAngle = 6 * min;
		double hourAngle = 30 * hour + 0.5 * min;
		double diff = Math.abs(hourAngle - minAngle);
		return Math.min(diff, 360 - diff);
	}

	public static void main(String[] args) {
		System.out.println(angleClock(12, 30)); // 165
		System.out.println(angleClock(3, 30)); // 75
		System.out.println(angleClock(3, 15)); // 7.5
	}
}