package com.svetanis.algorithms.dp.math;

// 397. Integer Replacement

public final class IntegerReplacementGreedy {
	// Time Complexity: O(log n)

	public static int intReplacement(int n) {
		int count = 0;
		while (n != 1) {
			if ((n & 1) == 0) { // if n is even
				n >>= 1; // divide by 2
			} else if (n != 3 && (n & 3) == 3) {
				n += 1;
			} else {
				n -= 1;
			}
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(intReplacement(8)); // 3: 8->4->2->1
		System.out.println(intReplacement(7)); // 4: 7->8->4->2->1 or 7->6->3->2->1
		System.out.println(intReplacement(4)); // 2
	}
}
