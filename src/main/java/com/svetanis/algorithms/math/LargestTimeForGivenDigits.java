package com.svetanis.algorithms.math;

// 949. Largest Time for Given Digits

public final class LargestTimeForGivenDigits {

	public static String largestTimeFromDigits(int[] a) {
		int max = -1;
		int[] counts = counts(a);
		for (int hour = 23; hour >= 0; hour--) {
			for (int min = 59; min >= 0; min--) {
				int[] time = currTimeFreq(hour, min);
				if (areEqual(time, counts)) {
					max = Math.max(max, hour * 60 + min);
				}
			}
		}
		return max < 0 ? "" : String.format("%02d:%02d", max / 60, max % 60);
	}

	private static boolean areEqual(int[] time, int[] counts) {
		for (int i = 0; i < 10; i++) {
			if (time[i] != counts[i]) {
				return false;
			}
		}
		return true;
	}

	private static int[] currTimeFreq(int hour, int min) {
		int[] time = new int[10];
		time[hour / 10] += 1;
		time[hour % 10] += 1;
		time[min / 10] += 1;
		time[min % 10] += 1;
		return time;
	}

	private static int[] counts(int[] a) {
		int[] counts = new int[10];
		for (int num : a) {
			counts[num]++;
		}
		return counts;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(largestTimeFromDigits(a1)); // 23:41
		int[] a2 = { 5, 5, 5, 5 };
		System.out.println(largestTimeFromDigits(a2));
	}
}