package com.svetanis.algorithms.sorting.kwaymerge;

import static java.util.Arrays.asList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 281. Zigzag Iterator

public final class ZigZagIteratorSubmit {
	// Time Complexity: O(k)
	// Space Complexity: ((n1 + n2)

	private Queue<int[]> queue;
	private List<List<Integer>> lists;

	public ZigZagIteratorSubmit(List<Integer> list1, List<Integer> list2) {
		this.lists = build(list1, list2);
		this.queue = init(lists);
	}

	private List<List<Integer>> build(List<Integer> list1, List<Integer> list2) {
		List<List<Integer>> lists = new ArrayList<>();
		lists.add(list1);
		lists.add(list2);
		return lists;
	}

	private Queue<int[]> init(List<List<Integer>> lists) {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).size() > 0) {
				int val = lists.get(i).get(0);
				queue.offer(new int[] { val, i, 1 });
			}
		}
		return queue;
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}

	public int next() {
		int[] node = queue.poll();
		int id = node[1];
		int next = node[2];
		int size = lists.get(id).size();
		if (next < size) {
			int val = lists.get(id).get(next);
			queue.offer(new int[] { val, id, next + 1 });
		}
		return node[0];
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(1, 2);
		List<Integer> list2 = asList(3, 4, 5, 6);
		ZigZagIteratorSubmit zzi = new ZigZagIteratorSubmit(list1, list2);
	}
}