package com.svetanis.algorithms.greedy;

// 517. Super Washing Machines

public final class SuperWashingMachines {
	// Time Complexity: O(n)

	public static int minMoves(int[] machines) {
		int sum = 0;
		int size = machines.length;
		for (int machine : machines) {
			sum += machine;
		}
		if (sum % size != 0) {
			return -1;
		}
		int average = sum / size;
		int max = 0;
		int total = 0;
		for (int machine : machines) {
			machine -= average;
			total += machine;
			max = Math.max(max, Math.max(machine, Math.abs(total)));
		}
		return max;
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
