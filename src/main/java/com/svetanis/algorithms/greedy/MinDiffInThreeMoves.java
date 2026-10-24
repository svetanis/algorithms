package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
//
// Input: nums -- an array of whole numbers. One move changes one element to any value. Returns the
// smallest possible difference between the largest and smallest element after at most three moves.
// On LeetCode: 1 <= nums.length <= 10^5, -10^9 <= nums[i] <= 10^9, so the largest difference,
// 2 * 10^9, still fits in an int.
//
// The one idea: changing an element to any value is the same as deleting it, because the changed
// element can be given the value of an element that stays, and then it moves neither the largest
// nor the smallest. So the question is: delete up to 3 elements so that what remains is as close
// together as possible. After sorting, deleting an element from the middle leaves the largest and
// the smallest where they were, so it buys nothing; the deletions only help at the two ends. What
// is not settled in advance is how the 3 deletions split between the ends, and there are only 4
// splits: 3 from the top, 2 from the top and 1 from the bottom, 1 and 2, or 3 from the bottom. The
// loop tries all four and keeps the best. With 4 or fewer elements, 3 moves can make all of them
// equal, so the answer is 0.
//
// Time: O(n log n) -- the sort; the four tries after it are constant work.
// Space: O(log n) -- the sort works inside the caller's array (and reorders it); its recursion
// keeps O(log n) frames.

public final class MinDiffInThreeMoves {

	public static int minDiff(int[] nums) {
		Arrays.sort(nums); // SORT: the ends are the candidates for deletion
		int n = nums.length;
		if (n <= 4) {
			return 0; // BASE: 3 moves can flatten 4 or fewer elements to one value
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) { // TRY each split of the 3 deletions
			// drop i from the front and the remaining 3 - i from the back, so the
			// survivors are the window nums[i .. n - 4 + i]
			// i = 0 removes the 3 largest, i = 3 removes the 3 smallest
			min = Math.min(min, nums[n - 4 + i] - nums[i]); // RECORD the closest window
		}
		return min;
	}

	public static void main(String[] args) {
		int[] p1 = { 5, 3, 2, 4 };
		System.out.println(minDiff(p1)); // 0
		int[] p2 = { 1, 5, 0, 10, 14 };
		System.out.println(minDiff(p2)); // 1
		int[] p3 = { 3, 100, 20 };
		System.out.println(minDiff(p3)); // 0
	}
}
