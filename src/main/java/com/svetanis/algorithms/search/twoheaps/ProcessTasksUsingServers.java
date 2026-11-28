package com.svetanis.algorithms.search.twoheaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 1882. Process Tasks Using Servers
//
// servers[i] is the weight of server i; task j takes tasks[j] seconds and is ready at second j.
// Tasks are assigned in index order: a ready task goes to the free server with the smallest
// weight, ties to the smaller index; with no server free it waits for the first one to finish,
// and every task behind it waits too. Returns, for each task, the server that runs it. n and m
// up to 2 * 10^5 and tasks[j] up to 2 * 10^5, so finish times pass Integer.MAX_VALUE -- a long.
//
// Two heaps of servers: idle by (weight, index), busy by (finish time, weight, index). Before task
// j, RELEASE every busy server finished by second j. If a server is idle, ASSIGN the top: it
// finishes at j + tasks[j]. If none is, WAIT: the top of busy -- earliest finish, then smallest
// weight and index, exactly the server the rule picks at that second -- takes the task without
// passing through idle, and finishes at its own finish time + tasks[j]. No separate clock is
// needed: after a wait until T every busy server finishes at T or later, so the next tasks find
// nothing to release before T, exactly as if the clock had jumped there.
//
// Siblings, same problem: none in the repo.
//
// Related: SingleThreadedCpu and SingleThreadedCpuPrimitives (1834) -- one CPU instead of many
// servers, so the busy heap shrinks to a single clock

public final class ProcessTasksUsingServers {
	// Time complexity: O((n + m) * log(n)), n pushes to build idle; each task then does one poll
	// and one push on a heap of at most n servers. RELEASE moves a server only after it finished
	// a task, so it runs at most m times over the whole loop
	// Auxiliary Space: O(n), every server sits in exactly one of the two heaps

	public static int[] assignTasks(int[] servers, int[] tasks) {
		// IDLE: smallest weight, then smallest index, on top
		Queue<Idle> idle = servers(servers);
		// BUSY: earliest finish, then smallest weight, then smallest index, on top
		Queue<Busy> busy = new PriorityQueue<>();
		int[] a = new int[tasks.length];
		int count = 0;
		for (int curr = 0; curr < tasks.length; curr++) {
			int task = tasks[curr];
			// RELEASE: every busy server finished by second curr becomes idle
			while (!busy.isEmpty() && busy.peek().endTime <= curr) {
				Busy top = busy.poll();
				idle.offer(new Idle(top.weight, top.index));
			}
			if (!idle.isEmpty()) {
				// ASSIGN: the best idle server starts now
				Idle top = idle.poll();
				a[count++] = top.index;
				long endTime = curr + task;
				busy.offer(new Busy(endTime, top.weight, top.index));
			} else {
				// WAIT: the first server to finish takes the task when it does
				Busy top = busy.poll();
				a[count++] = top.index;
				long endTime = top.endTime + task; // waits add up past Integer.MAX_VALUE
				busy.offer(new Busy(endTime, top.weight, top.index));
			}
		}
		return a;
	}

	private static Queue<Idle> servers(int[] servers) {
		Queue<Idle> pq = new PriorityQueue<>();
		for (int i = 0; i < servers.length; i++) {
			pq.offer(new Idle(servers[i], i));
		}
		return pq;
	}

	public static void main(String[] args) {
		int[] s1 = { 3, 3, 2 };
		int[] t1 = { 1, 2, 3, 2, 1, 2 };
		System.out.println(Arrays.toString(assignTasks(s1, t1))); // [2, 2, 0, 2, 1, 2]

		int[] s2 = { 5, 1, 4, 3, 2 };
		int[] t2 = { 2, 1, 2, 4, 5, 2, 1 };
		System.out.println(Arrays.toString(assignTasks(s2, t2))); // [1, 4, 1, 4, 1, 3, 2]

		// the largest inputs: waiting pushes end times past Integer.MAX_VALUE
		int[] s3 = { 3, 1, 2 };
		int[] t3 = new int[200_000];
		for (int i = 0; i < t3.length; i++) {
			t3[i] = 200_000 - (i % 5) * 1000;
		}
		// task 32538 is the first to start after Integer.MAX_VALUE
		System.out.println(assignTasks(s3, t3)[32_538]); // 0
	}

	private static class Busy implements Comparable<Busy> {
		private long endTime;
		private int weight;
		private int index;

		public Busy(long endTime, int weight, int index) {
			this.endTime = endTime;
			this.weight = weight;
			this.index = index;
		}

		@Override
		public int compareTo(Busy other) {
			if (this.endTime == other.endTime) {
				if (this.weight == other.weight) {
					return Integer.compare(this.index, other.index);
				} else {
					return Integer.compare(this.weight, other.weight);
				}
			}
			return Long.compare(this.endTime, other.endTime);
		}

		@Override
		public String toString() {
			return endTime + ":" + weight + ":" + index;
		}
	}

	private static class Idle implements Comparable<Idle> {
		private int weight;
		private int index;

		public Idle(int weight, int index) {
			this.weight = weight;
			this.index = index;
		}

		@Override
		public int compareTo(Idle other) {
			if (this.weight == other.weight) {
				return Integer.compare(this.index, other.index);
			}
			return Integer.compare(this.weight, other.weight);
		}

		@Override
		public String toString() {
			return weight + ":" + index;
		}
	}
}