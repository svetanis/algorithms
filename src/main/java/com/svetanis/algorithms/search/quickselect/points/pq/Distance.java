package com.svetanis.algorithms.search.quickselect.points.pq;

public final class Distance implements Comparable<Distance> {

	protected int dist;
	protected int index;

	public Distance(int dist, int index) {
		this.dist = dist;
		this.index = index;
	}

	@Override
	public int compareTo(Distance other) {
		return this.dist - other.dist;
	}
}