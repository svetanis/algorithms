package com.svetanis.algorithms.search.binary;

import java.util.TreeMap;

// 732. My Calendar III

public final class MyCalendarIII {

	public MyCalendarIII() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Integer> map;

	public int book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);
		int active = 0;
		int max = 0;
		for (int count : map.values()) {
			active += count;
			max = Math.max(max, active);
		}
		return max;
	}

	public static void main(String[] args) {
		MyCalendarIII mc = new MyCalendarIII();
		System.out.println(mc.book(10, 20)); // 1
		System.out.println(mc.book(50, 60)); // 1
		System.out.println(mc.book(10, 40)); // 2
		System.out.println(mc.book(5, 15)); // 3
		System.out.println(mc.book(5, 10)); // 3
		System.out.println(mc.book(25, 55)); // 3
	}
}
