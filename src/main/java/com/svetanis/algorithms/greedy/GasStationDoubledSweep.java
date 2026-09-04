package com.svetanis.algorithms.greedy;

import static com.google.common.primitives.Ints.toArray;
import static java.util.Arrays.asList;

import java.util.List;

// 134. Gas Station -- a doubled sweep with a restart latch
//
// A third algorithm, and the only one that never computes a total. It sweeps
// forward over 2n positions holding a latch: `start == -1` means "no candidate
// right now", and any candidate that survives n consecutive stations has driven
// the circuit and is the answer.
//
// Walking 2n is what replaces the feasibility test. The answer may sit near the
// end of the array, and its circuit wraps past position n-1; the second lap is
// there so that wrap can actually be walked. If no candidate survives n in a
// row across the doubled sweep, no start exists.
//
// Often met as the petrol-pump problem: a circle of pumps, each holding some
// petrol, with some DISTANCE to the next one -- that distance is this `cost`
// renamed, so it is a restatement of LC 134, not a different problem.
//
// The other two algorithms for this problem, in this package:
//   GasStationForwardScan    -- restart-on-failure, in two flavours
//   GasStationBackwardWindow -- a window extended BACKWARD

public final class GasStationDoubledSweep {
	// Time Complexity: O(n) -- the sweep is 2n
	// Space Complexity: O(1)

	public static int start(int[] gas, int[] cost) {
		int fuel = 0;
		int start = -1; // -1 means "between candidates"
		int location = 0;
		int n = gas.length;
		while (location != 2 * n) { // two laps, so every start gets a full circuit
			if (start == -1) {
				start = location; // adopt this position as the candidate
			}
			fuel += gas[location % n];
			fuel -= cost[location % n];
			if (fuel < 0) {
				fuel = 0;
				start = -1; // candidate dead; the next station re-seeds
			}
			location++;
			if (start != -1 && location - start == n) { // survived n in a row == drove it
				return start % n; // so there is nothing left to verify
			}
		}
		return -1; // nothing survived n in a row
	}

	public static int start(List<Integer> gas, List<Integer> cost) {
		return start(toArray(gas), toArray(cost));
	}

	public static void main(String[] args) {
		System.out.println(start(asList(1, 2, 3, 4, 5), asList(3, 4, 5, 1, 2))); // 3

		// the infeasible case is the one that costs the full 2n steps: the other
		// two algorithms stop at n because they have a total to test
		System.out.println(start(asList(2, 3, 4), asList(3, 4, 3))); // -1
	}
}
