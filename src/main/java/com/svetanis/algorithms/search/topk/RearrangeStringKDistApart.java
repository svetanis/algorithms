package com.svetanis.algorithms.search.topk;

import static java.util.Comparator.comparingInt;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

// Rearrange a String So Equal Letters Are at Least k Apart
//
// Takes a string and k >= 0. Returns a rearrangement of its letters in which any two equal
// letters stand at least k positions apart (their indices differ by k or more), or empty when
// no such rearrangement exists. k = 0 and k = 1 allow any order.
//
// A max-heap of letters keyed on copies left: always write the available letter with the most
// copies left. A letter just written must sit out the next k - 1 turns, so it goes into a
// waiting queue instead of back into the heap. Once the queue holds k letters, the oldest one
// has sat out long enough to be written next turn, and is released to the heap if it still
// has copies. If the heap runs dry while letters are still waiting, the result comes out
// shorter than the input, and that is the signal that no arrangement exists.
//
// Siblings:
//   RearrangeStringKDistApartSubmit  the same loop as the LC 358 submission: lowercase letters
//                                    only, counts in an int[26], "" for impossible
//   RearrangeString                  this loop at k = 2 (no two equal letters touch), where the
//                                    queue is one letter long and becomes a single variable

public final class RearrangeStringKDistApart {

	public static Optional<String> rearrange(String s, int k) {
		// Time Complexity: O(n log d) -- each of the n letters written is one poll and at most
		// one push, on a heap of at most d different letters
		// Space Complexity: O(d) for the map, the heap and the queue, plus the n-letter result

		Map<Character, Integer> map = frequencyMap(s); // COUNT copies of each letter
		Queue<Character> pq = maxHeap(map); // PUSH every letter once
		Queue<Character> queue = new ArrayDeque<>(); // WAIT queue: letters sitting out, oldest first
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Character curr = pq.poll(); // POLL: most copies left among the letters not waiting
			// append the current char to the result
			// and decrement its count
			sb.append(curr); // WRITE
			map.put(curr, map.get(curr) - 1); // COUNT: changed while curr is out of the heap
			// save current to the queue
			queue.add(curr); // WAIT: curr sits out the next k - 1 turns
			// insert it back the max heap
			// after k iterations
			if (queue.size() >= k) { // >= so that k = 0 releases at once too
				Character c = queue.poll(); // RELEASE the oldest: written k - 1 turns ago, so next turn is k apart
				if (map.get(c) > 0) {
					pq.add(c); // PUSH it back only if it has copies left
				}
			}
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
		String s1 = "mmpp"; // mpmp or pmpm
		System.out.println(rearrange(s1, 2));
		String s2 = "Programming"; // rgmPrgmiano
		System.out.println(rearrange(s2, 3));
		String s3 = "aab"; // aba
		System.out.println(rearrange(s3, 2));
		String s4 = "aappa"; // empty
		System.out.println(rearrange(s4, 3));
		String s5 = "aa"; // aa
		System.out.println(rearrange(s5, 0));
	}
}