package com.svetanis.algorithms.dp.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 970. Powerful Integers

public final class PowerfulIntegers {

	public static List<Integer> powerfulInts(int x, int y, int bound) {
		Set<Integer> set = new HashSet<>();
		for (int px = 1; px <= bound; px *= x) {
			for (int py = 1; px + py <= bound; py *= y) {
				set.add(px + py);
				if (y == 1) {
					break;
				}
			}
			if (x == 1) {
				break;
			}
		}
		return new ArrayList<>(set);
	}

	public static void main(String[] args) {
		System.out.println(powerfulInts(2, 3, 10)); // 2,3,4,5,7,9,10
		System.out.println(powerfulInts(3, 5, 15)); // 2,4,6,8,10,14
	}
}