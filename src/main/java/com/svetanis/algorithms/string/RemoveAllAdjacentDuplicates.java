package com.svetanis.algorithms.string;

// 1047. Remove All Adjacent Duplicates In String

public final class RemoveAllAdjacentDuplicates {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String remove(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			int len = sb.length();
			if (len > 0 && sb.charAt(len - 1) == c) {
				sb.deleteCharAt(sb.length() - 1);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(remove("abbaca")); // ca
		System.out.println(remove("azxxzy")); // ay
	}
}
