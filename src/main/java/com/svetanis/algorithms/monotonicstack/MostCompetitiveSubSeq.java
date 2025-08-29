package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
import java.util.Deque;

// 1673. Find the Most Competitive Subsequence

public final class MostCompetitiveSubSeq {
	// Time complexity: O(n)
	// Space complexity: O(1)

	public static int[] mostCompetitive(int[] a, int k) {
		int n = a.length;
		Deque<Integer> dq = new ArrayDeque<>();
		for (int index = 0; index < n; index++) {
			while (!dq.isEmpty() && dq.size() + n - index > k && dq.peek() > a[index]) {
				dq.pop();
			}
			if (dq.size() < k) {
				dq.push(a[index]);
			}
		}
		int[] subseq = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			subseq[i] = dq.pop();
		}
		return subseq;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 2, 6 };
		print(mostCompetitive(a1, 2)); // 2,6

		int[] a2 = { 2, 4, 3, 3, 5, 4, 9, 6 };
		print(mostCompetitive(a2, 4)); // 2,3,3,4
	}
}
