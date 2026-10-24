package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.List;

// 134. Gas Station -- a doubled sweep with a restart latch
//
// Input: gas[i] -- the fuel waiting at station i; cost[i] -- the fuel it takes to drive from
// station i to the next one, and the last station leads back to station 0. The car starts at one
// station with an empty tank, fills up at every station it reaches, and must drive the whole
// circle once. Returns the station to start from, or -1 if no start makes it round. On LeetCode:
// 1 <= n <= 10^5, 0 <= gas[i], cost[i] <= 10^4, and when a start exists it is the only one.
//
// The one idea: a third algorithm, and the only one that never computes a total. It sweeps
// forward over 2n positions holding a latch: `start == -1` means "no candidate right now", and
// any candidate that survives n consecutive stations has driven the circuit and is the answer.
// A candidate dies the moment its tank goes negative, and then no station it passed can be the
// answer either: each of them would have arrived at the same spot with no more fuel. So the next
// position is the next candidate, and no candidate ever has to be tried twice.
//
// Walking 2n is what replaces the feasibility test. The answer may sit near the end of the array,
// and its circuit wraps past position n - 1; the second lap is there so that wrap can actually be
// walked. If no candidate survives n in a row across the doubled sweep, no start exists.
//
// Often met as the petrol-pump problem: a circle of pumps, each holding some petrol, with some
// DISTANCE to the next one -- that distance is this `cost` renamed, so it is a restatement of
// LC 134, not a different problem.
//
// Time: O(n) -- the sweep is at most 2n steps.
// Space: O(1) -- a few counters.
//
// Siblings, in this package:
//   GasStationForwardScan    -- restart-on-failure, in two flavours
//   GasStationBackwardWindow -- a window extended BACKWARD

public final class GasStationDoubledSweep {

	public static int start(int[] gas, int[] cost) {
		int fuel = 0;
		int start = -1; // -1 means "between candidates"
		int location = 0;
		int n = gas.length;
		while (location != 2 * n) { // two laps, so every start gets a full circuit
			if (start == -1) {
				start = location; // adopt this position as the candidate
			}
			fuel += gas[location % n]; // FILL at this station
			fuel -= cost[location % n]; // DRIVE to the next one
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

	private static int[] toArray(List<Integer> list) {
		return list.stream().mapToInt(Integer::intValue).toArray(); // UNBOX: one int per element
	}

	public static void main(String[] args) {
		System.out.println(start(asList(1, 2, 3, 4, 5), asList(3, 4, 5, 1, 2))); // 3

		// the infeasible case is the one that costs the full 2n steps: the other
		// two algorithms stop at n because they have a total to test
		System.out.println(start(asList(2, 3, 4), asList(3, 4, 3))); // -1
	}
}
