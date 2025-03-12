package com.svetanis.algorithms.string.palindrome;

// 214. Shortest Palindrome

// given a string which can be converted
// to a palindrome by adding chars in 
// front of it. return the shortest palindrome
// you can find by performing this transformation

// quick and inefficient

public final class ShortestPalindrome {
	// Time Complexity: O(n)

	public static String palindrome(String s) {
		int left = 0;
		int right = s.length();
		StringBuilder sb = new StringBuilder(s);
		String r = sb.reverse().toString();
		while (left < right) {
			String s1 = s.substring(0, right);
			String s2 = r.substring(left);
			if (s1.equals(s2)) {
				return r.substring(0, left) + s;
			}
			left++;
			right--;
		}
		return r.substring(0, left) + s;
	}

	public static void main(String[] args) {
		System.out.println(palindrome("aacecaaa")); // aaacecaaa
		System.out.println(palindrome("abcd")); // dcbabcd
	}
}
