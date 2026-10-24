package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 455. Assign Cookies
//
// Input: greeds[i] -- the smallest cookie child i is happy with; cookies[j] -- the size of cookie
// j. Each child gets at most one cookie and each cookie goes to at most one child. Returns the
// most children that can be made happy. On LeetCode: 1 <= greeds.length <= 3 * 10^4,
// 0 <= cookies.length <= 3 * 10^4, 1 <= greeds[i], cookies[j] <= 2^31 - 1.
//
// The one idea: sort both, then walk the cookies from the smallest up, with i on the least greedy
// child still waiting. A cookie too small for child i is too small for every child after i as
// well -- they are all at least as greedy -- so it is thrown away. A cookie big enough for child
// i goes to child i: handing it to a greedier child instead would leave child i needing a cookie
// at least this big, so it could never make more children happy. Each decision is final, and the
// walk never goes back.
//
// Time: O(n log n + m log m) -- n = greeds.length, m = cookies.length: the two sorts. The walk
// moves j forward on every step, so it takes at most m steps.
// Space: O(1) -- beyond the sorts, which work in place: both arrays come back sorted.
//
// Siblings: MatchPlayersWithTrainers (2410. Maximum Matching of Players With Trainers) is the
// same problem with players for children and trainers for cookies, and the same code.

public final class AssignCookies {

	public static int assignCookies(int[] greeds, int[] cookies) {
		int n = greeds.length;
		int m = cookies.length;
		if (n == 0 || m == 0) { // BASE: no cookies, nobody is happy
			return 0;
		}
		Arrays.sort(greeds); // SORT: least greedy child first
		Arrays.sort(cookies); // SORT: smallest cookie first
		int count = 0;
		int i = 0, j = 0; // i -- the next child waiting; j -- the next cookie to hand out
		while (i < n && j < m) {
			if (cookies[j] >= greeds[i]) { // TAKE: big enough for child i
				count++;
				i++; // child i is happy; the next child waits
			}
			j++; // the cookie is given away, or too small for every child left and skipped
		}
		return count; // RETURN: happy children
	}

	public static void main(String[] args) {
		int[] g1 = { 1, 2, 3 };
		int[] c1 = { 1, 1 };
		System.out.println(assignCookies(g1, c1)); // 1

		int[] g2 = { 1, 2 };
		int[] c2 = { 1, 2, 3 };
		System.out.println(assignCookies(g2, c2)); // 2

		int[] g3 = { 10, 9, 8, 7 };
		int[] c3 = { 5, 6, 7, 8 };
		System.out.println(assignCookies(g3, c3)); // 2

	}
}
