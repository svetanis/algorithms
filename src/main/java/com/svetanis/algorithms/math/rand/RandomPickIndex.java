package com.svetanis.algorithms.math.rand;

import java.util.Random;

// 398. Random Pick Index

// Reservoir sampling, keeping one: walk the array, and at the c-th index
// holding the target, keep it with chance 1/c (otherwise keep the old one).
// The first match is kept for sure; each later match replaces it with a
// smaller chance -- and it works out that every match ends up kept with
// chance 1/c for c matches in all. No list of matches is stored.

public final class RandomPickIndex {
	// Time Complexity: O(n) per pick
	// Space Complexity: O(1)

	private static final Random RAND = new Random();

	private final int[] nums;

	public RandomPickIndex(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		int index = 0;
		int count = 0; // matches seen so far
		for (int i = 0; i < nums.length; i++) {
			if (target == nums[i]) {
				count++;
				// 1..count, each equally likely: equal to count with chance 1/count
				int rand = 1 + RAND.nextInt(count);
				if (rand == count) {
					index = i;
				}
			}
		}
		return index;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 3, 3 };
		RandomPickIndex rpi = new RandomPickIndex(a);
		System.out.println(rpi.pick(3)); // 2, 3 or 4, each a third of the time
		System.out.println(rpi.pick(1)); // 0
		System.out.println(rpi.pick(3)); // 2, 3 or 4
	}
}
