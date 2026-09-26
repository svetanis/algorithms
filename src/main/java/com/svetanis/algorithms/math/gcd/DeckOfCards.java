package com.svetanis.algorithms.math.gcd;

// 914. X of a Kind in a Deck of Cards

// A group size X works only if it divides the count of every value: four
// 1s and six 2s split into groups of 2, never of 4. The sizes that divide
// every count are exactly the divisors of their gcd, so some X >= 2 works
// exactly when the gcd of the counts is at least 2.
//
// counts 4, 6: gcd 2 -> true
// counts 3, 3, 2: gcd 1 -> false

public final class DeckOfCards {
	// Time Complexity: O(n + 10^4 * log n), 10^4 being the size of the
	// count array (card values are below 10^4) and n the deck size
	// Space Complexity: O(10^4) for the counts

	public static boolean hasGroupSizeX(int[] deck) {
		int[] counts = counts(deck);
		int gcd = gcd(counts);
		return gcd >= 2;
	}

	// gcd(0, c) is c, so 0 is a safe start, and a value that never appears
	// (count 0) leaves the gcd unchanged
	private static int gcd(int[] counts) {
		int gcd = 0;
		for (int count : counts) {
			gcd = gcd(gcd, count);
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
