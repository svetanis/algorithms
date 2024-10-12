package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// given two input vectors v1 and v2,
// create an iterator that returns 
// their elements in a zigzag or 
// alternating fashion. the iterator
// should also be able to tell whether
// there are more elements to return,
// which involves checking both vectors
// for remaining elements

public final class ZigZagIterator {
	// Time Complexity: O(k)
	// Space Complexity: ((n1 + n2)

	private Queue<Node> queue;
	private ImmutableList<ImmutableList<Integer>> lists;

	public ZigZagIterator(List<Integer> list1, List<Integer> list2) {
		this.lists = build(list1, list2);
		this.queue = init(lists);

	}

	private ImmutableList<ImmutableList<Integer>> build(List<Integer> list1, List<Integer> list2) {
		List<ImmutableList<Integer>> lists = newArrayList();
		lists.add(newList(list1));
		lists.add(newList(list2));
		return newList(lists);
	}

	private Queue<Node> init(List<ImmutableList<Integer>> lists) {
		Queue<Node> queue = new ArrayDeque<>();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).size() > 0) {
				int val = lists.get(i).get(0);
				queue.offer(new Node(val, i, 1));
			}
		}
		return queue;
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}

	public int next() {
		Node node = queue.poll();
		int size = lists.get(node.id).size();
		if (node.next < size) {
			int val = lists.get(node.id).get(node.next);
			int next = node.next + 1;
			queue.offer(new Node(val, node.id, next));
		}
		return node.val;
	}

	public ImmutableList<Integer> merge() {
		List<Integer> list = newArrayList();
		while (hasNext()) {
			list.add(next());
		}
		return newList(list);
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(1, 2);
		List<Integer> list2 = asList(3, 4, 5, 6);
		ZigZagIterator zzi = new ZigZagIterator(list1, list2);
		System.out.println(zzi.merge());
	}
}