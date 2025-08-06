package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1845. Seat Reservation Manager

public final class SeatReservationManagerEfficient {

	private int next;
	private PriorityQueue<Integer> pq;

	public SeatReservationManagerEfficient(int n) {
		this.next = 1;
		this.pq = new PriorityQueue<>();
	}

	public int reserve() {
		if (!pq.isEmpty() && pq.peek() < next) {
			return pq.poll();
		}
		return next++;
	}

	public void unreserve(int seat) {
		pq.offer(seat);
	}

	public static void main(String[] args) {
		SeatReservationManagerEfficient srm = new SeatReservationManagerEfficient(5);
		System.out.println(srm.reserve());
		System.out.println(srm.reserve());
		srm.unreserve(2);
		System.out.println(srm.reserve());
		System.out.println(srm.reserve());
		System.out.println(srm.reserve());
		System.out.println(srm.reserve());
		srm.unreserve(5);
	}
}
