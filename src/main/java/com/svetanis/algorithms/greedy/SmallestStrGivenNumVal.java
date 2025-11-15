package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1663. Smallest String With A Given Numeric Value

public final class SmallestStrGivenNumVal {
	// Time Complexity: O(n)

	public static String smallestStr(int n, int k) {
		char[] chars = new char[n];
		Arrays.fill(chars, 'a');
		int remains = k - n;
		int index = n - 1;
		while(remains > 25) {
			chars[index--] = 'z';
			remains -= 25;
		}
		chars[index] = (char) ('a' + remains);
		return new String(chars);
	}

	public static void main(String[] args) {
		System.out.println(smallestStr(3, 27)); // aay
		System.out.println(smallestStr(5, 73)); // aaszz
	}
}
