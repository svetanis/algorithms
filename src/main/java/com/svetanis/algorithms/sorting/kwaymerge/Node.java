package com.svetanis.algorithms.sorting.kwaymerge;

public final class Node implements Comparable<Node> {

	protected int val; // the element to be stored
	protected int id; // index of the array from which the element is taken
	protected int next; // index of the next element to be picked from array

	Node(int value, int id, int next) {
		this.val = value;
		this.id = id;
		this.next = next;
	}

	@Override
	public int compareTo(Node other) {
		return this.val - other.val;
	}
}