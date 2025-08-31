package com.svetanis.algorithms.permutations;

import java.util.ArrayList;
import java.util.List;

// CSES: Permutations

public final class BeautifulPermutaions {
	// Time Complexity: O(n)
	
	public static List<Integer> beautifulPermutation(int n) {
		List<Integer> list = new ArrayList<>();
		if (n == 2 || n == 3) {
			return list;
		}
		// add even nums
		for (int num = 2; num <= n; num += 2) {
			list.add(num);
		}
		// add odd nums
		for (int num = 1; num <= n; num += 2) {
			list.add(num);
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(beautifulPermutation(5)); // 2 4 1 3 5
		System.out.println(beautifulPermutation(3)); //
	}
}
