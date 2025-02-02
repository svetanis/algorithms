package com.svetanis.algorithms.dp.wordbreak;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// 140. Word Break II

public final class WordBreakAllCombinationsBottomUpSubmit {

	public static List<String> wb(String str, List<String> dict) {
		int n = str.length();
		Set<String> set = new HashSet<>(dict);
		List<String>[] dp = new LinkedList[n + 1];
		List<String> initial = new LinkedList<>();
		initial.add("");
		dp[0] = initial;
		for (int i = 1; i <= n; i++) {
			List<String> list = new LinkedList<>();
			for (int j = 0; j < i; j++) {
				String ss = str.substring(j, i);
				if (set.contains(ss)) {
					for (String s : dp[j]) {
						String del = s.equals("") ? "" : " ";
						list.add(s + del + ss);
					}
				}
			}
			dp[i] = list;
		}
		return dp[n];
	}

	public static void main(String[] args) {
		printLines(wb("iamsuperlady", dictionary()));
	}
}
