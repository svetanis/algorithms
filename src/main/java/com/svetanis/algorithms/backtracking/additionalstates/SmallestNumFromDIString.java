package com.svetanis.algorithms.backtracking.additionalstates;

// 2375. Construct Smallest Number From DI String
//
// Input: pattern -- a string of 'I' and 'D'. Returns the smallest number, as a string of
// pattern.length() + 1 digits, that uses the digits 1 to 9 at most once each, and whose digit at
// position i is smaller than the next one when pattern[i] is 'I' and larger when it is 'D'. On
// LeetCode: 1 <= pattern.length <= 8.
//
// The one idea: all candidates have the same length, so the smallest is the one whose first digit
// is smallest, then second, and so on. Fill the positions left to right and at each one try the
// digits in increasing order, skipping a digit already used or one that breaks the pattern against
// the digit just before it. The first complete string found is then the smallest, and the search
// stops there: smallest is both the answer and the stop flag, so it is cleared at the start of
// each call.
//
// Time: O(9!) in the worst case -- a string of at most 9 digits taken from 9, so at most 9! = 362880
// strings are tried; the first complete one ends the search.
// Space: O(n) -- the recursion, the buffer and the used flags are at most 9 deep.
//
// Siblings: SmallestNumFromDIStringSimple finds the same string in O(n), without search, by
// reversing each run of D.

public final class SmallestNumFromDIString {

	private String smallest;

	public String smallestNum(String s) {
		// `smallest` doubles as the stop flag, so it has to be cleared
		// here -- otherwise the second call on this object returns the
		// first call's answer
		this.smallest = null;
		boolean[] visited = new boolean[10]; // USED: visited[d] -- digit d is already in the buffer
		StringBuilder sb = new StringBuilder();
		dfs(0, s, visited, sb);
		return smallest;
	}

	private void dfs(int index, String s, boolean[] visited, StringBuilder sb) {
		if (smallest != null) { // STOP: the smallest is already found
			return;
		}
		if (index == s.length() + 1) { // BASE: every position filled, the first one found is smallest
			this.smallest = sb.toString();
			return;
		}
		for (int num = 1; num <= 9; num++) { // TRY: digits in increasing order
			if (!visited[num]) {
				boolean inc = index > 0 && s.charAt(index - 1) == 'I' && num < sb.charAt(index - 1) - '0';
				boolean dec = index > 0 && s.charAt(index - 1) == 'D' && num > sb.charAt(index - 1) - '0';
				if (inc || dec) { // SKIP: breaks the pattern against the digit before
					continue;
				}
				sb.append(num); // PLACE
				visited[num] = true;
				dfs(index + 1, s, visited, sb);
				visited[num] = false; // UNDO
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	public static void main(String[] args) {
		SmallestNumFromDIString sms = new SmallestNumFromDIString();
		System.out.println(sms.smallestNum("IIIDIDDD")); // 123549876
		SmallestNumFromDIString sms2 = new SmallestNumFromDIString();
		System.out.println(sms2.smallestNum("DDD")); // 4321
	}
}
