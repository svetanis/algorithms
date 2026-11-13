package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 830. Positions of Large Groups
//
// Input: a string s of lowercase letters. A group is a maximal run of one repeated letter; it is
// large if it has 3 or more letters. Returns the [start, end] indices of every large group,
// sorted by start. On LeetCode: 1 <= s.length <= 1000.
//
// The one idea: the groups are laid end to end with no gap, so each one starts exactly where the
// previous one stopped. left marks the start of a group; right walks forward while the letter is
// the same, and stops on the first different letter (or the end), which is the start of the next
// group. The group is s[left .. right - 1]; keep it if it has 3 or more letters.
//
// Time: O(n) -- right only moves forward, and left jumps to where right stopped, so each
// character is visited once.
// Space: O(1) -- beyond the returned list.

public final class LargeGroupPositions {

	public static List<List<Integer>> lgp(String s) {
		int n = s.length();
		int left = 0; // left: where the current group starts
		List<List<Integer>> list = new ArrayList<>();
		while (left < n) {
			int right = left;
			while (right < n && s.charAt(right) == s.charAt(left)) { // WALK: to the end of the group
				right++;
			}
			if (right - left >= 3) { // TAKE: the group is large
				list.add(Arrays.asList(left, right - 1));
			}
			left = right; // NEXT: the next group starts where this one stopped
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(lgp("abbxxxxzzy")); // [[3, 6]]
		System.out.println(lgp("abc")); // []
		System.out.println(lgp("abcdddeeeeaabbbcd")); // [[3, 5], [6, 9], [12, 14]]
	}
}
