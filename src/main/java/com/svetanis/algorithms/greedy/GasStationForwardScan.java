package com.svetanis.algorithms.greedy;

// 134. Gas Station -- the forward scan, twice
//
// The greedy claim: if the tank first goes negative on arriving at station f,
// then NO station from the current start through f can be the answer. Every
// partial sum before f was >= 0, so a later start inside that stretch only
// skips non-negative ground and arrives at f with no more fuel. So the scan
// jumps straight to f + 1 -- one pass, not n.
//
// Both methods below make that same argument. startFused is startTwoPass with
// the two passes run in one loop.
//
// The other two algorithms for this problem, in this package:
//   GasStationBackwardWindow -- a window extended BACKWARD, a different claim
//   GasStationDoubledSweep   -- a 2n sweep with a restart latch, no total at all

public final class GasStationForwardScan {
	// Time Complexity: O(n) -- both methods
	// Space Complexity: O(1) -- both methods

	// The readable one. Pass 1 answers "is it possible at all?" and pass 2
	// answers "where does it start?", and keeping them apart is what makes this
	// the version you can narrate out loud.
	public static int startTwoPass(int[] gas, int[] cost) {
		int n = gas.length;
		int totalFuel = 0;
		int totalCost = 0;
		for (int i = 0; i < n; i++) { // pass 1: the yes/no test, and only that
			totalFuel += gas[i];
			totalCost += cost[i];
		}
		if (totalFuel < totalCost) {
			return -1; // the ONLY place -1 can come from
		}
		int fuel = 0;
		int start = 0;
		for (int i = 0; i < n; i++) { // pass 2: which station, given one exists
			if (fuel + gas[i] - cost[i] < 0) { // would arriving at i+1 leave us in debt?
				start = i + 1; // then start..i is all dead
				fuel = 0; // and the new candidate starts empty
			} else {
				fuel += gas[i] - cost[i];
			}
		}
		// pass 2 alone is NOT a solution: it always ends holding some index and
		// never discovers that none of them works. Deleting pass 1 makes it
		// answer 1 on gas = {0,1}, cost = {2,0}, where no start completes
		return start;
	}

	// startTwoPass's two passes fused into one, which is why it is the shortest
	// and the hardest to read: `total` and `sum` accumulate the SAME quantity for
	// two different purposes.
	public static int startFused(int[] gas, int[] cost) {
		int n = gas.length;
		int sum = 0;
		int total = 0;
		int start = 0;
		for (int i = 0; i < n; i++) {
			total += gas[i] - cost[i]; // never reset -- the yes/no test
			sum += gas[i] - cost[i]; // reset at every failure -- this candidate's tank
			if (sum < 0) { // `< 0`, NOT `<= 0`: a tank of exactly
				start = i + 1; // zero arrived with precisely enough.
				sum = 0; // `<=` returns n, one past the end
			}
		}
		return total >= 0 ? start : -1; // `start` means nothing until total says yes
	}

	public static void main(String[] args) {
		int[] gas = { 1, 2, 3, 4, 5 };
		int[] cost = { 3, 4, 5, 1, 2 };
		System.out.println(startTwoPass(gas, cost)); // 3
		System.out.println(startFused(gas, cost)); // 3

		int[] gas1 = { 2, 3, 4 };
		int[] cost1 = { 3, 4, 3 };
		System.out.println(startTwoPass(gas1, cost1)); // -1
		System.out.println(startFused(gas1, cost1)); // -1

		// exactly enough on arrival is a SUCCESS, not a failure: the reset test
		// has to be `< 0`. A `<= 0` version answers 2, which is not a station
		int[] gas2 = { 2, 0 };
		int[] cost2 = { 0, 2 };
		System.out.println(startFused(gas2, cost2)); // 0

		// two stations work here (2 and 3), so this is not a legal LC 134 input --
		// LeetCode guarantees the answer is unique. GasStationBackwardWindow
		// answers 3 for it. Both are correct drives
		int[] gas3 = { 1, 2, 3, 4 };
		int[] cost3 = { 2, 3, 1, 1 };
		System.out.println(startFused(gas3, cost3)); // 2
	}
}
