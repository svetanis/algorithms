package com.svetanis.algorithms.search.heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

// 1825. Finding MK Average

public final class MkAverage {
	// Time Complexity: add O(log n)
	// Time Complexity: mkAverage O(1)
	// Space Complexity: O(m)

	public MkAverage(int m, int k) {
		this.m = m;
		this.k = k;
		this.pq = new ArrayDeque<>();
		this.low = new TreeMap<>();
		this.high = new TreeMap<>();
		this.mid = new TreeMap<>();
	}

	private final int m;
	private final int k;
	private int total;
	private int sizeLow;
	private int sizeHigh;
	private Deque<Integer> pq;
	private TreeMap<Integer, Integer> low;
	private TreeMap<Integer, Integer> high;
	private TreeMap<Integer, Integer> mid;

	public void add(int num) {
		// decide the right bucket and add number to it
		addToBucket(num);
		// add the new number to the end of the queue
		pq.offer(num);
		// if the number of elements exceeds m, remove the oldest
		if (pq.size() > m) {
			removeOldest();
		}
		// rebalance the buckets if needed
		balance();
	}

	private void removeOldest() {
		int oldest = pq.poll();
		if (low.containsKey(oldest)) {
			remove(oldest, low);
			sizeLow--;
		} else if (high.containsKey(oldest)) {
			remove(oldest, high);
			sizeHigh--;
		} else {
			remove(oldest, mid);
			total -= oldest;
		}
	}

	private void balance() {
		// move elements from low to middle
		while (sizeLow > k) {
			int last = low.lastKey();
			remove(last, low);
			mid.put(last, mid.getOrDefault(last, 0) + 1);
			total += last;
			sizeLow--;
		}
		// move elements from high to middle
		while (sizeHigh > k) {
			int first = high.firstKey();
			remove(first, high);
			mid.put(first, mid.getOrDefault(first, 0) + 1);
			total += first;
			sizeHigh--;
		}
		// move elements from middle to low
		while (sizeLow < k && !mid.isEmpty()) {
			int first = mid.firstKey();
			remove(first, mid);
			total -= first;
			low.put(first, low.getOrDefault(first, 0) + 1);
			sizeLow++;
		}
		// move elements from middle to high
		while (sizeHigh < k && !mid.isEmpty()) {
			int last = mid.lastKey();
			remove(last, mid);
			total -= last;
			high.put(last, high.getOrDefault(last, 0) + 1);
			sizeHigh++;
		}
	}

	private void remove(int num, TreeMap<Integer, Integer> map) {
		map.put(num, map.get(num) - 1);
		if (map.get(num) == 0) {
			map.remove(num);
		}
	}

	private void addToBucket(int num) {
		if (low.isEmpty() || num <= low.lastKey()) {
			low.put(num, low.getOrDefault(num, 0) + 1);
			sizeLow++;
		} else if (high.isEmpty() || num >= high.firstKey()) {
			high.put(num, high.getOrDefault(num, 0) + 1);
			sizeHigh++;
		} else {
			mid.put(num, mid.getOrDefault(num, 0) + 1);
			total += num;
		}
	}

	public int mkAverage() {
		int size = m - 2 * k;
		if (pq.size() < m || size == 0) {
			return -1;
		}
		return new Double(total / size).intValue();
	}

	public static void main(String[] args) {
		MkAverage mka = new MkAverage(3, 1);
		mka.add(3);
		mka.add(1);
		System.out.println(mka.mkAverage()); // -1
		mka.add(10);
		System.out.println(mka.mkAverage()); // 3
		mka.add(5);
		mka.add(5);
		mka.add(5);
		System.out.println(mka.mkAverage()); // 5
	}
}