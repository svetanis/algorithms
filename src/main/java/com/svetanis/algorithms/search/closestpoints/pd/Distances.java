package com.svetanis.algorithms.search.closestpoints.pd;

import static java.lang.Math.pow;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public final class Distances {

	public static List<Distance> distances(List<Point> points) {
		List<Distance> list = new ArrayList<>();
		for (int i = 0; i < points.size(); i++) {
			int dto = distToOrigin(points.get(i));
			list.add(new Distance(dto, i));
		}
		return list;
	}

	private static int distToOrigin(Point p) {
		return dist(p, new Point(0, 0));
	}

	private static int dist(Point p1, Point p2) {
		double dx = p1.getX() - p2.getX();
		double dy = p1.getY() - p2.getY();
		return new Double(pow(dx, 2) + pow(dy, 2)).intValue();
	}
}
