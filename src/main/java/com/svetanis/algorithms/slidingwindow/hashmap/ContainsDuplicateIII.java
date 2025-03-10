package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeSet;

// 220. Contains Duplicate III

public final class ContainsDuplicateIII {

	public static boolean duplicate(int[] a, int dist, int diff) {
		TreeSet<Long> set = new TreeSet<>();
		for (int i = 0; i < a.length; i++) {
			long target = (long) a[i] - (long) diff;
			long upper = (long) a[i] + (long) diff;
			Long floor = set.ceiling(target);
			if (floor != null && floor <= upper) {
				return true;
			}
			set.add((long) a[i]);
			if (set.size() > dist) {
				set.remove((long) a[i - dist]);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 1 };
		System.out.println(duplicate(a1, 3, 0)); // true

		int[] a2 = { 1, 5, 9, 1, 5, 9 };
		System.out.println(duplicate(a2, 2, 3)); // false
	}
}
