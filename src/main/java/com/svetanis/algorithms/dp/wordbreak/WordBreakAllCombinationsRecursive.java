package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static com.svetanis.java.base.Strings.trim;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public final class WordBreakAllCombinationsRecursive {
	// Time Complexity: O(2^n)

	public static ImmutableList<String> wb(String str, List<String> dict) {
		Set<String> set = new HashSet<>(dict);
		return newList(dfs(str, set, 0));
	}

	public static List<String> dfs(String str, Set<String> dict, int left) {
		int n = str.length();
		List<String> list = newArrayList();
		if (left == n) {
			list.add("");
		}
		for (int right = left + 1; right <= n; right++) {
			String ss = str.substring(left, right);
			if (dict.contains(ss)) {
				List<String> remained = dfs(str, dict, right);
				list.addAll(transform(remained, s -> trim(Joiner.on(" ").join(ss, s))));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		printLines(wb("iamsuperlady", dictionary()));
	}
}
