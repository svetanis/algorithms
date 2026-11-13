package com.svetanis.algorithms.string;

// 2437. Number of Valid Clock Times
//
// Input: a string time of the form "hh:mm" in which some digits may be '?'. A valid time has
// 00 <= hh <= 23 and 00 <= mm <= 59. Returns the number of valid times that fit the pattern when
// each '?' is replaced by a digit. On LeetCode: time is "hh:mm", with the colon in the middle,
// and at least one valid time exists.
//
// The one idea: the hour and the minute do not affect each other, so the answer is the number of
// hours that fit the hour pattern times the number of minutes that fit the minute pattern.
// Each factor is counted by trying every value the field can take -- 0 to 23, and 0 to 59 -- and
// keeping those whose two digits agree with the pattern, where '?' agrees with any digit.
//
// Time: O(1) -- at most 24 + 60 values are tried
// Space: O(1)

public final class CountValidClockTimes {

	public static int countTime(String time) {
		String[] tokens = time.split(":");
		int hours = count(tokens[0], 24); // HOURS: the patterns that fit 00..23
		int minutes = count(tokens[1], 60); // MINUTES: the patterns that fit 00..59
		return hours * minutes; // RETURN: any hour goes with any minute
	}

	private static int count(String time, int max) {
		int count = 0;
		char first = time.charAt(0);
		char second = time.charAt(1);
		for (int i = 0; i < max; i++) { // TRY: every value the field can take
			String val = i <= 9 ? String.valueOf("0" + i) : String.valueOf(i);
			boolean a = first == '?' || val.charAt(0) == first; // first digit agrees
			boolean b = second == '?' || val.charAt(1) == second; // second digit agrees
			count += (a && b) ? 1 : 0; // RECORD: this value fits the pattern
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countTime("?5:00")); // 2
		System.out.println(countTime("0?:0?")); // 100
		System.out.println(countTime("??:??")); // 1440
	}
}
