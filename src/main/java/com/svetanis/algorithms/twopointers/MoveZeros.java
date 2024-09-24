package com.svetanis.algorithms.twopointers;

import static java.util.Arrays.asList;

import java.util.List;

// given an array of integers, 
// move all the 0s to the back
// of the array while maintaining
// the relative order of the non-zero
// elements. do this in-place.

public final class MoveZeros {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static void moveZeros(List<Integer> nums) {
		int slow = 0;
		for (int fast = 0; fast < nums.size(); fast++) {
			int curr = nums.get(fast);
			if (curr != 0) {
				int prev = nums.get(slow);
				nums.set(slow, curr);
				nums.set(fast, prev);
				slow++;
			}
		}
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(1, 0, 2, 0, 0, 7);
		List<Integer> list2 = asList(3, 1, 0, 1, 3, 8, 9);
		List<Integer> list3 = asList(0, 0, 9, 4, 0, 0, 3, 8, 0);
		moveZeros(list1);
		moveZeros(list2);
		moveZeros(list3);
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
	}
}