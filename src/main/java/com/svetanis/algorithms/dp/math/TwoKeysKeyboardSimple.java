package com.svetanis.algorithms.dp.math;

// 650. 2 Keys Keyboard

public final class TwoKeysKeyboardSimple {

	public static int minSteps(int n) {
		if (n == 1) {
			return 0;
		}
		int count = 0;
		for (int i = 2; n > 1; i++) {
			while (n % i == 0) {
				count += i;
				n /= i;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(minSteps(3)); // 3
		System.out.println(minSteps(1)); // 0
	}
}
