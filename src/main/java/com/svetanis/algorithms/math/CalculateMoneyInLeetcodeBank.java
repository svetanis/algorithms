package com.svetanis.algorithms.math;

// 1716. Calculate Money in Leetcode Bank

public final class CalculateMoneyInLeetcodeBank {

	public static int totalMoney(int n) {
		int total = 0;
		int weeks = n / 7;
		int days = n % 7;
		total += weeks * 28 + (weeks * (weeks - 1) / 2) * 7;
		for (int i = 0; i < days; i++) {
			total += weeks + 1 + i;
		}
		return total;
	}

	public static int totalMoney2(int n) {
		int monday = 1;
		int prev = monday + 1;
		int count = 0;
		int total = 0;
		while (count < n) {
			if (count % 7 == 0) {
				total += monday;
				prev = monday + 1;
				monday += 1;
			} else {
				total += prev;
				prev += 1;
			}
			count++;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(totalMoney(4)); // 10
		System.out.println(totalMoney(10)); // 37
		System.out.println(totalMoney(20)); // 96
	}
}