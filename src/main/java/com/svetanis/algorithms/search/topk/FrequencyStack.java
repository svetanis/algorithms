package com.svetanis.algorithms.search.topk;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Joiner;

// design a class that simulates 
// a stack data structure with
// the following two operations

// push(int num) : pushes the number on the stack
// pop() : returns the most frequent number in the stack
// if there is a tie, return the number which was pushed later

public final class FrequencyStack {

	private int sequence;
	private Queue<Element> pq;
	private Map<Integer, Integer> map;

	public FrequencyStack() {
		this.sequence = 0;
		this.map = newHashMap();
		this.pq = new PriorityQueue<>(new ElementComparator());
	}

	public void push(int num) {
		sequence += 1;
		int freq = map.getOrDefault(num, 0) + 1;
		map.put(num, freq);
		pq.add(new Element(num, freq, sequence));
	}

	public int pop() {
		if (pq.size() == 0) {
			throw new IllegalArgumentException("stack is empty");
		}
		int val = pq.poll().val;
		int frq = map.get(val) - 1;
		if (frq > 0) {
			map.put(val, frq);
		} else {
			map.remove(val);
		}
		return val;
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
		private final int frq;
		private final int seq;

		public Element(int val, int frq, int seq) {
			this.val = val;
			this.frq = frq;
			this.seq = seq;
		}

		@Override
		public String toString() {
			return Joiner.on(',').join(val, frq, seq);
		}
	}

	private static final class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element e1, Element e2) {
			if (e2.frq != e1.frq) {
				return e2.frq - e1.frq;
			}
			// if both elements have same frequency,
			// return the one that was pushed later
			return e2.seq - e1.seq;
		}
	}
}
