package com.svetanis.algorithms.twopointers.triplet;

import java.util.HashMap;
import java.util.Map;

// Count geometric progression triplets with a given ratio
//
// Input: an array of integers, repeats allowed, and a whole-number ratio r >= 1.
// Return: how many triples of positions i < j < k have a[j] == a[i] * r and
// a[k] == a[j] * r.
//
// The one idea: FIX the middle term. For a middle value x, the first term must be x / r and
// sit to its LEFT; the third must be x * r and sit to its RIGHT. Keep two frequency maps --
// the values already passed, and the values still ahead -- and this middle completes
// left[x / r] * right[x * r] triplets. When x % r != 0 there is no whole first term.
//
// Sibling: twopointers.triplet.GeometricTriplets -- a different question: LISTS the
//   triplets with ANY whole ratio, in a sorted array of distinct numbers.
//
// Time: O(n) -- two passes, a map update or lookup per step.
// Space: O(n) for the two maps.

public final class GeometricTripletsHashing {

	public static int countTriplets(int[] a, int r) {
		int count = 0;
		Map<Integer, Integer> right = new HashMap<>();   // the values still ahead
		Map<Integer, Integer> left = new HashMap<>();    // the values already passed
		for (int element : a) {
			right.put(element, right.getOrDefault(element, 0) + 1); // COUNT everything as ahead
		}
		for (int element : a) {                                     // FIX the middle term
			right.put(element, right.getOrDefault(element, 0) - 1); // DROP it from the right
			if (element % r == 0) {                                 // SKIP: no whole first term
				int first = element / r;
				int second = element * r;
				count += left.getOrDefault(first, 0) * right.getOrDefault(second, 0); // COUNT
			}
			left.put(element, left.getOrDefault(element, 0) + 1);   // MOVE it to the left
		}

		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 4 };
		System.out.println(countTriplets(a1, 2)); // 1

		int[] a2 = { 5, 15, 45 };
		System.out.println(countTriplets(a2, 3)); // 1

		int[] a3 = { 2, 1, 2, 4, 8, 8 };
		System.out.println(countTriplets(a3, 2)); // 5
	}
}