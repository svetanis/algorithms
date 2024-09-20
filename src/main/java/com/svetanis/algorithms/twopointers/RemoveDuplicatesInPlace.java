package com.svetanis.algorithms.twopointers;

import static java.util.Arrays.asList;

import java.util.List;

// given a sorted list of numbers,
// remove duplicates and return
// the new length. do it in-place
// without using extra memory

public final class RemoveDuplicatesInPlace {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int removeDups(List<Integer> arr) {
		int slow = 0;
		for (int fast = 0; fast < arr.size(); fast++) {
			if (arr.get(fast) != arr.get(slow)) {
				slow++;
				arr.set(slow, arr.get(fast));
			}
		}
		return slow + 1;
	}

	public static void main(String[] args) {
		System.out.println(removeDups(asList(0, 0, 1, 1, 1, 2, 2))); // 3
		System.out.println(removeDups(asList(1, 2, 3))); // 3
		System.out.println(removeDups(asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))); // 1
	}
}
