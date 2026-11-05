package com.svetanis.algorithms.string.substr;

import java.util.ArrayList;
import java.util.List;

// 696. Count Binary Substrings
//
// Input: a binary string s. Returns the number of non-empty substrings that have as many 0s as 1s,
// with all the 0s together and all the 1s together (like "0011" or "10"). Substrings that occur at
// different places count separately. On LeetCode: 1 <= s.length <= 10^5, s[i] is '0' or '1'.
//
// The one idea: cut s into runs of equal digits; "00110011" is the runs 00, 11, 00, 11, so the
// lengths are 2, 2, 2, 2. One entry of groups is the length of one run. A counted substring has a
// run of 0s next to a run of 1s, and the same number k of each, so it sits across the border of two
// neighbouring runs, taking k digits from each side. For runs of lengths a and b, k can be 1 up to
// min(a, b), which gives min(a, b) substrings for that border. Every counted substring crosses
// exactly one border, so the answer is the sum of min over all borders.
//
// Time: O(n) -- one pass to cut into runs, one pass over the runs.
// Space: O(n) -- the run lengths (n runs at most, when no two neighbours are equal).
//
// Siblings: countBinarySubStrsSimple stores the run lengths in an int array; countBinarySubstrs
// builds them in a List with a separate helper. Same answer.

public final class CountBinarySubstrs {

	public static int countBinarySubStrsSimple(String s) {
		int n = s.length();
		int[] groups = new int[s.length()]; // groups[g] -- the length of the g-th run
		groups[0] = 1;
		int index = 0;
		for (int i = 1; i < n; i++) {
			if (s.charAt(i - 1) != s.charAt(i)) {
				groups[++index] = 1; // a new run starts
			} else {
				groups[index] += 1; // the run grows
			}
		}

		int count = 0;
		for (int i = 1; i <= index; i++) {
			count += Math.min(groups[i - 1], groups[i]); // COUNT: the border between run i - 1 and run i
		}
		return count;
	}

	public static int countBinarySubstrs(String s) {
		List<Integer> list = groups(s);
		int count = 0;
		for (int i = 1; i < list.size(); i++) {
			int curr = list.get(i);
			int prev = list.get(i - 1);
			count += Math.min(curr, prev); // COUNT: the border between two neighbouring runs
		}
		return count;
	}

	private static List<Integer> groups(String s) {
		List<Integer> list = new ArrayList<>();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			int count = 1;
			char curr = s.charAt(i);
			while (i + 1 < n && curr == s.charAt(i + 1)) { // WALK: to the end of this run
				count += 1;
				i += 1;
			}
			list.add(count); // RECORD: the length of the run
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(countBinarySubstrs("00110011")); // 6
		System.out.println(countBinarySubstrs("10101")); // 4
		System.out.println(countBinarySubStrsSimple("00110011")); // 6
		System.out.println(countBinarySubStrsSimple("10101")); // 4
	}
}
