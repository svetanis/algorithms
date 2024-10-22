package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.algorithms.sorting.mergesort.kwaymerge.KwayMerge.merge;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

public final class RoundRobinIterator {

	public static <C extends Comparable<C>> ImmutableList<C> roundRobin(List<List<C>> lists) {
		// Time Complexity: O(n + k)
		Queue<Entry<C>> queue = init(lists);
		return merge(queue);
	}

	private static <C extends Comparable<C>> Queue<Entry<C>> init(List<List<C>> lists) {
		Queue<Entry<C>> queue = newLinkedList();
		for (List<C> list : lists) {
			Iterator<C> iter = list.iterator();
			if (iter.hasNext()) {
				queue.offer(new Entry<>(iter.next(), iter));
			}
		}
		return queue;
	}

	private static ImmutableList<List<Integer>> build() {
		List<List<Integer>> lists = newArrayList();
		lists.add(newArrayList(1, 3, 5, 7, 9));
		lists.add(newArrayList(2, 4, 6, 8, 10));
		lists.add(newArrayList());
		lists.add(newArrayList(0, 1, 2, 3, 4, 5));
		lists.add(newArrayList(6, 7, 8));
		return newList(lists);
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = build();
		print(roundRobin(lists)); // 1 2 0 6 3 4 1 7 5 6 2 8 7 8 3 9 10 4 5
	}
}
