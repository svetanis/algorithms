package com.svetanis.algorithms.string;

import com.svetanis.java.base.utils.Print;

// 942. DI String Match

public final class DIStringMatch {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int[] diStringMatch(String s) {
		int n = s.length();
		int[] a = new int[n + 1];
		int left = 0;
		int right = n;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'I') {
				a[i] = left;
				left++;
			} else {
				a[i] = right;
				right--;
			}
		}
		a[n] = left;
		return a;
	}

	public static void main(String[] args) {
		Print.print(diStringMatch("IDID")); // 0 4 1 3 2
		Print.print(diStringMatch("III")); // 0 1 2 3
		Print.print(diStringMatch("DDI")); // 3 2 0 1
	}
}
