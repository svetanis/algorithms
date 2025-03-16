package com.svetanis.algorithms.search.kmostfrequent;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Maps.newMap;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableMap;

// 451. Sort Characters By Frequency

// given a string s, sort it in decreasing order
// based on the frequency of the characters

// return the sorted string. if there are 
// multiple answers, return any of them

// string consists of uppercase and lowercase
// English letters and digits

// the frequency of a character is the number
// of times it appears in the string

public final class SortCharsByFrequency {
	// Time Complexity: O(n log k)

	public static String sort(String s) {
		Map<Character, Integer> fm = frequencyMap(s);
		Queue<Character> pq = sortedQueue(fm);
		return sortedByFrequency(pq, fm);
	}

	private static ImmutableMap<Character, Integer> frequencyMap(String s) {
		Map<Character, Integer> map = newHashMap();
		for (char c : s.toCharArray()) {
			int freq = map.getOrDefault(c, 0);
			map.put(c, freq + 1);
		}
		return newMap(map);
	}

	private static Queue<Character> sortedQueue(Map<Character, Integer> fm) {
		Comparator<Character> c = (a, b) -> (fm.get(b) - fm.get(a));
		Queue<Character> pq = new PriorityQueue<>(c);
		// add all characters to the max heap
		pq.addAll(fm.keySet());
		return pq;
	}

	private static String sortedByFrequency(Queue<Character> pq, Map<Character, Integer> fm) {
		// build a string, appending the most occurring chars first
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			char c = pq.poll();
			int freq = fm.get(c);
			for (int i = 0; i < freq; i++) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s1 = "tree"; // eert
		String s2 = "cccaaa"; // aaaccc
		String s3 = "Aabb"; // bbAa

		System.out.println(sort(s1));
		System.out.println(sort(s2));
		System.out.println(sort(s3));
	}
}
