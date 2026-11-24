package com.svetanis.algorithms.search.median;

import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

// 295. Find Median from Data Stream

// Numbers arrive one at a time; after each, report the median of all of
// them so far -- the middle value for an odd count, the average of the
// two middle values for an even one. add(x) takes a number, median()
// reads, onlineMedian(x) does both. remove(x) takes out one copy of a
// value that was added, so that SlidingWindowMedianHeapRemove can slide a window
// with it; removing a value that was never added is not detected.
// median() on an empty structure throws.

// Two heaps split the numbers by value: max holds the smaller half and
// its top is their largest; min holds the larger half and its top is
// their smallest. Two statements make the two tops the middles.
// ORDER: everything in max is <= everything in min. SIZE: the two sizes
// differ by at most one. add() keeps ORDER by comparing with max's top.
// remove() can break it -- it can empty max while min still holds
// values -- so rebalance() first swaps the two tops until ORDER holds,
// then moves one value across for SIZE.

// Time: add -- O(log n); remove -- O(n), PriorityQueue.remove(Object)
//   scans the heap; median -- O(1).
// Space: O(n) -- every number is kept.

// Siblings:
//   OnlineMedianTwoHeaps -- the LC 295 submission: add and median only, no remove.
//   MedianTwoHeapsLazyDeletion -- the same two heaps with lazy deletion, for a window of
//     fixed size: O(log n) per remove.
//   SlidingWindowMedianHeapRemove -- drives this class with add and remove (LC 480).

public final class OnlineMedianTwoHeapsRemove {

	private static final Random RANDOM = new Random();

	private Queue<Integer> min;
	private Queue<Integer> max;

	public OnlineMedianTwoHeapsRemove() {
		this.min = new PriorityQueue<>(Integer::compare);
		this.max = new PriorityQueue<>(reverseOrder());
	}

	public double onlineMedian(int n) {
		add(n);
		return median();
	}

	public void add(int x) {
		if (max.isEmpty() || x <= max.peek()) { // ADD: an empty max is no information, not a home
			max.add(x);
		} else {
			min.add(x);
		}
		rebalance();
	}

	public void remove(int x) {
		// ask each heap to drop the value rather than deciding by
		// comparison: after a remove, max may be empty while min is not,
		// and then no comparison against max.peek() is available
		if (!max.remove(x)) { // REMOVE: from whichever heap holds it, O(n)
			min.remove(x);
		}
		rebalance();
	}

	private void rebalance() {
		// 1. ordering: every value in max must be <= every value in min.
		// add() alone cannot break this, remove() can, so it is restored
		// here by swapping the two boundary values
		while (!max.isEmpty() && !min.isEmpty() && max.peek() > min.peek()) { // ORDER: swap the two tops
			int larger = max.poll();
			int smaller = min.poll();
			max.add(smaller);
			min.add(larger);
		}
		// 2. balance: the two sizes differ by at most one
		if (min.size() > max.size() + 1) { // BALANCE: move one value across
			max.add(min.poll());
		} else if (max.size() > min.size() + 1) {
			min.add(max.poll());
		}
	}

	public double median() {
		if (min.isEmpty() && max.isEmpty()) {
			throw new IllegalStateException("no values added");
		}
		if (min.size() == max.size()) {
			return 0.5 * ((double) min.peek() + max.peek()); // EVEN: added as doubles, so no int overflow
		} else {
			return max.size() > min.size() ? max.peek() : min.peek(); // ODD: the bigger heap's top
		}
	}

	public static void main(String args[]) {
		int n = 10;
		int range = 7;
		OnlineMedianTwoHeapsRemove m = new OnlineMedianTwoHeapsRemove();
		for (int i = 0; i < n; i++) {
			int random = RANDOM.nextInt(range + 1);
			System.out.println("random--> " + random);
			System.out.println("median--> " + m.onlineMedian(random));
		}
		// scan();
	}

	private void scan() {
		OnlineMedianTwoHeapsRemove om = new OnlineMedianTwoHeapsRemove();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int num = in.nextInt();
			om.add(num);
			System.out.println(om.median());
		}
		in.close();
	}

	// 5, 15, 1, 3 ...
	// 5 -> 5
	// 5, 15 -> 10
	// 5, 15, 1 -> 5
	// 5, 15, 1, 3 -> 4
}
