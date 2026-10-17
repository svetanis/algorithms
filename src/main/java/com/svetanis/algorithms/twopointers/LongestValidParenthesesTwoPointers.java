package com.svetanis.algorithms.twopointers;

// 32. Longest Valid Parentheses
//
// Input: a string of '(' and ')'.
// Return: the length of the longest substring in which every '(' is closed by a later
// ')' and every ')' closes an earlier '('.
//
// The one idea: ONE bracket type, so the stack version's contents are always "some
// number of unmatched positions" -- and two counters describe that without the stack.
// This trades the stack's O(n) space for O(1), and there is no invariant to trust.
//
// WHY IT TAKES TWO PASSES, which is the whole reason the file is shaped this way.
// A pass records a length only at the moment the two counters are EQUAL, and it
// resets when the closers pull ahead, because a ')' with no partner can never sit
// inside a run that continues past it.
//
// That handles a string broken by an extra ')'. It cannot handle one broken by an
// extra '(' -- those counters never come back level and there is no reset for them,
// so everything after the unmatched '(' is invisible:
//
//     "(()"   left to right: left reaches 2, right reaches 1, never equal  -> 0
//             right to left: the ')' is met first, then a '(' levels it    -> 2
//
//     "())"   left to right: "()" is seen, then the extra ')' resets       -> 2
//             right to left: left pulls ahead immediately and resets       -> 0
//
// The two are exact mirrors: each is blind to one direction of imbalance, each
// alone is wrong on about a third of random strings, and the larger of the two is
// always right. Walking the other way is what turns the blind case into the
// resettable one.
//
// WHY 2 * count. When the counters are equal, every bracket counted so far pairs
// off, so the run is twice either counter.
//
// Sibling: datastructures.stack.parentheses.LongestValidParenthesesStack, in the
//   data-structures repo -- one pass with a stack of the positions that break a run,
//   O(n) space.
//
// Time: O(n) -- two passes, each reads every character once.
// Space: O(1) -- two counters per pass; the stack version costs O(n).

public final class LongestValidParenthesesTwoPointers {

	public static int lvp(String s) {
		int max = leftToRight(s); // RECORD: the best run the forward pass saw
		int left = 0;
		int right = 0;
		// right to left: catches the runs an extra '(' hides, e.g. "(()"
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;           // COUNT
			} else {
				right++;          // COUNT
			}

			if (left == right) {
				max = Math.max(max, 2 * left); // RECORD: every bracket so far pairs off
			} else if (left > right) {
				left = right = 0;              // RESET: an unmatched '(' -- nothing can cross it
			}
		}
		return max;
	}

	private static int leftToRight(String s) {
		int max = 0;
		int left = 0;
		int right = 0;
		// left to right: catches the runs an extra ')' hides, e.g. "())"
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;  // COUNT
			} else {
				right++; // COUNT
			}

			if (left == right) {
				max = Math.max(max, 2 * right); // RECORD: every bracket so far pairs off
			} else if (right > left) {
				left = right = 0;               // RESET: an unmatched ')' -- nothing can cross it
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lvp(""));          // 0
		System.out.println(lvp("(()"));       // 2
		System.out.println(lvp(")()())"));    // 4
		System.out.println(lvp("()(()))))")); // 6

		// the mirror pair: each is answered by one pass only, and either pass alone
		// would return 0 for one of them
		System.out.println(lvp("())"));  // 2 -- left to right finds it
		System.out.println(lvp("((()")); // 2 -- right to left finds it
		System.out.println(lvp("((("));  // 0
	}
}