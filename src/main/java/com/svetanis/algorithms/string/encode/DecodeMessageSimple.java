package com.svetanis.algorithms.string.encode;

import java.util.HashMap;
import java.util.Map;

// 2325. Decode the Message
//
// Input: a key and a message. The first appearance of each letter in the key, in order, gives the
// substitution table: the first new letter stands for 'a', the second for 'b', and so on; spaces
// stay spaces. Returns the decoded message. On LeetCode: 26 <= key.length <= 2000, the key holds
// every lowercase letter at least once, plus spaces; 1 <= message.length <= 2000, lowercase letters
// and spaces.
//
// The one idea: build the table once. One entry of map is a key letter and the plain letter it
// stands for. Walk the key, skipping spaces: a letter not yet in the map is new, so it gets the
// next plain letter. Then each message letter is one lookup, and a space is copied as it is.
//
// Time: O(k + m) -- k = key.length, m = message.length; one pass over each.
// Space: O(1) besides the answer -- at most 26 entries.
//
// Sibling: DecodeMessage keeps the same table in a char array of 128 slots, with the space mapped
// to itself.

public final class DecodeMessageSimple {

	public static String decode(String key, String message) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Character> map = substitution(key);
		for (char c : message.toCharArray()) {
			if (c == ' ') {
				sb.append(c); // SPACE: copied as it is
			} else {
				sb.append(map.get(c)); // LOOK UP: the plain letter for c
			}
		}
		return sb.toString();
	}

	private static Map<Character, Character> substitution(String key) {
		int index = 0; // how many plain letters are given out so far
		Map<Character, Character> map = new HashMap<>();
		for (char c : key.toCharArray()) {
			if (c != ' ' && !map.containsKey(c)) { // NEW: c's first appearance in the key
				char val = (char) ('a' + index);
				map.put(c, val); // ASSIGN: the next plain letter
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
