package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

public final class RoundRobin {

	public static ImmutableList<Integer> roundRobin(List<List<Integer>> lists) {
		// Time Complexity: O(n + k)
		Queue<Node> queue = init(lists);
		return merge(queue, lists);
	}

	private static Queue<Node> init(List<List<Integer>> lists) {
		Queue<Node> queue = new ArrayDeque<>();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).size() > 0) {
				int val = lists.get(i).get(0);
				queue.offer(new Node(val, i, 1));
			}
		}
		return queue;
	}

	private static ImmutableList<Integer> merge(Queue<Node> queue, List<List<Integer>> lists) {
		List<Integer> list = newArrayList();
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int id = node.id;
			int next = node.next;
			int size = lists.get(id).size();
			if (next < size) {
				int val = lists.get(id).get(next);
				queue.offer(new Node(val, id, next + 1));
			}
			list.add(node.val);
		}
		return newList(list);
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = build();
		print(roundRobin(lists)); // 1 2 0 6 3 4 1 7 5 6 2 8 7 8 3 9 10 4 5
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
}
