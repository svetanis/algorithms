package com.svetanis.algorithms.string.substr;

// 686. Repeated String Match

public class RepeatedStringMatch {

	public static int rsm(String a, String b) {
		int n = a.length();
		int m = b.length();
		int count = 1;
		StringBuilder sb = new StringBuilder(a);
		while (n < m) {
			sb.append(a);
			count++;
			n = sb.length();
		}
		for (int i = 0; i < 2; i++) {
			if (sb.toString().contains(b)) {
				return count;
			}
			sb.append(a);
			count++;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(rsm("abcd", "cdabcdab")); // 3
		System.out.println(rsm("a", "aa")); // 2
	}
}
