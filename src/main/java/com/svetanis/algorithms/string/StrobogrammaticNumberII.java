package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 247. Strobogrammatic Number II
//
// Input: n. A number is strobogrammatic if it looks the same rotated 180 degrees (0, 1, 8 turn
// into themselves, 6 and 9 into each other). Returns every strobogrammatic number of length n, in
// any order, with no leading zero (except the single number "0" when n = 1). On LeetCode:
// 1 <= n <= 14.
//
// The one idea: a strobogrammatic number of length len is a matching pair of digits wrapped around
// a strobogrammatic number of length len - 2. So build from the middle outwards: the middle is
// "" (length 0) or one of 0, 1, 8 (length 1), and each step wraps every number so far in each
// pair 1..1, 8..8, 6..9, 9..6, and also 0..0 -- except at the outermost level, where a zero
// would be a leading zero. The parameter n is kept so the recursion knows which level is outermost
// (len == n).
//
// Time: O(n * 5^(n/2)) -- there are about 5^(n/2) answers, each a string of length n.
// Space: O(n * 5^(n/2)) -- the answers; the recursion is n / 2 levels deep.

public final class StrobogrammaticNumberII {

	// the pairs that can wrap a number: left digit, right digit (zero pairs are added separately)
	private static int[][] pairs = { { 1, 1 }, { 8, 8 }, { 6, 9 }, { 9, 6 } };

	public static List<String> strobogrammatic(int n) {
		return dfs(n, n);
	}

	private static List<String> dfs(int n, int len) {
		if (len == 0) { // BASE: an empty middle
			return Arrays.asList("");
		}
		if (len == 1) { // BASE: a one-digit middle
			return Arrays.asList("0", "1", "8");
		}
		List<String> list = new ArrayList<>();
		List<String> nums = dfs(n, len - 2); // the inside, two digits shorter
		for (String num : nums) {
			for (int[] pair : pairs) {
				list.add(pair[0] + num + pair[1]); // WRAP: a non-zero pair around the inside
			}
			if (len != n) { // a 0..0 wrap is allowed only when it is not the outermost one
				list.add("0" + num + "0");
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(strobogrammatic(1)); // [0, 1, 8]
		System.out.println(strobogrammatic(2)); // [11, 88, 69, 96]
		System.out.println(strobogrammatic(3)); // [101, 808, 609, 906, 111, 818, 619, 916, 181, 888, 689, 986]
		System.out.println(strobogrammatic(4)); // [1111, 8118, 6119, 9116, 1881, 8888, 6889, 9886, 1691, 8698, 6699, 9696, 1961, 8968, 6969, 9966, 1001, 8008, 6009, 9006]
	}
}
