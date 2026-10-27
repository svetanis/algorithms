package com.svetanis.algorithms.prefixsum;

// 2055. Plates Between Candles
//
// Input: a string s of plates '*' and candles '|', and queries, each [left, right], naming the
// piece of s from index left to index right, both included. A plate counts for a query when it lies
// inside the piece with a candle to its left and a candle to its right, both also inside the piece.
// Returns one count per query. On LeetCode: 3 <= s.length <= 10^5, 1 <= queries.length <= 10^5,
// 0 <= left <= right < s.length.
//
// The one idea: the plates that count are exactly the plates between the piece's first candle and
// its last candle. Three arrays over s answer that in constant time per query.
// right(s)[i] holds the index of the nearest candle at or after i, so right(s)[left] is the piece's
// first candle; left(s)[i] holds the nearest candle at or before i, so left(s)[right] is its last
// candle; -1 means there is none. prefix[i] holds the number of plates in s[0 .. i - 1], so the
// plates strictly between candles at start and end are prefix[end] - prefix[start + 1]. When there
// is no candle, or only one, inside the piece, the first candle is not before the last and the
// count stays 0.
//
// Time: O(n + q) -- three passes over s, then constant work per query.
// Space: O(n + q) -- three arrays of n + 1 entries, and q answers.
//
// Siblings: none.

public final class PlatesBetweenCandles {

	public static int[] platesBetweenCandles(String s, int[][] queries) {
		int n = queries.length;
		int[] prefix = prefix(s); // PLATES before each index
		int[] left = left(s); // NEAREST candle at or before each index
		int[] right = right(s); // NEAREST candle at or after each index
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			int start = right[queries[i][0]]; // FIRST candle inside the piece
			int end = left[queries[i][1]]; // LAST candle inside the piece
			if (start >= 0 && end >= 0 && start < end) { // TWO candles at least; otherwise 0
				a[i] = prefix[end] - prefix[start + 1]; // COUNT plates strictly between them
			}
		}
		return a;
	}

	private static int[] left(String s) {
		int n = s.length();
		int[] a = new int[n + 1];
		int prev = -1; // NONE yet
		for (int i = 0; i < n; i++) { // WALK left to right
			if (s.charAt(i) == '|') {
				prev = i;
			}
			a[i] = prev; // LAST candle seen at or before i
		}
		return a;
	}

	private static int[] right(String s) {
		int n = s.length();
		int[] a = new int[n + 1];
		int next = -1; // NONE yet
		for (int i = n - 1; i >= 0; i--) { // WALK right to left
			if (s.charAt(i) == '|') {
				next = i;
			}
			a[i] = next; // FIRST candle at or after i
		}
		return a;
	}

	private static int[] prefix(String s) {
		int n = s.length();
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int plate = s.charAt(i) == '*' ? 1 : 0;
			prefix[i + 1] = prefix[i] + plate; // PLATES in s[0 .. i]
		}
		return prefix;
	}

	// PRINT: each value followed by a space, then a line break
	private static void print(int[] a) {
		for (int value : a) {
			System.out.printf("%d ", value);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String s1 = "**|**|***|";
		String s2 = "***|**|*****|**||**|*";
		int[][] q1 = { { 2, 5 }, { 5, 9 } };
		int[][] q2 = { { 1, 17 }, { 4, 5 }, { 14, 17 }, { 5, 11 }, { 15, 16 } };
		print(platesBetweenCandles(s1, q1)); // 2 3
		print(platesBetweenCandles(s2, q2)); // 9 0 0 0 0
	}
}
