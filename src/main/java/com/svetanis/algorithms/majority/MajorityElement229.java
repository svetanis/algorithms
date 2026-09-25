package com.svetanis.algorithms.majority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 229. Majority Element II
// given an integer array of size n,
// find all elements that appear more
// than n/3 times

// More than n/3 allows at most TWO answers: three answers would need
// 3 * (n/3 + 1) > n slots. So two cards are tracked, and a cancellation
// now discards three -- the newcomer plus one copy of each candidate.

public final class MajorityElement229 {

	public static List<Integer> majorityElements(int[] a) {
		// Time Complexity: O(n) -- two passes
		// Space Complexity: O(1) -- four ints, and never more than two candidates

		// 1. find candidates for majority
		List<Integer> candidates = candidates(a);

		// the set is NOT decoration. candidates() can hand back the same value
		// twice: on {0, 0} only slot 1 is ever occupied, so major2 keeps its
		// initial 0, which then passes the count below as well. Without the set
		// that input returns [0, 0].
		Set<Integer> set = new HashSet<>();
		for (int candidate : candidates) {
			// 2. check if candidate is majority
			if (isMajority(a, candidate)) {
				set.add(candidate);
			}
		}
		return new ArrayList<>(set);
	}

	private static boolean isMajority(int[] a, int candidate) {
		// the counters inside candidates() are NOT frequencies -- copies cancelled
		// along the way are gone from them for good. On {1,1,1,2,3,4,5,6} the loop
		// ends with count1 = 1 where 1 actually occurs three times. So count again,
		// from the array.
		int count = 0;
		for (int num : a) {
			if (num == candidate) {
				count++;
			}
		}
		// strictly greater: >= admits a value occurring exactly n/3 times, and on
		// a one-element array it admits the never-occupied second slot, 0 >= 0.
		return count > a.length / 3;
	}

	private static List<Integer> candidates(int[] a) {
		int major1 = 0;
		int count1 = 0;
		int major2 = 0;
		int count2 = 0;
		for (int num : a) {
			// the two MATCH tests must come first. Put an empty-slot test ahead of
			// them and a value already held by slot 1 gets copied into slot 2 --
			// e.g. major1 = 5 with count1 = 3, count2 = 0, and a newcomer holding 5.
			if (num == major1) {
				count1++;
			} else if (num == major2) {
				count2++;
			} else if (count1 == 0) {
				major1 = num;
				count1 = 1;       // adopting means one is standing there, never 0
			} else if (count2 == 0) {
				major2 = num;
				count2 = 1;
			} else {
				count1--;         // three leave: num, one of major1, one of major2
				count2--;
			}
		}
		// SOME surviving values -- candidates, not answers. isMajority decides.
		return Arrays.asList(major1, major2);
	}

	public static void main(String[] args) {
		int[] a = { 3, 2, 3 };
		System.out.println(majorityElements(a)); // [3]

		int[] a2 = { 1 };
		System.out.println(majorityElements(a2)); // [1]

		int[] a3 = { 1, 2 };
		System.out.println(majorityElements(a3)); // [1, 2]

		int[] a4 = { 0, 0, 0 };
		System.out.println(majorityElements(a4)); // [0]

		int[] a5 = { 0, 0 };
		System.out.println(majorityElements(a5)); // [0] -- returns [0, 0] without the set

		int[] a6 = { 1, 2, 3 };
		System.out.println(majorityElements(a6)); // [] -- no value occurs more than once
	}
}
