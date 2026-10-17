package com.svetanis.algorithms.twopointers;

// 251. Flatten 2D Vector
//
// Input: a 2D array whose rows may have different lengths, some of them empty.
// Return: an iterator over all its numbers, row by row: next() gives the next number,
// hasNext() says whether one is left. next() is called only when one is left.
//
// The one idea: two indexes, row and col, point at the next number to give out. Before
// either call answers, moveToNextRow pushes them past the end of a finished row and over
// every empty row, so afterwards (row, col) is either a real number or row equals the
// row count, which means the end. hasNext may be called any number of times in a row:
// once the indexes rest on a real number, moving again does nothing.
//
// Siblings, same problem:
//   Vector2D           two indexes, row and col (this file)
//   Vector2DIterators  the follow-up: two Java iterators, rows and cols, in place of the indexes
//
// Time: O(1) on average per call -- each row is skipped once over the whole iteration,
//   and the constructor copies nothing.
// Space: O(1) -- the array is read where it is.

public final class Vector2D {

	private int row;
	private int col;
	private int[][] vector;

	public Vector2D(int[][] vector) {
		this.row = 0;                    // START: the first number of the first row
		this.col = 0;
		this.vector = vector;
	}

	public int next() {
		moveToNextRow();                 // SKIP finished and empty rows first
		return vector[row][col++];       // RETURN the number and MOVE past it
	}

	public boolean hasNext() {
		moveToNextRow();                 // SKIP finished and empty rows first
		return row < vector.length;      // a number is left unless every row is finished
	}

	private void moveToNextRow() {
		int n = vector.length;
		while (row < n && col >= vector[row].length) { // SKIP: this row has nothing left
			row++;                       // MOVE to the start of the next row
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
