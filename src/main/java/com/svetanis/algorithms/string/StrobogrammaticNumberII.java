package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 247. Strobogrammatic Number II

public final class StrobogrammaticNumberII {

	private static int[][] pairs = { { 1, 1 }, { 8, 8 }, { 6, 9 }, { 9, 6 } };

	public static List<String> strobogrammatic(int n) {
		return dfs(n, n);
	}

	private static List<String> dfs(int n, int len) {
		if (len == 0) {
			return Arrays.asList("");
		}
		if (len == 1) {
			return Arrays.asList("0", "1", "8");
		}
		List<String> list = new ArrayList<>();
		List<String> nums = dfs(n, len - 2);
		for (String num : nums) {
			for (int[] pair : pairs) {
				list.add(pair[0] + num + pair[1]);
			}
			if (len != n) {
				list.add("0" + num + "0");
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(strobogrammatic(1));
		System.out.println(strobogrammatic(2));
		System.out.println(strobogrammatic(3));
		System.out.println(strobogrammatic(4));
	}
}
