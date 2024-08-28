package com.svetanis.algorithms.search.quickselect.median;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.quickselect.median.MedianUnsortedMinSelect.minSelect;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given an array of numbers and a number k,
// find the median of all the k sized sub-arrays
// (or windows) of the array

public final class SlidingWindowMedian {

	public static ImmutableList<Double> maxSlidingWindow(int[] a, int k) {
		// Time complexity: O(n * k)
		// Space complexity: O(k)

		int n = a.length;
		if (k > n) {
			return newList(new Double(minSelect(a)));
		}
		OnlineMedian om = new OnlineMedian();
		List<Double> list = newArrayList();
		for (int i = 0; i < k; i++) {
			om.add(a[i]);
		}
		list.add(om.median());
		for (int i = k; i < n; i++) {
			int left = a[i - k];
			om.remove(left);
			om.add(a[i]);
			list.add(om.median());
		}
		// max element of last window
		return newList(list);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, -1, 3, 5 };
		print(maxSlidingWindow(a1, 2));
		// 1.5, 0.5, 1.0, 4.0

		int[] a2 = { 1, 2, -1, 3, 5 };
		print(maxSlidingWindow(a2, 3));
		// 1.0, 2.0, 3.0
	}
}
