package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.List;

// Every n-letter word of a and b (not a LeetCode problem)
//
// Input: n (assumed 0 <= n <= about 20). Returns every word of n letters, each letter a or b, in
// lexicographic order: n = 2 gives [aa, ab, ba, bb].
//
// The one idea: BinaryStringsOfLengthN with 'a' and 'b' in place of '0' and '1', and with the word
// built so far carried a fourth way: as a list of letters (letters), where one call fills position
// letters.size(). Choose a letter by adding it to the list, recurse, then remove it again, or the
// next letter would be added after it -- the same add, recurse, remove as withBuilderAndUndo there,
// with a List where that uses a StringBuilder. The list is joined into one string only when the
// word is finished. The letters come from LETTERS, so the loop is over a list rather than two calls
// or a range of characters.
//
// Time: O(n * 2^n) -- 2^n words, each joined from n letters.
// Space: O(n) besides the output -- the recursion depth and the letters chosen.
//
// Siblings: BinaryStringsOfLengthN.withBuilderAndUndo -- the same walk over a StringBuilder.

public final class LetterCombinationBacktrack {
	private static final List<String> LETTERS = List.of("a", "b");

	public static List<String> letterCombination(int n) {
		List<String> letters = new ArrayList<>();
		List<String> words = new ArrayList<>();
		dfs(n, letters, words);
		return words;
	}

	// no index parameter: the position being filled is letters.size()
	private static void dfs(int n, List<String> letters, List<String> words) {
		if (letters.size() == n) { // every position chosen
			words.add(String.join("", letters)); // RECORD: a new string, letters keeps changing
			return;
		}
		for (String letter : LETTERS) { // WALK: a, then b
			letters.add(letter); // CHOOSE
			dfs(n, letters, words);
			letters.remove(letters.size() - 1); // UNDO, or the next letter lands after this one
		}
	}

	public static void main(String[] args) {
		System.out.println(letterCombination(2)); // [aa, ab, ba, bb]
		System.out.println(letterCombination(3)); // [aaa, aab, aba, abb, baa, bab, bba, bbb]
		System.out.println(letterCombination(4).size()); // 16
	}
}
