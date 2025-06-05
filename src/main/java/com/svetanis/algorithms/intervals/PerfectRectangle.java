package com.svetanis.algorithms.intervals;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 391. Perfect Rectangle 

public final class PerfectRectangle {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	private long totalArea;
	private Map<Point, Integer> counts = new HashMap<>();

	public boolean rectangleCover(int[][] rectangles) {
		init(rectangles);
		// bounding rectangle area
		long rectArea = (long) (maxX - minX) * (maxY - minY);
		Point botLeft = new Point(minX, minY);
		Point topLeft = new Point(minX, maxY);
		Point botRight = new Point(maxX, minY);
		Point topRight = new Point(maxX, maxY);
		boolean area = totalArea != rectArea;
		boolean blc = counts.getOrDefault(botLeft, 0) != 1;
		boolean tlc = counts.getOrDefault(topLeft, 0) != 1;
		boolean brc = counts.getOrDefault(botRight, 0) != 1;
		boolean trc = counts.getOrDefault(topRight, 0) != 1;
		if (area || blc || tlc || brc || trc) {
			return false;
		}
		// exclude outer corners of bounding rectangle
		counts.remove(topLeft);
		counts.remove(botLeft);
		counts.remove(botRight);
		counts.remove(topRight);
		// validate count of inner corners
		return counts.values().stream().allMatch(c -> c == 2 || c == 4);
	}

	private void init(int[][] rectangles) {
		minX = rectangles[0][0];
		minY = rectangles[0][1];
		maxX = rectangles[0][2];
		maxY = rectangles[0][3];
		for (int[] rectangle : rectangles) {
			int botX = rectangle[0], topX = rectangle[2];
			int botY = rectangle[1], topY = rectangle[3];
			// total area
			long dx = topX - botX;
			long dy = topY - botY;
			totalArea += dx * dy;
			// bounding rectangle coordinates
			minX = Math.min(minX, botX);
			minY = Math.min(minY, botY);
			maxX = Math.max(maxX, topX);
			maxY = Math.max(maxY, topY);
			// count each corner frequency
			counts.merge(new Point(botX, botY), 1, Integer::sum);
			counts.merge(new Point(botX, topY), 1, Integer::sum);
			counts.merge(new Point(topX, topY), 1, Integer::sum);
			counts.merge(new Point(topX, botY), 1, Integer::sum);
		}
	}

	public static void main(String[] args) {
		PerfectRectangle pr = new PerfectRectangle();
		int[][] r1 = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } };
		System.out.println(pr.rectangleCover(r1)); // true

		int[][] r2 = { { 1, 1, 2, 3 }, { 1, 3, 2, 4 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 } };
		System.out.println(pr.rectangleCover(r2)); // false

		int[][] r3 = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 1, 3, 2, 4 }, { 2, 2, 4, 4 } };
		System.out.println(pr.rectangleCover(r3)); // false
	}

	private static class Point {

		private final int x;
		private final int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (other == null || getClass() != other.getClass()) {
				return false;
			}
			Point point = (Point) other;
			return x == point.x && y == point.y;
		}
	}
}
