package com.svetanis.algorithms.slidingwindow.string;

// 2904. Shortest and Lexicographically Smallest Beautiful String

public final class ShortestBeautifulStr {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String sbs(String s, int k) {
		int left = 0;
		int right = 0;
		int ones = 0;
		String sbs = "";
		while (right < s.length()) {
			ones += s.charAt(right) - '0';
			while ((left < right && s.charAt(left) == '0') || ones > k) {
				ones -= s.charAt(left) - '0';
				left++;
			}
			right++;
			String ss = s.substring(left, right);
			boolean one = sbs.isEmpty();
			boolean two = right - left < sbs.length();
			boolean three = right - left == sbs.length();
			boolean four = ss.compareTo(sbs) < 0;
			if (ones == k && (one || two || (three && four))) {
				sbs = ss;
			}
		}
		return sbs;
	}

	public static void main(String args[]) {
		System.out.println(sbs("100011001", 3)); // 11001
		System.out.println(sbs("1011", 2)); // 11
		System.out.println(sbs("000", 1)); // ""
	}
}