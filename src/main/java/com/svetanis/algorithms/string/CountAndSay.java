package com.svetanis.algorithms.string;

// 38. Count and Say

public final class CountAndSay {
	// Time Complexity: O(n)

	public static String countAndSay(int n) {
		String curr = "1";
		while(--n > 0) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < curr.length();) {
				int count = 0;
				char c = curr.charAt(i);
				while(i < curr.length() && curr.charAt(i) == c) {
					i++;
					count++;
				}
				sb.append(count).append(c);
			}
			curr = sb.toString();
		}
		return curr;
	}

	public static void main(String[] args) {
		System.out.println(countAndSay(4)); // 1211
		System.out.println(countAndSay(1)); // 1
	}
}
