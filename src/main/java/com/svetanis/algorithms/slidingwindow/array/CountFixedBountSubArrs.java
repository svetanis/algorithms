package com.svetanis.algorithms.slidingwindow.array;

// 2444. Count Subarrays With Fixed Bounds
//
// Input: an array a and two numbers minK and maxK.
// Return: how many subarrays have minimum exactly minK and maximum exactly maxK, as a
// long -- there can be about n^2/2 of them.
//
// Count by end. For a fixed end right, a start works when three things hold: no value
// between the start and right is outside [minK, maxK], the subarray holds a minK, and it
// holds a maxK. So the start must come after the last out-of-range position (left), and
// at or before the last minK and the last maxK seen -- that is, at or before the smaller
// of the two. The working starts are left + 1 .. min(minIndex, maxIndex), which is
// min - left of them, or none when that is negative. A bound not yet seen has index -1,
// which gives none. "Contains a minK" is not a threshold that can be lowered by one, so
// the atMost(k) - atMost(k - 1) subtraction does not apply; three positions do the job.
//
// Time: O(n) -- one pass, constant work per element
// Space: O(1) -- three positions and a count
//
// Siblings: no other file solves 2444. BinarySubArrWithSum (930) is the other "exactly"
// count in this folder, done with the subtraction instead.

public final class CountFixedBountSubArrs {

	public static long countSubArrays(int[] a, int minK, int maxK) {
		long count = 0;
		int left = -1;
		int minIndex = -1;
		int maxIndex = -1;
		for (int right = 0; right < a.length; right++) {
			if (a[right] > maxK || a[right] < minK) {
				left = right; // STOP: out of range, so every start must come after it
			}
			if (a[right] == minK) {
				minIndex = right; // LAST minK seen
			}
			if (a[right] == maxK) {
				maxIndex = right; // LAST maxK seen
			}
			int min = Math.min(minIndex, maxIndex);
			count += Math.max(0, min - left); // COUNT: starts left + 1 .. min, ending at right
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 5, 2, 7, 5 };
		System.out.println(countSubArrays(a1, 1, 5)); // 2

		int[] a2 = { 1, 1, 1, 1 };
		System.out.println(countSubArrays(a2, 1, 1)); // 10
	}
}
