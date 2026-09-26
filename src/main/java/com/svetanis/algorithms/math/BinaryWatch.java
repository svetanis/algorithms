package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.List;

// 401. Binary Watch

// Try every time the watch can show and keep those with the right number
// of lights on: the lights are the 1-bits of the hour and of the minute.

public final class BinaryWatch {
	// Time Complexity: O(1) -- always 12 * 60 times
	// Space Complexity: O(1) besides the answer

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
		// [0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]
		System.out.println(binaryWatch(1));
		System.out.println(binaryWatch(9)); // []
	}
}