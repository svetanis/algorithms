package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

// 1253. Reconstruct a 2-Row Binary Matrix
//
// Input: upper and lower -- the sums the top and the bottom row must have; colsum[i] -- the sum
// column i must have, 0, 1 or 2. Returns a matrix of 0s and 1s with two rows and colsum.length
// columns that has exactly those row and column sums, as [top row, bottom row], or an empty list
// when no such matrix exists. Any valid matrix is accepted. On LeetCode:
// 1 <= colsum.length <= 10^5, 0 <= upper, lower <= colsum.length, 0 <= colsum[i] <= 2.
//
// The one idea: one column is two cells, one in each row. A column with sum 0 is 0 over 0 and a
// column with sum 2 is 1 over 1 -- no choice. Only a column with sum 1 has a choice: its single 1
// goes up or down. upper and lower count the 1s each row still needs. Give the 1 to the row that
// still needs more. Why that is safe: every sum-2 column still ahead takes a 1 from both rows, so
// the row that runs out first is the one with the smaller need, and giving the 1 to the larger
// keeps the smaller one as large as it can be. A need below 0 means more 1s were asked of a row
// than its sum allows: stop, no matrix. At the end both needs must be exactly 0.
//
// The two methods make the same choices: reconstructMatrix sets the column's two values and
// appends them once at the end; reconstructMatrix2 appends inside each branch.
//
// Time: O(n) -- one pass over the columns.
// Space: O(n) -- the two rows.

public final class Reconstruct2RowBinaryMatrix {

	public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		List<Integer> urow = new ArrayList<>();
		List<Integer> lrow = new ArrayList<>();
		for (int i = 0; i < colsum.length; i++) { // WALK the columns
			int sum = colsum[i];
			int uval = 0; // a sum-0 column stays 0 over 0
			int lval = 0;
			if (sum == 2) { // BOTH: 1 over 1, no choice
				uval = 1;
				lval = 1;
				upper -= 1;
				lower -= 1;
			} else if (sum == 1) { // CHOOSE: the 1 goes to the row that still needs more
				if (upper > lower) {
					uval = 1;
					upper -= 1;
				} else {
					lval = 1;
					lower -= 1;
				}
			}
			if (upper < 0 || lower < 0) { // FAIL: a row was asked for more 1s than its sum
				break;
			}
			urow.add(uval); // APPEND the column
			lrow.add(lval);
		}
		boolean valid = lower == 0 && upper == 0; // CHECK: both rows got exactly their sums
		return valid ? List.of(urow, lrow) : List.of(); // RETURN
	}

	public static List<List<Integer>> reconstructMatrix2(int upper, int lower, int[] colsum) {
		List<Integer> urow = new ArrayList<>();
		List<Integer> lrow = new ArrayList<>();
		for (int i = 0; i < colsum.length; i++) { // WALK the columns
			int sum = colsum[i];
			if (sum == 0) { // NONE: 0 over 0
				urow.add(0);
				lrow.add(0);
			} else if (sum == 2) { // BOTH: 1 over 1
				urow.add(1);
				lrow.add(1);
				upper -= 1;
				lower -= 1;
			} else { // CHOOSE: the 1 goes to the row that still needs more
				if (upper > lower) {
					urow.add(1);
					lrow.add(0);
					upper -= 1;
				} else {
					urow.add(0);
					lrow.add(1);
					lower -= 1;
				}
			}
			if (upper < 0 || lower < 0) { // FAIL: a row was asked for more 1s than its sum
				break;
			}
		}
		boolean valid = lower == 0 && upper == 0; // CHECK: both rows got exactly their sums
		return valid ? List.of(urow, lrow) : List.of(); // RETURN
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 1 };
		System.out.println(reconstructMatrix(2, 1, a)); // [[1, 0, 1], [0, 1, 0]]
		int[] a2 = { 2, 2, 1, 1 };
		System.out.println(reconstructMatrix(2, 3, a2)); // []
		int[] a3 = { 2, 1, 2, 0, 1, 0, 1, 2, 0, 1 };
		System.out.println(reconstructMatrix(5, 5, a3)); // [[1, 0, 1, 0, 1, 0, 0, 1, 0, 1], [1, 1, 1, 0, 0, 0, 1, 1, 0, 0]]
	}
}
