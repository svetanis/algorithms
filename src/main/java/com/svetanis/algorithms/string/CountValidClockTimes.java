package com.svetanis.algorithms.string;

// 2437. Number of Valid Clock Times
// given valid string in the format "hh:mm"
// "00 <= hh <= 23"
// "00 <= mm <= 59"

public final class CountValidClockTimes {
	// Time Complexity: O(1)
	// Time Complexity: O(1)

	public static int countTime(String time) {
		String[] tokens = time.split(":");
		int hours = count(tokens[0], 24);
		int minutes = count(tokens[1], 60);
		return hours * minutes;
	}

	private static int count(String time, int max) {
		int count = 0;
		char first = time.charAt(0);
		char second = time.charAt(1);
		for (int i = 0; i < max; i++) {
			String val = i <= 9 ? String.valueOf("0" + i) : String.valueOf(i);
			boolean a = first == '?' || val.charAt(0) == first;
			boolean b = second == '?' || val.charAt(1) == second;
			count += (a && b) ? 1 : 0;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countTime("?5:00")); // 2
		System.out.println(countTime("0?:0?")); // 100
		System.out.println(countTime("??:??")); // 1440
	}
}
