package com.svetanis.algorithms.dp.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 131. Palindrome Partitioning

// given a string, 
// cut it into pieces 
// such that each piece
// is a palindrome. 
// return all possible patitionings of s

public final class PalindromePartitions {

	public static List<List<String>> palindromePartition(String s) {
		boolean[][] dp = isPalindrome(s);
		List<String> partition = new ArrayList<>();
		List<List<String>> partitions = new ArrayList<>();
		dfs(s, 0, partition, partitions, dp);
		return partitions;
	}

	private static void dfs(String s, int left, List<String> partition, 
			List<List<String>> partitions, boolean[][] dp) {
		if (left == s.length()) {
			partitions.add(new ArrayList<>(partition));
			return;
		}
		for (int right = left; right < s.length(); right++) {
			if (dp[left][right]) {
				partition.add(s.substring(left, right + 1));
				dfs(s, right + 1, partition, partitions, dp);
				partition.remove(partition.size() - 1);
			}
		}
	}

	private static boolean[][] isPalindrome(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		for (boolean[] row : dp) {
			Arrays.fill(row, true);
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				boolean equals = s.charAt(i) == s.charAt(j);
				dp[i][j] = dp[i + 1][j - 1] && equals;
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		System.out.println(palindromePartition("aab")); // [a,a,b], [aa,b]
		System.out.println(palindromePartition("a")); // [a]
	}
}
