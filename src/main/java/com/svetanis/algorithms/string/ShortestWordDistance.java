package com.svetanis.algorithms.string;

// 243. Shortest Word Distance

public final class ShortestWordDistance {
	// Time Complexity: O(n)

	public static int shortestDist(String[] dictionary, String w1, String w2) {
		int left = -1;
		int right = -1;
		int min = Integer.MAX_VALUE;
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
	}
}
