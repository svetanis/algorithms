package com.svetanis.algorithms.search.heap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 792. Number of Matching Subsequences

public final class CountMatchingSubsequences {
	// Time Complexity: O(n + m + k)

	public static int count(String s, String[] words) {
		Deque<Node>[] chars = chars(words);
		int count = 0;
		for (char c : s.toCharArray()) {
			Deque<Node> dq = chars[c - 'a'];
			for (int i = dq.size(); i > 0; i--) {
				Node top = dq.pollFirst();
				int index = top.index;
				int position = top.position + 1;
				if (position == words[index].length()) {
					count++;
				} else {
					char next = words[index].charAt(position);
					Node node = new Node(index, position);
					chars[next - 'a'].offer(node);
				}
			}
		}
		return count;
	}

	private static Deque<Node>[] chars(String[] words) {
		Deque<Node>[] chars = new Deque[26];
		Arrays.setAll(chars, k -> new ArrayDeque<>());
		for (int i = 0; i < words.length; i++) {
			char first = words[i].charAt(0);
			int index = first - 'a';
			chars[index].offer(new Node(i, 0));
		}
		return chars;
	}

	public static void main(String[] args) {
		String[] w1 = { "a", "bb", "acd", "ace" };
		System.out.println(count("abcde", w1)); // 3
		String[] w2 = { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" };
		System.out.println(count("dsahjpjauf", w2)); // 2
	}

	private static class Node {
		private int index;
		private int position;

		public Node(int index, int position) {
			this.index = index;
			this.position = position;
		}
	}
}