package com.svetanis.algorithms.dp.interval;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

/*
F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), 
            Vj + min(F(i+1, j-1), F(i, j-2) )) 
Base Cases
 F(i, j)  = Vi            If j == i
 F(i, j)  = max(Vi, Vj)   If j == i+1
*/

public final class MaxGainBottomUp {

	public static int game(List<Integer> coins) {
		int n = coins.size();
		int[][] dp = new int[n][n];
		// fill the table in diagonal fashion
		// from diagonal elements to
		// dp[0][n - 1] which is the result
		for (int gap = 0; gap < n; ++gap) {
			for (int i = 0, j = gap; j < n; ++i, ++j) {
				// x is value of F(i + 2, j)
				int x = (i + 2 <= j) ? dp[i + 2][j] : 0;
				// y is value of F(i + 1, j - 1)
				int y = (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0;
				// z is value of F(i, j - 2)
				int z = (i <= j - 2) ? dp[i][j - 2] : 0;
				int sum1 = coins.get(i) + min(x, y);
				int sum2 = coins.get(j) + min(y, z);
				dp[i][j] = max(sum1, sum2);
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(game(asList(8, 15, 3, 7))); // 22
		System.out.println(game(asList(2, 2, 2, 2))); // 4
		System.out.println(game(asList(20, 30, 2, 2, 2, 10))); // 42
		System.out.println(game(asList(25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10))); // 140
		
		System.out.println(game(asList(5, 3, 4, 5))); // 9
		System.out.println(game(asList(4, 4, 9, 4))); // 13
		System.out.println(game(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))); // 55
		System.out.println(game(asList(1, 2, 9999, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))); // 10104
	}
}
