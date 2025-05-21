package com.svetanis.algorithms.backtracking.pruning;

import java.util.ArrayList;
import java.util.List;

// 842. Split Array into Fibonacci Sequence

public final class SplitIntoFibonacciSeq {

	public static List<Integer> sfs(String s) {
		List<Integer> list = new ArrayList<>();
		if (dfs(s, 0, list)) {
			return list;
		}
		return new ArrayList<>();
	}

	private static boolean dfs(String s, int index, List<Integer> list) {
		if (index == s.length()) {
			return list.size() >= 3;
		}
		long val = 0;
		for (int i = index; i < s.length(); i++) {
			if (i > index && s.charAt(index) == '0') {
				break;
			}
			val = val * 10 + s.charAt(i) - '0';
			if (val > Integer.MAX_VALUE) {
				break;
			}
			int size = list.size();
			if (size >= 2 && val > list.get(size - 1) + list.get(size - 2)) {
				break;
			}
			if (size < 2 || val == list.get(size - 1) + list.get(size - 2)) {
				list.add((int) val);
				if (dfs(s, i + 1, list)) {
					return true;
				}
				list.remove(list.size() - 1);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(sfs("1101111")); // 11,0,11,11
		System.out.println(sfs("112358130")); // []
		System.out.println(sfs("0123")); // []
	}
}
