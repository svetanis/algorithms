package com.svetanis.algorithms.sorting.mergesort.inversions;

// Count inversions (the classic problem; no LeetCode number)
//
// Takes an array a. Returns how many pairs of positions i < j have a[i] > a[j]. Sorts a as a side
// effect.
//
// The same count as InversionsCountNewBuffer: pairs inside each half are counted by the recursion,
// and pairs across the halves while merging, mid - i + 1 at a time whenever the right run's
// front is taken first. What changes is the buffer: one array aux is made once, every merge writes
// its range into aux and copies that range back into a.
//
// Three files, one algorithm; they differ only in where the merge writes (* is this file):
//
//   file                            merge writes into                 then
//   InversionsCountNewBuffer        a new array the size of the range copies the range back into a
// * InversionsCountSharedBuffer     one array made once, shared       copies the range back into a
//   InversionsCountNoCopyBack       the other of two full arrays      nothing: the two arrays swap
//                                                                     roles at every level

public final class InversionsCountSharedBuffer {
	// Time Complexity: O(n log n), log n levels of merging, each level touching every element once
	// Space Complexity: O(n), aux, plus the O(log n) recursion

	public static long count(int[] a) {
		int n = a.length;
		int[] aux = new int[n];
		return sortAndCount(a, aux, 0, n - 1);
	}

	private static long sortAndCount(int[] a, int[] aux, int lo, int hi) {
		long count = 0; // long: a reversed array of n has n(n - 1)/2 inversions, past int at n = 65,537

		if (lo >= hi) { // BASE: one element or none
			return count;
		}
		int mid = lo + (hi - lo) / 2; // SPLIT: the left run is lo..mid, the right run mid + 1..hi
		count += sortAndCount(a, aux, lo, mid);
		count += sortAndCount(a, aux, mid + 1, hi);
		count += merge(a, aux, lo, mid, hi); // MERGE: the across pairs
		return count;
	}

	private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
		int i = lo, j = mid + 1, k = lo; // k from lo: aux is indexed like a
		long count = 0;

		while (i <= mid && j <= hi) { // WALK: hi is included
			if (a[i] <= a[j]) { // TAKE LEFT: <=, an equal pair is not an inversion
				aux[k++] = a[i++];
			} else { // TAKE RIGHT
				aux[k++] = a[j++];
				count += mid - i + 1; // every left a[i..mid] is bigger
			}
		}

		while (i <= mid) { // REST
			aux[k++] = a[i++];
		}
		while (j <= hi) {
			aux[k++] = a[j++];
		}

		for (int index = lo; index <= hi; index++) { // BACK: only this range was merged
			a[index] = aux[index];
		}

		return count;
	}

	public static void main(String[] args) {
		int[] a = { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7,
				87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97,
				43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56,
				94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33,
				54 };
		System.out.println(count(a)); // 2372
	}
}
