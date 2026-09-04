package com.svetanis.algorithms.greedy;

import static com.google.common.primitives.Ints.toArray;
import static java.util.Arrays.asList;

import java.util.List;

// 134. Gas Station -- a window extended BACKWARD
//
// A different greedy claim from GasStationForwardScan's. That one says "a
// prefix that ran the tank dry is dead, jump past it". This one never abandons
// anything. `start` and `end` are NOT two pointers scanning: they are the two
// edges of one stretch of stations that only ever grows -- forward to commit to
// another leg, backward to take in the station behind `start`, whose fuel is
// collected before any driving already committed to.
//
// Growing backward does NOT always help immediately: the station behind can
// have a negative net and deepen the debt (nets +2,-1,-1 go -1 then -2 before
// clearing). That is fine -- adding a negative can never leave debt, so the
// inner loop can only STOP on a station with surplus.
//
// The invariant that makes it correct: after each round the whole stretch is
// drivable from `start` on an empty tank -- every arrival covered, not merely a
// non-negative total.
//
// The other two algorithms for this problem, in this package:
//   GasStationForwardScan  -- restart-on-failure, in two flavours
//   GasStationDoubledSweep -- a 2n sweep with a restart latch

public final class GasStationBackwardWindow {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int start(int[] gas, int[] cost) {
		int n = gas.length;
		int sum = 0;
		int start = n - 1; // the window is one station wide, at the end
		int end = n - 1;
		int stations = 0; // how many stations the window covers
		while (stations < n) {
			sum += gas[end] - cost[end]; // grow FORWARD: one more leg to drive
			stations++;
			end = (end + 1) % n;
			while (sum < 0 && stations < n) {
				start--; // grow BACKWARD: adopt the station behind, whose
				sum += gas[start] - cost[start]; // fuel arrives before the shortfall
				stations++;
			}
		}
		// every station was added exactly once, so `sum` IS the grand total here --
		// this line is GasStationForwardScan's separate first pass, for free
		return sum >= 0 ? start : -1;
	}

	// `start` cannot fall below 0: the outer loop always adds one station forward
	// before the inner loop can add one backward, so at most n-1 go backward.

	public static int start(List<Integer> gas, List<Integer> cost) {
		return start(toArray(gas), toArray(cost));
	}

	public static void main(String[] args) {
		System.out.println(start(asList(1, 2, 3, 4, 5), asList(3, 4, 5, 1, 2))); // 3
		System.out.println(start(asList(2, 3, 4), asList(3, 4, 3))); // -1

		// two stations work here (2 and 3), so this is not a legal LC 134 input --
		// LeetCode guarantees the answer is unique. GasStationForwardScan answers
		// 2 for it and this method answers 3. Both are correct drives
		System.out.println(start(asList(1, 2, 3, 4), asList(2, 3, 1, 1))); // 3
	}
}
