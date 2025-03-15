package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.List;

// 89. Gray Code

public final class GrayCode {
	// Time Complexity: O(2^n)

	public static List<Integer> grayCode(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 1 << n; i++) {
			int gray = i ^ (i >> 1);
			list.add(gray);
		}
		return list;
	}

	public static void main(String[] args) {
		print(grayCode(2)); // [0,1,3,2]
		print(grayCode(1)); // [0,1]
	}
}