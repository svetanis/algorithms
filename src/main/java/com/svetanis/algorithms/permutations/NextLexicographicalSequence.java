package com.svetanis.algorithms.permutations;

// given a string of lowercase English letters, 
// rearrange the chars to form a new string
// representing the next immediate sequence in
// lexicographical (alphabetical) order. If the
// given string is already last in lexicographical
// order among all possible arrangements, return
// the arrangement that is first in lexicographical order

public final class NextLexicographicalSequence {
	// Time Complexity: O(n)

	public static String nextGreater(String s) {
		char[] chars = s.toCharArray();
		int len = chars.length;
		int pivot = len - 2;
		// find digit that is less than
		// its immediate right digit
		while (pivot >= 0 && chars[pivot] >= chars[pivot + 1]) {
			pivot--;
		}
		if (pivot < 0) {
			reverse(chars, 0, len - 1);
			return new String(chars);
		}
		// find digit that is greater
		// than the digit found above
		int rightMostSuccessor = len - 1;
		while (chars[pivot] >= chars[rightMostSuccessor]) {
			rightMostSuccessor--;
		}
		swap(chars, pivot, rightMostSuccessor);
		reverse(chars, pivot + 1, len - 1);
		return new String(chars);
	}

	private static void reverse(char[] chars, int start, int end) {
		while (start < end) {
			swap(chars, start, end);
			start++;
			end--;
		}
	}

	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	public static void main(String[] args) {
		System.out.println(nextGreater("abcd")); // abdc
		System.out.println(nextGreater("dcba")); // abcd
	}
}
