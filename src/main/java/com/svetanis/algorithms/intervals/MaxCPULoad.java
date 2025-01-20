package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.PriorityQueue;

// Given a list of intervals representing the start and end time of ‘N’ meetings, 
// find the minimum number of rooms required to hold all the meetings.

public final class MaxCPULoad {
  // Time Complexity: O(n*log n)
  // Space Complexity: O(n)

  public static int minMeetingRooms(List<Job> jobs) {
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
    System.out.println(minMeetingRooms(list1));

    List<Job> list2 = newArrayList();
    list2.add(new Job(6, 7, 10));
    list2.add(new Job(2, 4, 11));
    list2.add(new Job(8, 12, 15));
    System.out.println(minMeetingRooms(list2));

    List<Job> list3 = newArrayList();
    list3.add(new Job(1, 4, 2));
    list3.add(new Job(2, 4, 1));
    list3.add(new Job(3, 6, 5));
    System.out.println(minMeetingRooms(list3));
  }
}
