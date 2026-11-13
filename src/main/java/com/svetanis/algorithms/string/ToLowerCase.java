package com.svetanis.algorithms.string;

// 709. To Lower Case
//
// Input: a string s. Returns s with every uppercase letter replaced by its lowercase letter; all
// other characters stay. On LeetCode: 1 <= s.length <= 100, s is printable ASCII.
//
// The one idea: copy the string into a character array, replace each character by its lowercase
// form, and build a string from the array. Character.toLowerCase changes only uppercase letters,
// so digits, symbols and lowercase letters pass through unchanged. (By hand, an ASCII uppercase
// letter becomes lowercase by adding 32.)
//
// Time: O(n) -- one pass over n characters.
// Space: O(n) -- the character array copy; the result string is the same size.

public final class ToLowerCase {

	public static String toLowerCase(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) { // WALK: every character
			chars[i] = Character.toLowerCase(chars[i]); // REPLACE with the lowercase form
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		System.out.println(toLowerCase("Hello")); // hello
		System.out.println(toLowerCase("here")); // here
		System.out.println(toLowerCase("LOVELY")); // lovely
	}
}
