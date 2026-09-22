package com.svetanis.algorithms.search.kmostfrequent;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// 692. Top K Frequent Words

public final class TopKFrequentWordsLexSorted {
	// Time Complexity: O(n * log k)

	public static List<String> topKFrequent(List<String> terms, int k) {
		Map<String, Integer> map = frequencyMap(terms);
		// the heap's top is the survivor to throw away, so BOTH halves of the key are
		// written backwards from the way the answer reads: lowest count first, and
		// among equal counts the alphabetically LAST word first. thenComparing with
		// the natural order instead passes both of LC 692's examples and still loses
		// every tie that lands on the k boundary.
		Queue<String> pq = new PriorityQueue<>(
				comparingInt((String s) -> map.get(s)).thenComparing(reverseOrder()));

		// add-then-trim: nothing is tested against the top, so the ordering rule lives
		// in the comparator alone. Testing by hand restates it a second time, and a
		// count-only test drops the tie-break exactly at the k boundary.
		// keep k top frequent elements in the heap
		for (String entry : map.keySet()) {
			pq.offer(entry);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		// poll hands back the worst survivor first, so pushing to the front reverses
		// it into the required order: highest frequency first, ties alphabetical.
		LinkedList<String> list = new LinkedList<>();
		while (!pq.isEmpty()) {
			list.addFirst(pq.poll());
		}
		return list;
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
		printLines(topKFrequent(list1, k));

		List<String> list2 = build2();
		printLines(topKFrequent(list2, k));
	}

	private static ImmutableList<String> build2() {
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
		return copyOf(list);
	}

	private static ImmutableList<String> build1() {
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
		return copyOf(list);
	}

}
