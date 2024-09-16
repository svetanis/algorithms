package com.svetanis.algorithms.recursive;

import static java.lang.Integer.parseInt;

import java.util.ArrayDeque;
import java.util.Deque;

// given a string number consisting of digits
// repeatedly apply operation: while it has
// consecutive equal digits while the same 
// digit repeats replace all ranges of consecutive
// equal digits with their sum in the string
// repeat on the previous result until no 
// consecutive digits

public final class ReplaceConsecutiveDigits {

	public static String single(String s) {
		if (noConsecutive(s)) {
			return s;
		}
		Deque<Integer> dq = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			dq.add(parseInt(c + ""));
		}
		int left = 0;
		StringBuilder sb = new StringBuilder();
		while (left < s.length()) {
			int sum = 0;
			int count = 0;
			int front = parseInt(s.charAt(left) + "");
			while (!dq.isEmpty() && front == dq.peek()) {
				sum += dq.poll();
				count++;
				left++;
			}
			if (count > 0) {
				sb.append(sum);
			} else {
				sb.append(dq.poll());
				left++;
			}
		}
		return single(sb.toString());
	}

	private static boolean noConsecutive(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// 1883199,1163118,26328
		System.out.println(single("66644319333")); // 26328
		// 08166,08112,0822,084
		System.out.println(single("0044886")); // 084
		System.out.println(single("999433")); // 2746
		System.out.println(single("429201")); // 429201
	}
}
