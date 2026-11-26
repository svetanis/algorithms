package com.svetanis.algorithms.search.topk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// 895. Maximum Frequency Stack
//
// A stack with two operations. push(num) puts num on top. pop() removes and returns the number
// with the most copies in the stack right now; among numbers tied for the most copies, the one
// whose latest copy was pushed most recently.
//
// Every push becomes its own heap entry carrying three numbers: the value, how many copies of
// that value the stack holds once this one is on (its count at push time), and a sequence
// number that only grows. The heap puts the highest count on top and, among equal counts, the
// highest sequence number. An entry's count never needs updating: the entry for the 3rd copy
// of a value says 3, and the 1st and 2nd copies are still in the heap saying 1 and 2. Popping
// the 3rd copy leaves the 2nd as that value's best entry, and 2 is exactly its count now.
//
// Siblings: the same problem with one stack per count instead of a heap, O(1) per operation,
// is data-structures' stack/design/FreqStack.

public final class FrequencyStack {
	// Time Complexity: push, pop: O(log n), one heap insert or removal, n = numbers on the stack
	// Space Complexity: O(n), one heap entry per number on the stack

	private int sequence;
	private Queue<Element> pq;
	private Map<Integer, Integer> map;

	public FrequencyStack() {
		this.sequence = 0;
		this.map = new HashMap<>();
		this.pq = new PriorityQueue<>(new ElementComparator());
	}

	public void push(int num) {
		sequence += 1; // STAMP: a later push always gets a larger number
		int freq = map.getOrDefault(num, 0) + 1; // COUNT: copies of num, this one included
		map.put(num, freq);
		pq.add(new Element(num, freq, sequence)); // PUSH: the count is frozen into the entry
	}

	public int pop() {
		if (pq.size() == 0) {
			throw new IllegalArgumentException("stack is empty");
		}
		int val = pq.poll().val; // POLL: highest count, then latest push
		int frq = map.get(val) - 1; // COUNT: one copy fewer
		if (frq > 0) {
			map.put(val, frq);
		} else {
			map.remove(val); // FORGET: a later push of val starts again at 1
		}
		return val; // REPORT
	}

	public static void main(String[] args) {
		// 2 -> 3, 1 -> 2, 3 -> 1, 5 -> 1
		FrequencyStack fs = new FrequencyStack();
		fs.push(1); // [1, 1, 0]
		fs.push(2); // [2, 1, 1]
		fs.push(3); // [3, 1, 2]
		fs.push(2); // [2, 2, 3] --> 3rd pop
		fs.push(1); // [1, 2, 4] --> 2nd pop
		fs.push(2); // [2, 3, 5] --> 1st pop
		fs.push(5); // [5, 1, 6]

		System.out.println(fs.pop()); // 2
		System.out.println(fs.pop()); // 1
		System.out.println(fs.pop()); // 2
	}

	private final static class Element {

		private final int val;
		private final int frq; // COUNT of val on the stack when this copy went on
		private final int seq; // STAMP: push order

		public Element(int val, int frq, int seq) {
			this.val = val;
			this.frq = frq;
			this.seq = seq;
		}

		@Override
		public String toString() {
			return "%d,%d,%d".formatted(val, frq, seq);
		}
	}

	private static final class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element e1, Element e2) {
			if (e2.frq != e1.frq) {
				return Integer.compare(e2.frq, e1.frq); // ORDER: higher count first
			}
			// if both elements have same frequency,
			// return the one that was pushed later
			return Integer.compare(e2.seq, e1.seq); // TIE: later push first
		}
	}
}
