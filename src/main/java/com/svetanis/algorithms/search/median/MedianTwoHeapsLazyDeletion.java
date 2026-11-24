package com.svetanis.algorithms.search.median;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Two heaps with lazy deletion: the median of a window of fixed size.
// The engine behind SlidingWindowMedianLazyDeletion (480. Sliding Window Median).

// Construct with the window size. add(x) puts a value in, remove(x) takes
// one copy of a value that is in the window out, median() reads the
// median of the window -- the middle value for an odd size, the average
// of the two middles for an even one. As the code assumes: median() is
// read only when the window holds exactly size values (it reads size, not
// a live count, to decide odd or even), never before the first add, and
// remove(x) is called only for a value in the window.

// max holds the smaller half, min the larger half; their tops are the two
// middles. A removed value is not dug out of its heap -- that is O(n) --
// but recorded in map as due for removal, and its half's live count drops
// by one. A due value is thrown away only when it reaches a heap's top,
// where poll() is O(log n); pruneHeap does that after every change that
// can expose one, so neither top is a due value when median() reads it.
// rebalance compares the live counts, not the heap sizes: max holds as
// many live values as min, or one more.

// Time: add, remove -- O(log n) amortised, n the values added so far: each
//   call pushes at most two values, and each due value is polled once.
// median -- O(1).
// Space: O(n) -- due values wait inside the heaps until they surface.

// Siblings:
//   OnlineMedianTwoHeapsRemove -- two heaps for a stream of any length; removes with
//     PriorityQueue.remove(Object), O(n) per remove.
//   SlidingWindowMedianLazyDeletion -- drives this class over LC 480.

public final class MedianTwoHeapsLazyDeletion {

	private final int size;
	private int minHeapSize = 0;
	private int maxHeapSize = 0;
	private Map<Integer, Integer> map = new HashMap<>();
	private PriorityQueue<Integer> min = new PriorityQueue<>();
	private PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

	public MedianTwoHeapsLazyDeletion(int size) {
		this.size = size;
	}

	public void add(int x) {
		if (max.isEmpty() || x <= max.peek()) { // ADD: to the smaller half
			max.offer(x);
			maxHeapSize++;
		} else { // ADD: to the larger half
			min.offer(x);
			minHeapSize++;
		}
		rebalance();
	}

	public double median() {
		return (size & 1) == 1 ? max.peek() : ((double) max.peek() + min.peek()) / 2.0; // READ: ODD max's top; EVEN the tops added as doubles
	}

	public void remove(int x) {
		map.put(x, map.getOrDefault(x, 0) + 1); // MARK: x is due for removal
		if (x <= max.peek()) { // FIND: x is in the smaller half
			maxHeapSize--;
			if (x == max.peek()) {
				pruneHeap(max); // PRUNE: x is on top, drop it now
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
		while (!heap.isEmpty() && map.containsKey(heap.peek())) { // PRUNE: a due value is on top
			int top = heap.peek();
			map.put(top, map.get(top) - 1);
			if (map.get(top) == 0) {
				map.remove(top);
			}
			heap.poll();
		}
	}

	private void rebalance() {
		if (maxHeapSize > minHeapSize + 1) { // BALANCE: max has two more live values
			min.offer(max.poll());
			maxHeapSize--;
			minHeapSize++;
			pruneHeap(max); // PRUNE: max's new top may be due
		} else if (maxHeapSize < minHeapSize) { // BALANCE: min has more live values
			max.offer(min.poll());
			minHeapSize--;
			maxHeapSize++;
			pruneHeap(min);
		}
	}

	public double median2() {
		if ((size & 1) == 1) {
			return max.peek(); // ODD: max's top
		} else {
			return min.peek() / 2.0 + max.peek() / 2.0; // EVEN: halved first, so no int sum
		}
	}
}