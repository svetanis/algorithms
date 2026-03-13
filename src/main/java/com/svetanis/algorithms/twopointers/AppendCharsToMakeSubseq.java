package com.svetanis.algorithms.twopointers;

// 2486. Append Characters to String to Make Subsequence

public final class AppendCharsToMakeSubseq {
	// Time Complexity: O(n)

	public static int appendChars(String s, String t) {
		int i = 0, j = 0;
		int n = s.length();
		int m = t.length();
		while(i < n && j < m) {
			if(s.charAt(i) == t.charAt(j)) {
				j += 1;
			}
			i += 1;
		}
		return m - j;
	}

	public static void main(String[] args) {
		System.out.println(appendChars("coaching", "coding")); // 4
		System.out.println(appendChars("abcde", "a")); // 0
		System.out.println(appendChars("z", "abcde")); // 5
		System.out.println(appendChars("lbg", "g")); // 0		
	}
}
