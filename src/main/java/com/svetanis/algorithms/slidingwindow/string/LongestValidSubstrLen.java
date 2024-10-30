package com.svetanis.algorithms.slidingwindow.string;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2781. Length of the Longest Valid Substring

// given a string s and an array of strings forbidden
// a string is called valid if none of its substrings
// are present in forbidden
// return the length of the longest valid substring
// a substr is a contiguous sequence of chars in a string
// possibly empty

public final class LongestValidSubstrLen {
	// Time complexity: O(n)

	public static int longestValidSubstrLen(String s, List<String> forbidden) {
		int max = 0;
		int left = 0;
		Set<String> set = new HashSet<>(forbidden);
		for (int right = 0; right < s.length(); right++) {
			// check substrings within the current window [left, right]
			for (int index = right; index > Math.max(right - 10, left - 1); index--) {
				String substr = s.substring(index, right + 1);
				// if forbidden substring is found within the window
				if (set.contains(substr)) {
					// move the start index after the forbidden substring
					left = index + 1;
					break;
				}
			}
			// max length so far
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestValidSubstrLen("cbaaaabc", asList("aaa", "cb"))); // 4
		System.out.println(longestValidSubstrLen("leetcode", asList("de", "le", "e"))); // 4
	}
}
