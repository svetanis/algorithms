package com.svetanis.algorithms.sorting.kwaymerge;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.Optionals.present;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;
import static java.util.Arrays.asList;

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
		Queue<Node> queue = init(lists);
		while (!queue.isEmpty()) {
			if (isUnival(queue, lists.size())) {
				return present(queue.peek().val);
			}
			nextSmallest(queue, lists);
		}
		return absent();
	}

	public static boolean isUnival(Queue<Node> queue, int size) {
		List<Integer> list = transform(queue, node -> node.val);
		return list.size() == size && newHashSet(list).size() == 1;
	}

	private static Queue<Node> init(List<List<Integer>> lists) {
		Queue<Node> queue = new PriorityQueue<>();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).size() > 0) {
				int val = lists.get(i).get(0);
				queue.offer(new Node(val, i, 1));
			}
		}
		return queue;
	}

	private static void nextSmallest(Queue<Node> queue, List<List<Integer>> lists) {
		Node node = queue.poll();
		int id = node.id;
		int next = node.next;
		int size = lists.get(id).size();
		if (next < size) {
			int val = lists.get(id).get(next);
			queue.offer(new Node(val, id, next + 1));
		}
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
