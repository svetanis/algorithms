package com.svetanis.algorithms.intervals.overlap;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.svetanis.algorithms.intervals.Interval;

// The most guests at a party at the same time.
//
// Each interval is one guest: start = entry time, end = exit time, start <= end. Returns the
// largest number of guests present at the same moment, but not that moment. A guest is
// present at both the entry and the exit time, so a guest leaving at t and one arriving at t
// are both counted at t. An empty list gives 0.
//
// This version keeps the guests themselves, not separate counts, and so cannot also say when
// the peak happens. One heap entry is a guest at the party right now; the one who leaves
// first is on top. Guests are taken in order of entry. When a guest enters, every guest who
// left strictly earlier leaves the heap. What remains, plus the new guest, are the guests
// present at this entry time. The count only rises when someone enters, so the largest of
// these heap sizes is the answer.
//
// MaxGuestsTwoArrays sorts the entries and exits apart, and so can also report the time.

public final class MaxGuestsPriorityQueue {
  // Time Complexity: O(n log n), the sort; each guest enters and leaves the heap once
  // Space Complexity: O(n), the sorted copy and the heap

  public static int maxGuests(List<Interval> intervals) {
    int max = 0;
    List<Interval> sorted = new ArrayList<>(intervals); // a copy: the caller's list keeps its order
    sorted.sort((a, b) -> Integer.compare(a.start, b.start));
    PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end)); // leaves first on top

    for (Interval interval : sorted) {
      while (!pq.isEmpty() && interval.start > pq.peek().end) { // left before, not at, this entry
        pq.poll();
      }
      pq.offer(interval);
      max = max(max, pq.size()); // guests present at this entry time
    }
    return max;
  }

  public static void main(String[] args) {
    List<Interval> list = new ArrayList<>();
    list.add(new Interval(1, 4));
    list.add(new Interval(2, 5));
    list.add(new Interval(10, 12));
    list.add(new Interval(5, 9));
    list.add(new Interval(5, 12));
    System.out.println(maxGuests(list)); // 3
  }
}
