package com.svetanis.algorithms.math;

// 1134. Armstrong Number

public final class ArmstrongNumber {
	// Time Complexity: O(d), d the number of digits
	// Space Complexity: O(1)

	public static boolean isArmstrong(int num) {
		int sum = 0;
		int len = String.valueOf(num).length();
		int temp = num;
		while (temp > 0) {
			int digit = temp % 10;
			sum += Math.pow(digit, len);
			temp /= 10;
		}
		return sum == num;
	}

	public static void main(String[] args) {
		System.out.println(isArmstrong(153)); // true
		System.out.println(isArmstrong(371)); // true
	}
}