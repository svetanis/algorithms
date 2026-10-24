package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 2323. Find Minimum Time to Finish All Jobs II
//
// Input: jobs[i] -- the hours of work job i needs; workers[j] -- the hours worker j can work in
// one day. Each job goes to exactly one worker and each worker takes exactly one job, and
// everyone works at the same time. Worker j finishes job i in ceil(jobs[i] / workers[j]) days.
// Returns the fewest days until every job is done, over all pairings. On LeetCode:
// n == jobs.length == workers.length, 1 <= n <= 10^5, 1 <= jobs[i], workers[j] <= 10^5. Sorts the
// caller's two arrays in place.
//
// The one idea: one pair is one job with one worker, and the answer is the slowest pair. Sort
// both lists and pair them in order: the smallest job with the worker who does the fewest hours a
// day, and so on up to the biggest job with the strongest worker. Why it is safe: take any
// pairing where a bigger job J sits with a weaker worker w while a smaller job j sits with a
// stronger worker W. Swap the two workers. J with W takes no longer than J with w did. j with w
// takes no longer than J with w did, because j is smaller. So neither of the two new pairs is
// slower than the slower of the two old ones, and every other pair is untouched: the slowest pair
// did not get slower. Swaps like this turn any pairing into the sorted one, so the sorted one is
// at least as fast as the best. (job + worker - 1) / worker is ceil(job / worker) in whole
// numbers: a part of a day is a day.
//
// Time: O(n log n) -- the two sorts; the walk after them is n steps.
// Space: O(1) beyond the sorts, which work in the caller's arrays.

public final class MinTimeFinishAllJobs {

	public static int minTime(int[] jobs, int[] workers) {
		Arrays.sort(jobs);
		Arrays.sort(workers);
		int minDays = 0;
		int n = jobs.length;
		for (int i = 0; i < n; i++) {
			int job = jobs[i];
			int worker = workers[i];
			int days = (job + worker - 1) / worker; // CEIL: a part of a day still counts as a day
			minDays = Math.max(minDays, days); // RECORD: the slowest pair decides
		}
		return minDays;
	}

	public static void main(String[] args) {
		int[] jobs = { 5, 2, 4 };
		int[] workers = { 1, 7, 5 };
		System.out.println(minTime(jobs, workers)); // 2

		int[] jobs1 = { 3, 18, 15, 9 };
		int[] workers1 = { 6, 5, 1, 3 };
		System.out.println(minTime(jobs1, workers1)); // 3
	}
}
