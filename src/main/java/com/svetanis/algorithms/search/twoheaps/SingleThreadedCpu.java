package com.svetanis.algorithms.search.twoheaps;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 1834. Single-Threaded CPU
//
// tasks[i] = [enqueueTime, processingTime]: task i becomes available at enqueueTime and runs
// for processingTime, start to finish. One CPU; when idle with tasks available it starts the
// shortest, ties going to the smaller index; when idle with none, it waits. Returns the task
// indexes in the order they run.
//
// One loop, one clock: time = when the CPU is next free. Each pass JUMPs the clock to the next
// arrival if nothing is waiting, PUSHes every task that has arrived by time, then POPs the
// shortest waiting task and runs it to the end. The jump takes Math.max with the current
// time: at the top of a pass the heap can be empty while tasks have arrived and are not
// pushed yet, and without the max the clock would move back.
//
// Siblings, same problem:
//   SingleThreadedCpuPrimitives  the same loop over int[] rows [enqueue, processing, index]
//                                and a comparator lambda -- quicker to write under pressure.
//                                It PUSHes before it JUMPs, so it needs no Math.max, and it
//                                skips POP on a pass that jumped
// Variable names match between the two: t, pq, ans, i, k, time, shortestTask.

public final class SingleThreadedCpu {
	// Time complexity: O(n * log(n)), the sort, then each task is pushed once and popped once
	// Auxiliary Space: O(n), the copy and the heap

	public static int[] getOrder(int[][] tasks) {
		int n = tasks.length;
		// COPY: each row becomes a Task that carries its index
		List<Task> t = copy(tasks);
		// SORT: arrival order
		Collections.sort(t, comparingInt(a -> a.enqueue));
		// WAITING: the order is Task.compareTo, shortest first, ties: smaller index
		PriorityQueue<Task> pq = new PriorityQueue<>();

		int[] ans = new int[n];
		int i = 0; // next unread task of t
		int k = 0; // next free slot of ans
		// when the CPU is next free; can reach about 10^14, past int
		long time = 0L;
		// a task still unread, or a task still waiting
		while (i < n || !pq.isEmpty()) {
			// JUMP: nothing waiting -> the next arrival, never back
			if (pq.isEmpty()) {
				time = Math.max(time, t.get(i).enqueue);
			}
			// PUSH: every unread task with enqueue <= time
			while (i < n && t.get(i).enqueue <= time) {
				pq.offer(t.get(i));
				i += 1;
			}
			// POP: the shortest waiting task runs to the end.
			// Never empty here: after a jump, PUSH takes at least the next arrival
			Task shortestTask = pq.poll();
			time += shortestTask.processing;
			ans[k++] = shortestTask.index;
		}
		return ans;
	}

	private static List<Task> copy(int[][] tasks) {
		List<Task> list = new ArrayList<>();
		for (int i = 0; i < tasks.length; i++) {
			list.add(new Task(tasks[i][0], tasks[i][1], i));
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] tasks1 = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
		System.out.println(Arrays.toString(getOrder(tasks1))); // [0, 2, 3, 1]

		int[][] tasks2 = { { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 } };
		System.out.println(Arrays.toString(getOrder(tasks2))); // [4, 3, 2, 0, 1]
	}

	private static class Task implements Comparable<Task> {
		private int enqueue;
		private int processing;
		private int index;

		public Task(int enqueue, int processing, int index) {
			this.enqueue = enqueue;
			this.processing = processing;
			this.index = index;
		}

		@Override
		public int compareTo(Task other) {
			if (this.processing == other.processing) {
				return Integer.compare(this.index, other.index);
			}
			return Integer.compare(this.processing, other.processing);
		}

		@Override
		public String toString() {
			return enqueue + ":" + processing + ":" + index;
		}
	}

}
