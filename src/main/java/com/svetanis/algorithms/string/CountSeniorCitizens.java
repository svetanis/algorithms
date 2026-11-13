package com.svetanis.algorithms.string;

// 2678. Number of Senior Citizens
//
// Input: an array details of 15-character strings, one per passenger: characters 0-9 the phone
// number, character 10 the gender, characters 11-12 the age, characters 13-14 the seat. Returns
// the number of passengers strictly older than 60. On LeetCode: 1 <= details.length <= 100,
// details[i].length == 15; the age is always two digits.
//
// The one idea: the age sits at the same two positions in every string, so read characters 11 and
// 12 as a number and compare it with 60. "Strictly older" means 61 and up; 60 itself does not count.
//
// Time: O(n) -- one constant-size substring per passenger
// Space: O(1)

public final class CountSeniorCitizens {

	public static int countSeniors(String[] details) {
		int count = 0;
		for (String s : details) {
			int age = Integer.parseInt(s.substring(11, 13)); // AGE: the two digits at 11 and 12
			if (age > 60) { // strictly older than 60
				count += 1; // RECORD: one more senior
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String[] a1 = { "7868190130M7522", "5303914400F9211", "9273338290F4010" };
		System.out.println(countSeniors(a1)); // 2
		String[] a2 = { "1313579440F2036", "2921522980M5644" };
		System.out.println(countSeniors(a2)); // 0
	}
}
