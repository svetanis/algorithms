package com.svetanis.algorithms.twopointers;

import static com.google.common.collect.Lists.transform;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

// 977. Squares of a Sorted Array

// given a sorted array, create a new array
// containing squares of all the numbers
// of the input array in the sorted order

public final class SquaringSortedArray {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] sortSquares(int[] a) {
		int n = a.length;
		int left = 0;
		int right = n - 1;
		int index = n - 1;
		int[] squares = new int[n];
		while (left <= right) {
			int leftSquare = a[left] * a[left];
			int rightSquare = a[right] * a[right];
			if (leftSquare > rightSquare) {
				squares[index] = leftSquare;
				left++;
			} else {
				squares[index] = rightSquare;
				right--;
			}
			index--;
		}
		return squares;
	}

	public static ImmutableList<Integer> sortSquares(List<Integer> list) {
		List<Integer> transformed = transform(list, i -> i * i);
		return sort(transformed);
	}

	public static void main(String[] args) {
		int[] a = { -2, -1, 0, 2, 3 };
		print(sortSquares(a));
		int[] a1 = { -3, -1, 0, 1, 2 };
		print(sortSquares(a1));

		int[] a2 = { -4, -1, 0, 3, 10};
		print(sortSquares(a2));
		int[] a3 = { -7, -3, 2, 3, 11};
		print(sortSquares(a3));
	}
}