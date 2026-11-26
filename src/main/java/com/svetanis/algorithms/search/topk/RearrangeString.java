package com.svetanis.algorithms.search.topk;

import static java.util.Comparator.comparingInt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

// Rearrange a String So No Two Equal Letters Touch
//
// Takes a string. Returns a rearrangement of its letters in which no two equal letters stand
// next to each other, or empty when no such rearrangement exists.
//
// A max-heap of letters keyed on copies left: always write the letter with the most copies
// left, except the one written last. That letter waits outside the heap (prev) for one turn
// and goes back in only after the next letter has been taken out, so it can never be written
// twice in a row. If the heap runs dry while a letter still has copies, the result comes out
// shorter than the input, and that is the signal that no arrangement exists.
//
// Siblings: RearrangeStringKDistApart is the same loop with the gap as a parameter k, the
// letters written last waiting in a queue of k - 1. This file is its k = 2: the queue is one
// letter long, so a single variable, prev, holds it.

public final class RearrangeString {

	public static Optional<String> rearrange(String s) {
		// Time Complexity: O(n log d) -- each of the n letters written is one poll and at most
		// one push, on a heap of at most d different letters
		// Space Complexity: O(d) for the map and the heap, plus the n-letter result

		Map<Character, Integer> map = frequencyMap(s); // COUNT copies of each letter
		Queue<Character> pq = maxHeap(map); // PUSH every letter once
		StringBuilder sb = new StringBuilder();
		Character prev = null; // WAIT: the letter written last, held out of the heap
		while (!pq.isEmpty()) {
			Character curr = pq.poll(); // POLL: most copies left, never the one just written
			// add the prev char back to the heap
			// if its frequency greater than zero
			if (prev != null && map.get(prev) > 0) {
				pq.add(prev); // RELEASE prev only after curr is out, so curr != prev
			}
			// append the current char to the result
			// and decrement its count
			sb.append(curr); // WRITE
			map.put(curr, map.get(curr) - 1); // COUNT: changed while curr is out of the heap
			prev = curr; // WAIT: curr sits out the next turn
		}
		String s2 = sb.toString();
		return s.length() == s2.length() ? Optional.of(s2) : Optional.empty(); // REPORT: short means impossible
	}

	private static Queue<Character> maxHeap(Map<Character, Integer> map) {
		// ORDER: most copies left first. The comparator reads the map, and a letter's count
		// only changes while that letter is out of the heap, so the heap order stays true.
		Queue<Character> pq = new PriorityQueue<>(comparingInt((Character ch) -> map.get(ch)).reversed());
		for (char c : map.keySet()) {
			pq.add(c); // PUSH
		}
		return pq;
	}

	private static Map<Character, Integer> frequencyMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			int freq = map.getOrDefault(c, 0);
			map.put(c, freq + 1); // COUNT: one more copy
		}
		return map;
	}

	public static void main(String[] args) {
		String s1 = "aappp";
		System.out.println(rearrange(s1));
		String s2 = "Programming";
		System.out.println(rearrange(s2));
		String s3 = "aapa";
		System.out.println(rearrange(s3));
	}
}