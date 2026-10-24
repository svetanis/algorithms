package com.svetanis.algorithms.greedy;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 826. Most Profit Assigning Work
//
// Input: difficulty[i] and profit[i] describe job i; workers[j] is the ability of worker j, who
// can do any job whose difficulty is at most workers[j]. Each worker does at most one job, and
// one job may be done by any number of workers. Returns the largest total profit. On LeetCode:
// n == difficulty.length == profit.length, m == workers.length, 1 <= n, m <= 10^4,
// 1 <= difficulty[i], profit[i], workers[j] <= 10^5. Sorts the caller's workers array in place.
//
// The one idea: jobs can be repeated, so workers never compete for one: each worker simply takes
// the most profitable job they are able to do, and the only work is finding it fast. Profit does
// not grow with difficulty, so that job is not the hardest one they can do -- it is the best of
// all the jobs at or below their ability. Sort the jobs by difficulty and the workers by ability.
// A job that one worker can do, every stronger worker can do too, so walking the workers from
// weakest to strongest, the set of doable jobs only grows. One index into the sorted jobs moves
// forward past every job the current worker can do, and maxProfit holds the best profit among the
// jobs passed so far: that is the current worker's best job. Nothing passed ever needs to be
// looked at again.
//
// Time: O(n log n + m log m) -- sorting the jobs and the workers; the walk after it is O(n + m),
// since index only moves forward.
// Space: O(n) -- the list of { difficulty, profit } pairs.

public final class MostProfitAssigningWork {

	public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {
		List<int[]> jobs = jobs(difficulty, profit); // JOBS: { difficulty, profit } pairs, sorted by difficulty
		Arrays.sort(workers);
		int total = 0;
		int maxProfit = 0;
		int index = 0;
		for (int worker : workers) {
			while (index < difficulty.length && jobs.get(index)[0] <= worker) { // UNLOCK every job this worker can do; BEST profit among them
				maxProfit = Math.max(maxProfit, jobs.get(index)[1]);
				index++;
			}
			total += maxProfit; // TAKE the best unlocked job; it stays available for the next worker
		}
		return total;
	}

	private static List<int[]> jobs(int[] difficulty, int[] profit) {
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < difficulty.length; i++) {
			list.add(new int[] { difficulty[i], profit[i] });
		}
		Collections.sort(list, comparingInt(i -> i[0])); // SORT: easiest job first
		return list;
	}

	public static void main(String[] args) {
		int[] difficulty = { 2, 4, 6, 8, 10 };
		int[] profit = { 10, 20, 30, 40, 50 };
		int[] worker = { 4, 5, 6, 7 };
		System.out.println(maxProfitAssignment(difficulty, profit, worker)); // 100

		int[] difficulty1 = { 85, 47, 57 };
		int[] profit1 = { 24, 66, 99 };
		int[] worker1 = { 40, 25, 25 };
		System.out.println(maxProfitAssignment(difficulty1, profit1, worker1)); // 0

		int[] difficulty2 = { 68, 35, 52, 47, 86 };
		int[] profit2 = { 67, 17, 1, 81, 3 };
		int[] worker2 = { 92, 10, 85, 84, 82 };
		System.out.println(maxProfitAssignment(difficulty2, profit2, worker2)); // 324
	}
}
