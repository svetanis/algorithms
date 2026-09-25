package com.svetanis.algorithms.majority;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

// A majority element in an array a[] of size n 
// is an element that appears more than n/2 times 

// Unlike MajorityElement169, the majority is NOT promised here,
// so this returns absent() when the array has none.

public final class MajorityElementMooreVoting {

	public static Optional<Integer> majorityElement(int[] a) {
		// Time Complexity: O(n) -- two passes
		// Space Complexity: O(1)

		// 1. find candidate for majority
		int candidate = a[findCandidate(a)];

		// 2. check if candidate is majority
		// the voting pass proves only "if a majority exists, it is this value".
		// {1, 2, 3} leaves a survivor too, so the count below is not optional.
		if (isMajority(a, candidate)) {
			return of(candidate);
		}
		return absent();
	}

	private static boolean isMajority(int[] a, int candidate) {
		int count = 0;
		for (int curr : a) {
			if (curr == candidate) {
				++count;
			}
		}
		// strictly greater: on {1, 1, 2, 2} two copies are not a majority of four
		return count > a.length / 2;
	}

	private static int findCandidate(int[] a) {
		// pairing off two elements with different values cannot cancel a majority
		// away, because it removes at most one copy of it and at least one of
		// something else -- so the gap in its favour never shrinks.
		int index = 0;
		int count = 1;
		for (int i = 1; i < a.length; ++i) {
			if (a[i] == a[index]) {
				++count;
			} else {
				--count;
			}
			if (count == 0) {
				index = i;
				count = 1;
			}
		}
		// SOME index holding the survivor -- not its first occurrence,
		// and not an index into anything sorted. See IsMajoritySortedScan.
		return index;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 3, 3, 3, 1, 2 };
		System.out.println(majorityElement(a)); // Optional.of(3)
		int[] a1 = { 1, 2, 3, 1, 1, 2, 1 };
		System.out.println(majorityElement(a1)); // Optional.of(1)
		int[] a2 = { 1, 2, 1, 2, 1 };
		System.out.println(majorityElement(a2)); // Optional.of(1)
		int[] a3 = { 1, 2, 3 };
		System.out.println(majorityElement(a3)); // Optional.absent()
		int[] a4 = { 1, 1, 2, 2 };
		System.out.println(majorityElement(a4)); // Optional.absent()
	}
}
