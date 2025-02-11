package com.svetanis.algorithms.string.reverse;

// 557. Reverse Words in a String III

public final class ReverseWordsIII {
	// Time Complexity: O(n)

	public static String reverse(String s) {
		int space = -1;
		int n = s.length();
		char[] chars = s.toCharArray();
		for (int index = 0; index <= n; index++) {
			if (index == n || chars[index] == ' ') {
				int start = space + 1;
				int end = index - 1;
				while (start < end) {
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
			sb.append(new StringBuilder(word).reverse());
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	public static String reverse3(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			for (int i = word.length() - 1; i >= 0; i--) {
				sb.append(word.charAt(i));
			}
			sb.append(" ");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static void main(String[] args) {
		// s'teL ekat edoCteeL tsetnoc
		System.out.println(reverse("Let's take LeetCode contest"));
		System.out.println(reverse("Mr Ding")); // rM gniD
	}
}
