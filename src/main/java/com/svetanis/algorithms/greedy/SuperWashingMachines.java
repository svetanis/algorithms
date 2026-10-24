package com.svetanis.algorithms.greedy;

// 517. Super Washing Machines
//
// Input: machines[i] -- the dresses in washing machine i; the machines stand in a line. In one
// move any number of machines each pass one dress to a neighbour, all at the same time. Returns
// the fewest moves that leave every machine with the same number of dresses, or -1 when that
// cannot happen. On LeetCode: n == machines.length, 1 <= n <= 10^4, 0 <= machines[i] <= 10^5.
//
// The one idea: if the total does not divide evenly by n, the answer is -1. Otherwise every
// machine must end at average = total / n, and its extra is machines[i] - average: positive when
// it has dresses to give, negative when it is short. Two things force moves. First, a line
// between two neighbouring machines: the machines left of it together hold total = the sum of
// their extras more than they should, so |total| dresses must cross that line, and at most one
// crosses it per move, since only the two machines beside it can pass across it and dresses
// passed both ways cancel. Second, a machine with extra e > 0 can hand out only one dress per
// move, so it needs e moves; a short machine is no limit of its own, because it can take dresses
// from both sides at once. The answer is the largest of all these limits -- each one is a number
// of moves no schedule can beat, and that many moves is always enough (the proof that a schedule
// exists is longer and not given here). One walk computes every limit.
//
// Time: O(n) -- two passes, one for the total, one for the limits.
// Space: O(1) -- the total, the average and two running numbers.

public final class SuperWashingMachines {

	public static int minMoves(int[] machines) {
		int sum = 0;
		int size = machines.length;
		for (int machine : machines) {
			sum += machine; // TOTAL: at most 10^4 * 10^5, fits an int
		}
		if (sum % size != 0) { // FAIL: the dresses cannot split evenly
			return -1;
		}
		int average = sum / size; // TARGET per machine
		int max = 0; // MOVES: the largest limit so far
		int total = 0; // FLOW: the extra held by the machines walked so far, which must cross to the right
		for (int machine : machines) {
			machine -= average; // EXTRA of this machine; negative when it is short
			total += machine; // FLOW across the line after this machine
			max = Math.max(max, Math.max(machine, Math.abs(total))); // LIMITS: a giver hands out one per move; one dress crosses a line per move
		}
		return max; // RETURN
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 5 };
		System.out.println(minMoves(a1)); // 3

		int[] a2 = { 0, 3, 0 };
		System.out.println(minMoves(a2)); // 2

		int[] a3 = { 0, 2, 0 };
		System.out.println(minMoves(a3)); // -1
	}
}
