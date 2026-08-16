package com.svetanis.algorithms.twopointers;

import java.util.Arrays;
import java.util.List;

// 524. Longest Word in Dictionary through Deleting

// the scan version is LongestWordInDictionary.java, and it is the one
// to write first. this file is the follow-up: "the dictionary is huge
// and s is fixed -- can you do better?"

// next[i][c] answers "starting at index i, where is the next c?" in one
// read, so matching a candidate never touches s again. building it is
// O(26n); each candidate then costs O(word.length()) instead of O(s.length())

public final class LongestWordInDictionaryNextOccurrence {
	// Time Complexity: O(26n + L), n = s.length(), L = total dictionary length
	// Space Complexity: O(26n)

	private static final int ALPHABET = 26;

	public static String longestWord(String s, List<String> list) {
		int[][] next = nextOccurrence(s);
		int n = s.length();
		String result = "";
		for (String word : list) {
			if (cannotWin(word, result)) {
				continue;
			}
			if (isSubSeq(word, next, n)) {
				result = word;
			}
		}
		return result;
	}

	// the same table, without the skip -- kept so the two speedups can be
	// measured apart. they help in opposite cases: the skip needs candidates
	// to MATCH, the table needs them to FAIL
	public static String longestWordNoSkip(String s, List<String> list) {
		int[][] next = nextOccurrence(s);
		int n = s.length();
		String result = "";
		for (String word : list) {
			if (isSubSeq(word, next, n) && beats(word, result)) {
				result = word;
			}
		}
		return result;
	}

	// next[i][c] = the smallest index j >= i with s.charAt(j) == 'a' + c,
	// or s.length() when that letter does not occur at or after i
	private static int[][] nextOccurrence(String s) {
		int n = s.length();
		int[][] next = new int[n + 1][ALPHABET];
		Arrays.fill(next[n], n); // past the end, nothing occurs
		for (int i = n - 1; i >= 0; i--) {
			next[i] = next[i + 1].clone(); // all still reachable from i
			next[i][s.charAt(i) - 'a'] = i; // and this one letter is closer
		}
		return next;
	}

	private static boolean isSubSeq(String word, int[][] next, int n) {
		int pos = 0;
		for (int i = 0; i < word.length(); i++) {
			int at = next[pos][word.charAt(i) - 'a'];
			if (at == n) {
				return false; // this letter never appears again
			}
			pos = at + 1; // continue strictly after what we consumed
		}
		return true;
	}

	// longer wins; equal length is broken lexicographically, smallest first
	private static boolean beats(String word, String result) {
		int len = word.length();
		int max = result.length();
		return len > max || (len == max && word.compareTo(result) < 0);
	}

	// the same test read backwards, so a candidate that cannot win is
	// skipped BEFORE paying for the subsequence check
	private static boolean cannotWin(String word, String result) {
		return !beats(word, result);
	}

	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> list = Arrays.asList("ale", "apple", "monkey", "plea");
		System.out.println(longestWord(s, list)); // apple
		System.out.println(longestWordNoSkip(s, list)); // apple

		List<String> list1 = Arrays.asList("a", "b", "c");
		System.out.println(longestWord(s, list1)); // a
		System.out.println(longestWordNoSkip(s, list1)); // a

		System.out.println("[" + longestWord("abc", Arrays.asList("zz")) + "]"); // []
		System.out.println("[" + longestWord("abc", Arrays.asList()) + "]"); // []
	}
}
