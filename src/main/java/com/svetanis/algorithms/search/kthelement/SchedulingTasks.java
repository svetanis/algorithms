package com.svetanis.algorithms.search.kthelement;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// given a list of tasks that need to be run
// in any order on a server. each task will
// take one CPU interval to execute but once
// a task has finished, it has a cooling
// period during which it can't be run again.
// if the cooling period for all tasks is 
// 'K' intervals, find the minimum number
// of CPU intervals that the server needs
// to finish all tasks. if at any time the 
// server can't execute any task then it
// must stay idle.

public final class SchedulingTasks {

	public static int schedulingTasks(char[] tasks, int k) {
		// Time Complexity: O(n * log n)
		// Space Complexity: O(n)

		Map<Character, Integer> map = frequencyMap(tasks);
		Queue<Character> pq = maxHeap(map);
		Queue<Character> queue = newLinkedList();
		int count = 0;
		int intervals = 0;
		while (count < tasks.length) {
			while (!pq.isEmpty()) {
				Character curr = pq.poll();
				// execute a task
				intervals++;
				// decrement its frequency
				int freq = map.get(curr) - 1;
				map.put(curr, freq);
				// put it in a waiting list
				if (freq > 0) {
					queue.add(curr);
				}
			}

			if (count < k) {
				intervals++;
			}
			else if (count == k) {
				// insert all waiting tasks
				// back the max heap
				// after k iterations
				pq.addAll(queue);
			}
			count++;
		}
		return intervals;
	}

	private static Queue<Character> maxHeap(Map<Character, Integer> map) {
		Queue<Character> pq = new PriorityQueue<>((x, y) -> map.get(y) - map.get(x));
		for (char c : map.keySet()) {
			pq.add(c);
		}
		return pq;
	}

	private static Map<Character, Integer> frequencyMap(char[] chars) {
		Map<Character, Integer> map = newHashMap();
		for (char c : chars) {
			int freq = map.getOrDefault(c, 0);
			map.put(c, freq + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		char[] tasks1 = { 'a', 'a', 'a', 'b', 'c', 'c' };
		System.out.println(schedulingTasks(tasks1, 2));

		char[] tasks2 = { 'a', 'b', 'a' };
		System.out.println(schedulingTasks(tasks2, 3));
	}
}