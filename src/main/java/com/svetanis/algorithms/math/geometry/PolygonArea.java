package com.svetanis.algorithms.math.geometry;

// CSES: Polygon Area

// NOTE the name. Both methods return TWICE the area, and that is what CSES asks
// for -- doubling is what keeps the answer a whole number, so halving here would
// be the bug.

// NOTE the type. The terms are products of raw coordinates, and CSES allows
// 10^9, so one term reaches 10^18. The running sum is bounded by twice the area
// of the box the vertices span, which is at most 8 * 10^18 and still fits a long
// -- but only just, and only because the coordinates are bounded. For a general
// polygon, subtract the first vertex from all of them first; the area does not
// move and every product shrinks to the polygon's own extent.

public final class PolygonArea {
	// Time Complexity: O(n) -- a single loop over the vertices

	// the shoelace sum
	public static long twicePolygonArea(int[][] points) {
		long area = 0;
		int n = points.length;
		for (int i = 0; i < n; i++) {
			long term1 = (long) points[i][0] * points[(i + 1) % n][1];
			long term2 = (long) points[(i + 1) % n][0] * points[i][1];
			area += term1 - term2;
		}
		// the sign is the winding direction and an area does not have one.
		// without this, a clockwise polygon comes back negative
		return Math.abs(area);
	}

	// the trapezoid spelling of the same sum
	public static long twicePolygonAreaTrapezoid(int[][] points) {
		long area = 0;
		int n = points.length;
		int prev = n - 1;
		for (int curr = 0; curr < n; curr++) {
			long term1 = (long) points[prev][0] + points[curr][0];
			long term2 = (long) points[prev][1] - points[curr][1];
			area += (term1 * term2);
			prev = curr;
		}
		return Math.abs(area);
	}

	// the fan spelling: triangles (p0, p[i], p[i + 1]), all from the
	// first corner. A triangle's cross product is twice its area, with
	// a sign: a triangle that reaches outside a dent comes out negative
	// and cancels exactly the part that is not in the polygon. This is
	// the header's "subtract the first vertex first" -- every arrow
	// starts at p0, so the products stay within the polygon's own extent
	public static long twicePolygonAreaFan(int[][] points) {
		long x0 = points[0][0], y0 = points[0][1];
		long area = 0;
		for (int i = 1; i < points.length - 1; i++) { // n - 2 triangles
			long dxI = points[i][0] - x0, dyI = points[i][1] - y0;
			long dxNext = points[i + 1][0] - x0, dyNext = points[i + 1][1] - y0;
			area += dxI * dyNext - dyI * dxNext; // keep the sign
		}
		// once, at the end: the sign only records which way round the
		// corners were listed. Not halved: CSES wants twice the area
		return Math.abs(area);
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 1 }, { 4, 2 }, { 3, 5 }, { 1, 4 } };
		System.out.println(twicePolygonArea(points1)); // 16
		System.out.println(twicePolygonAreaTrapezoid(points1)); // 16
		System.out.println(twicePolygonAreaFan(points1)); // 16

		int[][] points2 = { { 1, 3 }, { 5, 6 }, { 2, 5 }, { 1, 4 } };
		System.out.println(twicePolygonArea(points2)); // 6
		System.out.println(twicePolygonAreaTrapezoid(points2)); // 6
		System.out.println(twicePolygonAreaFan(points2)); // 6

		// the same polygon as points1, listed the other way round.
		// without the abs this would be -16
		int[][] clockwise = { { 1, 4 }, { 3, 5 }, { 4, 2 }, { 1, 1 } };
		System.out.println(twicePolygonArea(clockwise)); // 16
		System.out.println(twicePolygonAreaTrapezoid(clockwise)); // 16
		System.out.println(twicePolygonAreaFan(clockwise)); // 16

		// CSES's own range: needs long
		int max = 1000000000;
		int[][] square = { { 0, 0 }, { max, 0 }, { max, max }, { 0, max } };
		System.out.println(twicePolygonArea(square)); // 2000000000000000000
		System.out.println(twicePolygonAreaTrapezoid(square)); // 2000000000000000000
		System.out.println(twicePolygonAreaFan(square)); // 2000000000000000000

		// a polygon with a dent at (1, 1), started from the corner whose
		// fan reaches outside it: triangle (4,0), (1,1), (0,4) is negative
		int[][] dent = { { 4, 0 }, { 1, 1 }, { 0, 4 }, { 0, 0 } };
		System.out.println(twicePolygonAreaFan(dent)); // 8
	}
}