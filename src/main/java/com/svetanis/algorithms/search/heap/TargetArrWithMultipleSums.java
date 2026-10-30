package com.svetanis.algorithms.search.heap;

import java.util.Collections;
import java.util.PriorityQueue;

// 1354. Construct Target Array With Multiple Sums
//
// Start from an array of n ones. One step: let x be the sum of the array, pick any index i and set
// arr[i] = x. Given target, returns true if some sequence of steps turns the ones into target.
// 1 <= n <= 5 * 10^4, 1 <= target[i] <= 10^9.
//
// Run the steps backwards. The value written last is the sum of everything, so it is the largest
// element, and before it was written it held max - rest, where rest is the sum of the others.
// While what it holds is still larger than rest it is still the largest element (rest is at least
// any one of the others), so the same element keeps being undone, and the repeated subtraction
// collapses into max % rest. It fails when that leaves 0 (rest divides max) or does not go down
// (rest > max, so max - rest would be below 1). rest == 1 means two elements, the other a 1, and
// [1, 1] reaches [any, 1] -- checked first, since max % 1 is always 0. A max-heap names the
// largest element each time; the sum is a long, n values of 10^9 pass the int range.
//
// Time: O(n log n + n log M log n), M the largest value -- x % y < x / 2 when y <= x, so each
//   element is reduced at most log M times, each a heap poll and push
// Space: O(n) for the heap

public final class TargetArrWithMultipleSums {

	public static boolean canConstruct(int[] a) {
		int n = a.length;
		if (n == 1) {
			return a[0] == 1;                              // SINGLE: one element is only ever 1
		}
		long totalSum = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder()); // MAX-HEAP: the last value written is on top
		for (int num : a) {
			totalSum += num;
			pq.offer((long) num);                          // PUSH: every element
		}
		while (pq.peek() > 1) {                            // DONE: when the largest is 1, every element is 1
			long max = pq.poll();                          // POLL: the value written last
			long remainingSum = totalSum - max;            // REST: the sum of the others
			if (remainingSum == 1) {
				return true;                               // PAIR: [x, 1] is always reachable from [1, 1]
			}
			long value = max % remainingSum;               // UNDO: every subtraction of rest at once
			if (value == 0 || value == max) {
				return false;                              // FAIL: undoing would leave 0, or max was not above rest
			}
			pq.offer(value);                               // PUSH: the element as it was before
			totalSum = totalSum - max + value;             // SUM: updated for the undone element
		}
		return true;                                       // REPORT: back to all ones
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 3, 5 };
		System.out.println(canConstruct(a1)); // true
		int[] a2 = { 1, 1, 1, 2 };
		System.out.println(canConstruct(a2)); // false
		int[] a3 = { 8, 5 };
		System.out.println(canConstruct(a3)); // true
	}
}
