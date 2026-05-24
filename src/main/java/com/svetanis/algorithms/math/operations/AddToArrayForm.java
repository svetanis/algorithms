package com.svetanis.algorithms.math.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 989. Add to Array-Form of Integer

public final class AddToArrayForm {
	// Time Complexity: O(max(n, log k))
	// Space Complexity: O(max(n, log k))

	public static List<Integer> addToArrayForm(int[] a, int k) {
		List<Integer> list = new ArrayList<>();
		int index = a.length - 1;
		int carry = k;
		while (index >= 0 || carry > 0) {
			if (index >= 0) {
				carry += a[index--];
			}
			list.add(carry % 10);
			carry /= 10;
		}
		Collections.reverse(list);
		return list;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 0, 0 };
		System.out.println(addToArrayForm(a1, 34)); // 1 2 3 4
		int[] a2 = { 2, 7, 4 };
		System.out.println(addToArrayForm(a2, 181)); // 4 5 5
		int[] a3 = { 2, 1, 5 };
		System.out.println(addToArrayForm(a3, 806)); // 1 0 2 1
	}
}