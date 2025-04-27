package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 830. Positions of Large Groups

public final class LargeGroupPositions {
	// Time Complexity: O(n)

	public static List<List<Integer>> lgp(String s) {
		int n = s.length();
		int left = 0;
		List<List<Integer>> list = new ArrayList<>();
		while (left < n) {
			int right = left;
			while (right < n && s.charAt(right) == s.charAt(left)) {
				right++;
			}
			if (right - left >= 3) {
				list.add(Arrays.asList(left, right - 1));
			}
			left = right;
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(lgp("abbxxxxzzy")); // [3,6]
		System.out.println(lgp("abc")); // []
		System.out.println(lgp("abcdddeeeeaabbbcd")); // [3,5], [6,9], [12,14]
	}
}
