package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1291. Sequential Digits

public final class SequentialDigits {

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
		System.out.println(sequentialDigits(100, 300));
		System.out.println(sequentialDigits(1000, 13000));
	}
}