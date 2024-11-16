package com.svetanis.algorithms.math.rand;

import java.util.Random;

// 398. Random Pick Index

public final class RandomPickIndex {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	private final static Random RAND = new Random();

	public RandomPickIndex(int[] nums) {
		this.nums = nums;
	}

	private int[] nums;

	public int pick(int target) {
		int index = 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target == nums[i]) {
				count++;
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
		System.out.println(rpi.pick(3)); // 4
		System.out.println(rpi.pick(1)); // 0
		System.out.println(rpi.pick(3)); // 2
	}
}