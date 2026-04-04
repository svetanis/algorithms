package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1338. Reduce Array Size to The Half

public final class ReduceArrayToHalf {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int minSetSize(int[] a) {
		int max = 0;
		for (int num : a) {
			max = Math.max(max, num);
		}
		int[] counts = new int[max + 1];
		for (int num : a) {
			counts[num]++;
		}
		Arrays.sort(counts);
		int size = 0;
		int count = 0;
		for (int i = max;; i--) {
			if (counts[i] > 0) {
				count += counts[i];
				size += 1;
				if (count * 2 >= a.length) {
					return size;
				}
			}
		}
	}

	public static int minSetSize2(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : a) {
			map.merge(num, 1, Integer::sum);
		}
		List<Integer> frequencies = new ArrayList<>(map.values());
		Collections.sort(frequencies, Comparator.reverseOrder());
		int size = 0;
		int count = 0;
		while (2 * count < a.length || size > frequencies.size()) {
			count += frequencies.get(size);
			size += 1;
		}
		return size;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 3, 3, 3, 5, 5, 5, 2, 2, 7 };
		int[] a2 = { 7, 7, 7, 7, 7, 7 };

		System.out.println(minSetSize(a1)); // 2
		System.out.println(minSetSize(a2)); // 1
	}
}
