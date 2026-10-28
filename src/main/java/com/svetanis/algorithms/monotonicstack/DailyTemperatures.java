package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 739. Daily Temperatures
//
// temperatures[i] is the temperature on day i; the answer is an int array of the same length.
// For each day, the number of days until a warmer temperature; 0 if no warmer day comes.
//
// The stack holds the days still waiting for a warmer one. Their temperatures never rise from
// bottom to top: a warmer day would already have answered every colder day below it.

public final class DailyTemperatures {
	// Time Complexity: O(n), every day is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		int[] wait = new int[n]; // ANSWER: stays 0 for a day that never gets a warmer one
		Deque<Integer> stack = new ArrayDeque<>(); // days (positions), not temperatures
		for (int day = 0; day < n; day++) { // WALK: today arrives
			while (!stack.isEmpty() && temperatures[day] > temperatures[stack.peek()]) { // STOP: strictly warmer; an equal day waits on
				int colder = stack.pop(); // POP: today is the first warmer day after it
				wait[colder] = day - colder; // RECORD: the days the colder day waited
			}
			stack.push(day); // WAIT: today waits for a warmer day
		}
		return wait; // RETURN: one wait per day
	}

	public static void main(String[] args) {
		int[] a1 = { 73, 74, 75, 71, 69, 72, 76, 73 };
		System.out.println(Arrays.toString(dailyTemperatures(a1))); // [1, 1, 4, 2, 1, 1, 0, 0]

		int[] a2 = { 30, 40, 50, 60 };
		System.out.println(Arrays.toString(dailyTemperatures(a2))); // [1, 1, 1, 0]

		int[] a3 = { 30, 60, 90 };
		System.out.println(Arrays.toString(dailyTemperatures(a3))); // [1, 1, 0]
	}
}
