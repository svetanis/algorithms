package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.List;

// 728. Self Dividing Numbers

public final class SelfDividingNums {
	// Time Complexity: O(n * d), n = right - left + 1, d the digits in a number
	// Space Complexity: O(1) besides the answer

	public static List<Integer> sdn(int left, int right) {
		List<Integer> list = new ArrayList<>();
		for (int num = left; num <= right; num++) {
			if (isSelfDividing(num)) {
				list.add(num);
			}
		}
		return list;
	}

	private static boolean isSelfDividing(int num) {
		for (int rest = num; rest != 0; rest /= 10) {
			int digit = rest % 10;
			if (digit == 0 || num % digit != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(sdn(1, 22)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
		System.out.println(sdn(47, 85)); // [48, 55, 66, 77]
	}
}