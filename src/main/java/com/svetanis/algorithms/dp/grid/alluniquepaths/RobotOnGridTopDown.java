package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 62. Unique Paths

// a robot starts its journey at
// the top-left corner of a grid
// that measures m x n
// m rows by n columns
// at each step, the robot has only
// two possible directions: it can
// either move to the right or 
// move downward. its destination is 
// the bottom-right corner of the grid
// find the total number of unique paths
// the robot can take to reach its dst

public final class RobotOnGridTopDown {

	public static int countUniquePaths(int m, int n) {
		boolean[][] visited = new boolean[m][n];
		return count(m, n, visited, 0, 0, 0);
	}

	private static int count(int m, int n, boolean[][] visited, 
			int x, int y, int count) {
		if (x == m - 1 && y == n - 1) {
			count++;
			return count;
		}

		visited[x][y] = true;
		// go down (x, y) --> (x + 1, y)
		if (valid(m, n, visited, x + 1, y)) {
			count = count(m, n, visited, x + 1, y, count);
		}
		// go right (x, y) --> (x, y + 1)
		if (valid(m, n, visited, x, y + 1)) {
			count = count(m, n, visited, x, y + 1, count);
		}
		visited[x][y] = false; // backtrack
		return count;
	}

	private static boolean valid(int m, int n, 
			boolean[][] visited, int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y];
	}

	public static void main(String[] args) {
		// m rows and n columns
		System.out.println(countUniquePaths(3, 2)); // 3
		System.out.println(countUniquePaths(7, 3)); // 28
		System.out.println(countUniquePaths(1, 1)); // 1
		System.out.println(countUniquePaths(10, 5)); // 715
	}
}
