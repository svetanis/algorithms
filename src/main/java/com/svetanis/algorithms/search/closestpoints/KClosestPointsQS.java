package com.svetanis.algorithms.search.closestpoints;

import static com.svetanis.java.base.utils.Print.print;

import java.util.Arrays;
import java.util.Random;

// 973. K Closest Points to Origin

public final class KClosestPointsQS {
	// Time complexity: O(n)

	private static Random rand = new Random();

	public static int[][] kClosest(int[][] points, int k) {
		quickSelect(points, 0, points.length - 1, k);
		return Arrays.copyOfRange(points, 0, k);
	}

	private static void quickSelect(int[][] points, int left, int right, int k) {
		if (left >= right) {
			return;
		}
		int pivotIndex = left + rand.nextInt(right - left);
		int pivot = partition(points, left, right, pivotIndex);
		if (k < pivot) {
			quickSelect(points, left, pivot - 1, k);
		} else if (k > pivot) {
			quickSelect(points, pivot + 1, right, k);
		}
	}

	private static int partition(int[][] points, int left, int right, int index) {
		int dist = dist(points[index]);
		swap(points, index, right);
		int pivot = left;
		for (int i = left; i < right; i++) {
			if (dist(points[i]) < dist) {
				swap(points, pivot, i);
				pivot++;
			}
		}
		swap(points, pivot, right);
		return pivot;
	}

	private static void swap(int[][] points, int i, int j) {
		int[] temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}

	private static int dist(int[] p) {
		return p[0] * p[0] + p[1] * p[1];
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 0 }, { 2, 1 }, { 0, 1 } };
		print(kClosest(points1, 2)); // [x=0, y=1], [x=1, y=0]

		int[][] points2 = { { 1, 3 }, { 3, 4 }, { 2, -1 } };
		print(kClosest(points2, 2)); // [x=2, y=-1], [x=1, y=3]

		int[][] points3 = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
		print(kClosest(points3, 1)); // [x=1, y=1]

		int[][] points4 = { { 1, 3 }, { -2, 2 } };
		print(kClosest(points4, 1)); // [x=-2, y=2]

		int[][] points5 = { { 4, 4 }, { 2, 4 }, { 8, 1 }, { 3, -5 } };
		print(kClosest(points5, 2)); // [x=2, y=4], [4, 4]
	}
}
