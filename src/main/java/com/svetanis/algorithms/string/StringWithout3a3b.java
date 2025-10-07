package com.svetanis.algorithms.string;

// 984. String Without AAA or BBB

public final class StringWithout3a3b {
	// Time Complexity: O(max(a,b))
	// Space Complexity: O(a + b)

	public static String strWithout3a3b(int a, int b) {
		StringBuilder sb = new StringBuilder();
		while (a > 0 && b > 0) {
			if (a > b) {
				sb.append("aab");
				a -= 2;
				b -= 1;
			} else if (b > a) {
				sb.append("bba");
				a -= 1;
				b -= 2;
			} else {
				sb.append("ab");
				a -= 1;
				b -= 1;
			}
		}
		while (a > 0) {
			sb.append("a");
			a--;
		}
		while (b > 0) {
			sb.append("b");
			b--;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(strWithout3a3b(1, 2)); // abb
		System.out.println(strWithout3a3b(4, 1)); // aabaa
	}
}
