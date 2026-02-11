package com.svetanis.algorithms.twopointers;

import java.util.ArrayList;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 2149. Rearrange Array Elements by Sign

public final class RearrangeArrayBySign {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] rearrange(int[] nums) {
		int n = nums.length;
		int[] a = new int[n];
		int i = 0, j = 1;
		for (int num : nums) {
			if (num > 0) {
				a[i] = num;
				i += 2;
			} else {
				a[j] = num;
				j += 2;
			}
		}
		return a;
	}

	public static int[] rearrangeArray(int[] a) {
		List<Integer> positive = new ArrayList<>();
		List<Integer> negative = new ArrayList<>();
		for (int num : a) {
			if (num > 0) {
				positive.add(num);
			} else {
				negative.add(num);
			}
		}
		int n = positive.size();
		int i = 0, j = 0, k = 0;
		while (i < n && j < n) {
			a[k++] = positive.get(i++);
			a[k++] = negative.get(j++);
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, -2, -5, 2, -4 };
		Print.print(rearrange(a1)); // 3 -2 1 -5 2 -4

		int[] a2 = { -1, 1 };
		Print.print(rearrange(a2)); // 1 -1
	}
}
