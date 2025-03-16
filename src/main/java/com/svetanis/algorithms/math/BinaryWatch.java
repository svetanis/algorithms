package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.List;

// 401. Binary Watch

public final class BinaryWatch {

	public static List<String> binaryWatch(int turnedOn) {
		List<String> list = new ArrayList<>();
		for (int hour = 0; hour < 12; hour++) {
			for (int min = 0; min < 60; min++) {
				int hbc = Integer.bitCount(hour);
				int mbc = Integer.bitCount(min);
				if (hbc + mbc == turnedOn) {
					list.add(String.format("%d:%02d", hour, min));
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(binaryWatch(1));
		System.out.println(binaryWatch(9));
	}
}