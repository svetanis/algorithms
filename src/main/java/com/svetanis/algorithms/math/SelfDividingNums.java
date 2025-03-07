package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.List;

// 728. Self Dividing Numbers

public final class SelfDividingNums {
	// Time Complexity: O(n * k)

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
		for (int reminder = num; reminder != 0; reminder /= 10) {
			int digit = reminder % 10;
			if (digit == 0 || num % digit != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(sdn(1, 22));
		System.out.println(sdn(47, 85));
	}
}