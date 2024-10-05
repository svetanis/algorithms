package com.svetanis.algorithms.search.binary.math;

// 278. First Bad Version

public final class FirstBadVersion {
	// Time Complexity: O(log n)

	public static int badVersion(int n) {
		int left = 1;
		int right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static boolean isBadVersion(int version) {
		return false;
	}

	public static void main(String[] args) {
		System.out.println(badVersion(5));
		System.out.println(badVersion(1));
	}
}