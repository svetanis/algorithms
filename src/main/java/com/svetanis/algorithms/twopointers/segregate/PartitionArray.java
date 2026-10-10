package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// 2161. Partition Array According to Given Pivot
//
// Input: an array of integers and a pivot that is one of its values.
// Return: a new array holding every number less than the pivot, then every copy of the
// pivot, then every number greater than the pivot. Inside the "less" group and inside
// the "greater" group the numbers keep their original order.
//
// The one idea: one output cursor and three passes over the input, one pass per group.
// Each pass writes its group's numbers in the order it meets them, so the order inside
// every group is the input's order.
//
// Sibling: twopointers.segregate.DutchNationalFlag -- the same three groups in one
//   in-place pass; its long-distance swaps do not keep the order inside a group, so it
//   does not answer this problem.
//
// Time: O(n) -- three passes over the input.
// Space: O(n) -- the output array.

public final class PartitionArray {

	public static int[] partition(int[] a, int pivot) {
		int[] partitioned = new int[a.length];
		int index = 0;                      // START: the next free slot in the output
		for (int num : a) {
			if (num < pivot) {
				partitioned[index++] = num; // WRITE the smaller numbers, in input order
			}
		}
		for (int num : a) {
			if (num == pivot) {
				partitioned[index++] = num; // WRITE the copies of the pivot
			}
		}
		for (int num : a) {
			if (num > pivot) {
				partitioned[index++] = num; // WRITE the larger numbers, in input order
			}
		}
		return partitioned;
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 12, 5, 10, 14, 3, 10 };
		System.out.println(Arrays.toString(partition(a1, 10))); // [9, 5, 3, 10, 10, 12, 14]

		int[] a2 = { -3, 4, 3, 2 };
		System.out.println(Arrays.toString(partition(a2, 2))); // [-3, 2, 4, 3]
	}
}
