package com.svetanis.algorithms.greedy;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

// 1029. Two City Scheduling
//
// Input: costs[i] = { the cost of flying person i to city A, the cost of flying them to city B },
// for 2n people. Returns the least total cost that sends exactly n people to each city. On
// LeetCode: 2 * n == costs.length, 2 <= costs.length <= 100, costs.length is even,
// 1 <= costs[i][0], costs[i][1] <= 1000. Sorts the caller's costs array in place. Relies on an
// even count, as LeetCode promises; with an odd count the last person is left out.
//
// The one idea: first imagine everyone flies to B. Moving person i to A then changes the total by
// costs[i][0] - costs[i][1], which is negative when A is cheaper for them. Exactly n people must
// move, so the total is (all the B costs) + (the changes of the n people moved), and the first
// part is fixed whoever moves. The second part is smallest when the n people moved are the n with
// the smallest change. So sort by costs[i][0] - costs[i][1], send the first half to A and the
// second half to B; one sort settles everyone at once. The subtraction in the comparator cannot
// overflow: each cost is at most 1000.
//
// Time: O(n log n) -- the sort; the walk after it is n steps.
// Space: O(n) -- the sort of the rows (Java's object sort keeps a merge buffer); the walk uses
// one running total.

public final class TwoCityScheduling {

	public static int tcs(int[][] costs) {
		Arrays.sort(costs, comparingInt(c -> c[0] - c[1])); // SORT by how much cheaper A is than B
		int n = costs.length / 2;
		int total = 0;
		for (int i = 0; i < n; i++) {
			total += costs[i][0]; // A: the first half, where A saves the most
			total += costs[i + n][1]; // B: the rest
		}
		return total;
	}

	public static void main(String[] args) {
		int[][] costs1 = { { 10, 20 }, { 30, 200 }, { 400, 50 }, { 30, 20 } };
		System.out.println(tcs(costs1)); // 110

		int[][] costs2 = { { 259, 770 }, { 448, 54 }, { 926, 667 }, { 184, 139 }, { 840, 118 }, { 577, 469 } };
		System.out.println(tcs(costs2)); // 1859

		int[][] costs3 = { { 515, 563 }, { 451, 713 }, { 537, 709 }, { 343, 819 }, { 855, 779 }, { 457, 60 }, { 650, 359 },
				{ 631, 42 } };
		System.out.println(tcs(costs3)); // 3086
	}
}
