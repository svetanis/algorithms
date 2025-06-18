package com.svetanis.algorithms.twopointers;

// 277. Find the Celebrity

public class Celebrity {
	// Time Complexity: O(n)

	public int celebrity(int n) {
		// first pass: identify a celebrity candidate
		int candidate = candidate(n);
		// second pass: validate the candidate
		return isCelebrity(n, candidate);
	}

	private int candidate(int n) {
		int candidate = 0;
		for (int guest = 1; guest < n; guest++) {
			if (knows(candidate, guest)) {
				candidate = guest;
			}
		}
		return candidate;
	}

	private int isCelebrity(int n, int candidate) {
		// check if candidate is actually celebrity of not
		for (int i = 0; i < n; i++) {
			// celebrity candidate knows someone
			boolean one = knows(candidate, i);
			// someone doesn't know celebrity candidate
			boolean two = !knows(i, candidate);
			if ((i != candidate) && (one || two)) {
				return -1;
			}
		}
		return candidate;
	}

	public static void main(String[] args) {
		Celebrity c = new Celebrity();
		System.out.println(c.celebrity(4));
	}

	// provided by leetcode
	private boolean knows(int a, int b) {
		return false;
	}
}
