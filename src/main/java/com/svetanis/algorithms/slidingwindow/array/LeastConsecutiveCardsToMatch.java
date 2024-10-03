package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// a bunch of cards is laid out in a line,
// where the value of each card ranges from 0 to 10^6
// a pair of cards is matching if they have 
// the same number value.

// given a list of integers, match a pair of cards
// but you can only pick up cards in a consecutive
// manner. what's the min number of cards that you
// need to pick up to make a pair? 

public final class LeastConsecutiveCardsToMatch {
	// Time complexity: O(n)

	public static int match(List<Integer> list) {
		int left = 0;
		int min = MAX_VALUE;
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < list.size(); right++) {
			int curr = list.get(right);
			map.put(curr, map.getOrDefault(curr, 0) + 1);
			while (map.get(curr) > 1) {
				int first = list.get(left);
				min = min(min, right - left + 1);
				map.put(first, map.get(first) - 1);
				left++;
			}
		}
		return min == MAX_VALUE ? -1 : min;
	}

	public static int matchSimple(List<Integer> list) {
		int left = 0;
		int min = MAX_VALUE;
		Set<Integer> set = new HashSet<>();
		for (int right = 0; right < list.size(); right++) {
			int curr = list.get(right);
			while (set.contains(curr)) {
				min = min(min, right - left + 1);
				set.remove(list.get(left));
				left++;
			}
			set.add(curr);
		}
		return min == MAX_VALUE ? -1 : min;
	}

	public static void main(String[] args) {
		System.out.println(match(asList(3, 4, 2, 3, 4, 7))); // 4
		System.out.println(match(asList(1, 0, 5, 3))); // -1
		System.out.println(match(asList(5))); // -1
		System.out.println(match(asList(7, 7))); // 2
	}
}
