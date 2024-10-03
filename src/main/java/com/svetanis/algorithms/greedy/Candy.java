package com.svetanis.algorithms.greedy;

import static java.lang.Math.max;

import java.util.Arrays;

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

public final class Candy {

	public static int candy(int[] ratings) {
		int n = ratings.length;
		
		int[] left = new int[n];
		left[0] = 1;
		// from left to right
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i - 1]) {
				left[i] = left[i - 1] + 1;
			} else {
				left[i] = 1;
			}
		}

		int[] right = new int[n];
		right[n - 1] = 1;
		// from right to left
		for (int i = n - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				right[i] = right[i + 1] + 1;
			} else {
				right[i] = 1;
			}
		}

		int[] candies = new int[n];
		for (int i = 0; i < n; i++) {
			candies[i] = max(left[i], right[i]);
		}
		return Arrays.stream(candies).sum();
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 2 };
		int[] a2 = { 1, 2, 2 };

		System.out.println(candy(a1)); // 5
		System.out.println(candy(a2)); // 4
	}
}
