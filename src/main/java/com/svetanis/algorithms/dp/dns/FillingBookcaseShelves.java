package com.svetanis.algorithms.dp.dns;

// 1105. Filling Bookcase Shelves

public final class FillingBookcaseShelves {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int minHeightShelves(int[][] books, int shelfWidth) {
		int n = books.length;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int width = books[i - 1][0];
			int height = books[i - 1][1];
			dp[i] = dp[i - 1] + height;
			for (int j = i - 1; j > 0; j--) {
				width += books[j - 1][0];
				if (width > shelfWidth) {
					break;
				}
				height = Math.max(height, books[j - 1][1]);
				dp[i] = Math.min(dp[i], dp[j - 1] + height);
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int[][] books1 = { { 1, 1 }, { 2, 3 }, //
				{ 2, 3 }, { 1, 1 }, { 1, 1 }, //
				{ 1, 1 }, { 1, 2 } };//
		System.out.println(minHeightShelves(books1, 4)); // 6

		int[][] books2 = { { 1, 3 }, { 2, 4 }, { 3, 2 } };
		System.out.println(minHeightShelves(books2, 6)); // 4
	}
}
