package com.svetanis.algorithms.string;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 792. Number of Matching Subsequences
//
// Given a string s and an array of words, all lowercase, returns how many words are subsequences
// of s -- each word's letters appear in s in the same order, not necessarily next to each other.
// On LeetCode: 1 <= s.length <= 5 * 10^4, 1 <= words.length <= 5000, 1 <= words[i].length <= 50.
//
// Checking one word is the two-pointer walk of Is Subsequence. With many words, walk s ONCE and
// move every word at the same time: each word waits in the bucket of the letter it needs next.
// When s reaches letter c, every word waiting on c advances one letter and moves to the bucket of
// its new next letter, or is counted if it has no letters left. The count of words in c's bucket
// is read before the loop, so a word that needs c twice in a row (it lands back in the same
// bucket) waits for the next c in s instead of taking this one twice.
//
// Time: O(|s| + total length of the words) -- each step of the loop consumes one letter of a word
// Space: O(number of words) -- one waiting entry per word
//
// Siblings:
//   SubsequenceIterative (392) -- one word against one string with two pointers; 792 runs that walk
//     for every word at once, so s is read once instead of once per word

public final class CountMatchingSubsequences {

	public static int count(String s, String[] words) {
		Deque<Node>[] chars = chars(words);
		int count = 0;
		for (char c : s.toCharArray()) {
			Deque<Node> dq = chars[c - 'a'];           // BUCKET: the words waiting for c
			for (int i = dq.size(); i > 0; i--) {      // FIXED: only the words waiting before this c
				Node top = dq.pollFirst();             // POLL: a word waiting for c
				int index = top.index;
				int position = top.position + 1;       // ADVANCE: c matched its next letter
				if (position == words[index].length()) {
					count++;                           // COUNT: every letter matched
				} else {
					char next = words[index].charAt(position);
					Node node = new Node(index, position);
					chars[next - 'a'].offer(node);     // MOVE: wait in the bucket of its next letter
				}
			}
		}
		return count;                                  // REPORT
	}

	private static Deque<Node>[] chars(String[] words) {
		Deque<Node>[] chars = new Deque[26];
		Arrays.setAll(chars, k -> new ArrayDeque<>());
		for (int i = 0; i < words.length; i++) {
			char first = words[i].charAt(0);
			int index = first - 'a';
			chars[index].offer(new Node(i, 0));        // WAIT: every word starts on its first letter
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
		private int index;                             // WORD: which word
		private int position;                          // NEXT: the index of its letter waiting to be matched

		public Node(int index, int position) {
			this.index = index;
			this.position = position;
		}
	}
}