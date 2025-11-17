package com.svetanis.algorithms.string;

// 2678. Number of Senior Citizens

public final class CountSeniorCitizens {
	// Time Complexity: O(n)

	public static int countSeniors(String[] details) {
		int count = 0;
		for (String s : details) {
			int age = Integer.parseInt(s.substring(11, 13));
			if (age > 60) {
				count += 1;
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
