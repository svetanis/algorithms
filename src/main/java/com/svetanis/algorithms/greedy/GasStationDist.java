package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.List;

// 134. Gas Station -- the circular-tour framing (petrol pumps: gas + distance)
//
// The same problem stated the other common way, and a genuinely different
// algorithm -- not a copy of either GasStation134 method. It sweeps forward over
// 2n positions with a restart latch: `start == -1` means "no candidate right
// now", and any candidate that survives n consecutive stations has completed the
// circuit and is the answer.
//
// Walking 2n is what replaces GasStation134's `total >= 0` feasibility test. If
// no candidate survives n stations across the doubled sweep, no start exists.

public final class GasStationDist {
	// Time Complexity: O(n) -- the sweep is 2n
	// Space Complexity: O(1)

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
