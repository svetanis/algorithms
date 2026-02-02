package com.svetanis.algorithms.sorting.mergesort.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.svetanis.java.base.utils.Print;

// 350. Intersection of Two Arrays II

public final class IntersectionTwoArraysII {

	public static int[] intersectSimple(int[] a1, int[] a2) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a2.length; j++) {
				if (a1[i] == a2[j]) {
					list.add(a1[i]);
					a2[j] = -1;
					break;
				}
			}
		}
		int[] a = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			a[i] = list.get(i);
		}
		return a;
	}

	public static int[] intersectSort(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		int i = 0, j = 0, k = 0;
		while (i < a1.length && j < a2.length) {
			if (a1[i] < a2[j]) {
				i += 1;
			} else if (a1[i] > a2[j]) {
				j += 1;
			} else {
				a1[k++] = a1[i++];
				j++;
			}
		}
		return Arrays.copyOfRange(a1, 0, k);
	}

	public static int[] intersect(int[] a1, int[] a2) {
		if (a1.length > a2.length) {
			return intersect(a2, a1);
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : a1) {
			map.merge(num, +1, Integer::sum);
		}
		List<Integer> list = new ArrayList<>();
		for (int num : a2) {
			if (map.containsKey(num)) {
				list.add(num);
				map.merge(num, -1, Integer::sum);
				if (map.get(num) == 0) {
					map.remove(num);
				}
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 1 };
		int[] a2 = { 2, 2 };
		Print.print(intersectSimple(a1, a2)); // 2 2

		int[] a3 = { 4, 9, 5 };
		int[] a4 = { 9, 4, 9, 8, 4 };
		Print.print(intersectSimple(a3, a4)); // 4 9
	}
}