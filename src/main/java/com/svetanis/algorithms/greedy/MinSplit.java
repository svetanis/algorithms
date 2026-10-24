package com.svetanis.algorithms.greedy;

// 2436. Minimum Split Into Subarrays With GCD Greater Than One
//
// Input: an array a of whole numbers, each at least 2. Split it into pieces of consecutive
// elements, each element in exactly one piece, so that the GCD of every piece -- its greatest
// common divisor, the largest number that divides all of its elements -- is greater than 1.
// Returns the fewest pieces. On LeetCode: 1 <= a.length <= 2 * 10^5, 2 <= a[i] <= 10^9.
//
// The one idea: walk left to right and keep the GCD of the piece that is open. Adding an element
// can only keep that GCD or make it smaller, since a number that divides the new, longer piece
// must divide the old one too. So once the GCD reaches 1 it never climbs back: the element that
// brought it to 1 cannot join, the open piece closes just before it, and a new piece starts with
// that element alone, whose GCD is the element itself, at least 2. Why making every piece as long
// as it can be is safe: take any other valid split. Its first piece ends no later than ours,
// because ours runs until it cannot go further. And a piece that starts later, inside one of
// ours, is still valid whenever the longer one was, because dropping elements from the front of a
// piece can only keep or raise its GCD. So after each piece the walk is at least as far right as
// any other split is after the same number of pieces, and it never needs more of them.
// gcd(0, x) = x, so the walk starts with gcd = 0 and the first element opens the first piece.
//
// Time: O(n log m) -- one gcd per element; Euclid's gcd takes O(log m) steps, m the largest
// value.
// Space: O(log m) -- the recursion depth of gcd.

public final class MinSplit {

	public static int minSplits(int[] a) {
		int count = 1; // PIECES: the first piece is open from the start
		int gcd = 0; // GCD of the open piece; gcd(0, x) = x
		for (int num : a) {
			gcd = gcd(gcd, num); // GROW the open piece by num
			if (gcd == 1) { // CLOSE: num cannot join -- the GCD never climbs back above 1
				count += 1; // OPEN a new piece ...
				gcd = num; // ... holding num alone; its GCD is num, at least 2
			}
		}
		return count; // RETURN
	}

	private static int gcd(int a, int b) {
		if (b == 0) { // BASE: gcd(a, 0) = a
			return a;
		}
		return gcd(b, a % b); // EUCLID: the same common divisors, smaller numbers
	}

	public static void main(String[] args) {
		int[] a = { 12, 6, 3, 14, 8 };
		System.out.println(minSplits(a)); // 2
	}
}
