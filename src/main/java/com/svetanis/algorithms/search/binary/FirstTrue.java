package com.svetanis.algorithms.search.binary;

import static java.util.Arrays.asList;

import java.util.List;

// an array of boolean values is
// divided into two sections:
// the left section consists of 
// all false, and the right section
// consists of all true. find the 
// first true in a sorted boolean
// array of the right section, i.e.
// the index of the first true element
// if there is no true element 
// return -1

public final class FirstTrue {
	// Time Complexity: O(log n)

	public static int vanilla(List<Boolean> arr) {
		int n = arr.size();
		int left = 0;
		int right = n - 1;
		int boundary = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (arr.get(mid)) {
				boundary = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return boundary;
	}

	public static int binary(List<Boolean> arr) {
		int n = arr.size();
		int left = 0;
		int right = n - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (arr.get(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return arr.get(left) ? left : -1;
	}

	public static void main(String[] args) {
		System.out.println(vanilla(asList(false, false, true, true, true))); // 2
		System.out.println(vanilla(asList(true))); // 0
		System.out.println(vanilla(asList(false, false, false))); // -1
		System.out.println(vanilla(asList(true, true, true, true, true))); // 0
		System.out.println(vanilla(asList(false, true))); // 1
		System.out.println(vanilla(asList(false, false, false, false, false, false, false, false, true))); // 8
		System.out.println();
		System.out.println(binary(asList(false, false, true, true, true))); // 2
		System.out.println(binary(asList(true))); // 0
		System.out.println(binary(asList(false, false, false))); // -1
		System.out.println(binary(asList(true, true, true, true, true))); // 0
		System.out.println(binary(asList(false, true))); // 1
		System.out.println(binary(asList(false, false, false, false, false, false, false, false, true))); // 8
	}
}
