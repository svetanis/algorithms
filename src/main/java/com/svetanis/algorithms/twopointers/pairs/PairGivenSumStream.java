package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashMap;
import java.util.Map;

// 170. Two Sum III - Data structure design
//
// Input: a stream of calls. add(number) stores a number; find(value) asks whether two
// stored numbers -- from two different add calls -- add up to value.
// Return: find returns true or false.
//
// The one idea: store each value with how many copies were added. find tries every
// stored value key: its only partner is value - key. A partner equal to key itself is
// a second copy of key, so the count must be at least 2.
//
// Siblings:
//   PairGivenSumHashingIndices (LC 1) -- the whole array at once, one pass
//   PairGivenSumHashing -- the same check over a list
//
// Time: add O(1); find O(d), d = the number of distinct values stored.
// Space: O(d) -- the map.

public final class PairGivenSumStream {

	private final Map<Integer, Integer> map = new HashMap<>(); // value -> how many copies were added

	public void add(int number) {
		map.put(number, map.getOrDefault(number, 0) + 1); // COUNT one more copy
	}

	public boolean find(int value) {
		for (int key : map.keySet()) {
			int count = map.get(key);
			int complement = value - key; // the only partner key can have
			if (map.containsKey(complement)) {
				if (complement != key || count > 1) {
					return true;          // FOUND: a different value, or a second copy
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		PairGivenSumStream pgs = new PairGivenSumStream();
		pgs.add(1);
		pgs.add(3);
		pgs.add(5);
		System.out.println(pgs.find(4)); // true
		System.out.println(pgs.find(7)); // false
		pgs.add(1);
		System.out.println(pgs.find(2)); // true
	}
}
