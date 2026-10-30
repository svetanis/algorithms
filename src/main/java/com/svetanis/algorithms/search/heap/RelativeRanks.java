package com.svetanis.algorithms.search.heap;

import java.util.Arrays;

// 506. Relative Ranks
//
// score[i] is athlete i's score, all scores different. Returns answer[i], athlete i's place:
// "Gold Medal", "Silver Medal" and "Bronze Medal" for the top three, the place number as a string
// for everyone else.
//
// Sort the athletes' indices by score, highest first: the position in that order is the place, and
// the index says whose answer it is. Sorting indices instead of scores keeps the link back to the
// athlete without a pair type.
//
// Time: O(n log n) -- the sort
// Space: O(n) for the indices and the answer
//
// Siblings, same problem:
//   RelativeRanksPQ -- a max-heap of (score, index) pairs hands the places out one poll at a time;
//     the same O(n log n), with a pair class and a medal helper instead of an array of medals

public final class RelativeRanks {

	public static String[] ranks(int[] scores) {
		int n = scores.length;
		Integer[] indices = new Integer[n];
		for (int i = 0; i < n; i++) {
			indices[i] = i;                                      // INDEX: one per athlete
		}
		Arrays.sort(indices, (a, b) -> Integer.compare(scores[b], scores[a])); // ORDER: highest score first
		String[] ranks = new String[n];
		String[] medals = new String[] { "Gold Medal", "Silver Medal", "Bronze Medal" };
		for (int i = 0; i < n; i++) {
			if (i < 3) {
				ranks[indices[i]] = medals[i];                   // MEDAL: places 1 to 3
			} else {
				ranks[indices[i]] = String.valueOf(i + 1);       // PLACE: position i is place i + 1
			}
		}
		return ranks;                                            // REPORT
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 3, 2, 1 };
		System.out.println(Arrays.toString(ranks(a1))); // [Gold Medal, Silver Medal, Bronze Medal, 4, 5]
		int[] a2 = { 10, 3, 8, 9, 4 };
		System.out.println(Arrays.toString(ranks(a2))); // [Gold Medal, 5, Bronze Medal, Silver Medal, 4]
	}
}