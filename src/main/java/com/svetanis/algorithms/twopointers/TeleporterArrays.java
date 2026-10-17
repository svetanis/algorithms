package com.svetanis.algorithms.twopointers;

import static java.util.Arrays.asList;

import java.util.List;

// 1537. Get the Maximum Score
//
// Input: two arrays, each sorted in increasing order with no repeats inside it.
// Return: the largest sum along a path, modulo 10^9 + 7. A path walks one array from
// its start, left to right; at a value both arrays hold it may jump to that same value
// in the other array and carry on there. A shared value is counted once.
//
// The one idea: the shared values cut both arrays into matching segments, and every
// path passes through every shared value. Between two shared values a path walks
// exactly one of the two segments, freely chosen, so the best path takes the larger
// segment sum each time. Merge the two arrays as in merge sort, adding each number to
// its own array's running segment sum; at a shared value, settle the larger sum plus
// the value once, and restart both sums.
// The comparison runs on the true sums, held in long; only the running total is reduced
// modulo 10^9 + 7. Reducing the segment sums first would compare the wrong numbers.
//
// Time: O(n1 + n2) -- each number is read once.
// Space: O(1).

public final class TeleporterArrays {

	private static final int MOD = (int) 1e9 + 7;

	public static int maxScore(List<Integer> list1, List<Integer> list2) {
		int n1 = list1.size();
		int n2 = list2.size();
		long sum1 = 0;                                  // the current segment of list1
		long sum2 = 0;                                  // the current segment of list2
		long maxScore = 0;
		int left = 0, right = 0;                        // START: left walks list1, right walks list2
		while (left < n1 || right < n2) {               // STOP: both arrays are read
			// COMPARE with equals: == on two Integers above 127 compares the objects
			if (left < n1 && right < n2 && list1.get(left).equals(list2.get(right))) {
				maxScore += Math.max(sum1, sum2) + list1.get(left); // SETTLE: the larger segment, the shared value once
				maxScore %= MOD;
				sum1 = 0;                               // RESTART both segments
				sum2 = 0;
				left++;
				right++;
				continue;
			}
			if (left == n1 || (right != n2 && list1.get(left) > list2.get(right))) {
				sum2 += list2.get(right);               // MOVE the side with the smaller value: list2
				right++;
			} else {
				sum1 += list1.get(left);                // MOVE the side with the smaller value: list1
				left++;
			}
		}
		maxScore += Math.max(sum1, sum2);               // SETTLE the last segment
		return (int) (maxScore % MOD);
	}

	public static void main(String[] args) {
		System.out.println(maxScore(asList(2, 4, 5, 8, 10), asList(4, 6, 8, 9)));    // 30
		System.out.println(maxScore(asList(1, 3, 5, 7, 9), asList(3, 5, 100)));      // 109
		System.out.println(maxScore(asList(1, 2, 3, 4, 5), asList(6, 7, 8, 9, 10))); // 40
		System.out.println(maxScore(asList(1, 4, 5, 8, 9), asList(2, 3, 6, 7, 10))); // 28
	}
}
