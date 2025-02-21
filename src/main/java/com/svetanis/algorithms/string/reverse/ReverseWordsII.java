package com.svetanis.algorithms.string.reverse;

// 186. Reverse Words in a String II

// 1. Reverse each individual word.
// 2. Reverse the entire character array.

public final class ReverseWordsII {
	// Time Complexity: O(n)

	public static void reverse(char[] s) {
		int n = s.length;
		for (int start = 0, end = 0; end < n; end++) {
			if (s[end] == ' ') {
				reverse(s, start, end - 1);
				start = end + 1;
			} else if (end == n - 1) {
				// reverse the last word
				reverse(s, start, end);
			}
		}
		// reverse the entire string
		reverse(s, 0, n - 1);
	}

	private static void reverse(char[] chars, int i, int j) {
		while (i < j) {
			char temp = chars[i];
			chars[i] = chars[j];
			chars[j] = temp;
			i++;
			j--;
		}
	}

	public static void main(String[] args) {
		String s1 = "the sky is blue";
		char[] a1 = s1.toCharArray();
		reverse(a1);
		System.out.println(new String(a1)); // blue is sky the

		String s2 = "hello world";
		char[] a2 = s2.toCharArray();
		reverse(a2);
		System.out.println(new String(a2)); // world hello

		String s3 = "a good example";
		char[] a3 = s3.toCharArray();
		reverse(a3);
		System.out.println(new String(a3)); // example good a
	}
}
