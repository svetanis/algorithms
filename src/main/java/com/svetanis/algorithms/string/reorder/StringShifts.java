package com.svetanis.algorithms.string.reorder;

// 1427. Perform String Shifts

public final class StringShifts {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n)

	public static String shift(String s, int[][] shifts) {
		int total = 0;
		for (int[] shift : shifts) {
			int direction = shift[0];
			int amount = shift[1];
			if (direction == 0) {
				amount = -amount;
			}
			total += amount;
		}
		int n = s.length();
		total = (total % n + n) % n;
		int len = n - total;
		return s.substring(len) + s.substring(0, len);
	}

	public static void main(String[] args) {
		int[][] shifts = { { 0, 1 }, { 1, 2 }, { 0, 3 } };
		System.out.println(shift("abcdef", shifts));
	}
}