package com.svetanis.algorithms.math;

// 1344. Angle Between Hands of a Clock

// The minute hand turns 360 degrees in 60 minutes: 6 degrees a minute.
// The hour hand turns 30 degrees an hour, and half a degree more for each
// minute past the hour. Of the two ways round, the answer is the smaller.

public final class AngleBetweenClockHands {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

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