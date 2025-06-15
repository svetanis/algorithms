package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

// 139. Word Break

// the parameter for contains is str.substr(0, i)
// str.substr(0, i) which is prefix (of input string) of length i.
// we first check whether current prefix is in dictionary
// then we recursively check for remaining string str.substring(i)
// which is suffix of length n - i

public final class IsWordBreakTopDown {

	public static boolean isWordBreak(String s, List<String> dict) {
		Set<String> set = new HashSet<>(dict);
		Boolean[] dp = new Boolean[s.length()];
		return dfs(s, 0, set, dp);
	}

	private static boolean dfs(String s, int index, Set<String> set, Boolean[] dp) {
		int n = s.length();
		// base case
		if (index == n) {
			return true;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		boolean result = false;
		for (int i = index; i < n; ++i) {
			String ss = s.substring(index, i + 1);
			if (set.contains(ss)) {
				result = dfs(s, i + 1, set, dp);
				if (result) {
					break;
				}
			}
		}
		return dp[index] = result;
	}

	public static void main(String[] args) {
		List<String> dict = dictionary();
		System.out.println(isWordBreak("ilikesamsung", dict));
		System.out.println(isWordBreak("iiiiiiii", dict));
		System.out.println(isWordBreak("", dict));
		System.out.println(isWordBreak("ilikelikeimangoiii", dict));
		System.out.println(isWordBreak("samsungandmango", dict));
		System.out.println(isWordBreak("samsungandmangok", dict));

		System.out.println(isWordBreak("leetcode", asList("leet", "code"))); // true
		System.out.println(isWordBreak("applepenapple", asList("apple", "pen"))); // true
		System.out.println(isWordBreak("catsandog", asList("cats", "dog", "sand", "and", "cat"))); // false
	}

	private static ImmutableList<String> dictionary() {
		List<String> dict = newArrayList();
		dict.add("mobile");
		dict.add("samsung");
		dict.add("sam");
		dict.add("sung");
		dict.add("man");
		dict.add("mango");
		dict.add("icecream");
		dict.add("and");
		dict.add("go");
		dict.add("i");
		dict.add("like");
		dict.add("ice");
		dict.add("cream");
		return newList(dict);
	}
}
