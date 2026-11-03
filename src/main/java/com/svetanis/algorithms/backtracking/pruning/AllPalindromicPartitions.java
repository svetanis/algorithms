package com.svetanis.algorithms.backtracking.pruning;

import java.util.ArrayList;
import java.util.List;

// 131. Palindrome Partitioning
//
// Input: a string s. Returns every way to cut it into pieces so that each piece reads the same
// forwards and backwards, in any order. "aab" has two: [a, a, b] and [aa, b]. On LeetCode:
// 1 <= s.length <= 16, lowercase letters.
//
// The one idea: choose the FIRST piece of what is left, then solve the rest the same way. One
// call starts at index and tries every end for the first piece; only an end that makes the piece a
// palindrome is followed (that is the pruning -- a piece that is not a palindrome is never
// extended), and the call for the rest starts at end + 1. A single character is always a
// palindrome, so every call finds at least one piece. The piece list is shared and undone after
// each try, so a finished partition is recorded as a copy.
//
// Time: O(n * 2^n) -- up to 2^(n - 1) partitions when every character is the same; most calls sit
// near the end of the string, so the palindrome tests add up to O(2^n) (counted on "aaaa...":
// about 2/3 * 2^n character comparisons for n = 10 to 16) and copying the partitions O(n) each.
// Space: O(n) for the recursion and the current piece list, besides the output.
//
// Sibling -- the same problem: dp.palindrome.PalindromePartitions reads each test from a palindrome
// table filled first.

public final class AllPalindromicPartitions {

	public static List<List<String>> partitions(String str) {
		List<String> list = new ArrayList<>();
		List<List<String>> lists = new ArrayList<>();
		partitions(str, 0, list, lists);
		return lists;
	}

	private static void partitions(String s, int index, List<String> list,
			List<List<String>> lists) {
		if (index == s.length()) { // BASE: the whole string is cut
			lists.add(new ArrayList<>(list)); // RECORD: a copy, the list is shared
			return;
		}
		for (int end = index; end < s.length(); end++) { // TRY: the first piece is s[index, end]
			String prefix = s.substring(index, end + 1);
			if (isPalindrome(prefix)) { // PRUNE: only a palindrome is extended
				list.add(prefix); // TAKE
				// the next piece starts at end + 1, taken from the loop variable
				partitions(s, end + 1, list, lists);
				list.remove(list.size() - 1); // UNDO
			}
		}
	}

	public static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) { // WALK: from both ends toward the middle
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
		printLists(partitions(str)); // a a b | aa b
		System.out.println();
		String str1 = "nitin";
		printLists(partitions(str1)); // n i t i n | n iti n | nitin
		System.out.println();
		String str2 = "abab";
		printLists(partitions(str2)); // a b a b | a bab | aba b
	}

	private static void printLists(List<List<String>> lists) {
		for (List<String> list : lists) {
			for (String e : list) {
				System.out.print(e);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}