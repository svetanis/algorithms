package com.svetanis.algorithms.search.twoheaps;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 1834. Single-Threaded CPU

public final class SingleThreadedCpu {
	// Time Complexity: O(n * log(n))
	// Space Complexity: O(n)

	public static int[] order(int[][] grid) {
		List<Task> tasks = tasks(grid);
		Collections.sort(tasks, (a, b) -> a.start - b.start);
		Queue<Task> pq = new PriorityQueue<>();
		int size = tasks.size();
		int[] a = new int[size];
		int currentTime = 0;
		int count = 0;
		int index = 0;
		while (!pq.isEmpty() || count < size) {
			if (pq.isEmpty()) {
				currentTime = Math.max(currentTime, tasks.get(count).start);
			}
			while (count < size && tasks.get(count).start <= currentTime) {
				pq.offer(tasks.get(count));
				count++;
			}
			Task task = pq.poll();
			a[index++] = task.index;
			currentTime += task.process;
		}
		return a;
	}

	private static List<Task> tasks(int[][] tasks) {
		List<Task> list = new ArrayList<>();
		for (int i = 0; i < tasks.length; i++) {
			int[] task = tasks[i];
			list.add(new Task(task[0], task[1], i));
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] tasks1 = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
		print(order(tasks1)); // 0,2,3,1

		int[][] tasks2 = { { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 } };
		print(order(tasks2)); // 4,3,2,0,1
	}

	private static class Task implements Comparable<Task> {
		private int start;
		private int process;
		private int index;

		public Task(int start, int process, int index) {
			this.start = start;
			this.process = process;
			this.index = index;
		}

		@Override
		public int compareTo(Task other) {
			if (this.process == other.process) {
				return this.index - other.index;
			}
			return this.process - other.process;
		}

		@Override
		public String toString() {
			return start + ":" + process + ":" + index;
		}
	}

}