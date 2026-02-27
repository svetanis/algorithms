package com.svetanis.algorithms.string;

// 709. To Lower Case

public final class ToLowerCase {
	// Time Complexity: O(m * n/2)
	// Space Complexity: O(1)

	public static String toLowerCase(String s) {
		char[] chars = s.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			chars[i] = Character.toLowerCase(chars[i]);
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		System.out.println(toLowerCase("Hello")); // hello
		System.out.println(toLowerCase("here")); // here
		System.out.println(toLowerCase("LOVELY")); // lovely
	}
}
