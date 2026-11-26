package com.svetanis.algorithms.search.topk;

import static java.util.Comparator.comparingInt;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

// 358. Rearrange String k Distance Apart
//
// Takes a string of lowercase letters and k, 0 <= k <= length. Returns a rearrangement of its
// letters in which any two equal letters stand at least k positions apart, or "" when no such
// rearrangement exists.
//
// A max-heap of {copies left, letter}: always write the available letter with the most copies
// left. The letter just written goes into a waiting queue, carrying its new count, instead of
// back into the heap. Once the queue holds k entries the oldest has sat out k - 1 turns, so it
// can be written next turn, and goes back to the heap if it has copies left. If the heap runs
// dry while letters still wait, the result is short, which means no arrangement exists.
//
// Siblings:
//   RearrangeStringKDistApart  the same loop over any characters: a count map the comparator
//                              reads, and an Optional result instead of ""
//   RearrangeString            the k = 2 case, no two equal letters touching, where the queue
//                              is one letter long and becomes a single variable

public final class RearrangeStringKDistApartSubmit {
	// Time Complexity: O(n) -- each of the n letters written is one poll and at most one push,
	// on a heap of at most 26 entries
	// Space Complexity: O(1) for the counts, the heap and the queue, plus the n-letter result

	public static String rearrange(String s, int k) {
		int n = s.length();
		int[] counts = new int[26];
		for (char c : s.toCharArray()) {
			counts[c - 'a']++; // COUNT copies of each letter
		}
		// ORDER: most copies left first; the count travels on the entry
		PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt((int[] a) -> a[0]).reversed());
		for (int i = 0; i < 26; i++) {
			if (counts[i] > 0) {
				pq.offer(new int[] { counts[i], i }); // PUSH {copies, letter}
			}
		}
		Deque<int[]> dq = new ArrayDeque<>(); // WAIT queue: letters sitting out, oldest first
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] curr = pq.poll(); // POLL: most copies left among the letters not waiting
			int freq = curr[0];
			int index = curr[1];
			// append the current char to the result
			// and decrement its count
			char c = (char) (index + 'a');
			sb.append(c); // WRITE
			dq.offer(new int[] { freq - 1, index }); // WAIT, one copy fewer
			// insert it back the max heap
			// after k iterations
			if (dq.size() >= k) { // >= so that k = 0 releases at once too
				int[] released = dq.pollFirst(); // RELEASE the oldest: next turn is k apart
				if (released[0] > 0) {
					pq.offer(released); // PUSH it back only if it has copies left
				}
			}
		}
		String s2 = sb.toString();
		return s2.length() == n ? s2 : ""; // REPORT: short means impossible
	}

	public static void main(String[] args) {
		String s1 = "mmpp"; // mpmp or pmpm
		System.out.println(rearrange(s1, 2));
		String s3 = "aab"; // aba
		System.out.println(rearrange(s3, 2));
		String s4 = "aappa"; // "", impossible
		System.out.println(rearrange(s4, 3));
		String s5 = "aa"; // k = 0: any order
		System.out.println(rearrange(s5, 0)); // aa
	}
}