package com.svetanis.algorithms.search.treemap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 981. Time Based Key-Value Store
//
// set(key, value, timestamp) stores value for key at that time. get(key, timestamp) returns
// the value set at the largest time that is still <= timestamp, or "" if there is none -- the
// key was never set, or every set for it came later. On LeetCode the timestamps of set are
// strictly increasing, timestamps go up to 10^7, and there are at most 2 * 10^5 calls.
//
// The one idea: each key gets its own TreeMap from time to value. A TreeMap keeps its keys in
// sorted order, and floorKey(timestamp) hands back the largest stored time that is still
// <= timestamp, or null when every stored time is larger. That one call is the whole of get:
// null becomes "", any other time is looked up for its value. Because the TreeMap does the
// sorting itself, this version would stay correct even if timestamps arrived in any order.
// With the strictly increasing timestamps LeetCode promises, that sorting is work the input has
// already done -- the binary-search sibling relies on that instead.
//
// Siblings, same problem:
//   TimeBasedKeyValueStore              a TreeMap per key, floorKey(timestamp) (this file);
//                                       set pays O(log k) for an order the timestamps already give
//   TimeBasedKeyValueStoreBinarySearch  appended lists and a binary search (the version
//                                       derived in study)
//
// Time: set O(log k); get O(log k), k = entries stored for that key -- a TreeMap is a balanced
//       search tree, so finding a place in it takes about log k steps.
// Space: O(number of set calls) -- one TreeMap entry per set.

public final class TimeBasedKeyValueStore {

	private Map<String, TreeMap<Integer, String>> map; // key -> (time -> value), times sorted

	public TimeBasedKeyValueStore() {
		this.map = new HashMap<>();
	}

	public void set(String key, String val, int timestamp) {
		map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, val); // STORE: kept sorted by time
	}

	public String get(String key, int timestamp) {
		if (!map.containsKey(key)) { // MISSING: key never set
			return "";
		}
		TreeMap<Integer, String> tvm = map.get(key);
		Integer closestTimestamp = tvm.floorKey(timestamp);                // FLOOR: largest time <= timestamp
		return closestTimestamp == null ? "" : tvm.get(closestTimestamp); // RETURN: null = every set came later
	}

	public static void main(String[] args) {
		TimeBasedKeyValueStore sa = new TimeBasedKeyValueStore();
		sa.set("foo", "bar", 1);
		System.out.println(sa.get("foo", 1)); // bar
		System.out.println(sa.get("foo", 3)); // bar
		sa.set("foo", "bar2", 4);
		System.out.println(sa.get("foo", 4)); // bar2
		System.out.println(sa.get("foo", 5)); // bar2
		System.out.println("[" + sa.get("foo", 0) + "]"); // [] -- earlier than every set
		System.out.println("[" + sa.get("zzz", 5) + "]"); // [] -- never set
	}
}