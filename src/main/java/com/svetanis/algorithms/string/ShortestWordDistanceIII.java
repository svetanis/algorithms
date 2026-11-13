package com.svetanis.algorithms.string;

// 245. Shortest Word Distance III
//
// Input: an array of words, and two words w1 and w2 that both appear in it; unlike problem 243,
// they may be the same word. Returns the smallest |i - j| over positions i holding w1 and j holding
// w2 with i != j. On LeetCode: 1 <= wordsDict.length <= 10^5, 1 <= word length <= 10.
//
// The one idea: two different words are the 243 problem -- remember the latest position of each and
// take the distance whenever both are known. When the words are equal, a position cannot be paired
// with itself, so instead compare each occurrence with the previous occurrence of the same word:
// the closest two equal positions are always neighbours in the list of occurrences.
//
// Time: O(n) -- one pass, constant work per word (words are at most 10 letters).
// Space: O(1) -- a few positions.
//
// Siblings: ShortestWordDistance is the w1 != w2 case only. This file has two methods for the
// problem: shortestDist picks one of two scans (equal words or different words);
// shortestDistSimple does both in one scan, remembering the previous position holding either word.

public final class ShortestWordDistanceIII {

	public static int shortestDistSimple(String[] dict, String w1, String w2) {
		if (dict == null || dict.length == 0) {
			return 0;
		}
		if (w1 == null || w1.length() == 0) {
			return 0;
		}
		if (w2 == null || w2.length() == 0) {
			return 0;
		}
		int prev = -1; // latest position holding w1 or w2
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < dict.length; i++) { // WALK: left to right
			if (!dict[i].equals(w1) && !dict[i].equals(w2)) { // SKIP: a word we do not care about
				continue;
			}
			// pair with the previous hit if the words are equal, or if the previous hit is the other word
			if (prev != -1 && (w1.equals(w2) || !dict[i].equals(dict[prev]))) {
				minLen = Math.min(minLen, i - prev); // TAKE: the smaller distance
			}
			prev = i; // RECORD: this position
		}
		return minLen;
	}

	public static int shortestDist(String[] dictionary, String w1, String w2) {
		int min = dictionary.length;
		if (w1.equals(w2)) { // equal words: neighbours among the occurrences
			min = sde(dictionary, w1);
		} else { // different words: problem 243
			min = sdd(dictionary, w1, w2);
		}
		return min;
	}

	// shortest distance between two occurrences of the same word
	private static int sde(String[] dictionary, String word) {
		int prev = -1;
		int min = dictionary.length;
		for (int i = 0; i < dictionary.length; i++) { // WALK: left to right
			if (dictionary[i].equals(word)) {
				if (prev != -1) {
					min = Math.min(min, i - prev); // TAKE: distance to the previous occurrence
				}
				prev = i; // RECORD: this occurrence
			}
		}
		return min;
	}

	// shortest distance between an occurrence of w1 and an occurrence of w2, w1 != w2
	private static int sdd(String[] dictionary, String w1, String w2) {
		int left = -1;
		int right = -1;
		int min = dictionary.length;
		for (int index = 0; index < dictionary.length; index++) { // WALK: left to right
			if (dictionary[index].equals(w1)) {
				left = index; // RECORD: latest w1
			}
			if (dictionary[index].equals(w2)) {
				right = index; // RECORD: latest w2
			}
			if (left != -1 && right != -1) {
				int diff = Math.abs(left - right);
				min = Math.min(min, diff); // TAKE: the smaller distance
			}
		}
		return min;
	}

	public static void main(String[] args) {
		String[] dictionary = { "practice", "makes", "perfect", "coding", "makes" };
		System.out.println(shortestDist(dictionary, "coding", "practice")); // 3
		System.out.println(shortestDist(dictionary, "makes", "coding")); // 1
		System.out.println(shortestDist(dictionary, "makes", "makes")); // 3
		System.out.println(shortestDistSimple(dictionary, "coding", "practice")); // 3
		System.out.println(shortestDistSimple(dictionary, "makes", "coding")); // 1
		System.out.println(shortestDistSimple(dictionary, "makes", "makes")); // 3
	}
}
