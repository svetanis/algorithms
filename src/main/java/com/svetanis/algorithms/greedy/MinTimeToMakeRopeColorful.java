package com.svetanis.algorithms.greedy;

// 1578. Minimum Time to Make Rope Colorful

public final class MinTimeToMakeRopeColorful {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minCostSimple(String colors, int[] time) {
		int total = 0;
		char[] chars = colors.toCharArray();
		for (int i = 1; i < time.length; i++) {
			if (chars[i] == chars[i - 1]) {
				total += Math.min(time[i], time[i - 1]);
				time[i] = Math.max(time[i], time[i - 1]);
			}
		}
		return total;
	}

	public static int minCost(String colors, int[] time) {
		int total = 0;
		int n = time.length;
		for (int left = 0, right = 0; left < n; left = right) {
			right = left;
			int sum = 0;
			int max = 0;
			while (right < n && colors.charAt(right) == colors.charAt(left)) {
				sum += time[right];
				max = Math.max(max, time[right]);
				right++;
			}
			if (right - left > 1) {
				total += sum - max;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[] time1 = { 1, 2, 3, 4, 5 };
		System.out.println(minCost("abaac", time1)); // 3
		System.out.println(minCostSimple("abaac", time1)); // 3

		int[] time2 = { 1, 2, 3 };
		System.out.println(minCost("abc", time2)); // 0
		System.out.println(minCostSimple("abc", time2)); // 0

		int[] time3 = { 1, 2, 3, 4, 1 };
		System.out.println(minCost("aabaa", time3)); // 2
		System.out.println(minCostSimple("aabaa", time3)); // 2

		int[] time4 = { 3, 5, 10, 7, 5, 3, 5, 5, 4, 8, 1 };
		System.out.println(minCost("aaabbbabbbb", time4)); // 26
		System.out.println(minCostSimple("aaabbbabbbb", time4)); // 26
	}
}
