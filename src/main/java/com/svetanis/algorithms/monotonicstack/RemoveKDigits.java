package com.svetanis.algorithms.monotonicstack;

// 402. Remove K Digits
//
// Remove k digits from the number num (a string) so that what remains is the smallest
// possible number. Leading zeros are dropped; nothing left means "0".
//
// A digit on the left weighs more than every digit after it, so a larger digit followed by a
// smaller one should go. The StringBuilder is the stack: each pop deletes a digit, and what is
// left on it at the end is the answer.

public final class RemoveKDigits {
	// Time Complexity: O(n), every digit is appended once and deleted at most once
	// Space Complexity: O(n) for the StringBuilder

	public static String removeKDigits(String num, int k) {
		StringBuilder kept = new StringBuilder();
		for (char digit : num.toCharArray()) {
			while (kept.length() > 0 && k > 0 && kept.charAt(kept.length() - 1) > digit) {
				kept.deleteCharAt(kept.length() - 1); // a larger digit before a smaller one: delete it
				k--;
			}
			kept.append(digit);
		}
		while (k > 0 && kept.length() > 0) { // digits never fell: the largest are at the end
			kept.deleteCharAt(kept.length() - 1);
			k--;
		}
		int firstNonZero = 0;
		while (firstNonZero < kept.length() && kept.charAt(firstNonZero) == '0') {
			firstNonZero++;
		}
		String s = kept.substring(firstNonZero);
		return s.isEmpty() ? "0" : s;
	}

	public static void main(String[] args) {
		System.out.println(removeKDigits("1432219", 3)); // 1219
		System.out.println(removeKDigits("10200", 1)); // 200
		System.out.println(removeKDigits("10", 2)); // 0
	}
}
