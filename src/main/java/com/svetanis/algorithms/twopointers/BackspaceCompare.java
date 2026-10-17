package com.svetanis.algorithms.twopointers;

// 844. Backspace String Compare
//
// Input: two strings of lowercase letters and '#'. A '#' is a backspace: it deletes
// the nearest surviving letter to its left, and does nothing when there is none.
// Return: whether the two strings type out the same text.
//
// The one idea: walk both strings BACKWARDS, because a '#' deletes to its left.
// Going right to left, every '#' is met before the letter it deletes, so a count of
// pending backspaces says at once whether the current letter survives. backspace()
// moves an index left to the next surviving letter, or to -1 when none is left;
// then the two surviving letters are compared, one pair at a time.
// The loop runs while EITHER string has input left (||, not &&): with && it would
// stop as soon as one string ran out and miss that the other still has a letter.
//
// Time: O(n + m) -- each index only moves left, so every character is visited once.
// Space: O(1) -- the typed-out text of either string is never built.

public final class BackspaceCompare {

	public static boolean compare(String s, String t) {
		int i = s.length() - 1;                     // START: at the right end of both
		int j = t.length() - 1;
		while (i >= 0 || j >= 0) {                  // STOP: both used up -- OR, not AND
			i = backspace(s, i);                    // SKIP to the next surviving letter of s
			j = backspace(t, j);                    // SKIP to the next surviving letter of t
			if (i >= 0 && j >= 0) {
				if (s.charAt(i) != t.charAt(j)) {
					return false;                   // RETURN false: both have a letter, and they differ
				}
			} else if (i >= 0 || j >= 0) {
				return false;                       // RETURN false: exactly one ran out, different lengths
			}
			i--;                                    // MOVE past the two compared letters
			j--;
		}
		return true;                                // RETURN true: every surviving pair matched
	}

	// walk left past every deleted character, returning the index of the
	// next surviving character, or -1 if there is none
	private static int backspace(String s, int index) {
		int skip = 0;                               // backspaces met and not yet used
		while (index >= 0) {
			if (s.charAt(index) == '#') {
				skip++;                             // OWE: one more letter to the left is deleted
				index--;
			} else if (skip > 0) {
				skip--;                             // PAY: this letter is the one deleted
				index--;
			} else {
				break;                              // FOUND: a real character with nothing pending
			}
		}
		return index;
	}

	public static void main(String[] args) {
		System.out.println(compare("ab#c", "ad#c"));         // true
		System.out.println(compare("ab##", "c#d#"));         // true
		System.out.println(compare("a#c", "b"));             // false
		System.out.println(compare("bxj##tw", "bxj###tw"));  // false
		System.out.println(compare("a##c", "#a#c"));         // true

		System.out.println(compare("xy#z", "xzz#"));         // true
		System.out.println(compare("xy#z", "xyz#"));         // false
		System.out.println(compare("xp#", "xyz##"));         // true
		System.out.println(compare("xywrrmp", "xywrrmu#p")); // true
	}
}
