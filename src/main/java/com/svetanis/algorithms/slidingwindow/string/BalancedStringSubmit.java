package com.svetanis.algorithms.slidingwindow.string;

// 1234. Replace the Substring for Balanced String

public final class BalancedStringSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	private static final String QWER = "QWER";

	public static int balancedStr(String s) {
		int[] count = new int[4];
		for (char c : s.toCharArray()) {
			count[QWER.indexOf(c)]++;
		}
		int n = s.length();
		int target = n / 4;
		if (notExceed(count, target)) {
			return 0;
		}
		int min = n;
		int left = 0;
		for (int right = 0; right < n; right++) {
			char c = s.charAt(right);
			count[QWER.indexOf(c)]--;
			while (left <= right && notExceed(count, target)) {
				min = Math.min(min, right - left + 1);
				count[QWER.indexOf(s.charAt(left))]++;
				left++;
			}
		}
		return min;
	}

	private static boolean notExceed(int[] count, int target) {
		for (int i = 0; i < count.length; i++) {
			if (count[i] > target) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(balancedStr("QWER")); // 0
		System.out.println(balancedStr("QQWE")); // 1
		System.out.println(balancedStr("QQQW")); // 2
		System.out.println(balancedStr("QQQWWWEEEERRRR")); // 2
	}
}
