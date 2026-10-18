package com.svetanis.algorithms.slidingwindow.fixed;

// 1151. Minimum Swaps to Group All 1's Together
//
// Takes a binary array a. A swap exchanges any two elements. Returns the fewest swaps that
// put all the 1s next to each other, anywhere in the array; 0 when there are no 1s.
//
// If a has k ones, they end up filling some block of k positions. Each 0 inside that block
// needs one swap with a 1 outside it, and nothing else does, so that block costs k minus the
// 1s already in it. Every block of k positions is one place of a fixed window of size k:
// slide it, keep its count of 1s, and the answer is k minus the largest count.
// minSwaps keeps the largest count; minSwapsII keeps the smallest cost directly.
//
// Time: O(n) -- one pass counts the 1s, then each element enters the window once and leaves once
// Space: O(1) -- the window's count of 1s and the best so far
//
// Sibling: MinSwapsToGroupOnesII (2134) is the same window on a circular array, where a
// block may wrap from the end back to the start.

public final class MinSwapsToGroupOnes {

	public static int minSwaps(int[] a) {
		int n = a.length;
		int k = 0; // window size
		for (int num : a) {
			k += num; // COUNT: the 1s, which fix the window size
		}
		if (k == 0 || k == n) {
			return 0; // RETURN: nothing to group
		}
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += a[i]; // BUILD: 1s in the first window, a[0..k-1]
		}
		int maxOnes = sum;
		for (int right = k; right < n; right++) {
			sum += a[right] - a[right - k]; // IN a[right], OUT a[right - k]
			maxOnes = Math.max(maxOnes, sum); // RECORD
		}
		return k - maxOnes; // RETURN: the 0s in the best window
	}

	public static int minSwapsII(int[] a) {
		int k = 0; // window size
		for (int num : a) {
			k += num; // COUNT: the 1s, which fix the window size
		}
		int sum = 0;
		int minSwaps = Integer.MAX_VALUE;
		for (int right = 0; right < a.length; right++) {
			sum += a[right]; // IN
			if (right >= k) {
				sum -= a[right - k]; // OUT
			}
			if (right >= k - 1) { // the first whole window ends at k - 1
				minSwaps = Math.min(minSwaps, k - sum); // RECORD: the 0s in this window
			}
		}
		return minSwaps;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 1, 0, 1 };
		System.out.println(minSwaps(a1)); // 1

		int[] a2 = { 0, 0, 0, 1, 0 };
		System.out.println(minSwaps(a2)); // 0

		int[] a3 = { 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1 };
		System.out.println(minSwaps(a3)); // 3
	}
}
