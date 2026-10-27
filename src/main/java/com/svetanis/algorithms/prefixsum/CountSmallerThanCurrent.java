package com.svetanis.algorithms.prefixsum;

// 1365. How Many Numbers Are Smaller Than the Current Number
//
// Input: an array a. Returns an array whose entry i is how many numbers in a are strictly smaller
// than a[i]. On LeetCode: 2 <= a.length <= 500, 0 <= a[i] <= 100. A value above 100 or below 0 is
// outside that range and throws ArrayIndexOutOfBoundsException.
//
// The one idea: the values are small, so count them. After count, counts[v + 1] holds how many
// times the value v appears -- shifted up by one on purpose. After the running total, counts[v]
// holds how many numbers are below v: the cells 0 .. v hold the counts of the values -1 .. v - 1,
// and value -1 never appears. The answer for a[i] is then one read, counts[a[i]].
//
// Time: O(n + m) -- n numbers, and m = 102 cells for the values 0 .. 100 and the shift
// Space: O(n + m) -- the output array and the m count cells

public final class CountSmallerThanCurrent {

	public static int[] smallerThanCurrent(int[] a) {
		int[] counts = count(a);
		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1]; // PREFIX: counts[v] = numbers below v
		}
		int[] smaller = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			smaller[i] = counts[a[i]]; // READ: strictly smaller, thanks to the shift
		}
		return smaller;
	}

	private static int[] count(int[] a) {
		int[] counts = new int[102]; // CELLS: values 0 .. 100, shifted up by one
		for (int num : a) {
			counts[num + 1]++; // SHIFT: value v counts in cell v + 1
		}
		return counts;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[] a1 = { 8, 1, 2, 2, 3 };
		print(smallerThanCurrent(a1)); // 4 0 1 1 3
		int[] a2 = { 6, 5, 4, 8 };
		print(smallerThanCurrent(a2)); // 2 1 0 3
		int[] a3 = { 7, 7, 7, 7 };
		print(smallerThanCurrent(a3)); // 0 0 0 0
		int[] a4 = { 5, 0, 10, 0, 10, 6 };
		print(smallerThanCurrent(a4)); // 2 0 4 0 4 3
	}
}
