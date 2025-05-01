package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 881. Boats to Save People

public final class BoatsToSavePeople {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static int countBoats(int[] people, int limit) {
		Arrays.sort(people);
		int boats = 0;
		int n = people.length;
		for (int left = 0, right = n - 1; left <= right; right--) {
			if (people[left] + people[right] <= limit) {
				left++;
			}
			boats++;
		}
		return boats;
	}

	public static void main(String[] args) {
		int[] p1 = { 1, 2 };
		System.out.println(countBoats(p1, 3)); // 1
		int[] p2 = { 3, 2, 2, 1 };
		System.out.println(countBoats(p2, 3)); // 3
		int[] p3 = { 3, 5, 3, 4 };
		System.out.println(countBoats(p3, 5)); // 4
		int[] p4 = { 5, 1, 4, 2 };
		System.out.println(countBoats(p4, 6)); // 2
	}
}
