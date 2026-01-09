package com.svetanis.algorithms.string.encode;

// 3167. Better Compression of String

public final class StringCompressionSubmit {
	// Time Complexity: O(n + k log k)
	// Space Complexity: O(k)

	public static String compression(String compressed) {
		int index = 0;
		int n = compressed.length();
		int[] freq = new int[26];
		while (index < n) {
			char c = compressed.charAt(index++);
			int num = 0;
			while (index < n && Character.isDigit(compressed.charAt(index))) {
				num = num * 10 + (compressed.charAt(index) - '0');
				index += 1;
			}
			freq[c - 'a'] += num;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (freq[i] > 0) {
				char c = (char) (i + 'a');
				sb.append(c).append(freq[i]);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(compression("b2a3b1c2a1")); // a4b3c2
		System.out.println(compression("a3b1a1c2")); // a4b1c2
	}
}
