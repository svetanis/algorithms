package com.svetanis.algorithms.greedy;

// 1217. Minimum Cost to Move Chips to The Same Position
//
// Input: a[i] -- the position of chip i on a number line. Moving a chip 2 steps left or right costs
// 0; moving it 1 step costs 1. Returns the least total cost to bring every chip to one position.
// On LeetCode: 1 <= a.length <= 100, 1 <= a[i] <= 10^9.
//
// The one idea: a 2-step move is free, so a chip can reach any position with the same evenness as
// its own -- odd to odd, even to even -- at no cost. Changing evenness takes exactly one 1-step
// move, cost 1, and never more, since the rest of the trip can be done in free 2-steps. So the
// positions themselves do not matter, only how many chips sit on odd positions and how many on
// even ones. Gather everything on an odd position and every even chip pays 1; gather on an even
// position and every odd chip pays 1. The answer is the smaller of the two counts.
//
// Time: O(n) -- one pass to count.
// Space: O(1) -- two counters.

public final class MinCostToMoveChips {

	public static int minCost(int[] a) {
		int odd = 0;
		int even = 0;
		for (int num : a) {
			if (num % 2 == 0) { // COUNT chips by evenness
				even++;
			} else {
				odd++;
			}
		}
		return Math.min(odd, even); // RETURN: move the smaller group across, 1 each
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(minCost(a1)); // 1
		int[] a2 = { 2, 2, 2, 3, 3 };
		System.out.println(minCost(a2)); // 2
		int[] a3 = { 1, 1000000000 };
		System.out.println(minCost(a3)); // 1
	}
}
