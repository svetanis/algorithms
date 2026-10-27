package com.svetanis.algorithms.prefixsum.range;

import static com.svetanis.algorithms.prefixsum.PrefixSumArray.prefixSum;

// Range sum, rebuilt per call -- not a LeetCode submission; LeetCode 303's question as a static
// method
//
// Input: an array a and two indices, 0 <= left <= right < a.length. Returns a[left] + ... +
// a[right]. Within 303's bounds (n <= 10^4, |a[i]| <= 10^5) every total fits in an int.
//
// The one idea: PrefixSumArray.prefixSum gives prefix[i] = a[0] + ... + a[i], the total up to and
// including i. The range is the total up to right minus the total of everything before left:
// prefix[right] - prefix[left - 1]. Nothing comes before index 0, so left == 0 is its own case,
// prefix[right]. The prefix array is built inside the call and thrown away after it, so every
// query pays the whole build -- the same cost as adding up the range directly.
//
// Time: O(n) per query -- the build, every call.
// Space: O(n) -- the prefix array, rebuilt every call.
//
// Siblings: RangeSumQuery is the same rebuild-per-call over a List<Integer>, with an n + 1 array
// that starts at 0, so it has no left == 0 case. RangeSumQuerySubmit and RangeSumQuerySubmit2 are
// LeetCode 303 itself: the array is built once, in the constructor, and each query is O(1).

public final class RangeSumQueries {

	public static int rangeSum(int[] a, int left, int right) {
		int[] prefix = prefixSum(a); // BUILD: on every call
		if (left == 0) {
			return prefix[right]; // FROM 0: nothing before index 0 to take away
		}
		return prefix[right] - prefix[left - 1]; // RANGE: up to right, minus everything before left
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		System.out.println(rangeSum(a, 1, 3)); // 9
		System.out.println(rangeSum(a, 2, 4)); // 12
	}
}
