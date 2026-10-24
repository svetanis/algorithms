package com.svetanis.algorithms.greedy;

// 738. Monotone Increasing Digits
//
// Input: a number n. Returns the largest number at most n whose digits never fall from left to
// right: each digit is at most the digit after it. On LeetCode: 0 <= n <= 10^9.
//
// The one idea: one element is one decimal digit of n, read left to right. If the digits never
// fall, n itself is the answer. Otherwise the answer is smaller than n, so it copies n up to some
// position p, has a smaller digit than n at p, and is free after p -- and free means all 9s, the
// largest digit, which never falls after anything. Copying a longer stretch of n gives a larger
// number, so p should be as far right as it can be. At the first fall, digits[i - 1] > digits[i],
// p cannot be i or later (the copied part would keep the fall), so try p = i - 1: lower that
// digit by one. That can make a new fall against the digit before it -- in 332, lowering the
// middle 3 gives 3 > 2 at the front -- so the lowering is carried left while a fall remains, and
// it stops at the rightmost p whose lowered digit still sits at or above the digit before it.
// Every digit after p becomes 9. A leading 0 (100 -> "099") is dropped by parseInt.
//
// Time: O(d) -- d is the number of digits, at most 10; the three loops each move i at most d
// steps.
// Space: O(d) -- the digit array.

public final class MonotoneIncreasingDigits {

	public static int mid(int n) {
		char[] digits = String.valueOf(n).toCharArray();
		int i = 1;
		int len = digits.length;
		while (i < len && digits[i - 1] <= digits[i]) {
			i++; // RISE: walk right while no digit falls
		}
		if (i < len) {
			while (i > 0 && digits[i - 1] > digits[i]) {
				digits[i - 1]--; // BORROW: lower the digit before the fall, walking left
				i--;
			}
			while (++i < len) {
				digits[i] = '9'; // FILL: everything after the borrow becomes 9
			}
		}
		return Integer.parseInt(new String(digits));
	}

	public static void main(String[] args) {
		System.out.println(mid(10)); // 9
		System.out.println(mid(1234)); // 1234
		System.out.println(mid(332)); // 299
	}
}
