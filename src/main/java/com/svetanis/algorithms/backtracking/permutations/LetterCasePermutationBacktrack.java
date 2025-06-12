package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 784. Letter Case Permutation

public final class LetterCasePermutationBacktrack {
	// Time Complexity: O(2^m * n)

	public static List<String> permute(String s) {
		List<String> list = new ArrayList<>();
		permute(s.toCharArray(), 0, list);
		return list;
	}

	private static void permute(char[] chars, int index, List<String> list) {
		if (index >= chars.length) {
			list.add(new String(chars));
			return;
		}
		permute(chars, index + 1, list);
		if (Character.isLetter(chars[index])) {
			// toggle the case
			chars[index] ^= ' ';
			permute(chars, index + 1, list);
			// backtrack
			chars[index] ^= ' ';
		}
	}

	public static void main(String[] args) {
		System.out.println(permute("ad52"));
		System.out.println(permute("ab7c"));

		System.out.println(permute("a1b2")); // a1b2,a1B2,A1b2,A1B2
		System.out.println(permute("3z4")); // 3z4,3Z4
	}
}
