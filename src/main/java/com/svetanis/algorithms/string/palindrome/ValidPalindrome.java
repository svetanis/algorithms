package com.svetanis.algorithms.string.palindrome;

import static com.svetanis.java.base.collect.Lists.filter;
import static com.svetanis.java.base.collect.Lists.transform;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;
import static java.util.stream.Collectors.toList;

import java.util.List;

// a phrase is a palindrome if, after
// converting all uppercase letters
// into lowercase letters and removing
// all non-alphanumeric characters, it
// reads the same forward and backward.
// alphanumeric characters include 
// letters and numbers

public final class ValidPalindrome {
	// Time Complexity: O(n)

	public static boolean palindrome(String s) {
		List<Character> chars = s.codePoints().mapToObj(c -> (char) c).collect(toList());
		List<Character> filtered = filter(chars, c -> isLetterOrDigit(c));
		List<Character> list = transform(filtered, c -> toLowerCase(c));
		int left = 0;
		int right = list.size() - 1;
		while (left < right) {
			if (list.get(left) != list.get(right)) {
				return false;
			} else {
				left++;
				right--;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "A man, a plan, a canal: Panama";
		String s2 = "race a car";
		String s3 = "";
		System.out.println(palindrome(s1)); // true
		System.out.println(palindrome(s2)); // false
		System.out.println(palindrome(s3)); // true
	}
}
