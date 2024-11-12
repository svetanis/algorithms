package com.svetanis.algorithms.search.twoheaps;

import static com.svetanis.java.base.utils.Print.print;

import java.util.PriorityQueue;
import java.util.Queue;

// 1882. Process Tasks Using Servers

public final class ProcessTasksUsingServers {
	// Time Complexity: O(n + m * log(n))
	// Space Complexity: O(n + m)

	public static int[] assignTasks(int[] servers, int[] tasks) {
		Queue<Idle> idle = servers(servers);
		Queue<Busy> busy = new PriorityQueue<>();
		int[] a = new int[tasks.length];
		int count = 0;
		for (int curr = 0; curr < tasks.length; curr++) {
			int task = tasks[curr];
			// pop servers that have finished their tasks from busy to idle
			while (!busy.isEmpty() && busy.peek().endTime <= curr) {
				Busy top = busy.poll();
				idle.offer(new Idle(top.weight, top.index));
			}
			if (!idle.isEmpty()) {
				Idle top = idle.poll();
				a[count++] = top.index;
				int endTime = curr + task;
				busy.offer(new Busy(endTime, top.weight, top.index));
			} else {
				Busy top = busy.poll();
				a[count++] = top.index;
				int endTime = top.endTime + task;
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
		print(assignTasks(s1, t1)); // 2,2,0,2,1,2

		int[] s2 = { 5, 1, 4, 3, 2 };
		int[] t2 = { 2, 1, 2, 4, 5, 2, 1 };
		print(assignTasks(s2, t2)); // 1, 4, 1, 4, 1, 3, 2
	}

	private static class Busy implements Comparable<Busy> {
		private int endTime;
		private int weight;
		private int index;

		public Busy(int endTime, int weight, int index) {
			this.endTime = endTime;
			this.weight = weight;
			this.index = index;
		}

		@Override
		public int compareTo(Busy other) {
			if (this.endTime == other.endTime) {
				if (this.weight == other.weight) {
					return this.index - other.index;
				} else {
					return this.weight - other.weight;
				}
			}
			return this.endTime - other.endTime;
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
				return this.index - other.index;
			}
			return this.weight - other.weight;
		}

		@Override
		public String toString() {
			return weight + ":" + index;
		}
	}
}