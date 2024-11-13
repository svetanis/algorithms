package com.svetanis.algorithms.search.binary;

import static com.svetanis.java.base.utils.Print.print;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 888. Fair Candy Swap
// x is amount of candies Alice gives to Bob
// y is amount of candies Bob gives to Alice

// (sumA - x) + y = (sumB - y) + x
// sumA - sumB = 2 * (x - y)
// diff = sumA - sumB
// x - y = diff/2

public final class FairCandySwap {
	// Time Complexity: O(n + m)
	// Space Complexity: O(m)

	public static int[] fcs(int[] a, int[] b) {
		int sumA = IntStream.of(a).sum();
		int sumB = IntStream.of(b).sum();
		int diff = (sumA - sumB) / 2;
		Set<Integer> set = Arrays.stream(b).boxed().collect(Collectors.toSet());
		for (int candy : a) {
			int target = candy - diff;
			if (set.contains(target)) {
				int[] result = { candy, target };
				return result;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1 };
		int[] b1 = { 2, 2 };
		print(fcs(a1, b1)); // 1,2

		int[] a2 = { 1, 2 };
		int[] b2 = { 2, 3 };
		print(fcs(a2, b2)); // 1,2

		int[] a3 = { 2 };
		int[] b3 = { 1, 3 };
		print(fcs(a3, b3)); // 2,3
	}
}
