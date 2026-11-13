package com.svetanis.algorithms.string.reorder;

// 1427. Perform String Shifts
//
// Input: a lowercase string s and shifts, an array of pairs [direction, amount]. Direction 0
// shifts s left by amount (the front letter moves to the back, amount times); direction 1 shifts
// it right (the back letter moves to the front). Returns s after all the shifts, in order. On
// LeetCode: 1 <= s.length <= 100, 1 <= shifts.length <= 100, shifts[i].length == 2,
// 0 <= direction <= 1, 0 <= amount <= 100.
//
// The one idea: a left shift and a right shift of the same amount undo each other, so the order of
// the shifts does not matter, only their sum. Count a left shift as negative and a right shift as
// positive; the total is the net number of steps to the right. Shifting a string of n letters by n
// steps gives the string back, so only total mod n matters, and the string is cut and swapped once.
//
// Time: O(n + m) -- m shifts are added up; the string is cut and joined once.
// Space: O(n) -- the two pieces and the answer.

public final class StringShifts {

	public static String shift(String s, int[][] shifts) {
		int total = 0;
		for (int[] shift : shifts) {
			int direction = shift[0];
			int amount = shift[1];
			if (direction == 0) {
				amount = -amount; // LEFT counts as negative
			}
			total += amount; // SUM: net steps to the right
		}
		int n = s.length();
		total = (total % n + n) % n; // WRAP: into 0..n-1; the extra + n fixes a negative remainder
		int len = n - total; // the last `total` letters move to the front
		return s.substring(len) + s.substring(0, len);
	}

	public static void main(String[] args) {
		int[][] shifts = { { 0, 1 }, { 1, 2 }, { 0, 3 } };
		System.out.println(shift("abcdef", shifts)); // cdefab
		int[][] shifts1 = { { 0, 1 }, { 1, 2 } };
		System.out.println(shift("abc", shifts1)); // cab
		int[][] shifts2 = { { 1, 1 }, { 1, 1 }, { 0, 2 }, { 1, 3 } };
		System.out.println(shift("abcdefg", shifts2)); // efgabcd
	}
}