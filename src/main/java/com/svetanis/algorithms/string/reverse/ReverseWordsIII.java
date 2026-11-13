package com.svetanis.algorithms.string.reverse;

// 557. Reverse Words in a String III
//
// Input: a string s of words separated by a single space. Returns s with the characters of each
// word in the opposite order; the words stay where they are, and so do the spaces. On LeetCode:
// 1 <= s.length <= 5 * 10^4, s is printable ASCII, has no leading or trailing space, has at least
// one word, and the words are separated by a single space.
//
// The one idea: a word ends at a space or at the end of s. The loop moves index along s; each time
// it stands on a space (or one past the last character), the word just before it spans space + 1
// .. index - 1, where space is the previous space, and that stretch is reversed by swapping its
// ends inward. The same cut works for the last word because index == n counts as an end.
// reverse2 and reverse3 do the same job through split(" "): one reverses each word with
// StringBuilder.reverse, the other with a backward loop.
//
// Time: O(n) -- each character is read once to find the word ends and swapped at most once.
// Space: O(n) -- the char[] copy (reverse) or the array of words and the builder (reverse2, reverse3).
//
// Siblings: ReverseWordsII reverses the whole array and then each word, to reverse the word
// order instead.

public final class ReverseWordsIII {

	public static String reverse(String s) {
		int space = -1; // SPACE: index of the last space seen, -1 before the first word
		int n = s.length();
		char[] chars = s.toCharArray();
		for (int index = 0; index <= n; index++) {
			if (index == n || chars[index] == ' ') { // END of a word: a space, or the end of s
				int start = space + 1;
				int end = index - 1;
				while (start < end) { // SWAP the two ends of the word, moving inward
					char temp = chars[start];
					chars[start] = chars[end];
					chars[end] = temp;
					start++;
					end--;
				}
				space = index;
			}
		}
		return new String(chars);
	}

	public static String reverse2(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(new StringBuilder(word).reverse()); // REVERSE: one word with the library call
			sb.append(" ");
		}
		return sb.toString().trim(); // TRIM: the space after the last word
	}

	public static String reverse3(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			for (int i = word.length() - 1; i >= 0; i--) {
				sb.append(word.charAt(i)); // REVERSE: one word, read backwards
			}
			sb.append(" ");
		}
		return sb.substring(0, sb.length() - 1); // DROP: the space after the last word
	}

	public static void main(String[] args) {
		System.out.println(reverse("Let's take LeetCode contest")); // s'teL ekat edoCteeL tsetnoc
		System.out.println(reverse("Mr Ding")); // rM gniD
		System.out.println(reverse2("Let's take LeetCode contest")); // s'teL ekat edoCteeL tsetnoc
		System.out.println(reverse3("Mr Ding")); // rM gniD
	}
}
