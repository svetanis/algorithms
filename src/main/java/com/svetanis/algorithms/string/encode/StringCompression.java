package com.svetanis.algorithms.string.encode;

import java.util.TreeMap;

// 3167. Better Compression of String
//
// Input: a compressed string, each letter followed by a count ("a3c9b2c1"); a letter may appear
// in more than one pair. Returns the better compression: each letter once, in alphabetical order,
// followed by its total count ("a3b2c10"). On LeetCode: 1 <= compressed.length <= 6 * 10^4,
// lowercase letters and digits, every letter followed by a count from 1 to 10^4 with no leading
// zeros.
//
// The one idea: one entry of the map is a letter and its total count so far. Read the pairs left
// to right and add each count to its letter's total. A TreeMap keeps its letters in alphabetical
// order, so writing the entries out in turn is already the answer.
//
// Time: O(n + k log k) -- n = compressed.length, k = the number of pairs; each pair is one
// TreeMap update, and at most 26 letters are ever in the map.
// Space: O(1) besides the answer -- at most 26 entries.
//
// Sibling: StringCompressionSubmit keeps the totals in an array of 26 counts and writes the
// letters out by walking the array. StringCompression443 is a different problem: it compresses
// a plain string in place.

public final class StringCompression {

	public static String compression(String compressed) {
		TreeMap<Character, Integer> map = freqSimple(compressed);
		StringBuilder sb = new StringBuilder();
		for (char c : map.keySet()) { // WALK: letters in alphabetical order
			sb.append(c);
			sb.append(map.get(c)); // WRITE: the letter, then its total
		}
		return sb.toString();
	}

	private static TreeMap<Character, Integer> freqSimple(String compressed) {
		int n = compressed.length();
		int index = 0;
		TreeMap<Character, Integer> map = new TreeMap<>();
		while (index < n) { // WALK: one letter-and-count pair per pass
			char c = compressed.charAt(index);
			int numIndex = index + 1;
			int num = 0;
			while (numIndex < n && Character.isDigit(compressed.charAt(numIndex))) {
				num = num * 10 + (compressed.charAt(numIndex) - '0'); // READ: the count, digit by digit
				numIndex += 1;
			}
			map.merge(c, num, Integer::sum); // ADD: to the letter's total
			index = numIndex; // NEXT PAIR: starts after the digits
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(compression("b2a3b1c2a1")); // a4b3c2
		System.out.println(compression("a3b1a1c2")); // a4b1c2
		System.out.println(compression("a3c9b2c1")); // a3b2c10
	}
}
