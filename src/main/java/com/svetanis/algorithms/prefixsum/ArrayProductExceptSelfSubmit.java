package com.svetanis.algorithms.prefixsum;

// 238. Product of Array Except Self
//
// Input: an int array nums. Returns an array whose entry i is the product of every number in nums
// except nums[i], computed without division. On LeetCode: 2 <= nums.length <= 10^5,
// -30 <= nums[i] <= 30, and the product of any prefix or suffix fits in a 32-bit int. An empty
// array is outside that range and throws ArrayIndexOutOfBoundsException at output[0].
//
// The one idea: the numbers other than nums[i] are the ones to its left and the ones to its right.
// The first pass fills output[i] with the product of everything strictly left of i; output[0] is
// 1, the product of no numbers. The second pass walks back from the end with right, the product of
// everything strictly right of i, multiplies it into output[i], and only then takes in nums[i] --
// cell i must not include nums[i].
//
// Time: O(n) -- two passes of n steps
// Space: O(1) -- one int variable besides the output array, which doubles as the left products
//
// Siblings: ArrayProductExceptSelfMemoryOptimized is the same two passes on a List.
// ArrayProductExceptSelf builds the left and right products as two separate arrays.

public final class ArrayProductExceptSelfSubmit {

	public static int[] pes(int[] nums) {
		int n = nums.length;
		int[] output = new int[n];
		output[0] = 1; // BASE: nothing is left of index 0
		for (int i = 1; i < n; i++) {
			output[i] = output[i - 1] * nums[i - 1]; // LEFT: the product of nums[0 .. i - 1]
		}
		int right = 1; // RIGHT: the product of nums[i + 1 .. n - 1]; none yet
		for (int i = n - 1; i >= 0; i--) {
			output[i] *= right; // COMBINE first: left part times right part
			right *= nums[i]; // then FOLD nums[i] in, for cell i - 1
		}
		return output;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		print(pes(a1)); // 24 12 8 6
		int[] a2 = { -1, 1, 0, -3, 3 };
		print(pes(a2)); // 0 0 9 0 0
	}
}