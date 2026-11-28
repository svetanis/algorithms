package com.svetanis.algorithms.search.twoheaps;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 2534. Time Taken to Cross the Door
// Asked at Google
//
// Person i arrives at the door at second arrival[i], in non-decreasing order, and wants to enter
// (state[i] = 0) or exit (state[i] = 1). Each second one person can cross. When several are
// waiting: if the door was not used in the previous second, an exiting person goes first; if it
// was used to enter, an entering person goes first; if it was used to exit, an exiting person goes
// first. Within one direction, the smallest index goes first. Returns the second each person crosses.
//
// One pass per second. The arrivals are already sorted, so position i is the person: nothing is
// sorted or copied, and answer[i] is written directly. People join the line of their direction in
// index order, so plain queues keep the smallest index in front -- no heap. The three door rules
// have two outcomes ("not used" acts like "exit"), so the door remembers one number, last: the
// direction with priority. PICK reads it, CROSS writes it, so it always describes the previous
// second; a second with nobody waiting resets it to exit.
//
// It sits beside the two-heap problems because it is the same family -- events in time order,
// one loop walking a clock -- not because it uses a heap. With unsorted arrivals the lines would
// have to become min-heaps of indexes: people would no longer join in index order.

public final class TimeToCrossTheDoor {
	// Time complexity: O(n), one pass per second, and the last crossing is at most about 2n
	// seconds in, because every arrival is <= n
	// Auxiliary Space: O(n), the two queues

	public static int[] timeTaken(int[] arrival, int[] state) {
		int n = arrival.length;
		int[] answer = new int[n];
		Deque<Integer> enter = new ArrayDeque<>(); // waiting to enter, smallest index in front
		Deque<Integer> exit = new ArrayDeque<>(); // waiting to exit, smallest index in front
		int i = 0; // next person not yet at the door
		int t = 0; // the current second
		int last = 1; // direction with priority: 1 = exit (also "not used"), 0 = enter

		// someone not yet at the door, or someone waiting
		while (i < n || !enter.isEmpty() || !exit.isEmpty()) {
			// JOIN: everyone with arrival <= t goes to the back of their line,
			// as the person's number, not the arrival time
			while (i < n && arrival[i] <= t) {
				if (state[i] == 0) {
					enter.offer(i);
				} else {
					exit.offer(i);
				}
				i += 1;
			}
			if (enter.isEmpty() && exit.isEmpty()) {
				// EMPTY: the door is unused this second, so exit has priority next second;
				// nobody crosses, so no answer is written
				last = 1;
			} else {
				// PICK: one branch per row of the rule table
				int direction;
				if (enter.isEmpty()) {
					direction = 1; // only someone to exit
				} else if (exit.isEmpty()) {
					direction = 0; // only someone to enter
				} else {
					direction = last; // both waiting: same way as the second before
				}
				// CROSS: the front of that line
				int person = direction == 1 ? exit.poll() : enter.poll();
				answer[person] = t;
				last = direction;
			}
			// TICK: one second per pass
			t += 1;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 1, 2, 4 };
		int[] s1 = { 0, 1, 0, 0, 1 };
		System.out.println(Arrays.toString(timeTaken(a1, s1))); // [0, 3, 1, 2, 4]

		int[] a2 = { 0, 0, 0 };
		int[] s2 = { 1, 0, 1 };
		System.out.println(Arrays.toString(timeTaken(a2, s2))); // [0, 2, 1]

		// a second with nobody at the door resets the priority to exit
		int[] a3 = { 0, 2 };
		int[] s3 = { 1, 1 };
		System.out.println(Arrays.toString(timeTaken(a3, s3))); // [0, 2]
	}
}
