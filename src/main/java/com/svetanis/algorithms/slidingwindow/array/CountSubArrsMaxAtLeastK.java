package com.svetanis.algorithms.slidingwindow.array;

import java.util.Arrays;

// 2962. Count Subarrays Where Max Element Appears at Least K Times

public final class CountSubArrsMaxAtLeastK {
	// Time Complexity: O(n)

	public static long countSubArr(int[] a, int k) {
		int n = a.length;
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

	private static int max(int[] a) {
		int max = 0;
		for (int num : a) {
			max = Math.max(max, num);
		}
		return max;
	}

	public static long countSubArr2(int[] a, int k) {
		int n = a.length;
		int max = Arrays.stream(a).max().getAsInt();
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
