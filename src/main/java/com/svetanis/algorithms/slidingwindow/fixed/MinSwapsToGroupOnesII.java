package com.svetanis.algorithms.slidingwindow.fixed;

// 2134. Minimum Swaps to Group All 1's Together II
//
// Takes a circular binary array a: the last element is next to the first. A swap exchanges
// any two elements. Returns the fewest swaps that put all the 1s next to each other.
//
// If a has k ones, they end up filling some block of k positions, and each 0 inside that
// block costs one swap, so the block costs k minus the 1s already in it. In a circle a block
// may wrap from the end back to the start, so the fixed window of size k walks on past the
// end: index right % n is the element entering, (right - k + n) % n the element leaving. The
// n window positions ending at k .. n + k - 1 are every block once (the one ending at k - 1
// is the one ending at n + k - 1). minSwaps builds the first window before the loop;
// minSwapsII fills it inside the loop.
//
// Time: O(n) -- one pass counts the 1s, then n + k <= 2n window steps
// Space: O(1) -- the window's count of 1s and the best so far
//
// Sibling: MinSwapsToGroupOnes (1151) is the same window on a straight array, which stops
// at the end.

public final class MinSwapsToGroupOnesII {

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
		for (int right = k; right < n + k; right++) {
			sum += a[right % n] - a[(right - k + n) % n]; // IN a[right % n], OUT the one k behind, wrapping
			maxOnes = Math.max(maxOnes, sum); // RECORD
		}
		return k - maxOnes; // RETURN: the 0s in the best window
	}

	public static int minSwapsII(int[] a) {
		int n = a.length;
		int k = 0; // window size
		for (int num : a) {
			k += num; // COUNT: the 1s, which fix the window size
		}
		if (k == 0 || k == n) {
			return 0; // RETURN: nothing to group
		}
		int sum = 0;
		int minSwaps = Integer.MAX_VALUE;
		for (int right = 0; right < n + k; right++) {
			sum += a[right % n]; // IN
			if (right >= k) {
				sum -= a[(right - k + n) % n]; // OUT
				minSwaps = Math.min(minSwaps, k - sum); // RECORD: the 0s in this window
			}
		}
		return minSwaps;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 0, 1, 1, 0, 0 };
		System.out.println(minSwaps(a1)); // 1

		int[] a2 = { 0, 1, 1, 1, 0, 0, 1, 1, 0 };
		System.out.println(minSwaps(a2)); // 2

		int[] a3 = { 1, 1, 0, 0, 1 };
		System.out.println(minSwaps(a3)); // 0
	}
}
