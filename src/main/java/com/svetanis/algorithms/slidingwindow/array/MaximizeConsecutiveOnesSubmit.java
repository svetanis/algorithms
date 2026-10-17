package com.svetanis.algorithms.slidingwindow.array;

// 1004. Max Consecutive Ones III

// Given an array containing 0s and 1s, 
// if you are allowed to replace no more than ‘k’ 0s with 1s, 
// find the length of the longest subarray having all 1s.
// Takes the binary array a and k >= 0; returns that length, 0 for an
// empty array.

// A block can be made all 1s with k flips exactly when it holds at most
// k 0s. That is the window rule: shrink while the window holds more
// than k 0s, and the window is then the longest block ending at right.

// Two methods, the same answer:
//   sw -- counts the 0s, shrinks as far as it must, measures every window.
//   subStrLen -- counts the 1s instead (the 0s are the size minus the
//     1s), and never shrinks: when over budget, left moves one step with
//     right, so the size holds. The size only grows while the window is
//     valid, so the largest size measured is the answer.

// Time: O(n) for both -- right moves n times and left at most n times
// Space: O(1) for both -- one count and two indices

// Siblings, the same window:
//   MaximizeConsecutiveOnesII (487) -- k = 1.
//   MaximizeConsecutiveOnes -- k flips, and returns where the block starts.
//   LongestSubArrAfterDeletingOneElement (1493) -- one element must be
//     DELETED, so it measures right - left.
//   twopointers.dups.MaxConsecutiveOnes (485) -- no flips at all.

public final class MaximizeConsecutiveOnesSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int sw(int[] a, int k) {
		int max = 0;
		int left = 0;
		int zeros = 0;
		for (int right = 0; right < a.length; right++) {
			if (a[right] == 0) {
				zeros++; // ADD: a 0 entering
			}
			while (zeros > k) { // SHRINK: while more 0s than flips
				if (a[left] == 0) {
					zeros--; // REMOVE: a 0 leaving; a 1 changes nothing
				}
				left++;
			}
			int len = right - left + 1; // MEASURE: at most k 0s inside, all flipped
			max = Math.max(max, len);
		}
		return max;
	}

	public static int subStrLen(int[] a, int k) {
		int max = 0;
		int ones = 0;
		int left = 0;
		for (int right = 0; right < a.length; right++) {
			if (a[right] == 1) {
				ones++; // ADD: a 1 entering
			}
			if (right - left + 1 - ones > k) { // SHRINK: one step only, so the size holds
				if (a[left] == 1) {
					ones--; // REMOVE: a 1 leaving
				}
				left++;
			}
			int len = right - left + 1; // MEASURE
			max = Math.max(max, len);
		}
		return max;
	}

	public static void main(String args[]) {
		int[] a1 = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		System.out.println(sw(a1, 2)); // 6

		int[] a2 = { 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 };
		System.out.println(sw(a2, 3)); // 10
		
    int[] a3 = { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 };
    System.out.println(subStrLen(a3, 2)); // 6

    int[] a4 = { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 };
    System.out.println(subStrLen(a4, 3)); // 9
	}
}