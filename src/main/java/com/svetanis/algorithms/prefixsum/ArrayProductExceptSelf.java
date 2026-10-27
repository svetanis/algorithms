package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// 238. Product of Array Except Self
//
// Input: a list nums. Returns a list whose entry i is the product of every number in nums except
// nums[i], computed without division. On LeetCode: 2 <= nums.length <= 10^5,
// -30 <= nums[i] <= 30, and the product of any prefix or suffix fits in a 32-bit int. An empty
// list is outside that range and throws ArrayIndexOutOfBoundsException in leftPrefix.
//
// The one idea: the numbers other than nums[i] are the ones to its left and the ones to its right.
// left[i] holds the product of nums[0 .. i - 1], everything strictly left of i; right[i] holds the
// product of nums[i + 1 .. n - 1], everything strictly right of i. Nothing is left of index 0 and
// nothing is right of index n - 1, so those two cells hold 1, the product of no numbers. Each left
// cell is the one before it times one more number, and each right cell the one after it times one
// more, so both fill in one pass each; the answer at i is left[i] * right[i]. No division, so a
// zero in nums needs no special case.
//
// Time: O(n) -- three passes of n steps: left, right, and the products
// Space: O(n) -- the two arrays left and right, besides the output list
//
// Siblings: ArrayProductExceptSelfMemoryOptimized keeps left in the output list and right in one
// variable, so it needs no extra arrays, and returns an empty list for an empty input.
// ArrayProductExceptSelfSubmit is the same two passes on int[], the LeetCode signature.

public final class ArrayProductExceptSelf {

	private static List<Integer> pes(List<Integer> nums) {
		int[] left = leftPrefix(nums);
		int[] right = rightPrefix(nums);
		List<Integer> product = new ArrayList<>();
		for (int i = 0; i < nums.size(); i++) {
			product.add(right[i] * left[i]); // COMBINE: left of i times right of i
		}
		return product;
	}

	private static final int[] leftPrefix(List<Integer> list) {
		int n = list.size();
		int[] a = new int[n];
		a[0] = 1; // BASE: nothing is left of index 0
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] * list.get(i - 1); // EXTEND: one more from the left, not list[i]
		}
		return a;
	}

	private static final int[] rightPrefix(List<Integer> list) {
		int n = list.size();
		int[] a = new int[n];
		a[n - 1] = 1; // BASE: nothing is right of the last index
		for (int i = n - 2; i >= 0; i--) {
			a[i] = a[i + 1] * list.get(i + 1); // EXTEND: one more from the right, not list[i]
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(pes(asList(1, 2, 3, 4))); // [24, 12, 8, 6]
		System.out.println(pes(asList(-1, 1, 0, -3, 3))); // [0, 0, 9, 0, 0]
	}
}