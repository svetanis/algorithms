package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1845. Seat Reservation Manager
//
// Seats are numbered 1 to n, all free at the start. reserve() takes the smallest-numbered free seat
// and returns its number; unreserve(seatNumber) frees a seat. reserve is only called while a seat
// is free, and unreserve only on a reserved seat.
//
// Every free seat sits in a min-heap, so the smallest is always on top.
//
// Time: constructor O(n) -- the seats go in increasing order, so no push moves up the heap;
//       reserve and unreserve O(log n)
// Space: O(n) -- every seat is in the heap from the start
//
// Siblings, same problem:
//   SeatReservationManagerEfficient -- the heap holds only the seats given back, and a counter
//     stands for every seat never reserved; O(1) constructor, space only for returned seats

public final class SeatReservationManager {

	private PriorityQueue<Integer> pq;     // MIN-HEAP: every free seat, the smallest on top

	public SeatReservationManager(int n) {
		this.pq = new PriorityQueue<>();
		for (int seat = 1; seat <= n; seat++) {
			pq.offer(seat);                // PUSH: every seat starts free
		}
	}

	public int reserve() {
		return pq.poll();                  // POLL: the smallest free seat
	}

	public void unreserve(int seat) {
		pq.offer(seat);                    // PUSH: the seat is free again
	}

	public static void main(String[] args) {
		SeatReservationManager srm = new SeatReservationManager(5);
		System.out.println(srm.reserve()); // 1
		System.out.println(srm.reserve()); // 2
		srm.unreserve(2);
		System.out.println(srm.reserve()); // 2
		System.out.println(srm.reserve()); // 3
		System.out.println(srm.reserve()); // 4
		System.out.println(srm.reserve()); // 5
		srm.unreserve(5);
	}
}
