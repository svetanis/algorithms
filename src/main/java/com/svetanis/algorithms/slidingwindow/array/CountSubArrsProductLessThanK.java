package com.svetanis.algorithms.slidingwindow.array;

// 713. Subarray Product Less Than K

// given array of positive integers and number K
// count subarrays with product less than K

public final class CountSubArrsProductLessThanK {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int count(int[] a, int k) {
		int prod = 1;
		int count = 0;
		int left = 0;
		for (int right = 0; right < a.length; right++) {
			prod *= a[right];
			while (left <= right && prod >= k) {
				prod /= a[left++];
			}
			count += (right - left + 1);
		}
		return count;
	}

	public static void main(String[] args) {

		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(count(a1, 10));

		int[] a2 = { 1, 9, 2, 8, 6, 4, 3 };
		System.out.println(count(a2, 100));

		int[] a3 = { 5, 3, 2 };
		System.out.println(count(a3, 16));

		int[] a4 = { 100, 200 };
		System.out.println(count(a4, 100));

		int[] a5 = { 100, 200 };
		System.out.println(count(a5, 101));

		int[] a6 = { 10, 5, 2, 6 };
		System.out.println(count(a6, 100)); // 8

		int[] a7 = { 1, 2, 3 };
		System.out.println(count(a7, 0)); // 0
	}
}
