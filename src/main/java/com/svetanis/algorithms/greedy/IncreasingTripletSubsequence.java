package com.svetanis.algorithms.greedy;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.asList;

import java.util.List;

// 334. Increasing Triplet Subsequence

public final class IncreasingTripletSubsequence {
	// Time Complexity: O(n)

	public static boolean increasingTriplet(int[] a) {
		int low = Integer.MAX_VALUE;
		int mid = Integer.MAX_VALUE;
		for (int curr : a) {
			if (curr <= low) {
				low = curr;
			} else if (curr <= mid) {
				mid = curr;
			} else { // curr > mid
				return true;
			}
		}
		return false;
	}

	public static boolean increasingTriplet(List<Integer> list) {
		int low = MAX_VALUE;
		int mid = MAX_VALUE;
		for (int curr : list) {
			if (curr > mid) {
				return true;
			}
			if (curr <= low) {
				low = curr;
			} else {
				mid = curr;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(increasingTriplet(asList(1, 2, 3, 4, 5))); // true
		System.out.println(increasingTriplet(asList(5, 4, 3, 2, 1))); // false
		System.out.println(increasingTriplet(asList(2, 1, 5, 0, 4, 6))); // true

		int[] a1 = { 1, 2, 3, 4, 5 };
		int[] a2 = { 5, 4, 3, 2, 1 };
		int[] a3 = { 2, 1, 5, 0, 4, 6 };
		System.out.println(increasingTriplet(a1)); // true
		System.out.println(increasingTriplet(a2)); // false
		System.out.println(increasingTriplet(a3)); // true
	}
}
