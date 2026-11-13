package com.svetanis.algorithms.string;

// 246. Strobogrammatic Number
//
// Input: a string num of digits. A number is strobogrammatic if it looks the same after being
// rotated 180 degrees: 0, 1 and 8 turn into themselves, 6 and 9 turn into each other, no other
// digit survives. Returns true when num is strobogrammatic. On LeetCode: 1 <= num.length <= 50,
// num has digits only, and no leading zero except for "0" itself.
//
// The one idea: rotating reverses the order and turns each digit into its partner. So the digit
// at the left end must be the partner of the digit at the right end, and the same for the next
// pair inward. The array digits is the partner table: digits[d] is what d turns into, or -1 if d
// cannot be rotated. Compare ends, move inward; when the two ends meet in the middle the digit is
// compared with itself, so only 0, 1 and 8 pass there -- 6 and 9 do not.
//
// Time: O(n) -- at most n / 2 + 1 comparisons.
// Space: O(1) -- the fixed table of 10.

public final class StrobogrammaticNumber {

	// index = a digit, value = the digit it becomes upside down, -1 = cannot be rotated
	private static int[] digits = { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 };

	public static boolean isStrobogrammatic(String num) {
		int start = 0;
		int end = num.length() - 1;
		while (start <= end) { // WALK: both ends inward; start == end is the middle digit
			int left = num.charAt(start) - '0';
			int right = num.charAt(end) - '0';
			if (digits[left] != right) { // the left digit's partner must be the right digit
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isStrobogrammatic("69")); // true
		System.out.println(isStrobogrammatic("88")); // true
		System.out.println(isStrobogrammatic("962")); // false
		System.out.println(isStrobogrammatic("1")); // true
		System.out.println(isStrobogrammatic("6")); // false
	}
}
