package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.List;

// 134. Gas Station -- a window extended BACKWARD
//
// Input: gas[i] -- the fuel waiting at station i; cost[i] -- the fuel it takes to drive from
// station i to the next one, and the last station leads back to station 0. The car starts at one
// station with an empty tank, fills up at every station it reaches, and must drive the whole
// circle once. Returns the station to start from, or -1 if no start makes it round. On LeetCode:
// 1 <= n <= 10^5, 0 <= gas[i], cost[i] <= 10^4, and when a start exists it is the only one.
//
// The one idea: a different greedy claim from GasStationForwardScan's. That one says "a prefix
// that ran the tank dry is dead, jump past it". This one never abandons anything. `start` and
// `end` are NOT two pointers scanning: they are the two edges of one stretch of stations that
// only ever grows -- forward to commit to another leg, backward to take in the station behind
// `start`, whose fuel is collected before any driving already committed to.
//
// Growing backward does not always help at once: the station behind can have a negative net and
// deepen the debt (nets +2, -1, -1 go -1, then -2, before clearing). That is fine -- a station
// with a negative net can never clear the debt, so the inner loop can only STOP on a station with
// surplus.
//
// The invariant that makes it correct: after each round the whole stretch is drivable from
// `start` on an empty tank -- every arrival covered, not merely a non-negative total. The inner
// loop stops at the first station that clears the debt, so every shorter run of stations it had
// added before was still in debt; the stations taken in after each of those runs therefore carry
// a surplus, which covers the arrivals inside the stretch.
//
// `start` cannot fall below 0: the outer loop always adds one station forward before the inner
// loop can add one backward, so at most n - 1 stations go backward, from n - 1 down to 0.
//
// Time: O(n) -- every station is added to the stretch exactly once, by one loop or the other.
// Space: O(1) -- a few counters.
//
// Siblings, in this package:
//   GasStationForwardScan  -- restart-on-failure, in two flavours
//   GasStationDoubledSweep -- a 2n sweep with a restart latch

public final class GasStationBackwardWindow {

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

	public static int start(List<Integer> gas, List<Integer> cost) {
		return start(toArray(gas), toArray(cost));
	}

	private static int[] toArray(List<Integer> list) {
		return list.stream().mapToInt(Integer::intValue).toArray(); // UNBOX: one int per element
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
