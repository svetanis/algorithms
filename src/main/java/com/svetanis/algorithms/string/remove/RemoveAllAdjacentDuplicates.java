package com.svetanis.algorithms.string.remove;

// 1047. Remove All Adjacent Duplicates In String
//
// Input: a string s. Remove two equal neighbouring letters, again and again, until none are left.
// Returns the final string (it is the same whatever order the pairs are removed in). On
// LeetCode: 1 <= s.length <= 10^5, s is lowercase English letters.
//
// The one idea: the builder sb is the answer so far, and its last character is the only one a new
// letter can pair with. Read s left to right; if the new letter equals sb's last character the
// two cancel, so delete that last character, otherwise append the letter. A deletion can expose
// an older letter that now sits next to the following one, and that is found the same way,
// because the older letter is the new last character. sb works as a stack.
//
// Time: O(n) -- each letter is appended once and deleted at most once.
// Space: O(n) -- the builder.
//
// Siblings: RemoveAllAdjacentDuplicates1209 is the same idea for runs of k equal letters, so the
// stack must also count; RemoveStars2390 deletes the last character on a '*' instead of a match.

public final class RemoveAllAdjacentDuplicates {

	public static String remove(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			int len = sb.length();
			if (len > 0 && sb.charAt(len - 1) == c) { // MATCH: the new letter equals the last kept one
				sb.deleteCharAt(sb.length() - 1); // CANCEL: both go
			} else {
				sb.append(c); // KEEP: for now
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(remove("abbaca")); // ca
		System.out.println(remove("azxxzy")); // ay
	}
}
