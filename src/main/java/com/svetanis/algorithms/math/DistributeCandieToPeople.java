package com.svetanis.algorithms.math;

import com.svetanis.java.base.utils.Print;

// 1103. Distribute Candies to People

public final class DistributeCandieToPeople {

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