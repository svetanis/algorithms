package com.svetanis.algorithms.search.topk;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Optional;

// given a string, find if its letters can be rearranged
// in such a way that no two same characters come next
// to each other

public final class RearrangeString {

	public static Optional<String> rearrange(String s) {
		// Time Complexity: O(n * log n)
		// Space Complexity: O(n)

		Map<Character, Integer> map = frequencyMap(s);
		Queue<Character> pq = maxHeap(map);
		StringBuilder sb = new StringBuilder();
		Character prev = null;
		while (!pq.isEmpty()) {
			Character curr = pq.poll();
			// add the prev char back to the heap
			// if its frequency greater than zero
			if (prev != null && map.get(prev) > 0) {
				pq.add(prev);
			}
			// append the current char to the result
			// and decrement its count
			sb.append(curr);
			map.put(curr, map.get(curr) - 1);
			prev = curr;
		}
		String s2 = sb.toString();
		return s.length() == s2.length() ? of(s2) : absent();
	}

	private static Queue<Character> maxHeap(Map<Character, Integer> map) {
		Queue<Character> pq = new PriorityQueue<>((x, y) -> map.get(y) - map.get(x));
		for (char c : map.keySet()) {
			pq.add(c);
		}
		return pq;
	}

	private static Map<Character, Integer> frequencyMap(String s) {
		Map<Character, Integer> map = newHashMap();
		for (char c : s.toCharArray()) {
			int freq = map.getOrDefault(c, 0);
			map.put(c, freq + 1);
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