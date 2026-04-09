package com.svetanis.algorithms.string.substr;

import java.util.ArrayList;
import java.util.List;

// 696. Count Binary Substrings

public final class CountBinarySubstrs {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int countBinarySubStrsSimple(String s) {
		int n = s.length();
		int[] groups = new int[s.length()];
		groups[0] = 1;
		int index = 0;
		for (int i = 1; i < n; i++) {
			if (s.charAt(i - 1) != s.charAt(i)) {
				groups[++index] = 1;
			} else {
				groups[index] += 1;
			}
		}

		int count = 0;
		for (int i = 1; i <= index; i++) {
			count += Math.min(groups[i - 1], groups[i]);
		}
		return count;
	}

	public static int countBinarySubstrs(String s) {
		List<Integer> list = groups(s);
		int count = 0;
		for (int i = 1; i < list.size(); i++) {
			int curr = list.get(i);
			int prev = list.get(i - 1);
			count += Math.min(curr, prev);
		}
		return count;
	}

	private static List<Integer> groups(String s) {
		List<Integer> list = new ArrayList<>();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			int count = 1;
			char curr = s.charAt(i);
			while (i + 1 < n && curr == s.charAt(i + 1)) {
				count += 1;
				i += 1;
			}
			list.add(count);
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(countBinarySubstrs("00110011")); // 6
		System.out.println(countBinarySubstrs("10101")); // 4
	}
}
