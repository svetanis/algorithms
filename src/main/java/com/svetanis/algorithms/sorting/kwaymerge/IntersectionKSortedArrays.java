package com.svetanis.algorithms.sorting.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

/* 
Find common numbers in list of sorted arrays.

e.g.
array1: [ 1, 3, 5, 10, 20 ]
array2: [ 2, 4, 5, 10, 20]
array3: [ 2, 4, 10, 20]

common elements => 10 & 20

Constraints:
- Any number of arrays
- Each array could be of any size
 */

public final class IntersectionKSortedArrays {

	public static ImmutableList<Integer> intersection(List<List<Integer>> lists) {
		int n = lists.size();
		Queue<Node> queue = init(lists);
		List<Integer> list = newArrayList();
		while (!queue.isEmpty()) {
			if (isUnival(queue, n)) {
				list.add(queue.peek().val);
				readNext(queue, lists);
			} else {
				nextSmallest(queue, lists);
			}
		}
		return newList(list);
	}

	private static void readNext(Queue<Node> queue, List<List<Integer>> lists) {
		int size = queue.size();
		while (size-- > 0) {
			nextSmallest(queue, lists);
		}
	}

	private static void nextSmallest(Queue<Node> queue, List<List<Integer>> lists) {
		Node node = queue.poll();
		int id = node.id;
		int size = lists.get(id).size();
		int next = node.next;
		if (next < size) {
			int value = lists.get(id).get(next);
			queue.offer(new Node(value, id, next + 1));
		}
	}

	private static Queue<Node> init(List<List<Integer>> lists) {
		Queue<Node> queue = new PriorityQueue<>();
		for (int i = 0; i < lists.size(); i++) {
			int size = lists.get(i).size();
			if (size > 0) {
				int val = lists.get(i).get(0);
				queue.offer(new Node(val, i, 1));
			}
		}
		return queue;
	}

	public static boolean isUnival(Queue<Node> queue, int size) {
		List<Integer> list = transform(queue, node -> node.val);
		return list.size() == size && newHashSet(list).size() == 1;
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = build();
		System.out.println(intersection(lists)); // 10, 20
		List<List<Integer>> lists2 = build2();
		System.out.println(intersection(lists2)); // 1, 7

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
