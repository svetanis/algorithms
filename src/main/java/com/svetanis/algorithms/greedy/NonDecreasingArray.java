package com.svetanis.algorithms.greedy;

// 665. Non-decreasing Array

public final class NonDecreasingArray {
	// Time Complexity: O(n)
	// Space Complexity: O(1) -- nothing is allocated. The repair is written
	// straight into the caller's array, which comes back modified: the array
	// IS the running state, and that is what keeps this O(1). Deliberate,
	// like the sibling CanPlaceFlowers605 -- but say it out loud before an
	// interviewer asks, because the signature promises only a boolean.
	// Need the caller's data intact? checkPossibility(a.clone()).

	public static boolean checkPossibility(int[] a) {
		int n = a.length;
		int count = 0;
		for (int i = 0; i < n - 1; i++) {
			if (a[i] > a[i + 1]) {
				count += 1;
				if (count > 1) {
					return false;
				}
				if (i == 0 || a[i - 1] <= a[i + 1]) {
					a[i] = a[i + 1];
				} else {
					a[i + 1] = a[i];
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] p1 = { 4, 2, 3 };
		System.out.println(checkPossibility(p1)); // true
		int[] p2 = { 4, 2, 1 };
		System.out.println(checkPossibility(p2)); // false
		int[] p3 = { 3, 4, 2, 3 };
		System.out.println(checkPossibility(p3)); // false
	}
}
