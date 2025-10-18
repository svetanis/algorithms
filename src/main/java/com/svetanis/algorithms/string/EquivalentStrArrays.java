package com.svetanis.algorithms.string;

// 1662. Check If Two String Arrays are Equivalent

public final class EquivalentStrArrays {
	// Time Complexity: O(n)

	public static boolean areEqual(String[] a1, String[] a2) {
		String s1 = String.join("", a1);
		String s2 = String.join("", a2);
		return s1.equals(s2);
	}

	private static String concat(String[] words) {
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(word);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] w1 = { "ab", "c" };
		String[] w2 = { "a", "bc" };
		System.out.println(areEqual(w1, w2)); // true

		String[] w3 = { "a", "cb" };
		String[] w4 = { "ab", "c" };
		System.out.println(areEqual(w3, w4)); // false

		String[] w5 = { "abc", "d", "defg" };
		String[] w6 = { "abcddefg" };
		System.out.println(areEqual(w5, w6)); // true
	}
}
