package com.svetanis.algorithms.string;

// 245. Shortest Word Distance III

public final class ShortestWordDistanceIII {
	// Time Complexity: O(n)

	private static int shortestDist(String[] dictionary, String w1, String w2) {
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
