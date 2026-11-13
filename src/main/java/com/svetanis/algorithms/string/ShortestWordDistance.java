package com.svetanis.algorithms.string;

// 243. Shortest Word Distance
//
// Input: an array of words, and two different words w1 and w2 that both appear in it. Returns the
// smallest |i - j| over positions i holding w1 and j holding w2. On LeetCode:
// 2 <= wordsDict.length <= 3 * 10^4, 1 <= word length <= 10, w1 != w2.
//
// The one idea: walk left to right and remember the latest position of w1 (left) and of w2
// (right). Whenever both are known, the distance between them is a candidate. The closest pair
// that ends at the current position uses the latest position of each word, an older position
// of the same word is only farther away, so remembering only the latest one never loses the answer.
//
// Time: O(n) -- one pass, constant work per word (words are at most 10 letters).
// Space: O(1) -- two positions and the minimum.
//
// Siblings: ShortestWordDistanceII answers many queries on one array (index lists, merged);
// ShortestWordDistanceIII allows w1 equal to w2.

public final class ShortestWordDistance {

	public static int shortestDist(String[] dictionary, String w1, String w2) {
		int left = -1; // latest position of w1, -1 if not seen yet
		int right = -1; // latest position of w2, -1 if not seen yet
		int min = Integer.MAX_VALUE;
		for (int index = 0; index < dictionary.length; index++) { // WALK: left to right
			if (dictionary[index].equals(w1)) {
				left = index; // RECORD: latest w1
			}
			if (dictionary[index].equals(w2)) {
				right = index; // RECORD: latest w2
			}
			if (left != -1 && right != -1) { // both seen: the latest two are the closest pair so far
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
	}
}
