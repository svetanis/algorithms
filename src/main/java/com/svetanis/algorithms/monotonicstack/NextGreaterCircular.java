package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class NextGreaterCircular {

	public static List<Integer> nextGreater(List<Integer> list) {
		// Time complexity: O(n)

		int n = list.size();
		Integer[] greater = new Integer[n];
		fill(greater, -1);

		Deque<Element> dq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			int curr = list.get(i);
			while (!dq.isEmpty() && curr > dq.peekLast().val) {
				int top = dq.pollLast().ind;
				greater[top] = curr;
			}
			dq.add(new Element(curr, i));
		}
		for (int curr : list) {
			while (curr > dq.peekLast().val) {
				Element e = dq.pollLast();
				greater[e.ind] = curr;
			}
		}
		return asList(greater);
	}

	public static void main(String[] args) {
		print(nextGreater(asList(1, 2, 1))); // 2, -1, 2
		// 2, 4, 2, 2, 2, 4, -1, 2
		print(nextGreater(asList(1, 2, 1, 1, 1, 2, 4, 1)));
		// 5, 6, 8, 9, -1, 2, 4, 2, 4
		print(nextGreater(asList(4, 5, 6, 8, 9, 1, 2, 1, 2)));
		// 6, 3, 6, 8, 4, 8, -1, 8, 3, 8, -1, 8
		print(nextGreater(asList(5, 2, 3, 6, 1, 4, 8, 7, 2, 3, 8, 6)));
		// 10, 10, -1, 10, 10, 10, 10, 10, 10, 10
		print(nextGreater(asList(2, 1, 10, 9, 8, 7, 6, 5, 4, 3)));
		// 4, 5, 6, 4, 5, 6, 4, 5, 6, 7, -1, 2, 3
		print(nextGreater(asList(3, 4, 5, 3, 4, 5, 3, 4, 5, 6, 7, 1, 2)));
	}

	private static class Element {
		private int val;
		private int ind;

		public Element(int val, int ind) {
			this.val = val;
			this.ind = ind;
		}

		@Override
		public String toString() {
			return "[" + val + ", " + ind + "]";
		}
	}
}