package com.svetanis.algorithms.search.quickselect.points.pq;

import static java.lang.Math.pow;

public final class Point implements Comparable<Point> {

	protected int x;
	protected int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point other) {
		double d1 = dist(this, new Point(0, 0));
		double d2 = dist(other, new Point(0, 0));
		if (d1 < d2) {
			return -1;
		}
		if (d1 == d2) {
			return 0;
		}
		return 1;
	}

	private double dist(Point p1, Point p2) {
		int dx = p1.x - p2.x;
		int dy = p1.y - p2.y;
		return pow(dx, 2) + pow(dy, 2);
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}