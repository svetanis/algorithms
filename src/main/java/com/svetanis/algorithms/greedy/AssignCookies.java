package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 455. Assign Cookies
// same as 2410. Maximum Matching of Players With Trainers

public final class AssignCookies {
	// Time Complexity: O(n log n + m log m)

	public static int assignCookies(int[] greeds, int[] cookies) {
		int n = greeds.length;
		int m = cookies.length;
		if (n == 0 || m == 0) {
			return 0;
		}
		Arrays.sort(greeds);
		Arrays.sort(cookies);
		int count = 0;
		int i = 0, j = 0;
		while (i < n && j < m) {
			if (cookies[j] >= greeds[i]) {
				count++;
				i++;
			}
			j++;
		}
		return count;
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
