package com.svetanis.algorithms.twopointers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// 251. Flatten 2D Vector, the follow-up: Java iterators only, no indexes
//
// Input: a 2D array whose rows may have different lengths, some of them empty.
// Return: an iterator over all its numbers, row by row: next() gives the next number,
// hasNext() says whether one is left. next() is called only when one is left.
//
// The one idea: two iterators replace the two indexes. rows hands out one whole row per
// next(), so each rows.next() is what row++ was; cols hands out the numbers of the current
// row and remembers by itself how far it has got, so it is what col was. A fresh iterator
// always starts at its first item, so building a new cols is what col = 0 was.
// Arrays.stream(array).iterator() reads the array where it is: nothing is copied.
//
// cols starts as an iterator over an empty row. Its first hasNext() is already false, which
// to the skip loop looks exactly like a used-up row, so the first call takes row 0 through
// rows like every other row. (Building cols from vec[0] directly would leave rows still
// waiting to hand out row 0, and row 0 would come out twice.)
//
// hasNext answers from cols, not rows: on the last row, rows has nothing left while cols
// still holds that row's numbers.
//
// Siblings, same problem:
//   Vector2D           two indexes, row and col; a skip loop pushes them past finished and
//                      empty rows before either call answers
//   Vector2DIterators  two iterators, rows and cols (this file, the version derived in study)
//
// Time: O(1) on average per call -- each row is taken from rows once over the whole iteration.
// Space: O(1) -- two iterators, no copy of the array.

public final class Vector2DIterators {

	private final Iterator<int[]> rows; // ROWS: hands out one whole row per next()
	private Iterator<Integer> cols;     // COLS: the numbers of the current row

	public Vector2DIterators(int[][] vec) {
		this.rows = Arrays.stream(vec).iterator();
		this.cols = Arrays.stream(new int[0]).iterator(); // START empty: looks used up
	}

	public int next() {
		if (!hasNext()) {                // SKIP to a real number, and never fake a value
			throw new NoSuchElementException();
		}
		return cols.next();              // READ the number and STEP past it, in one call
	}

	public boolean hasNext() {
		while (!cols.hasNext() && rows.hasNext()) {     // SKIP: row used up, another exists
			cols = Arrays.stream(rows.next()).iterator(); // MOVE: a fresh iterator is col 0
		}
		return cols.hasNext();           // ANSWER from cols: it holds the current row
	}

	public static void main(String[] args) {
		int[][] v = { { 1, 2 }, {}, { 3 }, { 4, 5 } };
		Vector2DIterators v2d = new Vector2DIterators(v);
		System.out.println(v2d.next()); // 1
		System.out.println(v2d.next()); // 2
		System.out.println(v2d.hasNext()); // true
		System.out.println(v2d.next()); // 3
		System.out.println(v2d.hasNext()); // true
		System.out.println(v2d.next()); // 4
		System.out.println(v2d.next()); // 5
		System.out.println(v2d.hasNext()); // false
		System.out.println(new Vector2DIterators(new int[][] {}).hasNext()); // false
	}
}
