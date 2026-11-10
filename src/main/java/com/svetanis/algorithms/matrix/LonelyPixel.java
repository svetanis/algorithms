package com.svetanis.algorithms.matrix;

// 531. Lonely Pixel I
//
// Input: matrix -- n rows and m columns; a cell holds 'B' (a black pixel) or 'W' (a white one). A
// lonely black pixel is a 'B' whose row and whose column hold no other 'B'. Returns the number of
// lonely black pixels. On LeetCode: 1 <= rows, columns <= 500, each cell is 'W' or 'B'.
//
// The one idea: one cell holds a black or a white pixel. A 'B' is lonely exactly when its row
// holds one 'B' and its column holds one 'B' -- itself both times. So the first pass counts the
// 'B's of every row and of every column. The second pass looks only at rows with exactly one 'B',
// finds that 'B', and counts it when its column's count is 1 too.
//
// Time: O(n * m) -- two passes over the grid.
// Space: O(n + m) -- one count per row and per column.

public final class LonelyPixel {

	public static int lonelyPixel(char[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int[] rows = new int[n]; // rows[i]: 'B's in row i
		int[] cols = new int[m]; // cols[j]: 'B's in column j
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 'B') {
					rows[i]++; // COUNT the pixel in its row
					cols[j]++; // and in its column
				}
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (rows[i] == 1) { // SKIP rows with no 'B' or more than one
				for (int j = 0; j < m; j++) {
					if (matrix[i][j] == 'B' && cols[j] == 1) { // ALONE in its column too
						count++;
						// lonely B for this row is found
						break; // STOP: the row has no other 'B'
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		char[][] m1 = { { 'W', 'B', 'W' }, { 'W', 'W', 'W' }, { 'B', 'W', 'B' } };
		System.out.println(lonelyPixel(m1)); // 1

		char[][] m2 = { { 'W', 'W', 'B' }, { 'W', 'B', 'W' }, { 'B', 'W', 'W' } };
		System.out.println(lonelyPixel(m2)); // 3
	}
}
