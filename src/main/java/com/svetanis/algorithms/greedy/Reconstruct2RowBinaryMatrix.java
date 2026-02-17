package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

// 1253. Reconstruct a 2-Row Binary Matrix

public final class Reconstruct2RowBinaryMatrix {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		List<Integer> urow = new ArrayList<>();
		List<Integer> lrow = new ArrayList<>();
		for (int i = 0; i < colsum.length; i++) {
			int sum = colsum[i];
			int uval = 0;
			int lval = 0;
			if (sum == 2) {
				uval = 1;
				lval = 1;
				upper -= 1;
				lower -= 1;
			} else if (sum == 1) {
				if (upper > lower) {
					uval = 1;
					upper -= 1;
				} else {
					lval = 1;
					lower -= 1;
				}
			}
			if (upper < 0 || lower < 0) {
				break;
			}
			urow.add(uval);
			lrow.add(lval);
		}
		boolean valid = lower == 0 && upper == 0;
		return valid ? List.of(urow, lrow) : List.of();
	}

	public static List<List<Integer>> reconstructMatrix2(int upper, int lower, int[] colsum) {
		List<Integer> urow = new ArrayList<>();
		List<Integer> lrow = new ArrayList<>();
		for (int i = 0; i < colsum.length; i++) {
			int sum = colsum[i];
			if (sum == 0) {
				urow.add(0);
				lrow.add(0);
			} else if (sum == 2) {
				urow.add(1);
				lrow.add(1);
				upper -= 1;
				lower -= 1;
			} else {
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
			if (upper < 0 || lower < 0) {
				break;
			}
		}
		boolean valid = lower == 0 && upper == 0;
		return valid ? List.of(urow, lrow) : List.of();
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 1 };
		System.out.println(reconstructMatrix(2, 1, a)); // {{1,1,0},{0,0,1}}
		int[] a2 = { 2, 2, 1, 1 };
		System.out.println(reconstructMatrix(2, 3, a2));
		int[] a3 = { 2, 1, 2, 0, 1, 0, 1, 2, 0, 1 };
		System.out.println(reconstructMatrix(5, 5, a3));
	}
}
