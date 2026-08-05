package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.HashMap;
import java.util.Map;

// 494. Target Sum

// "Reduced" means the +/- question is rewritten as a subset count before
// any recursion happens, so the running sum only ever grows. Compare
// TargetSumTopDownDirect, which memoizes the +/- recursion untouched.
//
// this file counts the NEGATIVE subset: sum(neg) = (total - k) / 2.
// TargetSumBottomUp counts the POSITIVE one: sum(pos) = (total + k) / 2.
// neither is more correct -- naming one side fixes the other, so the two
// counts are equal. the halves look like a sign error side by side; they
// are not.

public final class TargetSumTopDownReduced {
	// Time complexity: O(n * sum)

	private Map<String, Integer> map;

	public int count(int[] a, int k) {
		this.map = new HashMap<>();
		int total = 0;
		for (int num : a) {
			total += num;
		}
		if (total < k || (total - k) % 2 == 1) {
			return 0;
		}
		int target = (total - k) / 2;
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
		int excl = dfs(a, target, index + 1, sum);
		map.put(key, incl + excl);
		return incl + excl;
	}

	public static void main(String[] args) {
		TargetSumTopDownReduced ts = new TargetSumTopDownReduced();
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
