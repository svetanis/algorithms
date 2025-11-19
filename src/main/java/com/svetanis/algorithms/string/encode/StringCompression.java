package com.svetanis.algorithms.string.encode;

import java.util.TreeMap;

// 3167. Better Compression of String

public final class StringCompression {
	// Time Complexity: O(n + k log k)
	// Space Complexity: O(k)

	public static String compression(String compressed) {
		TreeMap<Character, Integer> map = freqSimple(compressed);
		StringBuilder sb = new StringBuilder();
		for (char c : map.keySet()) {
			sb.append(c);
			sb.append(map.get(c));
		}
		return sb.toString();
	}

	private static TreeMap<Character, Integer> freqSimple(String compressed) {
		int n = compressed.length();
		int index = 0;
		TreeMap<Character, Integer> map = new TreeMap<>();
		while (index < n) {
			char c = compressed.charAt(index);
			int numIndex = index + 1;
			int num = 0;
			while (numIndex < n && Character.isDigit(compressed.charAt(numIndex))) {
				num = num * 10 + (compressed.charAt(numIndex) - '0');
				numIndex += 1;
			}
			map.merge(c, num, Integer::sum);
			index = numIndex;
		}
		return map;
	}

	private static TreeMap<Character, Integer> frequencies(String compressed) {
		TreeMap<Character, Integer> map = new TreeMap<>();
		int freq = 0;
		char prev = '@';
		for (char c : compressed.toCharArray()) {
			if (Character.isLetter(c)) {
				if (prev != '@' && freq > 0) {
					map.merge(prev, freq, Integer::sum);
				}
				prev = c;
				freq = 0;
			} else if (Character.isDigit(c)) {
				int num = c - '0';
				freq = freq * 10 + num;
			}
		}
		map.merge(prev, freq, Integer::sum);
		return map;
	}

	public static void main(String[] args) {
		System.out.println(compression("b2a3b1c2a1")); // a4b3c2
		System.out.println(compression("a3b1a1c2")); // a4b1c2
	}
}
