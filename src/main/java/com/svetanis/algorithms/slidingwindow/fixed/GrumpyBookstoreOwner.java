package com.svetanis.algorithms.slidingwindow.fixed;

// 1052. Grumpy Bookstore Owner
//
// customers[i] customers come in during minute i; grumpy[i] is 1 when the owner is grumpy
// that minute, and those customers leave unsatisfied. Once, for `minutes` minutes in a row,
// the owner can keep from being grumpy. Returns the most customers that can be satisfied.
//
// The answer is a fixed part plus a part the window decides. Customers in calm minutes are
// satisfied wherever the calm stretch goes, so they sit outside the window: all customers
// minus the grumpy-minute customers. The window, a fixed stretch of `minutes` minutes, only
// decides which grumpy-minute customers are rescued, so it keeps the sum of customers in its
// grumpy minutes, and the best such sum is added back at the end.
//
// Time: O(n) -- one pass for the grumpy-minute total, one pass where each minute enters the
//   window once and leaves once
// Space: O(1) -- five running numbers
//
// No other file solves 1052.

public final class GrumpyBookstoreOwner {

	public static int gbo(int[] customers, int[] grumpy, int minutes) {
		int total = 0; // COUNT: every customer, added up by the window loop below
		int unhappy = 0;
		for (int i = 0; i < customers.length; i++) {
			unhappy += customers[i] * grumpy[i]; // COUNT: customers in grumpy minutes only
		}
		int max = 0;
		int left = 0;
		int convertable = 0;
		for (int right = 0; right < customers.length; right++) {
			total += customers[right];
			if (grumpy[right] == 1) {
				convertable += customers[right]; // IN: only a grumpy minute can be rescued
			}
			if (right - left == minutes) { // the window holds minutes + 1 minutes: one too many
				if (grumpy[left] == 1) {
					convertable -= customers[left]; // OUT
				}
				left++;
			}
			max = Math.max(max, convertable); // RECORD: a shorter stretch, before the window fills, rescues no more than a full one
		}
		return total - unhappy + max; // RETURN: calm-minute customers plus the best rescue
	}

	public static void main(String[] args) {
		int[] customers = { 1, 0, 1, 2, 1, 1, 7, 5 };
		int[] grumpy = { 0, 1, 0, 1, 0, 1, 0, 1 };
		System.out.println(gbo(customers, grumpy, 3)); // 16

		int[] customers1 = { 1 };
		int[] grumpy1 = { 0 };
		System.out.println(gbo(customers1, grumpy1, 1)); // 1

		int[] customers2 = { 4, 10, 10 };
		int[] grumpy2 = { 1, 1, 0 };
		System.out.println(gbo(customers2, grumpy2, 2)); // 24
	}
}