package com.svetanis.algorithms.math;

import com.svetanis.java.base.utils.Print;

// 1103. Distribute Candies to People

// Hand out 1, 2, 3, ... candies round the circle until they run out; the
// last person gets whatever is left.

public final class DistributeCandiesToPeople {
	// Time Complexity: O(sqrt(candies) + people) -- after k turns
	// 1 + 2 + ... + k = k(k + 1) / 2 candies are gone
	// Space Complexity: O(people) for the answer

	public static int[] distributeCandies(int candies, int people) {
		int[] a = new int[people];
		int index = 0;
		while (candies > 0) {
			int candiesToGive = Math.min(index + 1, candies);
			a[index % people] += candiesToGive;
			candies -= candiesToGive;
			index++;
		}
		return a;
	}

	public static void main(String[] args) {
		Print.print(distributeCandies(7, 4)); // 1 2 3 1
		Print.print(distributeCandies(10, 3)); // 5 2 3
	}
}