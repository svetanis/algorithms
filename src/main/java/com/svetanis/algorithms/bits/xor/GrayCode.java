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

	public static List<String> grayCodeBinary(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 1 << n; i++) {
			int gray = i ^ (i >> 1);
			list.add(binaryCode(n, gray));
		}
		return list;
	}

	private static String binaryCode(int n, int gray) {
		String code = "";
		for (int j = n - 1; j >= 0; j--) {
			boolean isSet = (gray & (1 << j)) != 0;
			code += isSet ? '1' : '0';
		}
		return code;
	}

	public static void main(String[] args) {
		print(grayCode(2)); // [0,1,3,2]
		print(grayCode(1)); // [0,1]

		print(grayCodeBinary(2)); // [00 01 11 10]
		print(grayCodeBinary(1)); // [0 1]
	}
}