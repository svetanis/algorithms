package com.svetanis.algorithms.greedy;

import static java.util.Arrays.asList;

import java.util.List;

// 134. Gas Station -- the List<Integer> signature
//
// This is GasStation134.gasStation with .get(i) where that one has [i]. Same
// backward-extending window, same greedy argument, same answer. The parameter
// type is the only difference between the two files.

public final class GasStationCost {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int start(List<Integer> gas, List<Integer> cost) {
		int n = gas.size();
		int sum = 0;
		int start = n - 1;
		int end = n - 1;
		int stations = 0;
		while (stations < n) {
			sum += gas.get(end) - cost.get(end);
			stations++;
			end = (end + 1) % n;

			while (sum < 0 && stations < n) {
				start--;
				sum += gas.get(start) - cost.get(start);
				stations++;
			}
		}
		return sum >= 0 ? start : -1;
	}

	public static void main(String[] args) {
		List<Integer> gas = asList(1, 2, 3, 4);
		List<Integer> cost = asList(2, 3, 1, 1);
		// two stations work here (2 and 3), so this is not a legal LC 134 input --
		// GasStation134.sgs answers 2 for it and this method answers 3. Both are valid.
		System.out.println(start(gas, cost)); // 3

		List<Integer> gas1 = asList(1, 2, 3, 4, 5);
		List<Integer> cost1 = asList(3, 4, 5, 1, 2);
		System.out.println(start(gas1, cost1)); // 3

		List<Integer> gas2 = asList(2, 3, 4);
		List<Integer> cost2 = asList(3, 4, 3);
		System.out.println(start(gas2, cost2)); // -1
	}
}
