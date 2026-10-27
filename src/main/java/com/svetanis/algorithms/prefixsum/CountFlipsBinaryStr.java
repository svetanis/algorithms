package com.svetanis.algorithms.prefixsum;

// 926. Flip String to Monotone Increasing
//
// Input: a string s of '0's and '1's. A string is monotone increasing when it is some 0s followed
// by some 1s (either part may be empty). One flip turns a '0' into a '1' or back. Returns the
// fewest flips that make s monotone increasing. On LeetCode: 1 <= s.length <= 10^5, s[i] is '0'
// or '1'.
//
// The one idea: a monotone result is set by one split point i, from 0 to n: s[0 .. i - 1] become
// 0s and s[i .. n - 1] become 1s. The cost of split i is the 1s left of it plus the 0s right of it.
// In minFlips, prefix[i] holds the number of 1s in s[0 .. i - 1] and suffix[i] the number of 0s in
// s[i .. n - 1]; every split is tried and the cheapest is returned.
//
// minFlipsSimple reads s once. After reading s[0 .. i], ones holds the number of 1s so far -- the
// cost of making the whole prefix 0s -- and zeros holds the fewest flips that make the prefix
// monotone. A new '1' can always go at the end of a monotone prefix, so zeros stays. A new '0' is
// either flipped to '1' (zeros + 1) or the whole prefix becomes 0s (ones): the smaller wins.
//
// Time: O(n) -- each method makes a constant number of passes over s
// Space: O(n) -- minFlips keeps two count arrays of n + 1; minFlipsSimple keeps two counters,
//   plus the char array that toCharArray copies
//
// The two methods answer the same question: minFlips tries every split point with two count
// arrays, minFlipsSimple keeps the best answer so far in one variable.

public final class CountFlipsBinaryStr {

	public static int minFlipsSimple(String s) {
		int ones = 0; // ONES: 1s so far -- the cost of making the prefix all 0s
		int zeros = 0; // BEST: fewest flips that make the prefix monotone
		for (char c : s.toCharArray()) {
			int bit = c - '0'; // 0 or 1
			zeros = Math.min(zeros + 1 - bit, ones); // CHOOSE: flip this 0 to 1, or make all 0s
			ones += bit; // COUNT after the choice
		}
		return Math.min(zeros, ones); // RETURN: zeros <= ones always, so this is zeros
	}

	public static int minFlips(String s) {
		int n = s.length();
		int[] prefix = prefix(s);
		int[] suffix = suffix(s);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= n; i++) { // SPLIT: 0s before i, 1s from i; n + 1 choices
			int total = prefix[i] + suffix[i]; // COST: 1s on the left plus 0s on the right
			min = Math.min(min, total);
		}
		return min;
	}

	private static int[] suffix(String s) {
		int n = s.length();
		int[] right = new int[n + 1]; // right[i]: 0s in s[i .. n - 1]; right[n] = 0
		for (int i = n - 1; i >= 0; i--) {
			int count = s.charAt(i) == '1' ? 0 : 1; // a '0' right of the split must flip
			right[i] = right[i + 1] + count;
		}
		return right;
	}

	private static int[] prefix(String s) {
		int n = s.length();
		int[] left = new int[n + 1]; // left[i]: 1s in s[0 .. i - 1]; left[0] = 0
		for (int i = 1; i <= n; i++) {
			int count = s.charAt(i - 1) == '0' ? 0 : 1; // a '1' left of the split must flip
			left[i] = left[i - 1] + count;
		}
		return left;
	}

	public static void main(String[] args) {
		System.out.println(minFlips("00110")); // 1
		System.out.println(minFlips("010110")); // 2
		System.out.println(minFlips("00011000")); // 2

		System.out.println(minFlipsSimple("00110")); // 1
		System.out.println(minFlipsSimple("010110")); // 2
		System.out.println(minFlipsSimple("00011000")); // 2
	}
}
