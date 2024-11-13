package com.svetanis.algorithms.greedy;

import static com.svetanis.java.base.utils.Swap.swap;

public final class MaxSwapAtMostK {

	public static String maxNum(String s, int k) {
		char[] chars = s.toCharArray();
		char[] max = s.toCharArray();
		maxNum(chars, max, k);
		return String.valueOf(max);
	}

	private static void maxNum(char[] chars, char[] max, int k) {
		if (k == 0) {
			return;
		}
		int n = chars.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (chars[i] < chars[j]) {
					swap(chars, i, j);
					String s1 = new String(chars);
					String s2 = new String(max);
					if (s1.compareTo(s2) > 0) {
						copy(max, chars);
					}
					maxNum(chars, max, k - 1);
					// backtrack
					swap(chars, i, j);
				}
			}
		}
	}

	private static void copy(char[] one, char[] two) {
		for (int i = 0; i < one.length; i++) {
			one[i] = two[i];
		}
	}

	public static void main(String[] args) {
		System.out.println(maxNum("129814999", 4));
		System.out.println(maxNum("2736", 1)); // 7236
		System.out.println(maxNum("9973", 1)); // 9973
	}
}
