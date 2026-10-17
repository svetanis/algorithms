package com.svetanis.algorithms.slidingwindow.array;

// 487. Max Consecutive Ones II

// Takes a binary array a (only 0s and 1s). At most one 0 may be flipped
// to a 1. Returns the length of the longest run of 1s that can be made;
// 0 for an empty array.

// A block can be made all 1s with one flip exactly when it holds at
// most one 0. That is the window rule: shrink while the window holds
// two 0s, and the window is then the longest block ending at right.

// Two methods, the same answer:
//   sw -- the window shrinks as far as it must, and every window is measured.
//   subStrLen -- the window never shrinks: when it holds too many 0s,
//     left moves one step with right, so the size stays the same. The
//     size only grows when the window is valid, so at the end it is the
//     best size seen, and that is the answer with no max kept.

// Time: O(n) for both -- right moves n times and left at most n times
// Space: O(1) for both -- a zero count and two indices

// Siblings, the same window:
//   MaximizeConsecutiveOnesSubmit (1004) -- k flips instead of one.
//   MaximizeConsecutiveOnes -- k flips, and returns where the block starts.
//   LongestSubArrAfterDeletingOneElement (1493) -- one element must be
//     DELETED instead of flipped, so it measures right - left.
//   twopointers.dups.MaxConsecutiveOnes (485) -- no flips at all.

public final class MaximizeConsecutiveOnesII {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int sw(int[] a) {
		int max = 0;
		int left = 0;
		int zeros = 0;
		for (int right = 0; right < a.length; right++) {
			if (a[right] == 0) {
				zeros++; // ADD: a 0 entering
			}
			while (zeros > 1) { // SHRINK: while two 0s
				if (a[left] == 0) {
					zeros--; // REMOVE: a 0 leaving; a 1 changes nothing
				}
				left++;
			}
			int len = right - left + 1; // MEASURE: at most one 0 inside, flipped
			max = Math.max(max, len);
		}
		return max;
	}

	public static int subStrLen(int[] a) {
		int left = 0;
		int right = 0;
		int zerosAllowed = 1;
		while (right < a.length) {
			if (a[right++] == 0) {
				zerosAllowed--; // ADD: a 0 entering spends the flip
			}
			if (zerosAllowed < 0 && a[left++] == 0) { // SHRINK: one step only, so the size holds
				zerosAllowed++; // REMOVE: a 0 leaving gives the flip back
			}
		}
		return right - left; // MEASURE: the size never shrank, so it is the best seen
	}

	public static void main(String args[]) {
		int[] a1 = { 1, 0, 1, 1, 0 };
		System.out.println(sw(a1)); // 4
		System.out.println(subStrLen(a1)); // 4
	}
}