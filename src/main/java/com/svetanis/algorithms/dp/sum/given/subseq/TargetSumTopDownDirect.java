package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.HashMap;
import java.util.Map;

// 494. Target Sum

// "Direct" means this file does NOT reduce the problem first: it assigns
// a real + or - to every element, exactly as the question states it, and
// the running sum can go negative. That is the only difference from
// TargetSumTopDownReduced, which rewrites the question as a subset count
// before it recurses. This one is the memoized twin of TargetSumRecursive:
// same dfs, same signature, one cache added.

public final class TargetSumTopDownDirect {
	// Time complexity: O(n * sum)

	private Map<String, Integer> map;

	public int count(int[] a, int target) {
		this.map = new HashMap<>();
		return dfs(a, target, 0, 0);
	}

	private int dfs(int[] a, int target, int index, int sum) {
		if (index == a.length) {
			return sum == target ? 1 : 0;
		}
		String key = index + "," + sum;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		int incl = dfs(a, target, index + 1, sum + a[index]);
		int excl = dfs(a, target, index + 1, sum - a[index]);
		map.put(key, incl + excl);
		return incl + excl;
	}

	public static void main(String[] args) {
		TargetSumTopDownDirect ts = new TargetSumTopDownDirect();
		int[] a3 = { 1, 1, 1, 1, 1 };
		System.out.println(ts.count(a3, 3)); // 5

		int[] a4 = { 1 };
		System.out.println(ts.count(a4, 1)); // 1

		int[] a5 = { 0, 0, 0, 0, 0, 0, 0, 0, 1 };
		System.out.println(ts.count(a5, 1)); // 256

		int[] a6 = { 1, 2, 1 };
		System.out.println(ts.count(a6, 0)); // 2
	}
}
