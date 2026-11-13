package com.svetanis.algorithms.string.reverse;

// 186. Reverse Words in a String II
//
// Input: a char[] s of words separated by a single space, with no leading or trailing space.
// Returns nothing; s is changed in place so the words come in the opposite order, each word
// spelled as before. On LeetCode: 1 <= s.length <= 10^5, s holds letters, digits and ' ', there is
// at least one word, and the words are separated by exactly one space.
//
// The one idea: reversing the whole array puts the words in the right order but spells each one
// backwards. Reversing each word again, in place, spells it forwards. Two reversals, no new array.
// The order of the two steps does not matter: reversing each word first and the whole array
// second gives the same result. The code does the whole array first.
//
// Time: O(n) -- the whole-array reversal is n / 2 swaps and the words together are another n / 2.
// Space: O(1) -- the swaps happen inside s.
//
// Siblings: ReverseWordsInPlace is the same two reversals for the harder LeetCode 151, where the
// spaces are irregular, so it also squeezes them out; ReverseWordsIII reverses each word only.

public final class ReverseWordsII {

	public static void reverse(char[] s) {
		int n = s.length;
		reverse(s, 0, n - 1); // STEP 1: the whole array, so the words are in the right order
		for (int start = 0, end = 0; end < n; end++) {
			if (s[end] == ' ') {
				reverse(s, start, end - 1); // STEP 2: spell this word forwards again
				start = end + 1;
			} else if (end == n - 1) {
				reverse(s, start, end); // STEP 2: the last word has no space after it
			}
		}
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
