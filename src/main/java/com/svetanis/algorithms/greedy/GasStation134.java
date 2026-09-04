package com.svetanis.algorithms.greedy;

// 134. Gas Station
//
// THREE algorithms for one problem, not three copies. They can agree because
// LC 134 guarantees the answer is unique when one exists -- on inputs admitting
// several valid starts they return different ones, and that is not a
// disagreement about correctness.
//
//   canComplete -- two passes: first prove a solution exists, then find it
//   sgs         -- the same two passes fused into one loop
//   gasStation  -- a window extended BACKWARD, a different greedy argument
//
// See also, same problem, elsewhere in this package:
//   GasStationCost.java -- gasStation() with List<Integer> parameters
//   GasStationDist.java -- a fourth algorithm, under the petrol-pump framing

public final class GasStation134 {
	// Time Complexity: O(n) -- all three methods
	// Space Complexity: O(1) -- all three methods

	// The readable one. Pass 1 answers "is it possible at all?" and pass 2
	// answers "where does it start?", and keeping them apart is what makes this
	// the version you can narrate out loud.
	public static int canComplete(int[] gas, int[] cost) {
		int n = gas.length;
		int totalFuel = 0;
		int totalCost = 0;
		for (int i = 0; i < n; i++) {
			totalFuel += gas[i];
			totalCost += cost[i];
		}
		if (totalFuel < totalCost) {
			return -1;
		}
		int fuel = 0;
		int start = 0;
		for (int i = 0; i < n; i++) {
			if (fuel + gas[i] - cost[i] < 0) {
				// can't reach next station
				// try starting from next station
				start = i + 1;
				fuel = 0;
			} else {
				// can reach next station:
				// update remaining fuel
				fuel += gas[i] - cost[i];
			}
		}
		return start;
	}

	// canComplete's two passes fused into one, which is why it is the shortest
	// and the hardest to read: `total` and `sum` accumulate the SAME quantity for
	// two different purposes. `total` never resets and answers "possible?";
	// `sum` resets at every failure and tracks the current candidate start.
	// The reset-on-negative scan is the part that transfers to other problems.
	public static int sgs(int[] gas, int[] cost) {
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

	// A different shape entirely. Rather than scanning forward and restarting, it
	// holds a window that grows forward from n-1 and, whenever the tank goes
	// negative, extends BACKWARD by pulling in an earlier station. The greedy
	// claim is therefore "a station added behind me can only add fuel", not
	// "a prefix that failed is dead".
	// `start` cannot fall below 0: the outer loop always adds one station forward
	// before the inner loop can add one backward, so at most n-1 go backward.
	// GasStationCost.java is this method with List parameters.
	public static int gasStation(int[] gas, int[] cost) {
		int n = gas.length;
		int sum = 0;
		int start = n - 1;
		int end = n - 1;
		int stations = 0;
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
		// this line is canComplete's separate first pass, arrived at for free
		return sum >= 0 ? start : -1;
	}

	public static void main(String[] args) {
		int[] gas = { 1, 2, 3, 4 };
		int[] cost = { 2, 3, 1, 1 };
		System.out.println(sgs(gas, cost)); // 2

		int[] gas1 = { 1, 2, 3, 4, 5 };
		int[] cost1 = { 3, 4, 5, 1, 2 };
		System.out.println(sgs(gas1, cost1)); // 3

		int[] gas2 = { 2, 3, 4 };
		int[] cost2 = { 3, 4, 3 };
		System.out.println(sgs(gas2, cost2)); // -1
	}
}
