package com.svetanis.algorithms.math.geometry;

// 593. Valid Square

public class ValidSquare {

	public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		boolean one = validate(p1, p2, p3);
		boolean two = validate(p2, p3, p4);
		boolean thr = validate(p1, p3, p4);
		boolean fou = validate(p1, p2, p4);
		return one && two && thr && fou;
	}

	private static boolean validate(int[] a, int[] b, int[] c) {
		int d1 = distance(a, b);
		int d2 = distance(b, c);
		int d3 = distance(a, c);
		boolean one = d1 == d2 && d1 + d2 == d3 && d1 > 0;
		boolean two = d2 == d3 && d2 + d3 == d1 && d2 > 0;
		boolean thr = d1 == d3 && d1 + d3 == d2 && d1 > 0;
		return one || two || thr;
	}

	private static int distance(int[] a, int[] b) {
		int xx = (a[0] - b[0]) * (a[0] - b[0]);
		int yy = (a[1] - b[1]) * (a[1] - b[1]);
		return xx + yy;
	}

	public static void main(String[] args) {
		int[] p1 = { 0, 0 };
		int[] p2 = { 1, 1 };
		int[] p3 = { 1, 0 };
		int[] p4 = { 0, 1 };
		System.out.println(validSquare(p1, p2, p3, p4)); // true

		int[] r1 = { 0, 0 };
		int[] r2 = { 1, 1 };
		int[] r3 = { 1, 0 };
		int[] r4 = { 0, 12 };
		System.out.println(validSquare(r1, r2, r3, r4)); // false

		int[] s1 = { 1, 0 };
		int[] s2 = { -1, 0 };
		int[] s3 = { 0, 1 };
		int[] s4 = { 0, -1 };
		System.out.println(validSquare(s1, s2, s3, s4)); // true

		int[] t1 = { 6987, -473 };
		int[] t2 = { 6985, -473 };
		int[] t3 = { 6986, -472 };
		int[] t4 = { 6986, -474 };
		System.out.println(validSquare(t1, t2, t3, t4)); // true

		int[] q1 = { 0, 0 };
		int[] q2 = { 0, 0 };
		int[] q3 = { 0, 0 };
		int[] q4 = { 0, 0 };
		System.out.println(validSquare(q1, q2, q3, q4)); // false

	}
}
