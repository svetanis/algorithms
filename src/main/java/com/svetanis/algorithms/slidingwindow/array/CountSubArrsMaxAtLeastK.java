package com.svetanis.algorithms.slidingwindow.array;

// 2962. Count Subarrays Where Max Element Appears at Least K Times

public final class CountSubArrsMaxAtLeastK {
	// Time Complexity: O(n)

	public static long countSubArr(int[] a, int k) {
		int n = a.length;
		if (n == 0) {
			return 0;
		}
		int max = max(a);
		int left = 0;
		int maxCount = 0;
		long subArrCount = 0;
		for (int right = 0; right < n; right++) {
			if (a[right] == max) {
				maxCount++;
			}
			while (left <= right && maxCount >= k) {
				subArrCount += n - right;
				if (a[left] == max) {
					maxCount--;
				}
				left++;
			}
		}
		return subArrCount;
	}

	// seeded with a[0], not 0: seeding with 0 makes every all-negative
	// array report a max of 0, which no element equals, so maxCount
	// never rises and the answer is 0 for every k
	private static int max(int[] a) {
		int max = a[0];
		for (int num : a) {
			max = Math.max(max, num);
		}
		return max;
	}

	public static long countSubArr2(int[] a, int k) {
		int n = a.length;
		if (n == 0) {
			return 0;
		}
		int max = max(a);
		int right = 0;
		int maxCount = 0;
		long subArrCount = 0;
		for (int left = 0; left < n; left++) {
			while (right < n && maxCount < k) {
				if (a[right] == max) {
					maxCount++;
				}
				right++;
			}
			if (maxCount < k) {
				break;
			}
			subArrCount += n - right + 1;
			if (a[left] == max) {
				maxCount--;
			}
		}
		return subArrCount;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 2, 3, 3 };
		System.out.println(countSubArr(a1, 2)); // 6
		int[] a2 = { 1, 4, 2, 1 };
		System.out.println(countSubArr(a2, 3)); // 0
	}
}
