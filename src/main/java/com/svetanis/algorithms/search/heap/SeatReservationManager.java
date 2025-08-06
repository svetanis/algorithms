package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1845. Seat Reservation Manager

public final class SeatReservationManager {

	private PriorityQueue<Integer> pq;

	public SeatReservationManager(int n) {
		this.pq = new PriorityQueue<>();
		for (int seat = 1; seat <= n; seat++) {
			pq.offer(seat);
		}
	}

	public int reserve() {
		return pq.poll();
	}

	public void unreserve(int seat) {
		pq.offer(seat);
	}

	public static void main(String[] args) {
		SeatReservationManager srm = new SeatReservationManager(5);
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
