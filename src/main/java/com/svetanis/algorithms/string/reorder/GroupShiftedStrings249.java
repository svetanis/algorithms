package com.svetanis.algorithms.string.reorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 249. Group Shifted Strings
//
// Input: an array a of lowercase words. Shifting a word moves every letter to the next letter of
// the alphabet, and z wraps around to a, so "abc" becomes "bcd" and "az" becomes "ba". Returns
// the words grouped so that each group holds exactly the words that are shifts of each other, in
// any order. On LeetCode: 1 <= a.length <= 200, 1 <= a[i].length <= 50, lowercase letters.
//
// The one idea: give every word a key that is the same for all of its shifts and different for
// any other word, then group by key in a map. The key is the word slid back until its first
// letter is 'a'. One entry of the map is a key and the list of words that slide to it. Sliding
// moves every letter by the same amount, so two words that are shifts of each other end at the
// same key, and two words that end at the same key are shifts of each other (both are that key,
// slid). Words of different length never share a key.
//
// Time: O(n * k) -- n words of at most k letters; each is slid once and put in the map once.
// Space: O(n * k) -- the keys and the groups hold every letter once more.

public final class GroupShiftedStrings249 {

	public static List<List<String>> group(String[] a) {
		Map<String, List<String>> map = new HashMap<>();
		for (String s : a) {
			String hashKey = hash(s); // KEY: the word slid so it starts with 'a'
			map.computeIfAbsent(hashKey, k -> new ArrayList<>()).add(s); // GROUP: same key, same group
		}
		return new ArrayList<>(map.values());
	}

	private static String hash(String s) {
		int shift = s.charAt(0) - 'a'; // SHIFT: how far the first letter is from 'a'
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char shifted = (char) (chars[i] - shift); // SLIDE every letter back by the same amount
			if (shifted < 'a') {
				shifted += 26; // WRAP: below 'a' comes back in at the end of the alphabet
			}
			chars[i] = shifted;
		}
		return String.valueOf(chars);
	}

	public static void main(String[] args) {
		String[] a = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
		System.out.println(group(a)); // [[a, z], [abc, bcd, xyz], [az, ba], [acef]]
	}
}