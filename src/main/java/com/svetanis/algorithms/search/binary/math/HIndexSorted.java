package com.svetanis.algorithms.search.binary.math;

import static java.util.Arrays.asList;

import java.util.List;

// Leet 275. H-IndexII
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

	public static void main(String[] args) {
		List<Integer> list1 = asList(0, 1, 3, 5, 6);
		List<Integer> list2 = asList(1, 2, 100);

		System.out.println(binary(list1)); // 3
		System.out.println(binary(list2)); // 2
	}
}
