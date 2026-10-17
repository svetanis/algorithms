package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 2260. Minimum Consecutive Cards to Pick Up
//
// a bunch of cards is laid out in a line,
// where the value of each card ranges from 0 to 10^6
// a pair of cards is matching if they have
// the same number value.
//
// given a list of integers, match a pair of cards
// but you can only pick up cards in a consecutive
// manner. what's the min number of cards that you
// need to pick up to make a pair?
//
// Input: the card values in line order.
// Return: the fewest consecutive cards holding a matching pair, or -1 if no two match.
//
// A shortest window: grow on the right, and whenever the window holds a pair, measure it
// and then drop from the left, measuring again, until the pair is gone. A pair can only
// appear when the newest card matches one already inside, so the shrink only has to watch
// the newest card's value.
// match keeps a count per value: the window holds a pair while the newest card's count
// is above 1. matchSimple keeps the set of values inside the window before the newest
// card joins: the newest card makes a pair while its value is still in the set, and it
// joins only after its earlier copy has left.
//
// Time: O(n) for both -- right and left only move forward; each map or set update is
//   O(1) on average
// Space: O(n) -- the counts or the set, at most one entry per distinct value
//
// Siblings: the two methods above are the window versions.
// todo/slidingwindow/MinConsecutiveCardsToPickUp solves 2260 with no window: it keeps
// the last position of each value and measures from there.

public final class LeastConsecutiveCardsToMatch {

	public static int match(List<Integer> list) {
		int left = 0;
		int min = MAX_VALUE;
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < list.size(); right++) {
			int curr = list.get(right);
			map.put(curr, map.getOrDefault(curr, 0) + 1); // ADD
			while (map.get(curr) > 1) { // SHRINK while valid: a pair of curr is inside
				int first = list.get(left);
				min = min(min, right - left + 1); // MEASURE before list[left] leaves
				map.put(first, map.get(first) - 1); // REMOVE
				left++;
			}
		}
		return min == MAX_VALUE ? -1 : min; // RETURN: -1 when no value repeats
	}

	public static int matchSimple(List<Integer> list) {
		int left = 0;
		int min = MAX_VALUE;
		Set<Integer> set = new HashSet<>();
		for (int right = 0; right < list.size(); right++) {
			int curr = list.get(right);
			while (set.contains(curr)) { // SHRINK while valid: curr matches a card inside
				min = min(min, right - left + 1); // MEASURE before list[left] leaves
				set.remove(list.get(left)); // REMOVE
				left++;
			}
			set.add(curr); // ADD, once its earlier copy has left
		}
		return min == MAX_VALUE ? -1 : min; // RETURN: -1 when no value repeats
	}

	public static void main(String[] args) {
		System.out.println(match(asList(3, 4, 2, 3, 4, 7))); // 4
		System.out.println(match(asList(1, 0, 5, 3))); // -1
		System.out.println(match(asList(5))); // -1
		System.out.println(match(asList(7, 7))); // 2
	}
}
