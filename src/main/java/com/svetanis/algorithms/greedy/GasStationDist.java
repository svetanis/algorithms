package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.List;

public final class GasStationDist {

	public static int start(List<Integer> gas, List<Integer> dist) {
		int fuel = 0;
		int start = -1;
		int location = 0;
		int n = gas.size();
		while (location != 2 * n) {
			if (start == -1) {
				start = location;
			}
			fuel += gas.get(location % n);
			fuel -= dist.get(location % n);
			if (fuel < 0) {
				fuel = 0;
				start = -1;
			}
			location++;
			if (start != -1 && location - start == n) {
				return start % n;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		List<Integer> gas = asList(1, 2, 3, 4, 5);
		List<Integer> dist = asList(3, 4, 5, 1, 2);
		System.out.println(start(gas, dist));
	}
}
