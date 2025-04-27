package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 1710. Maximum Units on a Truck

public final class MaxUnitsOnTrack {

	public static int maxUnits(int[][] boxTypes, int truckSize) {
		Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
		int total = 0;
		int boxes = truckSize;
		for (int[] type : boxTypes) {
			int count = type[0];
			int units = type[1];
			int box = Math.min(boxes, count);
			total += box * units;
			boxes -= box;
			if (boxes == 0) {
				break;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[][] b1 = { { 1, 3 }, { 2, 2 }, { 3, 1 } };
		System.out.println(maxUnits(b1, 4)); // 8
		int[][] b2 = { { 5, 10 }, { 2, 5 }, { 4, 7 }, { 3, 9 } };
		System.out.println(maxUnits(b2, 10)); // 91
	}
}
