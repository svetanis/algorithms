package com.svetanis.algorithms.string;

import static java.util.Arrays.asList;

import java.util.List;

// 422. Valid Word Square
//
// Input: a list of words, read as rows of a grid (rows may have different lengths). The grid is a
// valid word square if the k-th row and the k-th column read the same string, for every k. Returns
// true for a valid word square. On LeetCode: 1 <= words.length <= 500, 1 <= words[i].length <= 500,
// lowercase letters.
//
// The one idea: one cell is the letter words[row].charAt(col). The square is valid when every cell
// equals its mirror cell across the diagonal, words[col].charAt(row) -- and the mirror must exist:
// if there is no row number col, or that row is too short to have a letter at position row, the
// column is longer than the row and the square is invalid. Checking every existing cell this way
// also catches the case where a column is longer than its row: that column's extra cell is itself
// checked from the other side, where its mirror is missing.
//
// Time: O(c) -- c is the total number of letters; each cell is looked at once.
// Space: O(1) -- no extra storage.

public final class ValidWordSquare {

	public static boolean validWordSquare(List<String> words) {
		int n = words.size();
		for (int row = 0; row < n; row++) {
			int m = words.get(row).length();
			for (int col = 0; col < m; col++) { // WALK: every cell of the row
				if (col >= n || row >= words.get(col).length()) { // the mirror cell does not exist
					return false;
				}
				char c1 = words.get(row).charAt(col);
				char c2 = words.get(col).charAt(row); // the mirror cell across the diagonal
				if (c1 != c2) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] agrs) {
		List<String> words = asList("AREA", "BALL", "DEAR", "LADY");
		System.out.println(validWordSquare(words)); // false
		List<String> words2 = asList("BALL", "AREA", "LEAD", "LADY");
		System.out.println(validWordSquare(words2)); // true
		List<String> words3 = asList("abcd", "bnrt", "crm", "dt");
		System.out.println(validWordSquare(words3)); // true
		List<String> words4 = asList("ball", "area", "read", "lady");
		System.out.println(validWordSquare(words4)); // false
	}
}
