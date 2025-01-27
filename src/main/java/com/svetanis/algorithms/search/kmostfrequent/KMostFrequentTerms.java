package com.svetanis.algorithms.search.kmostfrequent;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public final class KMostFrequentTerms {

	public static List<String> kMostFrequent(List<String> words, int k) {
		Map<String, Integer> map = frequencyMap(words);
		Queue<Map.Entry<String, Integer>> pq = priorityQueue(map, k);
		return kMostFrequent(pq);
	}

	private static List<String> kMostFrequent(Queue<Map.Entry<String, Integer>> pq) {
		List<String> list = new ArrayList<>();
		while (!pq.isEmpty()) {
			list.add(pq.poll().getKey());
		}
		return list;
	}

	private static Queue<Map.Entry<String, Integer>> priorityQueue(Map<String, Integer> map, int k) {
		// go through all numbers of the map and push them in the min heap
		// which will have top k frequent numbers. If the heap size is
		// more than k, remove the smallest top entry
		Comparator<Map.Entry<String, Integer>> c = (e1, e2) -> e1.getValue() - e2.getValue();
		Queue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(c);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			pq.offer(entry);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq;
	}

	private static Map<String, Integer> frequencyMap(List<String> terms) {
		Map<String, Integer> map = new HashMap<>();
		for (String term : terms) {
			int freq = map.getOrDefault(term, 0);
			map.put(term, freq + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		int k = 3;
		List<String> list1 = build1();
		System.out.println(kMostFrequent(list1, k));

		List<String> list2 = build2();
		System.out.println(kMostFrequent(list2, k));
	}

	private static List<String> build2() {
		List<String> list = newArrayList();
		list.add("Fee");
		list.add("Fi");
		list.add("Fo");
		list.add("Fum");
		list.add("Fee");
		list.add("Fo");
		list.add("Fee");
		list.add("Fee");
		list.add("Fo");
		list.add("Fi");
		list.add("Fi");
		list.add("Fo");
		list.add("Fum");
		list.add("Fee");
		return list;
	}

	private static List<String> build1() {
		List<String> list = newArrayList();
		list.add("ability");
		list.add("abortion");
		list.add("aaron");
		list.add("ab");
		list.add("absence");
		list.add("abortion");
		list.add("aa");
		list.add("a");
		list.add("able");
		list.add("aa");
		list.add("aaa");
		list.add("aberdeen");
		list.add("ab");
		list.add("abc");
		list.add("aaa");
		list.add("a");
		list.add("aaron");
		list.add("absent");
		list.add("abraham");
		list.add("able");
		return list;
	}

}
