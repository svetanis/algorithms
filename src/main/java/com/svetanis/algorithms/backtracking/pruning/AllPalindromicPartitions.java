package com.svetanis.algorithms.backtracking.pruning;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLists;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Partition a string into Palindromes

// given a string s, partition s such that
// every substring of the partition is a
// palindrome. return all possible palindrome
// partitioning of s.

public final class AllPalindromicPartitions {
	// Time Complexity: O(n * 2^n)

	public static ImmutableList<ImmutableList<String>> partitions(String str) {
		List<String> list = newArrayList();
		List<ImmutableList<String>> lists = newArrayList();
		partitions(str, 0, list, lists);
		return newList(lists);
	}

	private static void partitions(String s, int index, List<String> list, 
			List<ImmutableList<String>> lists) {
		if (index == s.length()) {
			lists.add(newList(list));
			return;
		}
		for (int end = index; end < s.length(); end++) {
			String prefix = s.substring(index, end + 1);
			if (isPalindrome(prefix)) {
				list.add(prefix);
				// step from the loop variable, never from the substring's length --
				// a step derived from a transformed value can disagree with the bounds
				partitions(s, end + 1, list, lists);
				list.remove(list.size() - 1);
			}
		}
	}

	public static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		String str = "aab";
		printLists(partitions(str));
		System.out.println();
		String str1 = "nitin";
		printLists(partitions(str1));
		System.out.println();
		String str2 = "abab";
		printLists(partitions(str2));
	}
}