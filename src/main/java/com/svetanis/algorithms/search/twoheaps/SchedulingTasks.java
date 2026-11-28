package com.svetanis.algorithms.search.twoheaps;

import static java.util.Comparator.comparingInt;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// 621. Task Scheduler
//
// tasks holds one letter per task (A to Z on LeetCode). Each task takes one interval, in any
// order, and two runs of the same letter need at least k intervals between them; an interval in
// which no letter is allowed to run is idle. Returns the fewest intervals that finish every task.
// On LeetCode: up to 10^4 tasks, k between 0 and 100.
//
// One interval per pass, greedy: RUN the allowed letter with the most copies left. A letter that
// ran WAITs in a queue with the first interval it may run again; letters run in time order, so
// the queue is in order of that interval too, and RELEASE takes from its front. When no letter is
// allowed, JUMP the clock to the front's interval -- the intervals skipped are the idle ones -- so
// the loop passes once per task, not once per interval.
//
// Siblings, same problem:
//   TaskScheduler (todo/heap)      no simulation: computes the length from the most frequent
//                                  letter's copies and how many letters have that many, O(t)
//   TaskSchedulerHeap (todo/heap)  the same heap of copies left, run in blocks of k + 1
//                                  intervals instead of one interval per pass
//   TaskSchedulerSlots (todo/heap) this loop without JUMP: one pass per interval, idle ones
//                                  included

public final class SchedulingTasks {
	// Time complexity: O(t * log(d)), t tasks and d distinct letters: each pass runs one task, so
	// there are t passes, each one heap poll and at most one push; JUMP skips the idle intervals
	// without a pass for each
	// Auxiliary Space: O(d), the counts, the heap and the queue hold at most one entry per letter

	public static int schedulingTasks(char[] tasks, int k) {
		// copies left per letter; the heap reads it, so a count
		// changes only while its letter is out of the heap
		Map<Character, Integer> map = frequencyMap(tasks);
		// READY: letters allowed to run now, most copies left on top
		Queue<Character> pq = maxHeap(map);
		// WAITING: letters that ran, in the order they may run again
		Queue<Cooling> queue = new ArrayDeque<>();
		int intervals = 0; // intervals used so far, idle ones included
		// a letter ready, or a letter waiting
		while (!pq.isEmpty() || !queue.isEmpty()) {
			// JUMP: nothing ready -> idle until the front letter may run.
			// Forward only: every waiting letter has readyAt >= intervals
			if (pq.isEmpty()) {
				intervals = queue.peek().readyAt();
			}
			// RELEASE: every waiting letter whose wait is over goes back to the heap
			while (!queue.isEmpty() && queue.peek().readyAt() <= intervals) {
				pq.offer(queue.poll().task());
			}
			// RUN: the letter with the most copies left takes this interval
			char curr = pq.poll();
			intervals++;
			int freq = map.get(curr) - 1;
			map.put(curr, freq);
			// WAIT: it ran in interval (intervals - 1), so the next allowed one is intervals + k
			if (freq > 0) {
				queue.add(new Cooling(curr, intervals + k));
			}
		}
		return intervals;
	}

	private static Queue<Character> maxHeap(Map<Character, Integer> map) {
		Queue<Character> pq =
				new PriorityQueue<>(comparingInt((Character ch) -> map.get(ch)).reversed());
		for (char c : map.keySet()) {
			pq.add(c);
		}
		return pq;
	}

	private static Map<Character, Integer> frequencyMap(char[] chars) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : chars) {
			int freq = map.getOrDefault(c, 0);
			map.put(c, freq + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		char[] tasks1 = { 'a', 'a', 'a', 'b', 'c', 'c' };
		System.out.println(schedulingTasks(tasks1, 2)); // 7

		char[] tasks2 = { 'a', 'b', 'a' };
		System.out.println(schedulingTasks(tasks2, 3)); // 5

		// one task: nothing waits, so no interval is idle
		char[] tasks3 = { 'a' };
		System.out.println(schedulingTasks(tasks3, 3)); // 1
	}

	// a letter that ran, and the first interval it may run again
	private record Cooling(char task, int readyAt) {
	}
}