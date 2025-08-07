package com.svetanis.algorithms.string.pangram;

import static com.svetanis.java.base.Exceptions.illegalArgument;
import static java.lang.Character.toLowerCase;

// 1832. Check if the Sentence Is Pangram

public final class IsPangram {
	// Time complexity: O(n)
	// Space complexity: O(1)

	private static int N = 26;

	public static boolean isPangram(String s) {
		int n = s.length();
		boolean[] chars = new boolean[N];
		for (int i = 0; i < n; i++) {
			int index = toLowerCase(s.charAt(i)) - 'a';
			if (index >= 0 && index < N) {
				chars[index] = true;
			}
		}
		for (int i = 0; i < N; i++) {
			if (!chars[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPangramCaseSensitive(String s) {
		int n = s.length();
		boolean[] chars = new boolean[N];

		for (int i = 0; i < n; i++) {
			int index = 0;
			char c = s.charAt(i);
			if ('A' <= c && c <= 'Z') {
				index = c - 'A';
			} else if ('a' <= c && c <= 'z') {
				index = c - 'a';
			} else {
				illegalArgument("not valid char %s", c);
			}
			chars[index] = true;
		}

		for (int i = 0; i < N; i++) {
			if (!chars[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String str1 = "A quick brown fox jumps over the lazy dog";
		System.out.println(isPangram(str1));
		System.out.println(isPangramCaseSensitive(str1));

		String str2 = "A slow yellow fox crawls under the proactive dog";
		System.out.println(isPangram(str2));
		System.out.println(isPangramCaseSensitive(str2));

		String str3 = "Lions, and tigers, and bears, oh my!";
		System.out.println(isPangram(str3));
		System.out.println(isPangramCaseSensitive(str3));

		String str4 = "";
		System.out.println(isPangram(str4));
		System.out.println(isPangramCaseSensitive(str4));

		String str5 = "thequickbrownfoxjumpsoverthelazydog";
		System.out.println(isPangram(str5)); // true

		String str6 = "leetcode";
		System.out.println(isPangram(str6)); // false
	}
}