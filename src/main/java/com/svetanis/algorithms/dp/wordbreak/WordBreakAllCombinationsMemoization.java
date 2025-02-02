package com.svetanis.algorithms.dp.wordbreak;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 140. Word Break II

public final class WordBreakAllCombinationsMemoization {

	public static List<String> wb(String str, List<String> dict) {
		Set<String> set = new HashSet<>(dict);
		Map<Integer, List<String>> map = new HashMap<>();
		return dfs(str, set, map, 0);
	}

	public static List<String> dfs(String str, Set<String> dict, 
			Map<Integer, List<String>> map, int left) {
		int n = str.length();
		if (map.get(left) != null) {
			return map.get(left);
		}
		List<String> list = new ArrayList<>();
		if (left == n) {
			list.add("");
		}
		for (int right = left + 1; right <= n; right++) {
			String ss = str.substring(left, right);
			if (dict.contains(ss)) {
				List<String> recursive = dfs(str, dict, map, right);
				for (String s : recursive) {
					String del = s.equals("") ? "" : " ";
					list.add(ss + del + s);
				}
			}
		}
		map.put(left, list);
		return list;
	}

	public static void main(String[] args) {
		printLines(wb("iamsuperlady", dictionary()));
	}
}
