package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 1877. Minimize Maximum Pair Sum in Array
//
// Input: an array of even length n.
// Return: the smallest possible value of the largest pair sum, over every way of
// splitting the array into n / 2 pairs.
//
// The one idea: the largest number has to go in some pair, and the cheapest partner
// for it is the smallest number. Pair those two, set them aside, and the same holds
// for what is left. After a sort that is the converging loop: smallest with largest,
// second smallest with second largest, and so on, keeping the largest sum seen.
// Every pair is formed -- nothing is dropped unpaired -- so both pointers move on
// every step.
// The array is sorted in place: the caller's order is lost.
//
// Time: O(n log n) -- the sort; the loop after it is O(n).
// Space: O(log n) -- the sort's recursion; the loop itself is O(1).

public final class MinMaxPairSum {

	public static int minPairSum(int[] a) {
		Arrays.sort(a);                   // SORT: smallest and largest at the ends
		int max = 0;
		int left = 0;                     // START: the smallest number
		int right = a.length - 1;         // START: the largest number
		while (left < right) {            // STOP: every number is in a pair (n is even)
			int sum = a[left] + a[right]; // PAIR the smallest left with the largest left
			max = Math.max(max, sum);     // RECORD the largest pair sum
			left++;                       // MOVE both: the two are paired
			right--;
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 2, 3 };
		System.out.println(minPairSum(a1)); // 7

		int[] a2 = { 3, 5, 4, 2, 4, 6 };
		System.out.println(minPairSum(a2)); // 8
	}
}
