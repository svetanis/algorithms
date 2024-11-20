package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

// 139. Word Break

// the value dp[i] will be true
// if str[0 ... i - 1] can be
// segmented into dictionary words,
// otherwise false

public final class IsWordBreakBottomUp {

	public static boolean isWordBreak(String s, List<String> dict) {
		int n = s.length();
		if (n == 0) {
			return true;
		}
		Set<String> set = new HashSet<>(dict);
		boolean[] dp = new boolean[n + 1];
		for (int i = 1; i <= n; ++i) {
			// if dp[i] is false,
			// then check if current
			// prefix can make it true
			// current prefix is str.substring(0, i)
			if (!dp[i] && set.contains(s.substring(0, i))) {
				dp[i] = true;
			}

			// dp[i] is true, then check for all
			// substring starting from (i + 1) char
			// and store their results
			if (dp[i]) {
				// if we reached the last prefix
				if (i == n) {
					return true;
				}

				for (int j = i + 1; j <= n; ++j) {
					// update dp[j] if it is false
					// and can be updated
					// note the parameter passed to
					// contains() is substring starting
					// from index 'i' and length j - i
					if (dp[j] == false && set.contains(s.substring(i, j))) {
						dp[j] = true;
					}

					// if we reached the last character
					if (j == n && dp[j] == true) {
						return true;
					}
				}
			}
		}
		// we have tried all prefixes
		// and none of them worked
		return false;
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
