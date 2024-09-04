package com.svetanis.algorithms.recursive.permutation;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.lang.Character.isLetter;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.lang.String.valueOf;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given a set of distinct numbers
// find all of its permutations

// Permutation is defined as the re-arranging 
// of the elements of the set

// if a set has 'n' distinct elements
// it will have n! permutations

public final class PermutationsChangeCaseIterative {
	// Time Complexity: O(n * n!)
	// Space Complexity: O(n * n!)

	public static ImmutableList<String> permute(String s) {
		List<String> lists = newArrayList();
		lists.add(s);
		for (int i = 0; i < s.length(); i++) {
			if (isLetter(s.charAt(i))) {
				int size = lists.size();
				for (int j = 0; j < size; j++) {
					char[] chars = lists.get(j).toCharArray();
					if (isUpperCase(chars[i])) {
						chars[i] = toLowerCase(chars[i]);
					} else {
						chars[i] = toUpperCase(chars[i]);
					}
					lists.add(valueOf(chars));
				}
			}
		}
		return newList(lists);
	}

	public static void main(String[] args) {
		String s1 = "ad52";
		printLines(permute(s1));

		String s2 = "ab7c";
		printLines(permute(s2));
	}
}
