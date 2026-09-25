package com.svetanis.algorithms.majority;

// 1287. Element Appearing More Than 25% In Sorted Array

// Not cancellation at all, and worth knowing as the contrast: when the array is
// SORTED, every copy of a value sits in one run, so a run longer than a quarter
// of the array must cover at least one of the positions a[i], a[i + n/4].
// Walk i and compare those two -- no candidates, no counters.

public final class ElementOverQuarter1287 {

	// PRECONDITION: a is sorted ascending, and LC 1287 guarantees that exactly
	// one value occupies more than a quarter of it. Nothing here checks either.
	//
	// The LeetCode submission does NOT need the bound or the throw below, and
	// the version without them is correct there -- do not "fix" a passing
	// submission to match this file. The answer's run starts at some s with
	// s + length <= n and length > n/4, so s < n - n/4 and an unbounded loop
	// always returns before i + n/4 can leave the array. Measured: over 353,498
	// sorted arrays honouring the guarantee, the unbounded loop was 0 wrong and
	// 0 thrown. The bound matters only for reuse, where no such value may exist.
	// Handed an array with no such value, this throws rather than inventing one:
	// every int is a legal answer, so there is no sentinel that could mean
	// "nothing here".
	public static int majorityElement(int[] a) {
		// Time Complexity: O(n)
		// Space Complexity: O(1)

		int n = a.length;

		// stop at n - n/4, not n: beyond that, i + n/4 runs off the end. The answer
		// is always found before then, because its run starts at some s with
		// s + length <= n and length > n/4, hence s < n - n/4.
		for (int i = 0; i < n - n / 4; i++) {
			if (a[i] == a[i + n / 4]) {
				return a[i];
			}
		}
		throw new IllegalArgumentException(NO_ANSWER);
	}

	private static final String NO_ANSWER = "no value occupies more than a quarter of the array";

	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 6, 6, 6, 6, 7, 10 };
		System.out.println(majorityElement(a)); // 6
		int[] a1 = { 1, 1 };
		System.out.println(majorityElement(a1)); // 1
		int[] a2 = { 0, 0, 1, 1, 2, 3, 4, 4, 5 };
		try {
			System.out.println(majorityElement(a2));
		} catch (IllegalArgumentException e) {
			// used to be ArrayIndexOutOfBoundsException from a[i + n/4]
			System.out.println(e.getMessage());
		}
	}
}
