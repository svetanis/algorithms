package com.svetanis.algorithms.search.heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

// 1825. Finding MK Average
//
// A stream of integers, and two integers m and k. add(num) appends num to the stream. mkAverage()
// returns -1 while the stream has fewer than m numbers; otherwise it takes the last m numbers,
// drops the k smallest and the k largest, and returns the average of the rest rounded down.
// 3 <= m <= 10^5, 1 <= 2k < m, 1 <= num <= 10^5, at most 10^5 calls.
//
// Keep the last m numbers in a queue, and split them across three counted sorted maps (value ->
// how many): low holds the k smallest, high the k largest, mid everything between, with the sum of
// mid kept as total. Every add places the number by comparing it with the boundaries, the oldest
// number leaves from whichever map holds its value (equal values are interchangeable), and balance
// moves boundary values until low and high hold exactly k again. mkAverage is then total / (m - 2k).
// total is a long: mid can hold m - 2k numbers of 10^5 each, about 10^10, past the int range.
//
// Time: add O(log m) -- a constant number of moves between the maps per add; mkAverage O(1)
// Space: O(m) for the queue and the maps

public final class MkAverage {

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
	private long total;                   // SUM: of mid, a long -- it can reach about 10^10
	private int sizeLow;
	private int sizeHigh;
	private Deque<Integer> pq;            // WINDOW: the last m numbers, oldest first
	private TreeMap<Integer, Integer> low;
	private TreeMap<Integer, Integer> high;
	private TreeMap<Integer, Integer> mid;

	public void add(int num) {
		// PLACE: decide the right bucket and add the number to it
		addToBucket(num);
		// PUSH: add the new number to the end of the queue
		pq.offer(num);
		// EVICT: if the number of elements exceeds m, remove the oldest
		if (pq.size() > m) {
			removeOldest();
		}
		// BALANCE: bring low and high back to k numbers each
		balance();
	}

	private void removeOldest() {
		int oldest = pq.poll();               // POLL: the oldest number leaves the window
		if (low.containsKey(oldest)) {
			remove(oldest, low);              // EVICT: from low; any copy of an equal value will do
			sizeLow--;
		} else if (high.containsKey(oldest)) {
			remove(oldest, high);             // EVICT: from high
			sizeHigh--;
		} else {
			remove(oldest, mid);              // EVICT: from mid, and from its sum
			total -= oldest;
		}
	}

	private void balance() {
		// SPILL: low's largest moves to mid
		while (sizeLow > k) {
			int last = low.lastKey();
			remove(last, low);
			mid.put(last, mid.getOrDefault(last, 0) + 1);
			total += last;
			sizeLow--;
		}
		// SPILL: high's smallest moves to mid
		while (sizeHigh > k) {
			int first = high.firstKey();
			remove(first, high);
			mid.put(first, mid.getOrDefault(first, 0) + 1);
			total += first;
			sizeHigh--;
		}
		// FILL: mid's smallest moves to low
		while (sizeLow < k && !mid.isEmpty()) {
			int first = mid.firstKey();
			remove(first, mid);
			total -= first;
			low.put(first, low.getOrDefault(first, 0) + 1);
			sizeLow++;
		}
		// FILL: mid's largest moves to high
		while (sizeHigh < k && !mid.isEmpty()) {
			int last = mid.lastKey();
			remove(last, mid);
			total -= last;
			high.put(last, high.getOrDefault(last, 0) + 1);
			sizeHigh++;
		}
	}

	private void remove(int num, TreeMap<Integer, Integer> map) {
		map.put(num, map.get(num) - 1);       // COUNT: one copy fewer
		if (map.get(num) == 0) {
			map.remove(num);                  // DROP: no copy left, so the key goes
		}
	}

	private void addToBucket(int num) {
		if (low.isEmpty() || num <= low.lastKey()) {
			low.put(num, low.getOrDefault(num, 0) + 1);     // LOW: no larger than low's largest
			sizeLow++;
		} else if (high.isEmpty() || num >= high.firstKey()) {
			high.put(num, high.getOrDefault(num, 0) + 1);   // HIGH: no smaller than high's smallest
			sizeHigh++;
		} else {
			mid.put(num, mid.getOrDefault(num, 0) + 1);     // MID: strictly between, and into the sum
			total += num;
		}
	}

	public int mkAverage() {
		int size = m - 2 * k;
		if (pq.size() < m || size == 0) {
			return -1;                        // SHORT: fewer than m numbers so far
		}
		return (int) (total / size);          // REPORT: long division rounds down, as asked
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

		// at the limits: mid holds 99,998 numbers of 100,000, a sum near 10^10
		MkAverage big = new MkAverage(100_000, 1);
		for (int i = 0; i < 100_000; i++) {
			big.add(100_000);
		}
		System.out.println(big.mkAverage()); // 100000
	}
}