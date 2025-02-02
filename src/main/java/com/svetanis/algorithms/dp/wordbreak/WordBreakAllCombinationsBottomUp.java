package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static com.svetanis.java.base.Strings.trim;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;
import static com.svetanis.java.base.collect.Maps.checkedGet;
import static com.svetanis.java.base.collect.Maps.checkedPut;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// 140. Word Break II

public final class WordBreakAllCombinationsBottomUp {

	public static ImmutableList<String> wb(String str, List<String> dict) {
		int n = str.length();
		Set<String> set = new HashSet<>(dict);
		Map<Integer, List<String>> map = newHashMap();
		checkedPut(map, 0, newArrayList(""));
		for (int i = 1; i <= n; i++) {
			List<String> list = newArrayList();
			for (int j = 0; j < i; j++) {
				String ss = str.substring(j, i);
				if (set.contains(ss)) {
					list.addAll(transform(checkedGet(map, j), s -> trim(Joiner.on(" ").join(s, ss))));
				}
			}
			checkedPut(map, i, list);
		}
		return newList(checkedGet(map, n));
	}

	public static void main(String[] args) {
		printLines(wb("iamsuperlady", dictionary()));
	}
}
