package com.svetanis.algorithms.twopointers;

// 251. Flatten 2D Vector

public final class Vector2D {

	private int row;
	private int col;
	private int[][] vector;

	public Vector2D(int[][] vector) {
		this.row = 0;
		this.col = 0;
		this.vector = vector;
	}

	public int next() {
		moveToNextRow();
		return vector[row][col++];
	}

	public boolean hasNext() {
		moveToNextRow();
		return row < vector.length;
	}

	private void moveToNextRow() {
		int n = vector.length;
		while (row < n && col >= vector[row].length) {
			row++;
			col = 0;
		}
	}

	public static void main(String[] args) {
		int[][] v = { { 1, 2 }, {}, { 3 }, { 4, 5 } };
		Vector2D v2d = new Vector2D(v);
		System.out.println(v2d.next()); // 1
		System.out.println(v2d.next()); // 2
		System.out.println(v2d.hasNext()); // true
		System.out.println(v2d.next()); // 3
		System.out.println(v2d.hasNext()); // true
		System.out.println(v2d.next()); // 4
		System.out.println(v2d.next()); // 5
		System.out.println(v2d.hasNext()); // false
	}
}
