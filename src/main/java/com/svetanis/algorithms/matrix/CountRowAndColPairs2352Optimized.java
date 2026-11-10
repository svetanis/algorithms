package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 2352. Equal Row and Column Pairs
//
// Input: grid -- an n x n grid of integers. Returns the number of pairs (r, c) such that row r and
// column c, the column read top to bottom, hold the same values in the same order. On LeetCode:
// 1 <= n <= 200, 1 <= grid[i][j] <= 10^5.
//
// The one idea: instead of setting every row against every column, count the rows once. The map
// holds, for each different row, how many rows of the grid have exactly those values. Then each
// column is read into one reused array and looked up, and the count found is the number of rows
// it matches -- so three identical rows matching one column add 3. Counting the rows, not just
// noting that a row was seen, is what makes repeated rows come out right.
//
// The map's key is Arrays.hashCode(row): an int computed from the row's n values, with no string
// built. Different rows can share one hash, so each key holds a short list of RowCount entries,
// one per different row, and Arrays.equals decides which one, if any, is the row looked for.
//
// Time: O(n^2) on average -- every cell is read twice: once to hash its row, once to fill its
// column. O(n^3) when many rows share one hash: each insert and lookup then runs Arrays.equals
// down a list of rows that agree until near the end. Rows [2, 3, ..., 3, 50000 + i, 99000 - 31i]
// for i = 0 .. 199 are 200 different rows, all values in range, with one Arrays.hashCode.
// Space: O(n) -- the map holds n references to the grid's own rows, plus one reused column array.
//
// Siblings: CountRowAndColPairs2352 compares every row with every column of a transposed copy --
// O(n^3) in the worst case, about n^2 steps on random input, O(n^2) space. On rows that share one
// hash it stops at the first differing position, where this file compares whole rows.

public final class CountRowAndColPairs2352Optimized {

	public static int countPairs(int[][] grid) {
		Map<Integer, List<RowCount>> rows = rowCounts(grid); // COUNT the rows once
		int n = grid.length;
		int[] col = new int[n]; // ONE column at a time, reused
		int count = 0;
		for (int c = 0; c < n; c++) {
			fillColumn(grid, c, col);
			count += countOf(rows, col); // ADD the rows equal to this column
		}
		return count;
	}

	private static Map<Integer, List<RowCount>> rowCounts(int[][] grid) {
		Map<Integer, List<RowCount>> rows = new HashMap<>(); // MAP: hash -> the different rows with it
		for (int[] row : grid) {
			List<RowCount> bucket = rows.computeIfAbsent(Arrays.hashCode(row), key -> new ArrayList<>());
			Optional<RowCount> seen = find(bucket, row);
			if (seen.isPresent()) {
				seen.get().count++; // REPEAT: one more row with these values
			} else {
				bucket.add(new RowCount(row)); // NEW row, count 1
			}
		}
		return rows;
	}

	private static int countOf(Map<Integer, List<RowCount>> rows, int[] col) {
		List<RowCount> bucket = rows.get(Arrays.hashCode(col));
		if (bucket == null) {
			return 0; // NO row has this hash
		}
		return find(bucket, col).map(RowCount::count).orElse(0); // SAME hash, maybe not the same row
	}

	// equal hashes do not mean equal rows -- settle it on the arrays
	private static Optional<RowCount> find(List<RowCount> bucket, int[] key) {
		for (RowCount candidate : bucket) {
			if (Arrays.equals(candidate.row, key)) { // EQUAL at every position
				return Optional.of(candidate);
			}
		}
		return Optional.empty();
	}

	private static void fillColumn(int[][] grid, int c, int[] col) {
		for (int r = 0; r < col.length; r++) {
			col[r] = grid[r][c]; // READ column c top to bottom
		}
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 3, 2, 1 }, { 1, 7, 6 }, { 2, 7, 7 } };
		System.out.println(countPairs(grid1)); // 1
		int[][] grid2 = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2 } };
		System.out.println(countPairs(grid2)); // 3
	}

	private static final class RowCount {
		private final int[] row; // the grid's own row, not a copy
		private int count; // how many rows of the grid hold these values

		private RowCount(int[] row) {
			this.row = row;
			this.count = 1;
		}

		private int count() {
			return count;
		}
	}
}
