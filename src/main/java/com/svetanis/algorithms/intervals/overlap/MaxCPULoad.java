package com.svetanis.algorithms.intervals.overlap;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// The largest total CPU load at any single moment.
//
// Each job has a start time, an end time (start <= end) and a CPU load it adds while it runs.
// Returns the largest sum of loads over the jobs running at the same moment; an empty list gives 0.
// A job is running at both its start and its end time, so a job ending at t and one starting at t
// add their loads together at t. The source's examples never test that moment; its reference
// solution removes a job only when the next one starts strictly after it ends, which is the
// comparison used here.
//
// The answer is not how many jobs run at once but the sum of their loads, so a running total
// of loads is kept next to the heap. One heap entry is a job running right now; the one that
// ends first is on top. load is the sum of the loads of the jobs in the heap. Jobs are taken
// in order of start time. When a job starts, every job that ended strictly earlier leaves the
// heap and its load is taken off. What remains, plus the new job, are the jobs running at
// this start time. The total only rises when a job starts, so its largest value at these
// moments is the answer.

public final class MaxCPULoad {
	// Time Complexity: O(n log n), the sort; each job enters and leaves the heap once
	// Space Complexity: O(n), the sorted copy and the heap

	public static int maxLoad(List<Job> jobs) {
		int max = 0;
		int load = 0; // total load of the jobs in the heap
		List<Job> sorted = new ArrayList<>(jobs); // a copy: the caller's list keeps its order
		sorted.sort((a, b) -> Integer.compare(a.start, b.start));
		PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end)); // first to end on top
		for (Job job : sorted) {
			while (!pq.isEmpty() && job.start > pq.peek().end) { // ended before, not at, this start
				load -= pq.poll().load;
			}
			pq.offer(job);
			load += job.load;
			max = max(max, load); // total load at this start time
		}
		return max;
	}

	public static void main(String[] args) {
		List<Job> list1 = new ArrayList<>();
		list1.add(new Job(1, 4, 3));
		list1.add(new Job(2, 5, 4));
		list1.add(new Job(7, 9, 6));
		System.out.println(maxLoad(list1)); // 7

		List<Job> list2 = new ArrayList<>();
		list2.add(new Job(6, 7, 10));
		list2.add(new Job(2, 4, 11));
		list2.add(new Job(8, 12, 15));
		System.out.println(maxLoad(list2)); // 15

		List<Job> list3 = new ArrayList<>();
		list3.add(new Job(1, 4, 2));
		list3.add(new Job(2, 4, 1));
		list3.add(new Job(3, 6, 5));
		System.out.println(maxLoad(list3)); // 8
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
