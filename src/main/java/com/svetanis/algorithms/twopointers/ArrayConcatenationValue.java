package com.svetanis.algorithms.twopointers;

import static java.lang.String.valueOf;

// 2562. Find the Array Concatenation Value
//
// Input: an array of positive integers.
// Return: the concatenation value. While two or more numbers are left, take the first
// and the last, write the first's digits followed by the last's digits, read that as
// one number and add it, then remove both. If one number is left, add it as it is.
//
// The one idea: every removal takes the two ends, so two pointers walking inward meet
// exactly the pairs the process removes -- nothing is ever deleted from the array.
// Writing x's digits followed by y's digits is the number x * 10^d + y, where d is
// the number of digits of y: x moves d places left to make room.
//
// Two methods, the same walk -- they differ only in how one pair is joined:
//   arrayConcatenationValue -- arithmetic: x * 10^d + y, with 10^d found by dividing y by 10
//   concatenationValueByStrings -- joins the two numbers as Strings and parses the
//                                  result back to a long
//
// Time: O(n log V) -- n/2 pairs, and counting the digits of y takes about log10 V steps,
//   where V is the largest value.
// Space: O(1) for arrayConcatenationValue; concatenationValueByStrings builds a short String per pair.
// The total is a long: one joined pair of two 5-digit numbers is already 10 digits.

public final class ArrayConcatenationValue {

	public static long arrayConcatenationValue(int[] a) {
		int left = 0;                         // START: the two ends
		int right = a.length - 1;
		long total = 0;

		while (left < right) {                // STOP: fewer than two numbers left to pair
			int x = a[left];
			int y = a[right];

			int pow = 1;                      // COUNT: pow becomes 10^(number of digits of y)
			int temp = y;
			if (temp == 0) {
				pow = 10;                     // SKIP the count: 0 has one digit, and the loop would find none
			} else {
				while (temp > 0) {
					pow *= 10;
					temp /= 10;
				}
			}

			long concat = (long) x * pow + y; // JOIN: x's digits, then y's digits
			total += concat;                  // ADD

			left++;                           // DROP LEFT and DROP RIGHT: both are removed
			right--;
		}

		if (left == right) {
			total += a[left];                 // ADD the middle number alone (odd length)
		}
		return total;
	}

	public static long concatenationValueByStrings(int[] a) {
		int left = 0;                         // START: the two ends
		int right = a.length - 1;
		long total = 0;
		while (left < right) {                // STOP: fewer than two numbers left to pair
			String concat = valueOf(a[left]) + valueOf(a[right]); // JOIN as text
			total += Long.parseLong(concat);  // ADD the joined number
			left++;                           // DROP LEFT and DROP RIGHT: both are removed
			right--;
		}
		if (left == right) {
			total += a[left];                 // ADD the middle number alone (odd length)
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 52, 2, 4 };
		System.out.println(arrayConcatenationValue(a1)); // 596
		System.out.println(concatenationValueByStrings(a1)); // 596

		int[] a2 = { 5, 14, 13, 8, 12 };
		System.out.println(arrayConcatenationValue(a2)); // 673
		System.out.println(concatenationValueByStrings(a2)); // 673
	}
}
