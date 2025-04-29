package com.svetanis.algorithms.greedy;

import java.util.HashMap;
import java.util.Map;

// 860. Lemonade Change

public final class LemonadeChange {
	// Time Complexity: O(n)

	public static boolean lemonadeChange(int[] bills) {
		int fives = 0;
		int tens = 0;
		for (int bill : bills) {
			switch (bill) {
			case 5:
				fives++;
				break;
			case 10:
				tens++;
				fives--;
				break;
			case 20:
				if (tens > 0) {
					tens--;
					fives--;
				} else {
					fives -= 3;
				}
				break;
			}
			if (fives < 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean lemonadeChange2(int[] bills) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int bill : bills) {
			int change = bill - 5;
			int tens = map.getOrDefault(10, 0);
			if (change == 15 && tens > 0) {
				change -= 10;
				map.merge(10, -1, Integer::sum);
			}
			while (change > 0) {
				int fives = map.getOrDefault(5, 0);
				if (fives == 0) {
					return false;
				}
				change -= 5;
				map.merge(5, -1, Integer::sum);
			}
			map.merge(bill, 1, Integer::sum);
		}
		return true;
	}

	public static void main(String[] args) {
		int[] bills1 = { 5, 5, 5, 10, 20 };
		System.out.println(lemonadeChange(bills1)); // true
		int[] bills2 = { 5, 5, 10, 10, 20 };
		System.out.println(lemonadeChange(bills2)); // false
		int[] bills3 = { 5, 5, 5, 10, 5, 5, 10, 20, 20, 20 };
		System.out.println(lemonadeChange(bills3)); // false
	}
}
