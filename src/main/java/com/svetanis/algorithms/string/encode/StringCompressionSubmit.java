package com.svetanis.algorithms.string.encode;

// 3167. Better Compression of String
//
// Input: a compressed string, each letter followed by a count ("a3c9b2c1"); a letter may appear
// in more than one pair. Returns the better compression: each letter once, in alphabetical order,
// followed by its total count ("a3b2c10"). On LeetCode: 1 <= compressed.length <= 6 * 10^4,
// lowercase letters and digits, every letter followed by a count from 1 to 10^4 with no leading
// zeros.
//
// The one idea: one slot of freq is a letter ('a' in slot 0, 'z' in slot 25) and holds its total
// count so far. Read the pairs left to right and add each count to its letter's slot. Walking the
// slots from 0 to 25 visits the letters in alphabetical order, so writing every slot above 0 is the
// answer.
//
// Time: O(n + 26) -- n = compressed.length; one pass to add, one walk over the 26 slots.
// Space: O(1) besides the answer -- 26 counts.
//
// Sibling: StringCompression keeps the totals in a TreeMap, which holds its letters in order.

public final class StringCompressionSubmit {

	public static String compression(String compressed) {
		int index = 0;
		int n = compressed.length();
		int[] freq = new int[26];
		while (index < n) { // WALK: one letter-and-count pair per pass
			char c = compressed.charAt(index++);
			int num = 0;
			while (index < n && Character.isDigit(compressed.charAt(index))) {
				num = num * 10 + (compressed.charAt(index) - '0'); // READ: the count, digit by digit
				index += 1;
			}
			freq[c - 'a'] += num; // ADD: to the letter's total
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) { // WALK: slots in alphabetical order
			if (freq[i] > 0) { // SKIP: letters that never appeared
				char c = (char) (i + 'a');
				sb.append(c).append(freq[i]); // WRITE: the letter, then its total
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(compression("b2a3b1c2a1")); // a4b3c2
		System.out.println(compression("a3b1a1c2")); // a4b1c2
		System.out.println(compression("a3c9b2c1")); // a3b2c10
	}
}
