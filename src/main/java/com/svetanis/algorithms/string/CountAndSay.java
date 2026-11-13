package com.svetanis.algorithms.string;

// 38. Count and Say
//
// Input: n. Term 1 is "1"; term k is term k - 1 read aloud, each run of equal digits as its
// length followed by the digit ("3322251" is "two 3s, three 2s, one 5, one 1" = "23321511").
// Returns term n. On LeetCode: 1 <= n <= 30.
//
// The one idea: build the terms one after another, because term k is defined only by term k - 1.
// To read one term aloud, walk it left to right; at each position take the digit there, run
// forward while the digit stays the same, counting, then write the count and the digit. The
// run's end is the start of the next run, so each digit is visited once per term.
//
// Time: O(1.3^n) -- the term grows by a factor of about 1.30 each step (Conway's constant), and
// building a term costs its previous term's length, so the total is a constant times the last length
// Space: O(1.3^n) -- the current term and the next one

public final class CountAndSay {

	public static String countAndSay(int n) {
		String curr = "1"; // TERM: term 1
		while (--n > 0) { // WALK: one pass per term still to build
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < curr.length();) {
				int count = 0;
				char c = curr.charAt(i);
				while (i < curr.length() && curr.charAt(i) == c) { // RUN: same digit as c
					i++;
					count++;
				}
				sb.append(count).append(c); // SAY: the run's length, then its digit
			}
			curr = sb.toString();
		}
		return curr;
	}

	public static void main(String[] args) {
		System.out.println(countAndSay(4)); // 1211
		System.out.println(countAndSay(1)); // 1
	}
}
