package com.svetanis.algorithms.greedy;

// 738. Monotone Increasing Digits

public final class MonotoneIncreasingDigits {

	public static int mid(int n) {
		char[] digits = String.valueOf(n).toCharArray();
		int i = 1;
		int len = digits.length;
		while (i < len && digits[i - 1] <= digits[i]) {
			i++;
		}
		if (i < len) {
			while (i > 0 && digits[i - 1] > digits[i]) {
				digits[i - 1]--;
				i--;
			}
			while (++i < len) {
				digits[i] = '9';
			}
		}
		return Integer.parseInt(new String(digits));
	}

	public static void main(String[] args) {
		System.out.println(mid(10)); // 9
		System.out.println(mid(1234)); // 1234
		System.out.println(mid(332)); // 299
	}
}
