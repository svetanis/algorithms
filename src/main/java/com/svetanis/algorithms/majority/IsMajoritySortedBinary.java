package com.svetanis.algorithms.majority;

// 1150. Check If a Number Is Majority Element in a Sorted Array

// Same algorithm as search/binary/frequency/IsMajoritySortedRecursive:
// find the first position of target, then look one slot n/2 further on.
// That one finds it with a recursive binary search, this one iteratively.

public final class IsMajoritySortedBinary {
	// Time Complexity: O(log n)

	// PRECONDITION: nums is sorted ascending. Nothing here checks it, and the
	// neighbours in this package do NOT share it -- MajorityElement169 and
	// MajorityElementMooreVoting take unsorted arrays. Handed an unsorted
	// one this returns a confident wrong answer rather than failing.
	public static boolean isMajorityElement(int[] nums, int target) {
		int n = nums.length;
		int start = binary(nums, target);

		// target owns a run of equal values starting at start. If the slot n/2
		// further on still holds it, that run is longer than half the array.
		return start + n/2 < n && nums[start + n/2] == target;
	}
	
	// the FIRST index holding target -- or, when target is absent, the index it
	// would be inserted at. Always in range, which is what keeps the caller safe:
	// an absent target lands on some other value and the test above answers false.
	// Contrast IsMajoritySortedScan, which looks up with indexOf and gets -1 back.
	private static int binary(int[] a, int target) {
		int n = a.length;
		int left = 0;
		int right = n - 1;
		
		while(left < right) {
			int mid = left + (right - left)/2;
			if(a[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 4, 5, 5, 5, 5, 5, 6, 6 };
		System.out.println(isMajorityElement(a1, 5)); // true

		int[] a2 = { 10, 100, 101, 101 };
		System.out.println(isMajorityElement(a2, 101)); // false
	}
}
