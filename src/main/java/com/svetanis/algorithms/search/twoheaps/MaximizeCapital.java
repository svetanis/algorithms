package com.svetanis.algorithms.search.twoheaps;

import java.util.PriorityQueue;
import java.util.Queue;

// given an array of numbers and a number k,
// find the median of all the k sized sub-arrays
// (or windows) of the array

public final class MaximizeCapital {

	public static int maximize(int[] c, int[] p, int k, int init) {
		// Time Complexity: O(n * log n + k * log n)
		// Space Complexity: O(n)

		Queue<Integer> min = new PriorityQueue<>((i, j) -> c[i] - c[j]);
		Queue<Integer> max = new PriorityQueue<>((i, j) -> p[j] - p[i]);

		// add all capitals to min-heap s.t.
		// we can select a project with the
		// smallest capital requirement
		for (int i = 0; i < c.length; i++) {
			min.add(i);
		}
		int total = init + add(min, max, p, init);
		for (int i = 1; i < k; i++) {
			total += add(min, max, p, total);
		}
		return total;
	}

	private static int add(Queue<Integer> min, Queue<Integer> max, int[] p, int target) {
		// go through the top projects of the min heap and
		// filter the projects that can be completed within
		// our available capital. insert the profits of all
		// these projects into a max-heap, s.t. we can choose
		// a project with the max profit
		while (!min.isEmpty() && min.peek() <= target) {
			max.add(min.poll());
		}
		// select the top project of the max heap for investment
		return p[max.poll()];
	}

	public static void main(String[] args) {
		int[] c1 = { 0, 1, 2 };
		int[] p1 = { 1, 2, 3 };
		System.out.println(maximize(c1, p1, 2, 1)); // 6

		int[] c2 = { 0, 1, 2, 3 };
		int[] p2 = { 1, 2, 3, 5 };
		System.out.println(maximize(c2, p2, 3, 0)); // 8
	}
}
