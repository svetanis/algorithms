package com.svetanis.algorithms.dp.wordbreak;

import static java.util.Arrays.asList;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// 139. Word Break

public final class IsWordBreakQueue {

	public static boolean isWordBreak(String s, List<String> dict) {
		int n = s.length();
		if (n == 0) {
			return true;
		}
		Set<String> set = new HashSet<>(dict);
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[n + 1];
		queue.add(0);
		while (!queue.isEmpty()) {
			int start = queue.poll();
			if (start == n) {
				return true;
			}
			for (int end = start + 1; end <= n; end++) {
				String substr = s.substring(start, end);
				if (!visited[end] && set.contains(substr)) {
					queue.add(end);
					visited[end] = true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isWordBreak("leetcode", asList("leet", "code"))); // true
		System.out.println(isWordBreak("applepenapple", asList("apple", "pen"))); // true
		System.out.println(isWordBreak("catsandog", asList("cats", "dog", "sand", "and", "cat"))); // false
	}
}
