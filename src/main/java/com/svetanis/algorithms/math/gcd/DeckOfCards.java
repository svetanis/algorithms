package com.svetanis.algorithms.math.gcd;

// 914. X of a Kind in a Deck of Cards

public final class DeckOfCards {
	// Time Complexity: O(n + m * log k)

	public static boolean hasGroupSizeX(int[] deck) {
		int[] counts = counts(deck);
		int gcd = gcd(counts);
		return gcd >= 2;
	}

	private static int gcd(int[] counts) {
		int gcd = -1;
		for (int count : counts) {
			if (count > 0) {
				gcd = gcd == -1 ? count : gcd(gcd, count);
			}
		}
		return gcd;
	}

	private static int[] counts(int[] deck) {
		int[] counts = new int[10000];
		for (int d : deck) {
			counts[d]++;
		}
		return counts;
	}

	private static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}

	public static void main(String[] args) {
		int[] deck1 = { 1, 2, 3, 4, 4, 3, 2, 1 };
		System.out.println(hasGroupSizeX(deck1)); // true

		int[] deck2 = { 1, 1, 1, 2, 2, 2, 3, 3 };
		System.out.println(hasGroupSizeX(deck2)); // false
	}
}
