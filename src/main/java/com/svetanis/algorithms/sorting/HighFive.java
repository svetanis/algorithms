package com.svetanis.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 1086. High Five

public final class HighFive {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int[][] highFive(int[][] items) {
		List<Integer>[] scoresPerStudent = scoresPerStudent(items);
		List<int[]> averageTopFiveScores = new ArrayList<>();
		for (int i = 1; i <= 1000; i++) {
			var scores = scoresPerStudent[i];
			if (!scores.isEmpty()) {
				int topFive = topFiveSum(scores);
				int average = topFive / Math.min(5, scores.size());
				averageTopFiveScores.add(new int[] { i, average });
			}
		}
		return averageTopFiveScores.toArray(new int[0][]);
	}

	private static int topFiveSum(List<Integer> scores) {
		int sum = 0;
		for (int i = 0; i < Math.min(5, scores.size()); i++) {
			sum += scores.get(i);
		}
		return sum;
	}

	private static List<Integer>[] scoresPerStudent(int[][] items) {
		List<Integer>[] scoresPerStudent = new List[1001];
		Arrays.setAll(scoresPerStudent, k -> new ArrayList<>());
		for (int[] item : items) {
			int sid = item[0];
			int score = item[1];
			scoresPerStudent[sid].add(score);
		}
		for (var scores : scoresPerStudent) {
			scores.sort(Comparator.reverseOrder());
		}
		return scoresPerStudent;
	}

	public static void main(String[] args) {
		int[][] items = { { 1, 91 }, { 1, 92 }, { 2, 93 }, //
				{ 2, 97 }, { 1, 60 }, { 2, 77 }, { 1, 65 }, //
				{ 1, 87 }, { 1, 100 }, { 2, 100 }, { 2, 76 } };//
		Print.print(highFive(items));
	}
}
