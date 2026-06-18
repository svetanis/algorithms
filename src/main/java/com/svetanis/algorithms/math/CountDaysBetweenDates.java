package com.svetanis.algorithms.math;

// 1360. Number of Days Between Two Dates

public final class CountDaysBetweenDates {
	// Time Complexity: O(y + m)
	// Space Complexity: O(1)

	private static final int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static int daysBetweenDates(String date1, String date2) {
		return Math.abs(days(date1) - days(date2));
	}

	private static int days(String date) {
		String[] tokens = date.split("-");
		int targetYear = Integer.parseInt(tokens[0]);
		int targetMonth = Integer.parseInt(tokens[1]);
		int day = Integer.parseInt(tokens[2]);
		int days = day;
		for (int year = 1971; year < targetYear; year++) {
			int leap = leapYear(year) ? 1 : 0;
			days += 365 + leap;
		}
		for (int month = 1; month < targetMonth; month++) {
			days += daysInMonth(targetYear, month);
		}
		return days;
	}

	private static int daysInMonth(int year, int month) {
		int index = month - 1;
		if (index == 1 && leapYear(year)) {
			return DAYS[index] + 1;
		}
		return DAYS[index];
	}

	private static boolean leapYear(int year) {
		boolean one = year % 4 == 0;
		boolean two = (year % 100 != 0) || (year % 400 == 0);
		return one && two;
	}

	public static void main(String[] args) {
		System.out.println(daysBetweenDates("2019-06-29", "2019-06-30")); // 1
		System.out.println(daysBetweenDates("2020-01-15", "2019-12-31")); // 15
	}
}