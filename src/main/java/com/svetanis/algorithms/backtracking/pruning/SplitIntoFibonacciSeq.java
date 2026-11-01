package com.svetanis.algorithms.backtracking.pruning;

import java.util.ArrayList;
import java.util.List;

// 842. Split Array into Fibonacci Sequence
//
// Input: num -- a string of digits. Returns any way to cut it into at least three numbers, in
// order, with no leading 0 on a number longer than one digit, each at most 2^31 - 1, and each
// number from the third on equal to the sum of the two before it; an empty list if there is none.
// On LeetCode: 1 <= num.length <= 200, digits only.
//
// The one idea: once the first two numbers are chosen, every later number is forced -- it must
// equal the sum of the two before it -- so the only real search is over the first two. One call
// starts a number at index and tries its length one digit at a time, reading the digits into val.
// With fewer than two numbers placed, any val is allowed. After that val must equal the sum of
// the last two, and as soon as val is bigger than that sum the loop stops, since adding digits
// only makes val bigger. The cut also stops at a leading 0 and at a number above 2^31 - 1. The
// string is split when it is used up with at least three numbers in the list. The sum of two ints
// can wrap to a negative; every val is then bigger than it and the loop stops, which is right,
// because the true sum is above the largest allowed number.
//
// Time: O(n) -- the first two numbers have at most 10 digits each, so at most 100 starts; after
// them every number is forced, so each start walks the string once, trying at most 10 digits per
// number. A constant of about a thousand times n.
// Space: O(n) -- the recursion and the list are at most n deep.

public final class SplitIntoFibonacciSeq {

	public static List<Integer> sfs(String s) {
		List<Integer> list = new ArrayList<>();
		if (dfs(s, 0, list)) {
			return list;
		}
		return new ArrayList<>();
	}

	private static boolean dfs(String s, int index, List<Integer> list) {
		if (index == s.length()) { // BASE: the string is used up
			return list.size() >= 3; // RETURN: at least three numbers
		}
		long val = 0;
		for (int i = index; i < s.length(); i++) { // TRY: the number is s[index..i]
			if (i > index && s.charAt(index) == '0') { // CUT: no number longer than 1 digit starts with 0
				break;
			}
			val = val * 10 + s.charAt(i) - '0';
			if (val > Integer.MAX_VALUE) { // CUT: above the largest allowed number
				break;
			}
			int size = list.size();
			if (size >= 2 && val > list.get(size - 1) + list.get(size - 2)) { // CUT: already above the sum
				break;
			}
			if (size < 2 || val == list.get(size - 1) + list.get(size - 2)) { // TAKE: free, or exactly the sum
				list.add((int) val);
				if (dfs(s, i + 1, list)) {
					return true;
				}
				list.remove(list.size() - 1); // UNDO
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(sfs("1101111")); // [11, 0, 11, 11]
		System.out.println(sfs("112358130")); // []
		System.out.println(sfs("0123")); // []
	}
}
