package com.svetanis.algorithms.backtracking.pruning;

import java.util.ArrayList;
import java.util.List;

// 93. Restore IP Addresses
//
// Input: s -- a string of digits. Returns every valid IP address that uses all the digits of s in
// order, with dots put between them: four numbers from 0 to 255, none with a leading 0 unless it
// is 0 itself. On LeetCode: 1 <= s.length <= 20, digits only.
//
// The one idea: the only free choice is where each of the first three dots goes. One call starts a
// new number at index, with the numbers already cut in segment, and tries the number's length as 1,
// 2 and 3 digits. A number is dropped the moment it is over 255 or starts with 0 and has more than
// one digit (that is the pruning -- a longer one from the same start can only be worse, so the
// loop stops), and a call is dropped when four numbers are already placed or the digits ran out.
// An address is complete when the fourth number ends exactly at the end of s.
//
// Time: O(1) -- at most four numbers of at most three digits, so at most 3^4 = 81 paths whatever
// the length of s; each answer is joined in O(n).
// Space: O(1) -- the recursion is at most four deep, besides the answers.

public final class RestoreIPAddresses {

	public static List<String> restoreIpAddresses(String s) {
		List<String> list = new ArrayList<>();
		List<String> segment = new ArrayList<>();
		dfs(s, 0, segment, list);
		return list;
	}

	private static void dfs(String s, int index, List<String> segment, List<String> list) {
		int n = s.length();
		if (index >= n && segment.size() == 4) { // BASE: four numbers, every digit used
			list.add(String.join(".", segment)); // RECORD
			return;
		}
		if (index >= n || segment.size() >= 4) { // CUT: digits left over, or digits missing
			return;
		}
		int val = 0;
		for (int i = index; i < Math.min(index + 3, n); i++) { // TRY: the number is s[index..i], 1 to 3 digits
			val = val * 10 + s.charAt(i) - '0';
			boolean leadingZero = s.charAt(index) == '0' && index != i;
			if (val > 255 || leadingZero) { // PRUNE: no longer number from this start can be valid
				break;
			}
			segment.add(s.substring(index, i + 1)); // TAKE
			dfs(s, i + 1, segment, list);
			segment.remove(segment.size() - 1); // UNDO
		}
	}

	public static void main(String[] args) {
		System.out.println(restoreIpAddresses("25525511135")); // [255.255.11.135, 255.255.111.35]
		System.out.println(restoreIpAddresses("0000")); // [0.0.0.0]
		System.out.println(restoreIpAddresses("101023")); // [1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]
	}
}
