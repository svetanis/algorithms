package com.svetanis.algorithms.slidingwindow.array;

// given array of positive integers and range (L,R)
// find number of subarrays with sum in the given range

public final class CountSubArrsSumInGivenRange {

	public static int countInRange(int[] a, int start, int end) {
		// Time Complexity: O(n)
		// Space Complexity: O(1)
		int right = count(a, end);
		int left = count(a, start - 1);
		return right - left;
	}

	private static int count(int[] a, int k) {
		int n = a.length;
		int sum = 0;
		int count = 0;
		int left = 0;

		for (int right = 0; right < n; right++) {
			sum += a[right];
			if (sum < k) {
				right++;
				count += (right - left);
			} else {
				sum -= a[left];
				left++;
			}
		}
		return count;
	}

	public static void main(String[] args) {

		int[] a1 = { 1, 4, 6 };
		System.out.println(countInRange(a1, 3, 8));

		int[] a2 = { 2, 3, 5, 8 };
		System.out.println(countInRange(a2, 4, 13));

	}
}
