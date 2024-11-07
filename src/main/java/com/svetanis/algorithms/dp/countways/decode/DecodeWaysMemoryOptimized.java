package com.svetanis.algorithms.dp.countways.decode;

// 91. Decode Ways

public final class DecodeWaysMemoryOptimized {
	// Time Complexity: O(n)

	public static int decode(String s) {
		int n = s.length();
		int prev = 0;
		int curr = 1;
		for (int i = 1; i <= n; i++) {
			int next = 0;
			if (s.charAt(i - 1) != '0') {
				next = curr;
			}
			if (i > 1 && s.charAt(i - 2) != '0') {
				int val = Integer.parseInt(s.substring(i - 2, i));
				if (val <= 26) {
					next += prev;
				}
			}
			prev = curr;
			curr = next;
		}
		return curr;
	}

	public static void main(String[] args) {
		System.out.println(decode("12")); // 2 : AB (1 2) or L (12)
		System.out.println(decode("121")); // 3 : ABA (1 2 1) or AU (1 21) or LA (12 1)
		System.out.println(decode("226")); // 3 : BZ (2 26), VF (22 6), BBF (2 2 6)
		System.out.println(decode("06")); // 0
	}
}
