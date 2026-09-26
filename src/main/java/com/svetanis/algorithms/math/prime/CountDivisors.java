package com.svetanis.algorithms.math.prime;

// CSES: Counting Divisors

// Many questions "how many divisors does x have?", x up to 10^6. Answer
// them all at once, sieve-style: every i is a divisor of i, 2i, 3i, ...,
// so walk those multiples and add one to each. After every i has walked,
// divisors[x] holds the count for x.
//
//   i = 1 adds to 1 2 3 4 5 6
//   i = 2 adds to   2   4   6
//   i = 3 adds to     3     6
//   ...                       -> divisors[6] = 4 (1, 2, 3, 6)

public final class CountDivisors {
	// Time Complexity: O(N log N) to fill the table once -- i walks N / i
	// multiples, and N/1 + N/2 + N/3 + ... is about N log N; O(1) per question
	// Space Complexity: O(N)

	private static final int N = 1_000_001; // x <= 10^6, and the table is indexed by x

	private final int[] divisors; // int is enough: no x <= 10^6 has more than 240

	public CountDivisors() {
		this.divisors = divisors();
	}

	public int countDivisors(int x) {
		return divisors[x];
	}

	private static int[] divisors() {
		int[] divisors = new int[N];
		for (int i = 1; i < N; i++) {
			for (int j = i; j < N; j += i) {
				divisors[j]++;
			}
		}
		return divisors;
	}

	public static void main(String[] args) {
		CountDivisors cd = new CountDivisors();
		System.out.println(cd.countDivisors(5)); // 2
		System.out.println(cd.countDivisors(6)); // 4
		System.out.println(cd.countDivisors(7)); // 2

		System.out.println(cd.countDivisors(16)); // 5
		System.out.println(cd.countDivisors(17)); // 2
		System.out.println(cd.countDivisors(18)); // 6
	}
}
