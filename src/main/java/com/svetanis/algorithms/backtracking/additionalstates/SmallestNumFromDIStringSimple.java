package com.svetanis.algorithms.backtracking.additionalstates;

// 2375. Construct Smallest Number From DI String
//
// Input: pattern -- a string of 'I' and 'D'. Returns the smallest number, as a string of
// pattern.length() + 1 digits, that uses the digits 1 to 9 at most once each, and whose digit at
// position i is smaller than the next one when pattern[i] is 'I' and larger when it is 'D'. On
// LeetCode: 1 <= pattern.length <= 8.
//
// The one idea: the smallest answer uses the digits 1, 2, ..., n + 1 in order wherever it can, and
// only a run of D forces a change. A run of k 'D' needs k + 1 digits falling one after another, so
// the digits that would have come up in order, i + 1 to i + k + 1, are written backwards. So walk
// the digits 1, 2, 3, ... and park each one in a stack, a[0..index); at an 'I' (or at the end)
// the parked digits are written out from the top down, which reverses exactly the run of D before
// it. A digit that follows an 'I' starts a new run, so smaller digits are never held back.
//
// Time: O(n) -- each digit is parked once and written once.
// Space: O(n) -- the stack and the answer.
//
// Siblings: SmallestNumFromDIString searches the digits in increasing order and keeps the first
// string that fits; this one builds the same string directly.

public final class SmallestNumFromDIStringSimple {

	public String smallestNum(String s) {
		int n = s.length();
		int index = 0; // SIZE: how many digits are parked in the stack a
		int[] a = new int[n + 1];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= n; i++) {
			a[index++] = i + 1; // PARK: the next digit
			if (i == n || s.charAt(i) == 'I') { // RELEASE: an I (or the end) closes the run of D
				while (index > 0) {
					sb.append(a[--index]); // WRITE: parked digits, top first
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SmallestNumFromDIStringSimple sms = new SmallestNumFromDIStringSimple();
		System.out.println(sms.smallestNum("IIIDIDDD")); // 123549876
		SmallestNumFromDIStringSimple sms2 = new SmallestNumFromDIStringSimple();
		System.out.println(sms2.smallestNum("DDD")); // 4321
	}
}
