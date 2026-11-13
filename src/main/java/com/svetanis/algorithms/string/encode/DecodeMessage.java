package com.svetanis.algorithms.string.encode;

// 2325. Decode the Message
//
// Input: a key and a message. The first appearance of each letter in the key, in order, gives the
// substitution table: the first new letter stands for 'a', the second for 'b', and so on; spaces
// stay spaces. Returns the decoded message. On LeetCode: 26 <= key.length <= 2000, the key holds
// every lowercase letter at least once, plus spaces; 1 <= message.length <= 2000, lowercase letters
// and spaces.
//
// The one idea: build the table once. One slot of decoder is a key letter, and it holds the
// plain letter it stands for, or 0 while that key letter has not appeared yet. Walk the key: a
// letter whose slot is still 0 is new, so it gets the next plain letter. Then each message
// letter is one table lookup.
//
// Time: O(k + m) -- k = key.length, m = message.length; one pass over each.
// Space: O(1) besides the answer -- a table of 128 slots.
//
// Sibling: DecodeMessageSimple keeps the same table in a HashMap and handles the space in the
// decoding loop instead of in the table.

public final class DecodeMessage {

	public static String decode(String key, String message) {
		StringBuilder sb = new StringBuilder();
		char[] decoder = decoder(key);
		for (char c : message.toCharArray()) {
			sb.append(decoder[c]); // LOOK UP: the plain letter for c; a space maps to a space
		}
		return sb.toString();
	}

	private static char[] decoder(String key) {
		int index = 0; // how many plain letters are given out so far
		char[] decoder = new char[128];
		decoder[' '] = ' '; // SPACE: stays a space, and is never given a letter
		for (char c : key.toCharArray()) {
			if (decoder[c] == 0) { // NEW: c's first appearance in the key
				decoder[c] = (char) ('a' + index++); // ASSIGN: the next plain letter
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
