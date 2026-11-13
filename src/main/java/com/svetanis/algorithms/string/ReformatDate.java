package com.svetanis.algorithms.string;

// 1507. Reformat Date
//
// Input: a date "Day Month Year", e.g. "20th Oct 2052": the day is 1 to 31 with a suffix (1st,
// 2nd, 3rd, 4th ... 31st), the month is a three-letter English name, the year is 1900 to 2100.
// Returns the date as "YYYY-MM-DD". On LeetCode: the input is always a valid date in that form.
//
// The one idea: split on the spaces into the three parts. The day is the number in front of its
// two-letter suffix, so drop the last two characters. The month's number is its place in the
// year: with the twelve names glued into one string, each takes 3 characters. A space in front
// puts month m (counting from 1) at index 3 * (m - 1) + 1, so index / 3 rounds down to m - 1,
// and the month is 1 + index / 3. %02d then pads the month and the day to two digits.
//
// Time: O(1) -- the input has a fixed shape.
// Space: O(1).

public final class ReformatDate {

	public static String reformat(String date) {
		String[] parts = date.split(" ");
		String months = " JanFebMarAprMayJunJulAugSepOctNovDec"; // the leading space shifts every name by one
		int day = Integer.parseInt(parts[0].substring(0, parts[0].length() - 2)); // DAY: without "st", "nd", "rd" or "th"
		int month = 1 + months.indexOf(parts[1]) / 3; // MONTH: "Jan" at 1, "Feb" at 4, ... -> 1, 2, ...
		return String.format("%s-%02d-%02d", parts[2], month, day); // RETURN: year, padded month, padded day
	}

	public static void main(String[] args) {
		System.out.println(reformat("20th Oct 2052")); // 2052-10-20
		System.out.println(reformat("6th Jun 1933")); // 1933-06-06
		System.out.println(reformat("26th May 1960")); // 1960-05-26
	}
}
