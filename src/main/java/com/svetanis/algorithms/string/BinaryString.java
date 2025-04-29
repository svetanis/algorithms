package com.svetanis.algorithms.string;

// 1016. Binary String With Substrings Representing 1 To N

public final class BinaryString {
	// Time Complexity: O(m * n/2)
	// Space Complexity: O(1)

	public static boolean query(String s, int n) {
		if (n > 1023) {
			return false;
		}
		for (int num = n; num > n / 2; num--) {
			String binary = Integer.toBinaryString(num);
			if (!s.contains(binary)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(query("0110", 3)); // true
		System.out.println(query("0110", 4)); // false
	}
}
