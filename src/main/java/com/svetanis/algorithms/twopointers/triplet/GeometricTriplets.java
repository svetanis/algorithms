package com.svetanis.algorithms.twopointers.triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Geometric progression triplets in a sorted array
//
// Input: a sorted array of DISTINCT positive integers.
// Return: every triplet a[l] < a[i] < a[r] (positions l < i < r) that is a geometric
// progression with a WHOLE-number ratio q: a[i] == a[l] * q and a[r] == a[i] * q.
//
// The one idea: FIX the middle term a[i] and walk outwards from it. Moving left makes the
// left ratio a[i] / a[left] bigger; moving right makes the right ratio a[right] / a[i]
// bigger. So whichever ratio is smaller is the one to raise -- the converging pointers of
// Two Sum, run apart. The number with the smaller ratio has no partner further out: every
// partner there has an even larger ratio. A number that does not divide a[i], or that
// a[i] does not divide, can never give a whole ratio and is dropped first.
//
// Sibling: twopointers.triplet.GeometricTripletsHashing -- a different question: COUNTS
//   triples of positions for a GIVEN ratio r, in any array, with two frequency maps.
//
// Time: O(n^2) -- n middle terms; each walk moves a pointer outwards on every step.
// Space: O(1) besides the output.

public final class GeometricTriplets {

	public static List<List<Integer>> triplets(int[] a) {
		int n = a.length;
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 1; i < n - 1; i++) {                        // FIX the middle term a[i]
			int left = i - 1;                                    // START: its two neighbours
			int right = i + 1;
			while (left >= 0 && right < n) {                     // STOP: one side ran out
				boolean leftDivides = a[i] % a[left] == 0;       // a[left] can be a first term
				boolean rightDivides = a[right] % a[i] == 0;     // a[right] can be a third term
				if (!leftDivides) {
					left--;                                      // DROP LEFT: no whole ratio to a[i]
				} else if (!rightDivides) {
					right++;                                     // DROP RIGHT: no whole ratio from a[i]
				} else if (a[i] / a[left] < a[right] / a[i]) {
					left--;                                      // DROP LEFT: its ratio is the smaller
				} else if (a[i] / a[left] > a[right] / a[i]) {
					right++;                                     // DROP RIGHT: its ratio is the smaller
				} else {
					list.add(Arrays.asList(a[left], a[i], a[right])); // FOUND: equal ratios
					left--;                                      // MOVE both: distinct values, no second partner
					right++;
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 4, 16 };
		System.out.println(triplets(a1)); // [[1, 2, 4], [1, 4, 16]]

		int[] a2 = { 1, 2, 6, 10, 18, 54 };
		System.out.println(triplets(a2)); // [[2, 6, 18], [6, 18, 54]]

		int[] a3 = { 2, 8, 10, 15, 16, 30, 32, 64 };
		System.out.println(triplets(a3)); // [[2, 8, 32], [8, 16, 32], [16, 32, 64]]

		int[] a4 = { 1, 2, 6, 18, 36, 54 };
		System.out.println(triplets(a4)); // [[2, 6, 18], [1, 6, 36], [6, 18, 54]]

		int[] a5 = { 1, 2, 3, 6, 18, 22 };
		System.out.println(triplets(a5)); // [[2, 6, 18]]
	}
}