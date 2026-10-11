package com.svetanis.algorithms.twopointers.dups;

// 287. Find the Duplicate Number
//
// Input: n + 1 integers, each in [1, n], 1 <= n <= 10^5. All of them appear once except
// one value, which appears two or more times.
// Return: that repeated value -- without modifying the array, in O(1) extra space.
//
// The one idea: read the array as a linked list: node i points to node a[i].
// Every node has exactly one outgoing edge, so following the edges
// from index 0 must eventually repeat a node, which means the walk
// enters a cycle. Two indices holding the same value point to the
// same node, so that value is the node the cycle is entered at.
// Index 0 is never inside the cycle, because every value is >= 1
// and so no edge leads back to 0. That is what makes 0 a safe start.
//
// Siblings -- the same problem:
//   DuplicateInPlaceBinary  -- binary search on the value, counting the numbers <= mid: O(n log n)
//   DuplicateInPlaceMarking -- O(n), but flips signs in the array, which 287 forbids
//   DuplicateInPermutationSum, DuplicateInPermutationXor -- need every value 1..n present; 287 does not promise it
//
// Time: O(n) -- phase 1 meets before slow finishes one lap of the cycle; phase 2 walks
// the lead-in once. Both are at most n + 1 nodes long.
// Space: O(1), and the array is not modified.

public final class DuplicateInPlaceFloyd {

	public static int duplicate(int[] a) {
		int meeting = meetingPoint(a);
		return cycleEntrance(a, meeting);
	}

	// Phase 1: a fast walker moving two edges per step and a slow one
	// moving one edge per step must meet somewhere inside the cycle.
	private static int meetingPoint(int[] a) {
		int slow = a[0]; // STEP before the test: both would start equal on index 0
		int fast = a[a[0]];
		while (slow != fast) { // MEET: the only way this loop ends, a cycle always exists
			slow = a[slow]; // STEP: one edge
			fast = a[a[fast]]; // STEP: two edges
		}
		return slow;
	}

	// Phase 2: restart one walker at index 0 and move both one edge per
	// step. The distance from 0 to the entrance equals the distance from
	// the meeting point to the entrance, so they meet exactly there.
	private static int cycleEntrance(int[] a, int meeting) {
		int slow = 0; // RESTART on index 0, the head of the walk, not a[0]
		int fast = meeting;
		while (slow != fast) { // WALK both one edge per step
			slow = a[slow];
			fast = a[fast];
		}
		return slow; // RETURN the entrance's index, which is the repeated value
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
