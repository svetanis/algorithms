package com.svetanis.algorithms.slidingwindow.array;

import static java.util.Arrays.asList;

import java.util.List;

// Max continuous series of 1s, with its position -- no LeetCode number; source unknown

// You are given with an array of 1s and 0s. 
// And you are given with an integer M, 
// which signifies number of flips allowed.
// Find the position of zeros which when flipped 
// will produce maximum continuous series of 1s.
// For this problem, return the indices of max
// continuous series of 1s in order.

// Takes the list and k (the M), k >= 0. Returns the block as a Pair:
// left is the index it starts at, right is how many numbers it holds,
// so its indices are left .. left + right - 1 and the 0s inside it are
// the ones to flip. When several blocks tie for longest, the one that
// starts first is returned. An empty list returns Pair{left=0, right=0}.

// A block can be made all 1s with k flips exactly when it holds at most
// k 0s. For each right end the window keeps the leftmost start that
// holds at most k 0s, so it is the longest block ending there; a new
// best is taken only when strictly longer, which keeps the first one.

// Time: O(n) -- each index enters the window once and leaves at most once
// Space: O(1) -- a zero count, two indices and the best block

// Siblings, the same window:
//   MaximizeConsecutiveOnesSubmit (1004) -- k flips, returns the length only.
//   MaximizeConsecutiveOnesII (487) -- one flip, returns the length only.
//   LongestSubArrAfterDeletingOneElement (1493) -- one element must be
//     deleted, so it measures right - left.
//   twopointers.dups.MaxConsecutiveOnes (485) -- no flips at all.

public final class MaximizeConsecutiveOnes {

	// left: where the block starts; right: how many numbers it holds
	public record Pair(int left, int right) {
		@Override
		public String toString() {
			return "Pair{left=%d, right=%d}".formatted(left, right);
		}
	}

	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static Pair sw(List<Integer> a, int k) {
		int n = a.size();
		int left = 0;
		int zeros = 0;
		int max = 0;
		int start = 0;
		for (int right = 0; right < n; right++) {
			if (a.get(right) == 0) {
				zeros++; // ADD: a 0 entering
			}
			while (zeros > k) { // SHRINK: while more 0s than flips
				if (a.get(left) == 0) {
					zeros--; // REMOVE: a 0 leaving; a 1 changes nothing
				}
				left++;
			}
			if (right - left + 1 > max) { // MEASURE: strictly longer, so a tie keeps the first block
				max = right - left + 1;
				start = left;
			}
		}
		return new Pair(start, max);
	}

	public static void main(String args[]) {
		List<Integer> a = asList(1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1);
		System.out.println(sw(a, 2)); // Pair{left=3, right=8}

		List<Integer> b = asList(1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1);
		System.out.println(sw(b, 1)); // Pair{left=6, right=5}

		List<Integer> c = asList(0, 0, 0, 1);
		System.out.println(sw(c, 4)); // Pair{left=0, right=4}

		List<Integer> d = asList(1, 1, 0, 1, 1, 0, 0, 1, 1, 1);
		System.out.println(sw(d, 1)); // Pair{left=0, right=5}
	}
}