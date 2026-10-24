package com.svetanis.algorithms.greedy;

// 134. Gas Station -- the forward scan, twice
//
// Input: gas[i] -- the fuel waiting at station i; cost[i] -- the fuel it takes to drive from
// station i to the next one, and the last station leads back to station 0. The car starts at one
// station with an empty tank, fills up at every station it reaches, and must drive the whole
// circle once. Returns the station to start from, or -1 if no start makes it round. On LeetCode:
// 1 <= n <= 10^5, 0 <= gas[i], cost[i] <= 10^4, and when a start exists it is the only one.
//
// The one idea: if the tank first goes negative on arriving at station f, then NO station from
// the current start through f can be the answer. Every partial sum before f was >= 0, so a later
// start inside that stretch only skips ground that added fuel or cost none, and arrives at f with
// no more fuel. So the scan jumps straight to f + 1 -- one pass, not n. What is left at the end is
// one candidate, and whether it works is a separate question: a start exists exactly when the
// total gas is at least the total cost. The candidate reaches the end of the array with fuel in
// hand; the stretches it skipped lose, together, less than that whenever the totals allow it, and
// inside them the tank is lowest at the end of each stretch -- so the drive back round to the
// candidate never runs dry.
//
// Both methods make that same argument. startTwoPass checks the totals in one loop and finds the
// candidate in a second; startFused runs the two in one loop.
//
// Time: O(n) -- both methods: startTwoPass two passes, startFused one.
// Space: O(1) -- both methods.
// Every sum stays within 10^5 * 10^4 = 10^9 in size, which fits an int.
//
// Siblings, in this package:
//   GasStationBackwardWindow -- a window extended BACKWARD, a different claim
//   GasStationDoubledSweep   -- a 2n sweep with a restart latch, no total at all

public final class GasStationForwardScan {

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
				fuel += gas[i] - cost[i]; // DRIVE: the tank on arriving at i + 1
			}
		}
		// pass 2 always ends holding some index, possible or not; pass 1 is what
		// says it works. On gas = {0,1}, cost = {2,0} pass 2 ends holding 1, and
		// no start completes the circle
		return start; // RETURN: the candidate, which pass 1 guaranteed
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
			if (sum < 0) { // `< 0`: a tank of exactly zero
				start = i + 1; // arrived with precisely enough,
				sum = 0; // so that candidate lives on
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

		// exactly enough on arrival is a success: from station 0 the tank is 0 on
		// reaching station 1, and station 0 is the answer
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
