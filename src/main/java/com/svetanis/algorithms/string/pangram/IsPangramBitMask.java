package com.svetanis.algorithms.string.pangram;

// 1832. Check if the Sentence Is Pangram
//
// Input: a sentence. On LeetCode: 1 <= sentence.length <= 1000, lowercase English letters
// only. Both methods rely on that: isPangram2 puts every character in a bit by its distance
// from 'a', so a space or an upper-case letter would land in the wrong bit.
// Returns: whether every letter a-z appears at least once.
//
// The one idea: ask for each of the 26 letters whether it is in the sentence. isPangram
// asks the string directly with indexOf. isPangram2 keeps the answers as the bits of one
// int: bit i is 1 once the letter number i ('a' is 0, 'z' is 25) was seen, and the sentence
// is a pangram when the 26 low bits are all 1, which is (1 << 26) - 1.
//
// The methods:
//   isPangram  -- 26 indexOf calls, no flags
//   isPangram2 -- one pass setting bits in an int, then one comparison
//
// Time: isPangram O(26 * n) -- each indexOf scans the sentence; isPangram2 O(n) -- one pass.
// Space: O(1) -- no storage in isPangram; one int in isPangram2.
//
// Siblings: IsPangram -- the same 26 flags as a boolean array, and a version that rejects
// characters that are not letters.

public final class IsPangramBitMask {

	public static boolean isPangram(String s) {
		for (char c = 'a'; c <= 'z'; c++) { // WALK the 26 letters
			if (s.indexOf(c) == -1) {
				return false; // RETURN false: this letter never appears
			}
		}
		return true; // RETURN true: every letter was found
	}

	public static boolean isPangram2(String s) {
		int mask = 0; // FLAGS: bit i is 1 once letter number i was seen
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			mask |= 1 << index; // SET: add the letter to the set
		}
		return mask == (1 << 26) - 1; // RETURN: all 26 low bits are on
	}

	public static void main(String[] args) {
		String s1 = "thequickbrownfoxjumpsoverthelazydog";
		System.out.println(isPangram(s1)); // true
		System.out.println(isPangram2(s1)); // true

		String s2 = "leetcode";
		System.out.println(isPangram(s2)); // false
		System.out.println(isPangram2(s2)); // false
	}
}