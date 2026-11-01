package com.svetanis.algorithms.backtracking.combinations.choosek;

import java.util.ArrayList;
import java.util.List;

// 1286. Iterator for Combination
//
// Input: a string of sorted, distinct lowercase letters and a length; next() returns the next
// combination of that many letters in lexicographic order, and hasNext() says whether there is one
// more. On LeetCode: 1 <= combinationLength <= characters.length <= 15; at most 10^4 calls to next
// and hasNext; next is only called when hasNext is true.
//
// The one idea: build every combination once, in the constructor, then hand them out one by one;
// next is the position of the combination the next call returns. The building is the start-index
// backtracking of 77: after taking the letter at position i, only letters to its right may follow
// (i + 1). The letters are sorted, so every combination comes out with its letters in order, and
// the combinations themselves come out in lexicographic order -- the order next() must follow.
//
// Time: O(2^n + k * C(n, k)) to build, n letters, k the length -- the tree holds every set of up to
// k letters, at most 2^n, and each of the C(n, k) answers is copied in k steps; O(1) per next and
// hasNext.
// Space: O(k * C(n, k)) -- every combination is kept.
//
// Siblings: todo.bits.LetterCombinationIteratorBitMask solves 1286 with a bitmask and no stored
// list. AllUniqueCombinationsSizeK is the same loop returning a list of integer combinations.

public final class CombinationIterator {
	private final List<String> combinations = new ArrayList<>();
	private int next = 0; // position of the combination next() returns

	public CombinationIterator(String characters, int combinationLength) {
		dfs(characters, combinationLength, 0, new StringBuilder());
	}

	private void dfs(String characters, int length, int index, StringBuilder combination) {
		if (combination.length() == length) { // RECORD: a full-length combination
			combinations.add(combination.toString());
			return;
		}
		for (int i = index; i < characters.length(); i++) {
			combination.append(characters.charAt(i)); // CHOOSE
			dfs(characters, length, i + 1, combination); // RECURSE: i + 1, letters to the right
			combination.deleteCharAt(combination.length() - 1); // UNDO
		}
	}

	public String next() {
		return combinations.get(next++); // RETURN: this one, then move on
	}

	public boolean hasNext() {
		return next < combinations.size(); // RETURN: one more is left
	}

	public static void main(String[] args) {
		CombinationIterator itr = new CombinationIterator("abc", 2);
		System.out.println(itr.next()); // ab
		System.out.println(itr.hasNext()); // true
		System.out.println(itr.next()); // ac
		System.out.println(itr.hasNext()); // true
		System.out.println(itr.next()); // bc
		System.out.println(itr.hasNext()); // false
	}
}
