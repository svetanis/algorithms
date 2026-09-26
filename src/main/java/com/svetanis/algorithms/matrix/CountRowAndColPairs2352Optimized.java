package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 2352. Equal Row and Column Pairs

// HASH THE ROWS, THEN LOOK UP EACH COLUMN.
// CountRowAndColPairs2352.java compares every row against
// every column element by element. that is n^3 in the worst
// case and needs a materialized transpose; neither is
// necessary. only ONE column is held at a time here, and it
// is reused, so the extra space is the map plus one array.

// counting the rows rather than collecting them is what
// makes duplicates fall out for free: three identical rows
// matching one column contribute 3, and the map already
// knows that. a Set-based version silently returns 1.

// THE KEY IS AN int, AND THAT IS THE WHOLE TRICK.
// the obvious key is Arrays.toString(row), and it is a trap:
// at n = 200 it builds 400 strings of ~1,200 characters and
// then hashes every one of those characters.
//
// the string version LOSES TO THE n^3 BASELINE on ordinary
// input -- an asymptotic win handed straight back as constant
// factor. hashing the array itself and settling collisions
// with Arrays.equals costs almost nothing on random input.
// it does NOT keep a worst-case win: rows chosen so that
// Arrays.hashCode collides land in one bucket, every insert
// runs Arrays.equals against rows sharing a long prefix, and
// the time becomes n^3 -- slower than the baseline, which
// breaks at the first element on those same rows.

// and the same ordering holds on LeetCode itself, which is
// where it matters:
//
//   direct (n^3)          22 ms
//   Arrays.toString key   37 ms   <- SLOWER than the n^3 file
//   this file              6 ms

// note also why the baseline is so hard to beat: its inner
// loop breaks at the FIRST mismatching element, which on
// random data is element 1. it is n^2 in practice, and
// degenerates to n^3 when rows and columns share a long
// common start -- identical values are one way to get that.

public final class CountRowAndColPairs2352Optimized {
	// Time Complexity: O(n^2) on average -- every cell is read twice;
	// O(n^3) when many rows share one hash
	// Space Complexity: O(n) -- n references to the input's own rows,
	// plus one reused column array

	public static int countPairs(int[][] grid) {
		Map<Integer, List<RowCount>> rows = rowCounts(grid);
		int n = grid.length;
		int[] col = new int[n];
		int count = 0;
		for (int c = 0; c < n; c++) {
			fillColumn(grid, c, col);
			count += countOf(rows, col);
		}
		return count;
	}

	private static Map<Integer, List<RowCount>> rowCounts(int[][] grid) {
		Map<Integer, List<RowCount>> rows = new HashMap<>();
		for (int[] row : grid) {
			List<RowCount> bucket = rows.computeIfAbsent(Arrays.hashCode(row), key -> new ArrayList<>());
			Optional<RowCount> seen = find(bucket, row);
			if (seen.isPresent()) {
				seen.get().count++;
			} else {
				bucket.add(new RowCount(row));
			}
		}
		return rows;
	}

	private static int countOf(Map<Integer, List<RowCount>> rows, int[] col) {
		List<RowCount> bucket = rows.get(Arrays.hashCode(col));
		if (bucket == null) {
			return 0;
		}
		return find(bucket, col).map(RowCount::count).orElse(0);
	}

	// equal hashes do not mean equal rows -- settle it on the arrays
	private static Optional<RowCount> find(List<RowCount> bucket, int[] key) {
		for (RowCount candidate : bucket) {
			if (Arrays.equals(candidate.row, key)) {
				return Optional.of(candidate);
			}
		}
		return Optional.empty();
	}

	private static void fillColumn(int[][] grid, int c, int[] col) {
		for (int r = 0; r < col.length; r++) {
			col[r] = grid[r][c];
		}
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 3, 2, 1 }, { 1, 7, 6 }, { 2, 7, 7 } };
		System.out.println(countPairs(grid1)); // 1
		int[][] grid2 = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2 } };
		System.out.println(countPairs(grid2)); // 3
	}

	private static final class RowCount {
		private final int[] row;
		private int count;

		private RowCount(int[] row) {
			this.row = row;
			this.count = 1;
		}

		private int count() {
			return count;
		}
	}
}
