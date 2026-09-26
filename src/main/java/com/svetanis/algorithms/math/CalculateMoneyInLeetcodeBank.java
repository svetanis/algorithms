package com.svetanis.algorithms.math;

// 1716. Calculate Money in Leetcode Bank

public final class CalculateMoneyInLeetcodeBank {

	// Week w (counting from 0) pays w + 1, w + 2, ..., w + 7: 28 + 7w in all.
	// So the full weeks pay 28 * weeks + 7 * (0 + 1 + ... + (weeks - 1)),
	// and the days of the last, unfinished week are added one by one.
	// Time Complexity: O(1) -- at most 6 leftover days
	// Space Complexity: O(1)
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

	// every day one by one: each Monday pays one more than the last Monday,
	// and each other day one more than the day before
	// Time Complexity: O(n)
	// Space Complexity: O(1)
	public static int totalMoneyDayByDay(int n) {
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
		System.out.println(totalMoneyDayByDay(4)); // 10
		System.out.println(totalMoneyDayByDay(10)); // 37
		System.out.println(totalMoneyDayByDay(20)); // 96
	}
}