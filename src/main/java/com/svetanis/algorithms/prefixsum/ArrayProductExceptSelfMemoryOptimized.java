package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// 238. Product of Array Except Self
//
// Input: a list nums. Returns a list whose entry i is the product of every number in nums except
// nums[i], computed without division. On LeetCode: 2 <= nums.length <= 10^5,
// -30 <= nums[i] <= 30, and the product of any prefix or suffix fits in a 32-bit int. An empty
// list is outside that range; here it returns an empty list.
//
// The one idea: the numbers other than nums[i] are the ones to its left and the ones to its right.
// The first pass writes into list[i] the product of everything strictly left of i: the variable
// left holds that product when cell i is written, and only then takes in nums[i]. The second pass
// walks back from the end with right, the product of everything strictly right of i, and multiplies
// it into list[i]. The order inside each loop is the whole correctness: read the running product
// into the cell first, then fold nums[i] into it, because the cell must not include nums[i].
//
// Time: O(n) -- two passes of n steps
// Space: O(1) -- two int variables besides the output list, which doubles as the left products
//
// Siblings: ArrayProductExceptSelf builds the left and right products as two arrays and throws on
// an empty list. ArrayProductExceptSelfSubmit is this method on int[], the LeetCode signature.

public final class ArrayProductExceptSelfMemoryOptimized {

	private static List<Integer> pes(List<Integer> nums) {
		int n = nums.size();
		int left = 1; // LEFT: the product of nums[0 .. i - 1]; none yet
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(left); // WRITE first: everything strictly left of i
			left *= nums.get(i); // then FOLD nums[i] in, for cell i + 1
		}
		int right = 1; // RIGHT: the product of nums[i + 1 .. n - 1]; none yet
		for (int i = n - 1; i >= 0; i--) {
			int product = list.get(i) * right; // COMBINE: left part times right part
			list.set(i, product);
			right *= nums.get(i); // then FOLD nums[i] in, for cell i - 1
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(pes(asList(1, 2, 3, 4))); // [24, 12, 8, 6]
		System.out.println(pes(asList(-1, 1, 0, -3, 3))); // [0, 0, 9, 0, 0]
	}
}