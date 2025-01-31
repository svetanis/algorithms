package com.svetanis.algorithms.search.binary.frequency;

import static java.util.Arrays.sort;

public final class LastOccurrenceBinaryIterative {

	public static int lastOccurrence2(int[] a, int target) {
		sort(a);
		int left = 0;
		int right = a.length - 1;
		int index = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (target < a[mid]) {
				right = mid - 1;
			} else if (target > a[mid]) {
				left = mid + 1;
			} else { // a[mid] == k) {
				index = mid;
				left = mid + 1; // search right to find the last index
			}
		}
		return index;
	}

	public static int lastOccurrence(int[] a, int target) {
		sort(a);
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2 + 1;
			if (target < a[mid]) {
				right = mid - 1;
			} else if (target > a[mid]) {
				left = mid + 1;
			} else {
				left = mid;
			}
		}
		return right < a.length && a[right] == target ? right : -1;
	}

	public static void main(String[] args) {

		int[] a = { 1, 30, 40, 50, 60, 60, 70, 23, 20 };
		System.out.println(lastOccurrence(a, 60)); // 7

		int[] a1 = { 2, 2, 3, 5, 6 };
		System.out.println(lastOccurrence(a1, 2)); // 1

		int[] a2 = { 1, 3, 5, 5, 5, 5, 67, 123, 125 };
		System.out.println(lastOccurrence(a2, 5)); // 5

		int[] a3 = { 1, 3, 5, 5, 5, 5, 7, 123, 125 };
		System.out.println(lastOccurrence(a3, 7)); // 6

		int[] a4 = { 5, 7, 7, 8, 8, 10 };
		System.out.println(lastOccurrence(a4, 6)); // -1
	}
}