package com.svetanis.algorithms.sorting.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.svetanis.algorithms.sorting.interval.Interval;
import com.svetanis.java.base.Pair;

// given m sorted arrays, find the smallest range
// that includes at least one number from each list

// create a min heap with m heap nodes.
// every heap node has first element of an array
// id is index of array
// next is index of next element to be stored from array

public final class SmallestNumberRange {
	// Time Complexity: O(n log k)
	// Space Complexity: O(k)

	public static Interval smallestRange(List<List<Integer>> lists) {
		Pair<Queue<Node>, Integer> pair = init(lists);
		return smallestRange(lists, pair.getLeft(), pair.getRight());
	}

	private static Pair<Queue<Node>, Integer> init(List<List<Integer>> lists) {
		int max = MIN_VALUE;
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < lists.size(); i++) {
			int val = lists.get(i).get(0);
			max = max(max, val);
			pq.offer(new Node(val, i, 1));
		}
		return Pair.build(pq, max);
	}

	private static Interval smallestRange(List<List<Integer>> lists, Queue<Node> pq, int max) {
		// one by one get the min element
		// from min heap and replace it
		// with next element of its array
		int start = 0, end = MAX_VALUE;
		while (pq.size() == lists.size()) {
			Node node = pq.poll();
			int curr = node.val;
			if(end - start > max - curr) {
				start = curr;
				end = max;
			}
			// find the next element that will
			// replace the current root of heap
			// the next element belongs to the
			// same array as the current root
			int size = lists.get(node.id).size();
			if (node.next < size) {
				int val = lists.get(node.id).get(node.next);
				max = max(max, val);
				int next = node.next + 1;
				pq.offer(new Node(val, node.id, next));
			}
		}
		return new Interval(start, end);
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = newArrayList();
		lists.add(asList(1, 5, 8));
		lists.add(asList(4, 12));
		lists.add(asList(7, 8, 10));
		System.out.println(smallestRange(lists));
	}
}