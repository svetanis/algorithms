package com.svetanis.algorithms.string.reorder;

// 1758. Minimum Changes To Make Alternating Binary String

public final class MinChangesAlterStrSimple {
	// Time Complexity: O(n)

	public static int minOperations(String s) {
		return Math.min(count(s, '0'), count(s, '1'));
	}

	private static int count(String s, char bit) {
		int count = 0;
		for (char c : s.toCharArray()) {
			if (c != bit) {
				count++;
			}
			bit = bit == '0' ? '1' : '0';
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(minOperations("0100")); // 1
		System.out.println(minOperations("10")); // 0
		System.out.println(minOperations("1111")); // 2
	}
}
