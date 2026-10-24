package com.svetanis.algorithms.greedy;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.asList;

import java.util.List;

// 334. Increasing Triplet Subsequence
//
// Input: a -- an array of integers. Returns whether there are indices i < j < k with
// a[i] < a[j] < a[k]. On LeetCode: 1 <= a.length <= 5 * 10^5, -2^31 <= a[i] <= 2^31 - 1.
//
// The one idea: walk left to right holding two numbers. low -- the smallest value seen so far.
// mid -- the smallest value seen so far that has a smaller value somewhere before it, so the end
// of the best pair. A value larger than mid finishes a triplet. Any other value can only make low
// or mid smaller, and a smaller low or mid only makes it easier for a later value to finish, so
// replacing them never loses an answer. low may move to a value that comes after mid's partner;
// mid still has its partner before it, so mid keeps its meaning.
//
// Both start at Integer.MAX_VALUE, which no value is larger than, so an unset mid never finishes
// a triplet. A real value equal to MAX_VALUE that lands in mid cannot be finished either, which is
// right: nothing is larger than it.
//
// The two methods make the same walk. The int[] one tries low, then mid, then finishes; the List
// one tests for the finish first.
//
// Time: O(n) -- one pass.
// Space: O(1) -- two numbers.

public final class IncreasingTripletSubsequence {

	public static boolean increasingTriplet(int[] a) {
		int low = Integer.MAX_VALUE; // the smallest value so far
		int mid = Integer.MAX_VALUE; // the smallest value so far with a smaller one before it
		for (int curr : a) {
			if (curr <= low) {
				low = curr; // NEW LOW
			} else if (curr <= mid) {
				mid = curr; // NEW MID: low comes before it and is smaller
			} else { // curr > mid
				return true; // FINISH: low < mid < curr, in that order
			}
		}
		return false; // RETURN: no value ever topped mid
	}

	public static boolean increasingTriplet(List<Integer> list) {
		int low = MAX_VALUE;
		int mid = MAX_VALUE;
		for (int curr : list) {
			if (curr > mid) {
				return true; // FINISH: larger than the end of a pair
			}
			if (curr <= low) {
				low = curr; // NEW LOW
			} else {
				mid = curr; // NEW MID: low < curr <= mid
			}
		}
		return false; // RETURN: no value ever topped mid
	}

	public static void main(String[] args) {
		System.out.println(increasingTriplet(asList(1, 2, 3, 4, 5))); // true
		System.out.println(increasingTriplet(asList(5, 4, 3, 2, 1))); // false
		System.out.println(increasingTriplet(asList(2, 1, 5, 0, 4, 6))); // true

		int[] a1 = { 1, 2, 3, 4, 5 };
		int[] a2 = { 5, 4, 3, 2, 1 };
		int[] a3 = { 2, 1, 5, 0, 4, 6 };
		System.out.println(increasingTriplet(a1)); // true
		System.out.println(increasingTriplet(a2)); // false
		System.out.println(increasingTriplet(a3)); // true
	}
}
