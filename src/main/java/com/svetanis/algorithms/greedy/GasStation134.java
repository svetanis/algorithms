package com.svetanis.algorithms.greedy;

// 134. Gas Station
// com.svetanis.algorithms.greedy.GasStationCost

public final class GasStation134 {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

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

	public static int sgs(int[] gas, int[] cost) {
		int n = gas.length;
		int sum = 0;
		int total = 0;
		int start = 0;
		for (int i = 0; i < n; i++) {
			total += gas[i] - cost[i];
			sum += gas[i] - cost[i];
			if (sum < 0) {
				start = i + 1;
				sum = 0;
			}
		}
		return total >= 0 ? start : -1;
	}

	public static int gasStation(int[] gas, int[] cost) {
		int n = gas.length;
		int sum = 0;
		int start = n - 1;
		int end = n - 1;
		int stations = 0;
		while (stations < n) {
			sum += gas[end] - cost[end];
			stations++;
			end = (end + 1) % n;
			while (sum < 0 && stations < n) {
				start--;
				sum += gas[start] - cost[start];
				stations++;
			}
		}
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
