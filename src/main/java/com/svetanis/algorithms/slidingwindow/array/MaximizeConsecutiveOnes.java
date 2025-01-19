package com.svetanis.algorithms.slidingwindow.array;

import static java.util.Arrays.asList;

import java.util.List;

import com.svetanis.java.base.Pair;

// You are given with an array of 1s and 0s. 
// And you are given with an integer M, 
// which signifies number of flips allowed.
// Find the position of zeros which when flipped 
// will produce maximum continuous series of 1s.
// For this problem, return the indices of max
// continuous series of 1s in order.

public final class MaximizeConsecutiveOnes {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static Pair<Integer, Integer> sw(List<Integer> a, int k) {
		int n = a.size();
		int left = 0;
		int zeros = 0;
		int max = 0;
		int start = 0;
		for (int right = 0; right < n; right++) {
			if (a.get(right) == 0) {
				zeros++;
			}
			while (zeros > k) {
				if (a.get(left) == 0) {
					zeros--;
				}
				left++;
			}
			if (right - left + 1 > max) {
				max = right - left + 1;
				start = left;
			}
		}
		return Pair.build(start, max);
	}

	public static void main(String args[]) {
		List<Integer> a = asList(1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1);
		System.out.println(sw(a, 2));

		List<Integer> b = asList(1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1);
		System.out.println(sw(b, 1));

		List<Integer> c = asList(0, 0, 0, 1);
		System.out.println(sw(c, 4));

		List<Integer> d = asList(1, 1, 0, 1, 1, 0, 0, 1, 1, 1);
		System.out.println(sw(d, 1));
	}
}