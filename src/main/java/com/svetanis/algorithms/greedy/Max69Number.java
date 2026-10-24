package com.svetanis.algorithms.greedy;

// 1323. Maximum 69 Number
//
// Input: num -- a positive integer whose digits are all 6 or 9. At most one digit may be changed,
// a 6 into a 9 or a 9 into a 6. Returns the largest number that can be made. On LeetCode:
// 1 <= num <= 10^4, and every digit of num is 6 or 9.
//
// The one idea: a digit is worth more than all the digits to its right put together, so the best
// single change is the one that raises the leftmost digit it can -- the first 6, turned into a 9.
// Turning a 9 into a 6 only makes the number smaller, and a number with no 6 in it is already the
// largest it can be.
//
// The two methods make the same change. max69NumStr lets String.replaceFirst change the first 6;
// max69Num scans the characters itself and stops at the first 6.
//
// Time: O(d) -- d = the number of digits, at most 5: one scan of the digits.
// Space: O(d) -- the string, and the character array in max69Num.

public final class Max69Number {

	public static int max69NumStr(int num) {
		String s = String.valueOf(num);
		String replaced = s.replaceFirst("6", "9"); // CHANGE the first 6, if there is one
		return Integer.parseInt(replaced); // RETURN: back to a number
	}

	public static int max69Num(int num) {
		String s = String.valueOf(num);
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) { // WALK: leftmost digit first
			char c = chars[i];
			if (c == '6') {
				chars[i] = '9'; // CHANGE the first 6
				break; // STOP: one change only
			}
		}
		return Integer.parseInt(String.valueOf(chars)); // RETURN: back to a number
	}

	public static void main(String[] args) {
		System.out.println(max69NumStr(9669)); // 9969
		System.out.println(max69NumStr(9996)); // 9999
	}
}
