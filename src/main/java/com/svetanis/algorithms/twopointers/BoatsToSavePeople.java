package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 881. Boats to Save People
//
// Input: people's weights and a boat limit; no weight is above the limit. A boat
// carries at most TWO people, and their weights may add up to at most the limit.
// Return: the fewest boats that carry everyone.
//
// The one idea: the heaviest person left needs a boat, and the only partner worth
// trying is the lightest person left. If even the lightest does not fit, nobody
// does, so the heaviest goes alone. If the lightest fits, taking them loses nothing:
// in any plan where the heaviest rides with someone else X and the lightest rides
// with Y, swap X and the lightest -- X's new partner Y weighs no more than the
// heaviest did, so X still fits. Every step sends the heaviest off, with or without
// the lightest.
// The loop runs while left <= right: one person left still needs a boat of their
// own, and with < that person is never counted.
//
// Time: O(n log n) -- the sort; the walk is O(n), each step sends one boat.
// Space: O(1) besides the sort, which reorders the input array.

public final class BoatsToSavePeople {

	public static int countBoats(int[] people, int limit) {
		Arrays.sort(people);                               // SORT: lightest left, heaviest right
		int boats = 0;
		int n = people.length;
		// STOP: everyone is on a boat; MOVE: right-- because the heaviest always leaves
		for (int left = 0, right = n - 1; left <= right; right--) {
			if (people[left] + people[right] <= limit) {
				left++;                                    // PAIR: the lightest rides with the heaviest
			}
			boats++;                                       // COUNT: one boat for the heaviest
		}
		return boats;
	}

	public static void main(String[] args) {
		int[] p1 = { 1, 2 };
		System.out.println(countBoats(p1, 3)); // 1
		int[] p2 = { 3, 2, 2, 1 };
		System.out.println(countBoats(p2, 3)); // 3
		int[] p3 = { 3, 5, 3, 4 };
		System.out.println(countBoats(p3, 5)); // 4
		int[] p4 = { 5, 1, 4, 2 };
		System.out.println(countBoats(p4, 6)); // 2
	}
}
