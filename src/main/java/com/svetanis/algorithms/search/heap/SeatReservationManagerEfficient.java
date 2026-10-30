package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1845. Seat Reservation Manager
//
// Seats are numbered 1 to n, all free at the start. reserve() takes the smallest-numbered free seat
// and returns its number; unreserve(seatNumber) frees a seat. reserve is only called while a seat
// is free, and unreserve only on a reserved seat.
//
// The seats from next upward have never been reserved, so they need no storage: one counter stands
// for all of them. The heap holds only the seats given back, and every one of those was handed out
// before, so it is below next. The smallest free seat is the heap's top if the heap has one, and
// next otherwise. n is never read: the problem promises reserve is not called when every seat is
// taken.
//
// Time: constructor O(1); reserve and unreserve O(log r), r the seats given back and not retaken
// Space: O(r)
//
// Siblings, same problem:
//   SeatReservationManager -- every free seat in one min-heap from the start; O(n) constructor and space

public final class SeatReservationManagerEfficient {

	private int next;                      // NEXT: the smallest seat never reserved; every seat above it is free too
	private PriorityQueue<Integer> pq;     // MIN-HEAP: the seats given back, all below next

	public SeatReservationManagerEfficient(int n) {
		this.next = 1;
		this.pq = new PriorityQueue<>();
	}

	public int reserve() {
		if (!pq.isEmpty() && pq.peek() < next) {
			return pq.poll();              // POLL: a returned seat is below next, so it is the smallest free
		}
		return next++;                     // TAKE: no seat was given back; the first never-reserved seat
	}

	public void unreserve(int seat) {
		pq.offer(seat);                    // PUSH: the seat is free again
	}

	public static void main(String[] args) {
		SeatReservationManagerEfficient srm = new SeatReservationManagerEfficient(5);
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
