package com.svetanis.algorithms.search.binary;

import java.util.Map;
import java.util.TreeMap;

// 729. My Calendar I

public final class MyCalendar {
	// Time Complexity: O(log n)
	// Space Complexity: O(n)

	public MyCalendar() {
		this.map = new TreeMap<>();
	}

	private TreeMap<Integer, Integer> map;

	public boolean book(int start, int end) {
		// overlap with previous interval
		Map.Entry<Integer, Integer> floor = map.floorEntry(start);
		if (floor != null && floor.getValue() > start) {
			return false;
		}
		// overlap with next interval
		Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(start);
		if (ceiling != null && ceiling.getKey() < end) {
			return false;
		}
		map.put(start, end);
		return true;
	}

	public static void main(String[] args) {
		MyCalendar mc = new MyCalendar();
		System.out.println(mc.book(10, 20)); // true
		System.out.println(mc.book(15, 25)); // false
		System.out.println(mc.book(20, 30)); // true

		MyCalendar mc1 = new MyCalendar();
		System.out.println(mc1.book(47, 50)); // true
		System.out.println(mc1.book(33, 41)); // true
		System.out.println(mc1.book(39, 45)); // false
		System.out.println(mc1.book(33, 42)); // false
		System.out.println(mc1.book(25, 32)); // true
		System.out.println(mc1.book(26, 35)); // false
		System.out.println(mc1.book(19, 25)); // true
		System.out.println(mc1.book(3, 8)); // true
		System.out.println(mc1.book(8, 13)); // true
		System.out.println(mc1.book(18, 27)); // false
	}
}
