package com.svetanis.algorithms.search.binary.invariant;

// 410. Split Array Largest Sum

public final class SplitArrayLargestSum {
	// Time Complexity: O( n * log(sum - max))

	public static int splitArray(int[] a, int k) {
		int max = 0;
		int sum = 0;
		for (int num : a) {
			sum += num;
			max = Math.max(max, num);
		}
		int low = max;
		int high = sum;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (splitPossible(a, mid, k)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static boolean splitPossible(int[] a, int max, int k) {
		int sum = 0;
		int count = 1;
		for (int num : a) {
			if (sum + num > max) {
				count++;
				sum = num;
			} else {
				sum += num;
			}
		}
		return count <= k;
	}

	private static boolean splitPossible2(int[] a, int max, int k) {
		int count = 0;
		int sum = 1 << 30;
		for (int num : a) {
			sum += num;
			if (sum > max) {
				count++;
				sum = num;
			}
		}
		return count <= k;
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 2, 5, 10, 8 };
		System.out.println(splitArray(a1, 2)); // 18
		int[] a2 = { 1, 2, 3, 4, 5 };
		System.out.println(splitArray(a2, 2)); // 9
	}
}
