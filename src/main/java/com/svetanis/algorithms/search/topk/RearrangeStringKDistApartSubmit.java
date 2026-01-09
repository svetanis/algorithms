package com.svetanis.algorithms.search.topk;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

// 358. Rearrange String k Distance Apart

public final class RearrangeStringKDistApartSubmit {
	// Time Complexity: O(n * log n)
	// Space Complexity: O(n)

	public static String rearrange(String s, int k) {
		int n = s.length();
		int[] counts = new int[26];
		for (char c : s.toCharArray()) {
			counts[c - 'a']++;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		for (int i = 0; i < 26; i++) {
			if (counts[i] > 0) {
				pq.offer(new int[] { counts[i], i });
			}
		}
		Deque<int[]> dq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int freq = curr[0];
			int index = curr[1];
			// append the current char to the result
			// and decrement its count
			char c = (char) (index + 'a');
			sb.append(c);
			dq.offer(new int[] { freq - 1, index });
			// insert it back the max heap
			// after k iterations
			if (dq.size() >= k) {
				int[] released = dq.pollFirst();
				if (released[0] > 0) {
					pq.offer(released);
				}
			}
		}
		String s2 = sb.toString();
		return s2.length() == n ? s2 : "";
	}

	public static void main(String[] args) {
		String s1 = "mmpp"; // mpmp or pmpm
		System.out.println(rearrange(s1, 2));
		String s3 = "aab"; // aba
		System.out.println(rearrange(s3, 2));
		String s4 = "aappa"; // absent
		System.out.println(rearrange(s4, 3));
		String s5 = "aa"; // absent
		System.out.println(rearrange(s5, 0)); // aa
	}
}