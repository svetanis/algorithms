package com.svetanis.algorithms.search.twoheaps;

import java.util.Arrays;
import java.util.PriorityQueue;

// 1834. Single-Threaded CPU
//
// tasks[i] = [enqueueTime, processingTime]: task i becomes available at enqueueTime and runs
// for processingTime, start to finish. One CPU; when idle with tasks available it starts the
// shortest, ties going to the smaller index; when idle with none, it waits. Returns the task
// indexes in the order they run.
//
// One loop, one clock: time = when the CPU is next free. Each pass PUSHes every task that has
// arrived by time, JUMPs the clock to the next arrival if nothing is waiting, else POPs the
// shortest waiting task and runs it to the end. PUSH must come before JUMP: before it, an empty
// heap can mean "arrived but not pushed yet", and the jump would move the clock back.
//
// Siblings, same problem:
//   SingleThreadedCpu  the same loop over a List of a Task class that implements Comparable;
//                      the right shape for production code, more to type under pressure.
//                      It writes the jump as Math.max(time, next arrival) before PUSH
//
// Related: ProcessTasksUsingServers (1882) -- many servers, so the one clock becomes a heap
// of busy servers ordered by the time each becomes free

public final class SingleThreadedCpuPrimitives {
	// Time complexity: O(n * log(n)), the sort, then each task is pushed once and popped once
	// Auxiliary Space: O(n), the copy with indexes and the heap

	public static int[] getOrder(int[][] tasks) {
		int n = tasks.length;
		// COPY: each row becomes [enqueue, processing, index],
		// the index is lost once the rows move
		int[][] t = new int[n][3];
		for (int i = 0; i < n; i++) {
			t[i] = new int[] { tasks[i][0], tasks[i][1], i };
		}
		// SORT: arrival order
		Arrays.sort(t, (a, b) -> Integer.compare(a[0], b[0]));
		// WAITING: shortest processing first, ties: smaller index.
		// The rows are int[], so == compares numbers; on Integer[] it would compare objects
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] == b[1])
				? Integer.compare(a[2], b[2]) : Integer.compare(a[1], b[1]));

		int[] ans = new int[n];
		int i = 0; // next unread row of t
		int k = 0; // next free slot of ans
		// when the CPU is next free; can reach about 10^14, past int
		long time = 0L;
		// a row still unread, or a task still waiting
		while (i < n || !pq.isEmpty()) {
			// PUSH: every unread row with enqueue <= time
			while (i < n && t[i][0] <= time) {
				pq.offer(t[i]);
				i += 1;
			}
			// JUMP: nothing waiting -> the next arrival. Forward only,
			// because PUSH ran first: every row with enqueue <= time is inside
			if (pq.isEmpty()) {
				time = t[i][0];
			}
			// POP: the shortest waiting task runs to the end.
			// Skipped on a pass that jumped: the heap is still empty
			if (!pq.isEmpty()) {
				int[] shortestTask = pq.poll();
				time += shortestTask[1];
				ans[k++] = shortestTask[2];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[][] tasks1 = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
		System.out.println(Arrays.toString(getOrder(tasks1))); // [0, 2, 3, 1]

		int[][] tasks2 = { { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 } };
		System.out.println(Arrays.toString(getOrder(tasks2))); // [4, 3, 2, 0, 1]
	}
}
