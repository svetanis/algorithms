package com.svetanis.algorithms.slidingwindow.fixed;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Map;

// 2461. Maximum Sum of Distinct Subarrays With Length K

// Given an array of positive numbers and a positive number ‘k’, 
// find the maximum sum of any contiguous subarray such that 
// 1. the length of the subarray is k
// 2. all the elements of the subarray are distinct
// return the max subarray sum of all the subarrays
// that meet the conditions. if no subarray meets the
// conditions, return 0.

// a subarray is a contiguous non-empty sequence of elements 
// within an array

public final class MaxSumDistinctSubArraySizeK {
	// Time Complexity: O(n)
	// Aux Space: O(n)

	public static int maxSum(int k, List<Integer> list) {
		int n = list.size();
		if (n < k) {
			return -1;
		}
		Map<Integer, Integer> map = newHashMap();
		int sum = sum(list, k, map);
		int max = map.size() == k ? sum : 0;
		for (int i = k; i < n; i++) {
			int left = list.get(i - k);
			int right = list.get(i);
			sum += right - left;
			// add current to map and update its frequency
			map.put(right, map.getOrDefault(right, 0) + 1);
			// decrement the count of the element that is k
			// positions behind the current and remove it 
			// from the map if its count becomes 0
			int count = map.getOrDefault(left, 0) - 1;
			map.put(left, count);
			if (count == 0) {
				map.remove(left);
			}
			// update the max sum when there are k distinct 
			// elements in the map
			if (map.size() == k) {
				max = max(max, sum);
			}
		}
		return max;
	}

	private static int sum(List<Integer> list, int k, Map<Integer, Integer> map) {
		int sum = 0;
		for (int i = 0; i < k; i++) {
			int curr = list.get(i);
			int freq = map.getOrDefault(curr, 0);
			map.put(curr, freq + 1);
			sum += curr;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(maxSum(3, asList(1, 5, 4, 2, 9, 9, 9))); // 15
		System.out.println(maxSum(3, asList(4, 4, 4))); // 0
	}
}
