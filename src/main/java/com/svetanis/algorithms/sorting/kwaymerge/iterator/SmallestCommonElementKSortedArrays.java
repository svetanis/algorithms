package com.svetanis.algorithms.sorting.kwaymerge.iterator;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.sorting.kwaymerge.iterator.IntersectionKSortedArrays.isUnival;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/* 
Find smallest common number in list of sorted arrays.

e.g.
array1: [ 1, 3, 5, 10, 20 ]
array2: [ 2, 4, 5, 10, 20]
array3: [ 2, 4, 10, 20]

common elements => 10 & 20
result => 10

Constraints:
- Any number of arrays
- Each array could be of any size
 */

public final class SmallestCommonElementKSortedArrays {

	public static Optional<Integer> smallestCommon(List<List<Integer>> lists) {
		int n = lists.size();
		Queue<Entry<Integer>> queue = init(lists);
		while (!queue.isEmpty()) {
			if (isUnival(queue, n)) {
				return of(queue.peek().getValue());
			}
			nextSmallest(queue);
		}
		return absent();
	}

	private static <C extends Comparable<C>> Queue<Entry<C>> init(List<List<C>> lists) {
		Queue<Entry<C>> queue = new PriorityQueue<>();
		for (List<C> list : lists) {
			Iterator<C> iter = list.iterator();
			if (iter.hasNext()) {
				queue.offer(new Entry<>(iter.next(), iter));
			}
		}
		return queue;
	}

	private static <C extends Comparable<C>> C nextSmallest(Queue<Entry<C>> queue) {
		Entry<C> entry = queue.poll();
		C value = entry.getValue();
		if (entry.readNext()) {
			queue.offer(entry);
		}
		return value;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = build();
		System.out.println(smallestCommon(lists)); // 10
		List<List<Integer>> lists2 = build2();
		System.out.println(smallestCommon(lists2)); // 1

	}

	private static ImmutableList<List<Integer>> build() {
		List<List<Integer>> lists = newArrayList();
		lists.add(asList(1, 3, 5, 10, 20));
		lists.add(asList(2, 4, 5, 10, 10, 20));
		lists.add(asList(2, 4, 10, 20));
		return newList(lists);
	}

	private static ImmutableList<List<Integer>> build2() {
		List<List<Integer>> lists = newArrayList();
		lists.add(asList(1, 3, 5, 7));
		lists.add(asList(1, 1, 3, 5, 7));
		lists.add(asList(1, 4, 7, 9));
		lists.add(asList(1, 7));
		lists.add(asList(1, 2, 3, 7));
		return newList(lists);
	}
}
