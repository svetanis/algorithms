package com.svetanis.algorithms.recursive.permutation;

import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

import java.util.Set;

import com.google.common.collect.ImmutableList;

// String Permutations by changing case
// Given a string, find all of its permutations 
// preserving the character sequence but changing case.

public final class PermutationsChangeCaseRecursive {

	public static ImmutableList<String> permute(String str) {
		Set<String> set = newHashSet();
		permute(str.toCharArray(), 0, set);
		return newList(set);
	}

	private static void permute(char[] a, int i, Set<String> set) {
		if (i == a.length) {
			return;
		}
		a[i] = toLowerCase(a[i]);
		set.add(new String(a));
		permute(a, i + 1, set);

		a[i] = toUpperCase(a[i]);
		set.add(new String(a));
		permute(a, i + 1, set);
	}

	public static void main(String[] args) {
		String s1 = "ad52";
		printLines(permute(s1));

		String s2 = "ab7c";
		printLines(permute(s2));
	}
}
