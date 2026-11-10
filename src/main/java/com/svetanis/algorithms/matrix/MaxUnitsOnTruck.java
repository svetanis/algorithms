package com.svetanis.algorithms.matrix;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

// 1710. Maximum Units on a Truck
//
// Input: boxTypes[i] = [numberOfBoxes, numberOfUnitsPerBox] -- how many boxes of type i there are,
// and how many units each of those boxes holds; truckSize -- the most boxes the truck can carry.
// Returns the most units the truck can carry. On LeetCode: 1 <= boxTypes.length <= 1000,
// 1 <= numberOfBoxes, numberOfUnitsPerBox <= 1000, 1 <= truckSize <= 10^6.
//
// The one idea: one box takes one place on the truck whatever its type, so the only thing that
// tells two boxes apart is the units inside. Every place should get the fullest box still left:
// sort the types by units per box, most first, and load whole types until the truck is full,
// taking part of the last type if it does not fit. The choice never needs undoing: every box left
// behind holds no more units than any box loaded, so swapping one in can only lower the total.
// The method sorts the caller's boxTypes in place.
//
// Time: O(k log k), k = boxTypes.length -- the sort; the loading loop is O(k).
// Space: O(k) -- Arrays.sort on an array of objects is a merge sort that may allocate up to k / 2
//        references of scratch space.

public final class MaxUnitsOnTruck {

	public static int maxUnits(int[][] boxTypes, int truckSize) {
		Arrays.sort(boxTypes, comparingInt((int[] a) -> a[1]).reversed()); // SORT: most units per box first
		int total = 0;
		int boxes = truckSize; // ROOM: places left on the truck
		for (int[] type : boxTypes) { // WALK: fullest type first
			int count = type[0];
			int units = type[1];
			int box = Math.min(boxes, count); // TAKE: the whole type, or as many as still fit
			total += box * units;
			boxes -= box;
			if (boxes == 0) { // STOP: the truck is full
				break;
			}
		}
		return total; // RETURN: at most 10^6 boxes x 1000 units = 10^9, fits an int
	}

	public static void main(String[] args) {
		int[][] b1 = { { 1, 3 }, { 2, 2 }, { 3, 1 } };
		System.out.println(maxUnits(b1, 4)); // 8
		int[][] b2 = { { 5, 10 }, { 2, 5 }, { 4, 7 }, { 3, 9 } };
		System.out.println(maxUnits(b2, 10)); // 91
	}
}
