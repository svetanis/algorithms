package com.svetanis.algorithms.search.median;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public final class MedianFinder {

	private final int size;
	private int minHeapSize = 0;
	private int maxHeapSize = 0;
	private Map<Integer, Integer> map = new HashMap<>();
	private PriorityQueue<Integer> min = new PriorityQueue<>();
	private PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

	public MedianFinder(int size) {
		this.size = size;
	}

	public void add(int x) {
		if (max.isEmpty() || x <= max.peek()) {
			max.offer(x);
			maxHeapSize++;
		} else {
			min.offer(x);
			minHeapSize++;
		}
		rebalance();
	}

	public double median() {
		return (size & 1) == 1 ? max.peek() : ((double) max.peek() + min.peek()) / 2.0;
	}

	public void remove(int x) {
		map.put(x, map.getOrDefault(x, 0) + 1);
		if (x <= max.peek()) {
			maxHeapSize--;
			if (x == max.peek()) {
				pruneHeap(max);
			}
		} else {
			minHeapSize--;
			if (x == min.peek()) {
				pruneHeap(min);
			}
		}
		rebalance();
	}

	private void pruneHeap(PriorityQueue<Integer> heap) {
		while (!heap.isEmpty() && map.containsKey(heap.peek())) {
			int top = heap.peek();
			map.put(top, map.get(top) - 1);
			if (map.get(top) == 0) {
				map.remove(top);
			}
			heap.poll();
		}
	}

	private void rebalance() {
		if (maxHeapSize > minHeapSize + 1) {
			min.offer(max.poll());
			maxHeapSize--;
			minHeapSize++;
			pruneHeap(max);
		} else if (maxHeapSize < minHeapSize) {
			max.offer(min.poll());
			minHeapSize--;
			maxHeapSize++;
			pruneHeap(min);
		}
	}

	public double median2() {
		if ((size & 1) == 1) {
			return max.peek();
		} else {
			return min.peek() / 2.0 + max.peek() / 2.0;
		}
	}
}