package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1291. Sequential Digits

// There are only 36 such numbers (12 .. 123456789): start at each digit
// and keep adding the next one. Keep those in range, then sort.

public final class SequentialDigits {
	// Time Complexity: O(1) -- at most 36 numbers
	// Space Complexity: O(1)

	public static List<Integer> sequentialDigits(int low, int high) {
		List<Integer> list = new ArrayList<>();
		for (int digit = 1; digit <= 9; digit++) {
			int num = digit;
			for (int next = digit + 1; next <= 9; next++) {
				num = num * 10 + next;
				if (num >= low && num <= high) {
					list.add(num);
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public static void main(String[] args) {
		System.out.println(sequentialDigits(100, 300)); // [123, 234]
		// [1234, 2345, 3456, 4567, 5678, 6789, 12345]
		System.out.println(sequentialDigits(1000, 13000));
	}
}