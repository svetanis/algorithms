package com.svetanis.algorithms.string.remove;

import java.util.ArrayDeque;
import java.util.Deque;

// 1209. Remove All Adjacent Duplicates In String II

public final class RemoveAllAdjacentDuplicates1209 {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String remove(String s, int k) {
		Deque<int[]> dq = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			if (!dq.isEmpty() && dq.peek()[0] == index) {
				dq.peek()[1] = (dq.peek()[1] + 1) % k;
				if (dq.peek()[1] == 0) {
					dq.pop();
				}
			} else {
				dq.push(new int[] { index, 1 });
			}
		}
		return build(dq);
	}

	private static String build(Deque<int[]> dq) {
		StringBuilder sb = new StringBuilder();
		for (int[] element : dq) {
			char c = (char) (element[0] + 'a');
			for (int i = 0; i < element[1]; i++) {
				sb.append(c);
			}
		}
		sb.reverse();
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(remove("abcd", 2)); // abcd
		System.out.println(remove("deeedbbcccbdaa", 3)); // aa
		System.out.println(remove("pbbcggttciiippooaais", 2)); // ps
	}
}
