package com.svetanis.algorithms.string.encode;

import java.util.HashMap;
import java.util.Map;

// 2325. Decode the Message

public final class DecodeMessageSimple {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String decode(String key, String message) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Character> map = substitution(key);
		for (char c : message.toCharArray()) {
			if (c == ' ') {
				sb.append(c);
			} else {
				sb.append(map.get(c));
			}
		}
		return sb.toString();
	}

	private static Map<Character, Character> substitution(String key) {
		int index = 0;
		Map<Character, Character> map = new HashMap<>();
		for (char c : key.toCharArray()) {
			if (c != ' ' && !map.containsKey(c)) {
				char val = (char) ('a' + index);
				map.put(c, val);
				index++;
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String key1 = "the quick brown fox jumps over the lazy dog";
		String mes1 = "vkbs bs t suepuv";
		System.out.println(decode(key1, mes1)); // this is a secret
		String key2 = "eljuxhpwnyrdgtqkviszcfmabo";
		String mes2 = "zwx hnfx lqantp mnoeius ycgk vcnjrdb";
		System.out.println(decode(key2, mes2)); // the five boxing wizards jump quickly
	}
}
