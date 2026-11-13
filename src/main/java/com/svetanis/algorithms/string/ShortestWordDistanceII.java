package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 244. Shortest Word Distance II
//
// Input: the constructor takes an array of words; shortestDist(w1, w2) is then called many times
// with two different words that both appear in the array. Returns the smallest |i - j| over
// positions i holding w1 and j holding w2. On LeetCode: 1 <= wordsDict.length <= 3 * 10^4,
// 1 <= word length <= 10, w1 != w2, at most 5000 calls.
//
// The one idea: the array does not change between calls, so index it once. One map entry is a word
// and the list of all positions where it occurs, in increasing order. A query then has two sorted
// lists, and the closest pair is found by walking both: look at the two current positions, record
// their distance, and move past the smaller one -- the smaller position cannot get closer to
// anything later in the other list than it already is to the current one.
//
// Time: constructor O(n); each query O(a + b) -- a and b are the occurrence counts of the two words.
// Space: O(n) -- every position is stored once.
//
// Siblings: ShortestWordDistance does one query by a single scan of the array, with no index.

public final class ShortestWordDistanceII {

	private Map<String, List<Integer>> map = new HashMap<>();

	public ShortestWordDistanceII(String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			map.computeIfAbsent(dictionary[i], k -> new ArrayList<>()).add(i); // RECORD: position under its word
		}
	}

	public int shortestDist(String w1, String w2) {
		List<Integer> indices1 = map.get(w1);
		List<Integer> indices2 = map.get(w2);
		int min = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		while (i < indices1.size() && j < indices2.size()) { // WALK both sorted lists
			int index1 = indices1.get(i);
			int index2 = indices2.get(j);
			int diff = Math.abs(index1 - index2);
			min = Math.min(min, diff); // TAKE: the smaller distance
			if (index1 <= index2) { // MOVE past the smaller position
				i++;
			} else {
				j++;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		String[] dictionary = { "the", "quick", "brown", "fox", "quick" };
		ShortestWordDistanceII swd = new ShortestWordDistanceII(dictionary);
		System.out.println(swd.shortestDist("brown", "quick")); // 1

		String[] dictionary1 = { "practice", "makes", "perfect", "coding", "makes" };
		ShortestWordDistanceII swd1 = new ShortestWordDistanceII(dictionary1);
		System.out.println(swd1.shortestDist("coding", "practice")); // 3
		System.out.println(swd1.shortestDist("makes", "coding")); // 1
	}
}
