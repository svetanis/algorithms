package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.PriorityQueue;

// Given a list of jobs, each with a start time, an end time and a CPU load,
// find the maximum total load at any single moment. Jobs run concurrently,
// so the answer is the largest sum of loads over the jobs active at once --
// not a count of jobs, which is what MinMeetingRooms answers.

public final class MaxCPULoad {
	// Time Complexity: O(n*log n)
	// Space Complexity: O(n)

	public static int maxLoad(List<Job> jobs) {
		int max = 0;
		int load = 0;
		List<Job> sorted = sort(jobs, comparing(j -> j.start));
		PriorityQueue<Job> pq = new PriorityQueue<>(comparing(j -> j.end));
		for (Job job : sorted) {
			while (!pq.isEmpty() && job.start > pq.peek().end) {
				load -= pq.poll().load;
			}
			pq.offer(job);
			load += job.load;
			max = max(max, load);
		}
		return max;
	}

	public static void main(String[] args) {
		List<Job> list1 = newArrayList();
		list1.add(new Job(1, 4, 3));
		list1.add(new Job(2, 5, 4));
		list1.add(new Job(7, 9, 6));
		System.out.println(maxLoad(list1));

		List<Job> list2 = newArrayList();
		list2.add(new Job(6, 7, 10));
		list2.add(new Job(2, 4, 11));
		list2.add(new Job(8, 12, 15));
		System.out.println(maxLoad(list2));

		List<Job> list3 = newArrayList();
		list3.add(new Job(1, 4, 2));
		list3.add(new Job(2, 4, 1));
		list3.add(new Job(3, 6, 5));
		System.out.println(maxLoad(list3));
	}

	private static final class Job {
		protected int start;
		protected int end;
		protected int load;

		public Job(int start, int end, int load) {
			this.start = start;
			this.end = end;
			this.load = load;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + ", " + load + "]";
		}
	}
}
