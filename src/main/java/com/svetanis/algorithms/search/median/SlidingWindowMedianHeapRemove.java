package com.svetanis.algorithms.search.median;

import static com.svetanis.algorithms.search.median.MedianUnsortedMinSelect.minSelect;

import java.util.ArrayList;
import java.util.List;

// 480. Sliding Window Median

// given an array of numbers and a number k,
// find the median of all the k sized sub-arrays
// (or windows) of the array
// Returns n - k + 1 medians, left to right: the middle value for odd k,
// the average of the two middles for even k. LC 480 takes 1 <= k <= n
// and values over the whole int range. Named maxSlidingWindow, but it
// returns medians. k > n gives one value, the whole array's median from
// MedianUnsortedMinSelect (the lower middle for even n), and rearranges a.

// Slide a window over OnlineMedianTwoHeapsRemove's two heaps: add the first k values
// and read; then at each step add the value entering on the right,
// remove the one leaving on the left, and read again.

// Time: O(n * k) -- each remove is PriorityQueue.remove(Object), a scan of up to k values.
// Space: O(k) -- the two heaps hold one window.

// Siblings:
//   SlidingWindowMedianLazyDeletion -- the same slide over MedianTwoHeapsLazyDeletion's lazy
//     deletion, O(n log n); returns a double[].
//   OnlineMedianTwoHeapsRemove -- the two heaps this drives.

public final class SlidingWindowMedianHeapRemove {

	public static List<Double> maxSlidingWindow(int[] a, int k) {
		int n = a.length;
		if (k > n) {
			return List.of((double) minSelect(a));
		}
		OnlineMedianTwoHeapsRemove om = new OnlineMedianTwoHeapsRemove();
		List<Double> list = new ArrayList<>();
		for (int i = 0; i < k; i++) { // SEED: the first window
			om.add(a[i]);
		}
		list.add(om.median()); // READ
		for (int i = k; i < n; i++) {
			om.add(a[i]); // ENTER: the value on the right
			om.remove(a[i - k]); // LEAVE: the value that drops off the left
			list.add(om.median()); // READ
		}
		return list;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, -1, 3, 5 };
		System.out.println(maxSlidingWindow(a1, 2));
		// 1.5, 0.5, 1.0, 4.0

		int[] a2 = { 1, 2, -1, 3, 5 };
		System.out.println(maxSlidingWindow(a2, 3));
		// 1.0, 2.0, 3.0
	}
}
