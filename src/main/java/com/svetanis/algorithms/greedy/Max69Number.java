package com.svetanis.algorithms.greedy;

// 1323. Maximum 69 Number

public final class Max69Number {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int max69NumStr(int num) {
		String s = String.valueOf(num);
		String replaced = s.replaceFirst("6", "9");
		return Integer.parseInt(replaced);
	}

	public static int max69Num(int num) {
		String s = String.valueOf(num);
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			char c = chars[i];
			if (c == '6') {
				chars[i] = '9';
				break;
			}
		}
		return Integer.parseInt(String.valueOf(chars));
	}

	public static void main(String[] args) {
		System.out.println(max69NumStr(9669)); // 9969
		System.out.println(max69NumStr(9996)); // 9999
	}
}
