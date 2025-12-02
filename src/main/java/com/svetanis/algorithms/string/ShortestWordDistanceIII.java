package com.svetanis.algorithms.string;

// 245. Shortest Word Distance III

public final class ShortestWordDistanceIII {
	// Time Complexity: O(n)

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
		int prev = -1;
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < dict.length; i++) {
			if (!dict[i].equals(w1) && !dict[i].equals(w2)) {
				continue;
			}
			if (prev != -1 && (w1.equals(w2) || !dict[i].equals(dict[prev]))) {
				minLen = Math.min(minLen, i - prev);
			}
			prev = i;
		}
		return minLen;
	}

	public static int shortestDist(String[] dictionary, String w1, String w2) {
		int min = dictionary.length;
		if (w1.equals(w2)) {
			min = sde(dictionary, w1);
		} else {
			min = sdd(dictionary, w1, w2);
		}
		return min;
	}

	private static int sde(String[] dictionary, String word) {
		int prev = -1;
		int min = dictionary.length;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(word)) {
				if (prev != -1) {
					min = Math.min(min, i - prev);
				}
				prev = i;
			}
		}
		return min;
	}

	private static int sdd(String[] dictionary, String w1, String w2) {
		int left = -1;
		int right = -1;
		int min = dictionary.length;
		for (int index = 0; index < dictionary.length; index++) {
			if (dictionary[index].equals(w1)) {
				left = index;
			}
			if (dictionary[index].equals(w2)) {
				right = index;
			}
			if (left != -1 && right != -1) {
				int diff = Math.abs(left - right);
				min = Math.min(min, diff);
			}
		}
		return min;
	}

	public static void main(String[] args) {
		String[] dictionary = { "practice", "makes", "perfect", "coding", "makes" };
		System.out.println(shortestDist(dictionary, "coding", "practice")); // 3
		System.out.println(shortestDist(dictionary, "makes", "coding")); // 1
		System.out.println(shortestDist(dictionary, "makes", "makes")); // 3
	}
}
