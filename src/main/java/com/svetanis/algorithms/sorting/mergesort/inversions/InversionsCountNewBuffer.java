package com.svetanis.algorithms.sorting.mergesort.inversions;

// Count inversions (the classic problem; no LeetCode number)
//
// Takes an array a. Returns how many pairs of positions i < j have a[i] > a[j]. Sorts a as a side
// effect.
//
// Every pair lies wholly in the left half, wholly in the right half, or has one end in each. The
// recursion counts the first two kinds. The third kind is counted while merging the two sorted
// halves: when the right run's front a[j] is taken before the left run's front a[i], a[j] is
// smaller than a[i] and, the left run being sorted, smaller than everything after a[i] in it too.
// That is every left element still waiting, a[i..mid], mid - i + 1 of them, found by one
// comparison. Sorting the halves first is allowed because an across pair is one value from each
// half, the left one always first, so reordering inside a half cannot change the across count.
// Taking a[i] first on a tie (<=) keeps equal values from counting.
//
// Three files, one algorithm; they differ only in where the merge writes (* is this file):
//
//   file                            merge writes into                 then
// * InversionsCountNewBuffer        a new array the size of the range copies the range back into a
//   InversionsCountSharedBuffer     one array made once, shared       copies the range back into a
//   InversionsCountNoCopyBack       the other of two full arrays      nothing: the two arrays swap
//                                                                     roles at every level
//
// Related: ReversePairs (493, com.svetanis.algorithms.todo) counts a[i] > 2 * a[j] in a separate
// pass before the same merge; SurpasserCountMergeSort counts, for each element, the larger ones
// after it.

public final class InversionsCountNewBuffer {
	// Time Complexity: O(n log n), log n levels of merging, each level touching every element once
	// Space Complexity: O(n), the largest merge buffer, plus the O(log n) recursion

	public static long count(int[] a) {
		return sortAndCount(a, 0, a.length - 1);
	}

	private static long sortAndCount(int[] a, int lo, int hi) {
		if (lo >= hi) { // BASE: one element or none
			return 0;
		}
		int mid = lo + (hi - lo) / 2; // SPLIT: the left run is lo..mid, the right run mid + 1..hi
		// long: a reversed array of n has n(n - 1)/2 inversions, past int at n = 65,537
		long count = sortAndCount(a, lo, mid) + sortAndCount(a, mid + 1, hi); // both come back sorted
		count += merge(a, lo, mid, hi); // MERGE: the across pairs
		return count;
	}

	private static long merge(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1, k = 0; // TEMP
		int[] temp = new int[hi - lo + 1]; // the size of this range, not of the whole array
		long count = 0;
		while (i <= mid && j <= hi) { // WALK: hi is included
			if (a[i] <= a[j]) { // TAKE LEFT: <=, an equal pair is not an inversion
				temp[k++] = a[i++];
			} else { // TAKE RIGHT
				temp[k++] = a[j++];
				count += mid - i + 1; // every left a[i..mid] is bigger
			}
		}
		while (i <= mid) { // REST
			temp[k++] = a[i++];
		}
		while (j <= hi) {
			temp[k++] = a[j++];
		}
		for (int index = 0; index < temp.length; index++) { // BACK: into a[lo..hi]
			a[lo + index] = temp[index];
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 2, 4, 1, 3, 5 };
		System.out.println(count(a)); // 3: (2,1), (4,1), (4,3)

		int[] equal = { 2, 2, 2 };
		System.out.println(count(equal)); // 0: equal values are not inversions

		int[] reversed = { 4, 3, 2, 1 };
		System.out.println(count(reversed)); // 6: every pair
	}
}
