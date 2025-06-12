package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 784. Letter Case Permutation

// String Permutations by changing case
// Given a string, find all of its permutations 
// preserving the character sequence but changing case.

public final class LetterCasePermutationRecursive {

	public static List<String> permute(String s) {
		Set<String> set = new HashSet<>();
		permute(s.toCharArray(), 0, set);
		return new ArrayList<>(set);
	}

	private static void permute(char[] chars, int index, Set<String> set) {
		if (index == chars.length) {
			return;
		}
		chars[index] = Character.toLowerCase(chars[index]);
		set.add(new String(chars));
		permute(chars, index + 1, set);

		chars[index] = Character.toUpperCase(chars[index]);
		set.add(new String(chars));
		permute(chars, index + 1, set);
	}

	public static void main(String[] args) {
		System.out.println(permute("ad52"));
		System.out.println(permute("ab7c"));

		System.out.println(permute("a1b2")); // a1b2,a1B2,A1b2,A1B2
		System.out.println(permute("3z4")); // 3z4,3Z4
	}
}
