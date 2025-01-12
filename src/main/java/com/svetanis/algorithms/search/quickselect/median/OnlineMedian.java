package com.svetanis.algorithms.search.quickselect.median;

import static com.svetanis.java.base.utils.Random.rand;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 295. Find Median from Data Stream

// Design an algorithm for computing the running median of a sequence. 

// We use two heaps, max heap, and min heap. 
// The invariant here is that for every incoming element from the stream, 
// we want to let Max Heap store the smaller half of the stream data so far, 
// and let Min Heap store the bigger half. By keeping this invariant, 
// we can output the median easily according to the number of elements 
// we have seen so far. 

public final class OnlineMedian {
	// Time Complexity: add - O(log n)
	// Time Complexity: findMedian - O(1)

	private Queue<Integer> min;
	private Queue<Integer> max;

	public OnlineMedian() {
		this.min = new PriorityQueue<>((a, b) -> a - b);
		this.max = new PriorityQueue<>((a, b) -> b - a);
	}

	public double onlineMedian(int n) {
		add(n);
		return median();
	}

	public void add(int x) {
		if (!max.isEmpty() && x > max.peek()) {
			min.add(x);
		} else {
			max.add(x);
		}
		rebalance();
	}

	public void remove(int x) {
		if (!max.isEmpty() && x <= max.peek()) {
			max.remove(x);
		} else {
			min.remove(x);
		}
		rebalance();
	}

	private void rebalance() {
		if (min.size() > max.size() + 1) {
			max.add(min.poll());
		} else if (max.size() > min.size() + 1) {
			min.add(max.poll());
		}
	}

	public double median() {
		if (max.isEmpty()) {
			return 0;
		}
		if (min.size() == max.size()) {
			return 0.5 * (min.peek() + max.peek());
		} else {
			return max.size() > min.size() ? max.peek() : min.peek();
		}
	}

	public static void main(String args[]) {
		int n = 10;
		int range = 7;
		OnlineMedian m = new OnlineMedian();
		for (int i = 0; i < n; i++) {
			int random = rand(range);
			System.out.println("randon--> " + random);
			System.out.println("median--> " + m.onlineMedian(random));
		}
		// scan();
	}

	private void scan() {
		OnlineMedian om = new OnlineMedian();
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
