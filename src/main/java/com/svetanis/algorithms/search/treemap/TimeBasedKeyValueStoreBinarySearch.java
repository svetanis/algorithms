package com.svetanis.algorithms.search.treemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 981. Time Based Key-Value Store
//
// set(key, value, timestamp) stores value for key at that time. get(key, timestamp) returns
// the value set at the largest time that is still <= timestamp, or "" if there is none -- the
// key was never set, or every set for it came later. On LeetCode the timestamps of set are
// strictly increasing, timestamps go up to 10^7, and there are at most 2 * 10^5 calls.
//
// The one idea: because set timestamps only increase, appending each entry to its key's list
// keeps that list sorted by time with no sorting at all. get is then a binary search for the
// LAST entry whose time is <= timestamp: every entry before it also qualifies, every entry
// after it does not. answer keeps the best index found so far (-1 = none); on a qualifying mid
// it records mid and searches to its right, otherwise to its left. Every pass moves left or
// right past mid, so the loop cannot get stuck, and "nothing qualifies" comes out as -1.
//
// A scan from the end would be correct but too slow: 10^5 gets over a list of 10^5 entries is
// 10^10 steps. If timestamps could arrive in any order the list would not be sorted and this
// search would quietly return wrong values; the TreeMap sibling keeps the order itself.
//
// Siblings, same problem:
//   TimeBasedKeyValueStore              a TreeMap per key, floorKey(timestamp); set pays
//                                       O(log k) for an order the timestamps already give
//   TimeBasedKeyValueStoreBinarySearch  appended lists and a binary search (this file, the
//                                       version derived in study)
//
// Time: set O(1) amortized -- an ArrayList append; get O(log k), k = entries stored for that
//       key -- each pass of the search halves the range left..right.
// Space: O(number of set calls) -- one Entry per set.

public final class TimeBasedKeyValueStoreBinarySearch {

	private record Entry(int time, String value) {} // one set() call

	private final Map<String, List<Entry>> map = new HashMap<>(); // key -> its entries, oldest first

	public void set(String key, String value, int timestamp) {
		map.computeIfAbsent(key, k -> new ArrayList<>())  // APPEND: stays sorted,
				.add(new Entry(timestamp, value));        //   times only increase
	}

	public String get(String key, int timestamp) {
		List<Entry> entries = map.getOrDefault(key, List.of()); // LIST: never set -> empty
		int answer = -1;                                        // best "yes" so far; -1 = none
		int left = 0;
		int right = entries.size() - 1;
		while (left <= right) {                                 // SEARCH
			int mid = left + (right - left) / 2;
			if (entries.get(mid).time() <= timestamp) {         // TEST: <=, a set AT timestamp counts
				answer = mid;                                   //   yes: best so far ...
				left = mid + 1;                                 //   ... anything better is right
			} else {
				right = mid - 1;                                //   no: the answer is left
			}
		}
		return answer == -1 ? "" : entries.get(answer).value(); // ANSWER
	}

	public static void main(String[] args) {
		TimeBasedKeyValueStoreBinarySearch tm = new TimeBasedKeyValueStoreBinarySearch();
		tm.set("foo", "bar", 1);
		System.out.println(tm.get("foo", 1)); // bar
		System.out.println(tm.get("foo", 3)); // bar
		tm.set("foo", "bar2", 4);
		System.out.println(tm.get("foo", 4)); // bar2
		System.out.println(tm.get("foo", 5)); // bar2
		System.out.println("[" + tm.get("foo", 0) + "]"); // [] -- earlier than every set
		System.out.println("[" + tm.get("zzz", 5) + "]"); // [] -- never set
	}
}
