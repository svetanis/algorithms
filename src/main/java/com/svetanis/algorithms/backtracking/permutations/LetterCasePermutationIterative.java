package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 784. Letter Case Permutation

// given a set of distinct numbers
// find all of its permutations

// Permutation is defined as the re-arranging 
// of the elements of the set

// if a set has 'n' distinct elements
// it will have n! permutations

public final class LetterCasePermutationIterative {
	// Time Complexity: O(n * n!)
	// Space Complexity: O(n * n!)

	public static List<String> permute(String s) {
		List<String> list = new ArrayList<>();
		list.add(s);
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				int size = list.size();
				for (int j = 0; j < size; j++) {
					char[] chars = list.get(j).toCharArray();
					if (Character.isUpperCase(chars[i])) {
						chars[i] = Character.toLowerCase(chars[i]);
					} else {
						chars[i] = Character.toUpperCase(chars[i]);
					}
					list.add(String.valueOf(chars));
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(permute("ad52"));
		System.out.println(permute("ab7c"));

		System.out.println(permute("a1b2")); // a1b2,a1B2,A1b2,A1B2
		System.out.println(permute("3z4")); // 3z4,3Z4
	}
}
