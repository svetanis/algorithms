package com.svetanis.algorithms.dp.interleaved;

// Given three strings A, B and C. 
// Write a function that checks 
// whether C is an interleaving of A and B. 
// C is said to be interleaving A and B, 
// if it contains all characters of A and B 
// and order of all characters in individual strings is preserved.

public final class InterleavedNoCommonChars {

	public static boolean isInterleaved(String s1, String s2, String s) {
		int n = s1.length();
		int m = s2.length();
		int l = s.length();

		int i = 0;
		int j = 0;
		int k = 0;

		while (k < l) {
			if (i < n && s.charAt(k) == s1.charAt(i)) {
				i++;
			} else if (j < m && s.charAt(k) == s2.charAt(j)) {
				j++;
			} else {
				return false;
			}
			k++;
		}
		return (i == n && j == m);
	}

	public static void main(String[] args) {
		String str1 = "AB";
		String str2 = "CD";
		System.out.println(isInterleaved(str1, str2, "ACBD"));
	}
}
