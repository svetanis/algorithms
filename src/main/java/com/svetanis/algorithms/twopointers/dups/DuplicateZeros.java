package com.svetanis.algorithms.twopointers.dups;

import com.svetanis.java.base.utils.Print;

// 1089. Duplicate Zeros

public final class DuplicateZeros {
	// Time Complexity: O(n)
	// Aux Space: O(1)

	public static void duplicateZeros(int[] a) {
		int n = a.length;
		int srcIndex = -1;
		int count = 0;
		while(count < n) {
			srcIndex += 1;
			int inc = a[srcIndex] == 0 ? 2 : 1;
			count += inc;
		}
		int dstIndex = n - 1;
		if(count == n + 1) {
			a[dstIndex] = 0;
			dstIndex -= 1;
			srcIndex -= 1;
		}
		
		while(dstIndex >= 0) {
			a[dstIndex] = a[srcIndex];
			if(a[srcIndex] == 0) {
				dstIndex -= 1;
				a[dstIndex] = 0;
			}
			srcIndex -= 1;
			dstIndex -= 1;
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 0, 2, 3, 0, 4, 5, 0 };
		duplicateZeros(a);
		Print.print(a); // 1 0 0 2 3 0 0 4
		int[] a1 = { 1, 2, 3 };
		duplicateZeros(a);
		Print.print(a1); // 1 2 3
	}
}
