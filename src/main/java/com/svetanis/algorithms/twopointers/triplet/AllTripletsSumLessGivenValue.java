package com.svetanis.algorithms.twopointers.triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// All triplets with sum smaller than a target
//
// Input: an array of integers and a target k. Repeats are allowed: a triplet of values is
// then listed once for every triple of positions that forms it.
// Return: every triple of positions with a[i] + a[j] + a[l] < k, as its three values in
// ascending order.
//
// The one idea: sort, then FIX the smallest number a[i] and converge on the numbers to its
// right, looking for pairs below k - a[i]. When a[left] + a[right] fits, every partner of
// a[left] from left + 1 to right fits too, because each is at most a[right] -- list all of
// them in one sweep and drop left. Otherwise a[right] is too big even with the smallest
// partner: drop right. This is 3Sum Smaller (LC 259) writing out the block it would count.
//
// Sibling: twopointers.triplet.CountTripletsSumLessGivenValue -- LC 259, the same loop adding
//   right - left instead of listing the block.
//
// Time: O(n^2 + t) for t triplets listed -- n fixed numbers, an O(n) converging pass for each,
// plus one step per triplet written; t can reach n^3 / 6, so O(n^3) in the worst case.
// Space: O(1) besides the output; the array is sorted in place.

public final class AllTripletsSumLessGivenValue {

	public static List<List<Integer>> triplets(int[] a, int k) {
		Arrays.sort(a);                                  // SORT: the converging pass needs it
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {             // FIX the smallest number a[i]
			int target = k - a[i];                       // the other two must sum below this
			list.addAll(triplets(a, target, i));
		}
		return list;
	}

	private static List<List<Integer>> triplets(int[] a, int target, int first) {
		int left = first + 1;                            // START: the two ends right of first
		int right = a.length - 1;
		List<List<Integer>> list = new ArrayList<>();
		while (left < right) {                           // STOP: a pair needs two positions
			int sum = a[left] + a[right];                // COMPARE
			if (sum >= target) {
				right--;                                 // DROP RIGHT: too big even with the smallest
			} else {
				for (int i = right; i > left; i--) {     // RECORD: every partner in (left, right] fits
					list.add(Arrays.asList(a[first], a[left], a[i]));
				}
				left++;                                  // DROP LEFT: all its triplets are listed
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] a = { 5, 1, 3, 4, 7 };
		System.out.println(triplets(a, 12)); // [[1, 3, 7], [1, 3, 5], [1, 3, 4], [1, 4, 5]]
	}
}
