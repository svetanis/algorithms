package com.svetanis.algorithms.twopointers.dups;

// 485. Max Consecutive Ones
//
// Input: an array of 0s and 1s, 1 <= length <= 10^5.
// Return: the length of the longest run of 1s in a row.
//
// The one idea: carry the length of the run that is still open. A 1 makes it one
// longer; a 0 closes it, so it is compared with the best so far and reset to 0. A run
// that reaches the end of the array is never closed by a 0, so it is compared once
// more after the loop.
//
// Two methods, the same problem:
//   count(int[])  -- one pass, the open run carried in a counter
//   count2(int[]) -- from every 1, walks to the end of its run and measures it: a run
//                    of length L is walked L times, once from each of its 1s
// Siblings: slidingwindow.array.MaximizeConsecutiveOnesII (LC 487, one 0 may be flipped)
//   and MaximizeConsecutiveOnesSubmit (LC 1004, k 0s may be flipped) -- a window instead of a run.
//
// Time: count O(n) -- each number read once. count2 O(n^2) -- all 1s walks n + (n - 1) + ... + 1.
// Space: O(1).

public final class MaxConsecutiveOnes {

	public static int count(int[] a) {
		int count = 0;                        // START: the length of the open run
		int max = 0;
		for (int i = 0; i < a.length; i++) {  // READ every number once
			if (a[i] == 1) {
				count++;                      // COUNT: the run grows
			} else {
				max = Math.max(max, count);   // RECORD the run a 0 just closed
				count = 0;                    // START a new run after the 0
			}
		}
		return Math.max(max, count);          // RETURN: a run reaching the end is still open here
	}

	public static int count2(int[] a) {
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 1) {
				int count = 0;
				int j = i;
				while (j < a.length && a[j] == 1) { // COUNT the run that starts at i
					count += 1;
					j += 1;
				}
				max = Math.max(max, count);   // RECORD it
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 0, 1, 1, 1 };
		System.out.println(count(a)); // 3
		System.out.println(count2(a)); // 3
		int[] a1 = { 1, 0, 1, 1, 0, 1 };
		System.out.println(count(a1)); // 2
		System.out.println(count2(a1)); // 2
	}
}
