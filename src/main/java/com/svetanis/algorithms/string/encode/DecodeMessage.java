package com.svetanis.algorithms.string.encode;

// 2325. Decode the Message

public final class DecodeMessage {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String decode(String key, String message) {
		StringBuilder sb = new StringBuilder();
		char[] decoder = decoder(key);
		for (char c : message.toCharArray()) {
			sb.append(decoder[c]);
		}
		return sb.toString();
	}

	private static char[] decoder(String key) {
		int index = 0;
		char[] decoder = new char[128];
		decoder[' '] = ' ';
		for (char c : key.toCharArray()) {
			if (decoder[c] == 0) {
				decoder[c] = (char) ('a' + index++);
			}
		}
		return decoder;
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
