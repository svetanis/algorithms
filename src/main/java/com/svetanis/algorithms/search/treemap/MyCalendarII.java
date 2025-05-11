package com.svetanis.algorithms.search.treemap;

import java.util.TreeMap;

// 731. My Calendar II

public final class MyCalendarII {

	public MyCalendarII() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Integer> map;

	public boolean book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);
		int active = 0;
		for (int count : map.values()) {
			active += count;
			if (active > 2) {
				map.put(start, map.get(start) - 1);
				map.put(end, map.get(end) + 1);
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		MyCalendarII mc = new MyCalendarII();
		System.out.println(mc.book(10, 20)); // true
		System.out.println(mc.book(50, 60)); // true
		System.out.println(mc.book(10, 40)); // true
		System.out.println(mc.book(5, 15)); // false
		System.out.println(mc.book(5, 10)); // true
		System.out.println(mc.book(25, 55)); // true
	}
}
