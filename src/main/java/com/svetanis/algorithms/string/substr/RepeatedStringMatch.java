package com.svetanis.algorithms.string.substr;

// 686. Repeated String Match
//
// Input: two strings a and b. Returns the smallest number of times a must be written one after
// another so that b is a substring of the result, or -1 if that never happens. On LeetCode:
// 1 <= a.length, b.length <= 10^4, lowercase letters.
//
// The one idea: only two counts are worth testing. The result must be at least as long as b, so
// start from the count that first makes it so (about m / n copies). If b occurs at all, it starts
// inside the first copy of a -- an occurrence starting later could be shifted one copy to the
// left -- and then it ends within the first m letters after that start, so one more copy than the
// length count is enough. If neither count works, no more copies will either: a longer result
// only adds a further copy of a at the end.
//
// Time: O(m * (n + m)) -- n = a.length, m = b.length; two contains tests, each a plain scan of a
// string of up to m + 2n letters for a string of m letters.
// Space: O(n + m) -- the repeated string.

public class RepeatedStringMatch {

	public static int rsm(String a, String b) {
		int n = a.length();
		int m = b.length();
		int count = 1;
		StringBuilder sb = new StringBuilder(a);
		while (n < m) { // GROW: until the result is at least as long as b
			sb.append(a);
			count++;
			n = sb.length();
		}
		for (int i = 0; i < 2; i++) { // TRY: this count, then one more copy
			if (sb.toString().contains(b)) {
				return count; // RETURN: the first count that holds b
			}
			sb.append(a);
			count++;
		}
		return -1; // b never fits
	}

	public static void main(String[] args) {
		System.out.println(rsm("abcd", "cdabcdab")); // 3
		System.out.println(rsm("a", "aa")); // 2
		System.out.println(rsm("abc", "wxyz")); // -1
	}
}
