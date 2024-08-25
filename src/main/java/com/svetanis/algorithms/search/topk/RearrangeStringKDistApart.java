package com.svetanis.algorithms.search.topk;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Optional;

// given a string and a number k, 
// find if the string can be rearranged 
// such that the same characters are 
// at least k distance apart from each other

public final class RearrangeStringKDistApart {

	public static Optional<String> rearrange(String s, int k) {
		// Time Complexity: O(n * log n)
		// Space Complexity: O(n)

		Map<Character, Integer> map = frequencyMap(s);
		Queue<Character> pq = maxHeap(map);
		Queue<Character> queue = newLinkedList();
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Character curr = pq.poll();
			// append the current char to the result
			// and decrement its count
			sb.append(curr);
			map.put(curr, map.get(curr) - 1);
			// save current to the queue
			queue.add(curr);
			// insert it back the max heap
			// after k iterations
			if (queue.size() == k) {
				Character c = queue.poll();
				if (map.get(c) > 0) {
					pq.add(c);
				}
			}
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
		String s1 = "mmpp"; // mpmp or pmpm
		System.out.println(rearrange(s1, 2));
		String s2 = "Programming"; // rgmPrgmiano
		System.out.println(rearrange(s2, 3));
		String s3 = "aab"; // aba
		System.out.println(rearrange(s3, 2));
		String s4 = "aappa"; // absent
		System.out.println(rearrange(s4, 3));
	}
}