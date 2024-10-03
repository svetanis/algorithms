package com.svetanis.algorithms.greedy;

import static java.lang.Math.max;

// there are n children standing in a line
// each child is assigned a rating value
// given in the integer array ratings
// you are giving candies to these children
// subjected to the following requirements:

// 1. each child must have at least one candy
// 2. children with a higher rating get more
// candies than their neighbors

// return min number of candies you need to
// have to distribute the cadies to the children

public final class CandySpaceEfficient {

	public static int candy(int[] ratings) {
		int[] candies = new int[ratings.length];
		candies[0] = 1;
		// from left to right
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			} else {
				candies[i] = 1;
			}
		}
		int count = candies[ratings.length - 1];
		// from right to left
		for (int i = ratings.length - 2; i >= 0; i--) {
			int current = 1;
			if (ratings[i] > ratings[i + 1]) {
				current = candies[i + 1] + 1;
			}
			count += max(current, candies[i]);
			candies[i] = current;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 2 };
		int[] a2 = { 1, 2, 2 };

		System.out.println(candy(a1)); // 5
		System.out.println(candy(a2)); // 4
	}
}
