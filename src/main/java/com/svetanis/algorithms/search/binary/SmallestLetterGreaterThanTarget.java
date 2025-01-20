package com.svetanis.algorithms.search.binary;

// 744. Find Smallest Letter Greater Than Target
// com.svetanis.algorithms.search.binary.FirstLargerThanTarget

public final class SmallestLetterGreaterThanTarget {
	// Time Complexity: O(log n)

	public static char vanilla(char[] chars, char target) {
		int n = chars.length;
		int left = 0;
		int right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (chars[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return chars[left % n];
	}

	public static void main(String[] args) {
		char[] a1 = { 'c', 'f', 'j' };
		System.out.println(vanilla(a1, 'a')); // c
		System.out.println(vanilla(a1, 'c')); // f
		char[] a2 = { 'x', 'x', 'y', 'y' };
		System.out.println(vanilla(a2, 'z')); // x
	}
}
