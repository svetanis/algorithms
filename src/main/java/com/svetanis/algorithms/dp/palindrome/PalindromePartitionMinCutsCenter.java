package com.svetanis.algorithms.dp.palindrome;

// 132. Palindrome Partitioning II

// given a string, 
// cut it into pieces 
// such that each piece
// is a palindrome. 
// find minimum number of cuts.

public final class PalindromePartitionMinCutsCenter {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int palindromePartition(String s) {
		int n = s.length();
		int[] minCuts = cutsInit(n);
		for (int cnt = 0; cnt < n; cnt++) {
			// odd length palindrome check
			for (int j = 0; cnt - j >= 0 && cnt + j < n && s.charAt(cnt - j) == s.charAt(cnt + j); j++) {
				if (cnt - j == 0) {
					minCuts[cnt + j] = 0;
				} else {
					minCuts[cnt + j] = Math.min(minCuts[cnt + j], 1 + minCuts[cnt - j - 1]);
				}
			}
			// even length palindrome check
			for (int j = 0; cnt - j >= 0 && cnt + j + 1 < n && s.charAt(cnt - j) == s.charAt(cnt + j + 1); j++) {
				if (cnt - j == 0) {
					minCuts[cnt + j + 1] = 0;
				} else {
					minCuts[cnt + j + 1] = Math.min(minCuts[cnt + j + 1], 1 + minCuts[cnt - j - 1]);
				}
			}
		}
		return minCuts[n - 1];
	}

	private static int[] cutsInit(int n) {
		int[] cuts = new int[n];
		for (int i = 0; i < n; i++) {
			cuts[i] = i;
		}
		return cuts;
	}

	public static void main(String[] args) {
		System.out.println(palindromePartition("abdbca")); // 3
		System.out.println(palindromePartition("cddpd")); // 2
		System.out.println(palindromePartition("pqr")); // 2
		System.out.println(palindromePartition("pp")); // 0
	}
}
