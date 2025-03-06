package com.svetanis.algorithms.sorting.kwaymerge;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Nums.isEven;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.svetanis.algorithms.search.median.OnlineMedian;

// given m sorted arrays, find the
// median number among all arrays

// if there are N total numbers in
// all the arrays we need to find
// the K'th min number where K = N/2

public final class MedianInMSortedArrays {

	public static int median(List<List<Integer>> lists) {
		int n = 0;
		Queue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).size() > 0) {
				n += lists.get(i).size();
				int val = lists.get(i).get(0);
				pq.offer(new Node(val, i, 1));
			}
		}
		if (isEven(n)) {
			return onlineMedian(lists, pq);
		}
		int medianIndex = n / 2;
		// this works when the total number of elements is odd
		return kSmallest(lists, pq, medianIndex + 1);
	}

	private static int onlineMedian(List<List<Integer>> lists, Queue<Node> pq) {
		// TimeComplexity: O(n)

		// one by one get the min element
		// from min heap and replace it
		// with next element of its array
		double median = 0.0;
		OnlineMedian om = new OnlineMedian();
		while (!pq.isEmpty()) {
			// get min element and store it in out
			Node node = pq.poll();
			int id = node.id;
			int next = node.next;
			om.add(node.val);
			median = om.median();
			// find the next element that will
			// replace the current root of heap
			// the next element belongs to the
			// same array as the current root
			int size = lists.get(id).size();
			if (next < size) {
				int val = lists.get(id).get(next);
				pq.offer(new Node(val, id, next + 1));
			}
		}
		return new Double(median).intValue();
	}

	private static int kSmallest(List<List<Integer>> lists, Queue<Node> pq, int k) {
		// Time Complexity: O(k log m)

		// one by one get the min element
		// from min heap and replace it
		// with next element of its array
		int current = 0, count = 0;
		while (!pq.isEmpty() && count < k) {
			// get min element and store it in out
			Node node = pq.poll();
			int id = node.id;
			int next = node.next;
			current = node.val;
			count++;
			// find the next element that will
			// replace the current root of heap
			// the next element belongs to the
			// same array as the current root
			int size = lists.get(id).size();
			if (next < size) {
				int val = lists.get(id).get(next);
				pq.offer(new Node(val, id, next + 1));
			}
		}
		return current;
	}

	public static void main(String[] args) {
		// 1, 2, 3, 3, 4, 6, 6, 7, 8
		// median == 4
		List<List<Integer>> lists = newArrayList();
		lists.add(asList(2, 6, 8));
		lists.add(asList(3, 6, 7));
		lists.add(asList(1, 3, 4));
		System.out.println(median(lists));

		// 1, 2, 3, 3, 4, 6, 6, 7
		// median == (3 + 4)/2 = 3
		List<List<Integer>> lists2 = newArrayList();
		lists2.add(asList(2, 6));
		lists2.add(asList(3, 6, 7));
		lists2.add(asList(1, 3, 4));
		System.out.println(median(lists2));
	}
}