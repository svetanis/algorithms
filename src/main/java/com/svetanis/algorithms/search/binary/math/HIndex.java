package com.svetanis.algorithms.search.binary.math;

import static com.google.common.collect.Ordering.natural;
import static java.util.Arrays.asList;

import java.util.List;

// 274. H-Index

// given an array of integers citations
// where citations[i] is the number of
// citations a researcher received for
// their i-th paper, return the 
// researcher's h-index

// h-index is defined as the max value 
// of h such that the given researcher 
// has published at least h papers that
// have each been cited at least h times

public final class HIndex {
	// Time Complexity: O(n log n)

	public static int binary(List<Integer> citations) {
		int low = 0;
		int high = citations.size();
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (hasAtLeastHPapersWithHCitations(citations, mid)) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return high;
	}

	private static boolean hasAtLeastHPapersWithHCitations(List<Integer> citations, int h) {
		int count = 0;
		for (int citation : citations) {
			if (citation >= h) {
				count++;
			}
		}
		return count >= h;
	}

	public static int hindex(List<Integer> citations) {
		List<Integer> reversed = natural().reverse().sortedCopy(citations);
		int h = 0;
		while (h < reversed.size() && reversed.get(h) >= h + 1) {
			h += 1;
		}
		return h;
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(3, 0, 6, 1, 5);
		List<Integer> list2 = asList(1, 3, 1);
		System.out.println(hindex(list1)); // 3
		System.out.println(hindex(list2)); // 1

		System.out.println(binary(list1)); // 3
		System.out.println(binary(list2)); // 1
	}
}
