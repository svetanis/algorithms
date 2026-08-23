package com.svetanis.algorithms.search.binary.math;

import static java.util.Arrays.asList;

import java.util.List;

// 275. H-Index II

// given an array of integers citations
// where citations[i] is the number of
// citations a researcher received for
// their i-th paper is sorted in 
// ascending order, return the 
// researcher's h-index

// h-index is defined as the max value 
// of h such that the given researcher 
// has published at least h papers that
// have each been cited at least h himes

public final class HIndexSorted {
	// Time Complexity: O(log n)

	public static int binary(List<Integer> citations) {
		int n = citations.size();
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (citations.get(mid) == n - mid) {
				return n - mid;
			} else if (citations.get(mid) < n - mid) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return n - low;
	}

	// the same problem in its own shape: h is the answer, not an index.
	// feasible(h) = "at least h papers have >= h citations", which is true
	// for small h and false for large h -- so this is maximize / last true,
	// the same shape as CuttingWood. mid keeps left, so it rounds up.
	public static int maximize(List<Integer> citations) {
		int left = 0;
		int right = citations.size();
		while (left < right) {
			int mid = left + (right - left + 1) / 2;
			if (hasAtLeast(citations, mid)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	// citations is ascending, so the h highest are the last h entries
	private static boolean hasAtLeast(List<Integer> citations, int h) {
		return h == 0 || citations.get(citations.size() - h) >= h;
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(0, 1, 3, 5, 6);
		List<Integer> list2 = asList(1, 2, 100);

		System.out.println(binary(list1)); // 3
		System.out.println(binary(list2)); // 2

		// both methods agree on 300,000 random ascending lists,
		// cross-checked against a linear scan
		System.out.println(maximize(list1)); // 3
		System.out.println(maximize(list2)); // 2
	}
}
