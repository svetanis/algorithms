package com.svetanis.algorithms.monotonicstack;

// 402. Remove K Digits
//
// Remove k digits from the number num (a string) so that what remains is the smallest
// possible number. Leading zeros are dropped; nothing left means "0".
//
// A digit on the left weighs more than every digit after it, so a larger digit followed by a
// smaller one should go. The StringBuilder is the stack: each pop deletes a digit, and what is
// left on it at the end is the answer.
//
// RemoveKDigitsDeque is the same walk with an ArrayDeque of digits as the stack, read back from
// the bottom at the end.

public final class RemoveKDigits {
	// Time Complexity: O(n), every digit is appended once and deleted at most once
	// Space Complexity: O(n) for the StringBuilder

	public static String removeKDigits(String num, int k) {
		StringBuilder kept = new StringBuilder(); // STACK: the kept digits, front of the number first
		for (char digit : num.toCharArray()) { // WALK: the next digit arrives
			while (kept.length() > 0 && k > 0 && kept.charAt(kept.length() - 1) > digit) { // CHECK: owed and it helps
				kept.deleteCharAt(kept.length() - 1); // DROP: a larger digit before a smaller one: delete it
				k--; // SPEND: one deletion used
			}
			kept.append(digit); // KEEP: the digit goes on top
		}
		while (k > 0 && kept.length() > 0) { // END: digits never fell: the largest are at the end
			kept.deleteCharAt(kept.length() - 1); // DROP: the last digit, the largest still kept
			k--; // SPEND: one deletion used
		}
		int firstNonZero = 0; // ZEROS: count the leading zeros
		while (firstNonZero < kept.length() && kept.charAt(firstNonZero) == '0') { // SKIP: a leading zero
			firstNonZero++;
		}
		String s = kept.substring(firstNonZero); // TRIM: the kept digits without leading zeros
		return s.isEmpty() ? "0" : s; // RETURN: everything deleted is the number 0
	}

	public static void main(String[] args) {
		System.out.println(removeKDigits("1432219", 3)); // 1219
		System.out.println(removeKDigits("10200", 1)); // 200
		System.out.println(removeKDigits("10", 2)); // 0
	}
}
