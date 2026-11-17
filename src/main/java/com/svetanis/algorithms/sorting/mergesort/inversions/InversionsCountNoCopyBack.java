package com.svetanis.algorithms.sorting.mergesort.inversions;

// Count inversions (the classic problem; no LeetCode number)
//
// Takes an array a. Returns how many pairs of positions i < j have a[i] > a[j]. Sorts a as a side
// effect.
//
// The same count as InversionsCountNewBuffer: pairs inside each half are counted by the recursion,
// and pairs across the halves while merging, mid - i + 1 at a time whenever the right run's
// front is taken first. What changes is that nothing is ever copied back. Two full arrays hold the
// same values at the start. A call sorts its range into dst by first sorting each half into src
// (the recursive calls get the two arrays swapped) and then merging src's halves into dst. Each
// level reads from the array the level below wrote, so the roles alternate all the way down, and
// the top call's dst is a itself.
//
// Three files, one algorithm; they differ only in where the merge writes (* is this file):
//
//   file                            merge writes into                 then
//   InversionsCountNewBuffer        a new array the size of the range copies the range back into a
//   InversionsCountSharedBuffer     one array made once, shared       copies the range back into a
// * InversionsCountNoCopyBack       the other of two full arrays      nothing: the two arrays swap
//                                                                     roles at every level

public final class InversionsCountNoCopyBack {
	// Time Complexity: O(n log n), log n levels of merging, each level touching every element once
	// Space Complexity: O(n), the second array, plus the O(log n) recursion

	public static long count(int[] a) {
		int n = a.length;
		int[] aux = a.clone(); // the same values: a range of one is already "sorted" in both
		return sortAndCount(aux, a, 0, n - 1);
	}

	// sorts src[lo..hi]'s values into dst[lo..hi], counting inversions on the way
	private static long sortAndCount(int[] src, int[] dst, int lo, int hi) {
		long count = 0; // long: a reversed array of n has n(n - 1)/2 inversions, past int at n = 65,537

		if (lo >= hi) { // BASE: one element or none
			return count;
		}

		int mid = lo + (hi - lo) / 2; // SPLIT: the left run is lo..mid, the right run mid + 1..hi
		count += sortAndCount(dst, src, lo, mid); // swapped: the halves end up sorted in src
		count += sortAndCount(dst, src, mid + 1, hi);
		count += merge(src, dst, lo, mid, hi); // MERGE: the across pairs, are merged from src into dst
		return count;
	}

	private static long merge(int[] src, int[] dst, int lo, int mid, int hi) {
		int i = lo, j = mid + 1, k = lo; // k from lo: dst is indexed like src
		long count = 0;

		while (i <= mid && j <= hi) { // WALK: hi is included
			if (src[i] <= src[j]) { // TAKE LEFT: <=, an equal pair is not an inversion
				dst[k++] = src[i++];
			} else { // TAKE RIGHT
				dst[k++] = src[j++];
				count += mid - i + 1; // every left src[i..mid] is bigger
			}
		}

		while (i <= mid) { // REST
			dst[k++] = src[i++];
		}
		while (j <= hi) {
			dst[k++] = src[j++];
		}
		return count;
	}

	public static void main(String[] args) {
		int[] array = { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51,
				7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63,
				97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50,
				56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33,
				54 };
		System.out.println(count(array)); // 2372
	}
}
