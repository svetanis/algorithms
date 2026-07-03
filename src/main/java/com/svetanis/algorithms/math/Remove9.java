package com.svetanis.algorithms.math;

// 660. Remove 9

public final class Remove9 {

	public static int newInteger(int n) {
		int base = 1;
		int result = 0;
		while (n > 0) {
			result += (n % 9) * base;
			base *= 10;
			n /= 9;
		}
		return result;
	}

	public static int newInteger2(int n) {
		String s = "";
		while (n > 0) {
			s = (n % 9) + s;
			n /= 9;
		}
		return Integer.parseInt(s);
	}

	// time limit exceeded
	public static int newInteger3(int n) {
		int num = 0;
		int count = 0;
		while (count < n) {
			num++;
			if (!Integer.toString(num).contains("9")) {
				count++;
			}
		}
		return num;
	}

	public static void main(String[] args) {
		System.out.println(newInteger(9)); // 10
	}
}