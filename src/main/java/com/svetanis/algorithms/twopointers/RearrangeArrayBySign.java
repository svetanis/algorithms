package com.svetanis.algorithms.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2149. Rearrange Array Elements by Sign
//
// Input: an array of even length, half of its numbers positive and half negative, no zeros.
// Return: the same numbers with the signs alternating, starting with a positive; the
// positives keep their original order among themselves, and so do the negatives.
//
// The one idea: every number's slot is known before it is read. Counting from 0, the
// k-th positive goes to index 2k and the k-th negative to index 2k + 1. So keep two
// WRITE cursors, one stepping over the even slots and one over the odd slots, read the
// input once, and send each number to its sign's cursor. Reading left to right is what
// keeps each sign in its original order.
//
// Two methods, the same problem:
//   rearrange      -- two write cursors stepping by 2 into a new array, one pass
//   rearrangeArray -- collects the positives and the negatives into two lists first, then
//                     interleaves them back over the input array, which it overwrites
//
// Time: O(n) -- each number is read once and written once.
// Space: O(n) -- the output array (rearrange) or the two lists (rearrangeArray).

public final class RearrangeArrayBySign {

	public static int[] rearrange(int[] nums) {
		int n = nums.length;
		int[] a = new int[n];
		int i = 0, j = 1;               // START: i on the even slots, j on the odd slots
		for (int num : nums) {          // READ every number once, in order
			if (num > 0) {
				a[i] = num;             // WRITE a positive into the next even slot
				i += 2;
			} else {
				a[j] = num;             // WRITE a negative into the next odd slot
				j += 2;
			}
		}
		return a;
	}

	public static int[] rearrangeArray(int[] a) {
		List<Integer> positive = new ArrayList<>();
		List<Integer> negative = new ArrayList<>();
		for (int num : a) {
			if (num > 0) {
				positive.add(num);      // SPLIT: each sign keeps its order
			} else {
				negative.add(num);
			}
		}
		int n = positive.size();
		int i = 0, j = 0, k = 0;
		while (i < n && j < n) {        // STOP: the counts are equal, so both lists run out together
			a[k++] = positive.get(i++); // WRITE a positive,
			a[k++] = negative.get(j++); // WRITE a negative after it
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, -2, -5, 2, -4 };
		System.out.println(Arrays.toString(rearrange(a1)));      // [3, -2, 1, -5, 2, -4]
		System.out.println(Arrays.toString(rearrangeArray(a1))); // [3, -2, 1, -5, 2, -4]

		int[] a2 = { -1, 1 };
		System.out.println(Arrays.toString(rearrange(a2)));      // [1, -1]
		System.out.println(Arrays.toString(rearrangeArray(a2))); // [1, -1]
	}
}
