package com.svetanis.algorithms.slidingwindow.fixed;

// 1052. Grumpy Bookstore Owner

public final class GrumpyBookstoreOwner {
	// Time Complexity: O(n)

	public static int gbo(int[] customers, int[] grumpy, int minutes) {
		int total = 0; // Arrays.stream(customers).sum();
		int unhappy = 0;
		for (int i = 0; i < customers.length; i++) {
			unhappy += customers[i] * grumpy[i];
		}
		int max = 0;
		int left = 0;
		int convertable = 0;
		for (int right = 0; right < customers.length; right++) {
			total += customers[right];
			if (grumpy[right] == 1) {
				convertable += customers[right];
			}
			if (right - left == minutes) {
				if (grumpy[left] == 1) {
					convertable -= customers[left];
				}
				left++;
			}
			max = Math.max(max, convertable);
		}
		return total - unhappy + max;
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