package com.svetanis.algorithms.twopointers;

// 165. Compare Version Numbers

public final class CompareVersionNumbers {
	// Time Complexity: O(max(n,m))

	public static int compare(String v1, String v2) {
		int n = v1.length();
		int m = v2.length();
		for (int i = 0, j = 0; i < n || j < m; i++, j++) {
			int num1 = 0, num2 = 0;
			while (i < n && v1.charAt(i) != '.') {
				num1 = num1 * 10 + (v1.charAt(i) - '0');
				i++;
			}
			while (j < m && v2.charAt(j) != '.') {
				num2 = num2 * 10 + (v2.charAt(j) - '0');
				j++;
			}
			if (num1 != num2) {
				return num1 < num2 ? -1 : 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(compare("1.2", "1.10")); // -1
		System.out.println(compare("1.01", "1.001")); // 0
		System.out.println(compare("1.0", "1.0.0.0")); // 0
	}
}
