package com.svetanis.algorithms.dp.countways;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Maps.newMap;
import static java.util.Arrays.asList;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

// Given a phone keypad
// How many different phone numbers of given length 
// can be formed starting from the given digit? 
// The constraint is that the movement from one digit 
// to the next is similar to the movement of 
// the Knight in a chess game.

public final class KnightTourBruteForce {

	public static int recursive(int src, int len) {
		Map<Integer, ImmutableList<Integer>> map = neighbours();
		return dfs(src, len, map);
	}

	private static int dfs(int src, int len, Map<Integer, ImmutableList<Integer>> map) {
		if (len == 1) {
			return 1;
		}
		int count = 0;
		for (int val : map.get(src)) {
			count += dfs(val, len - 1, map);
		}
		return count;
	}

	private static ImmutableMap<Integer, ImmutableList<Integer>> neighbours() {
		Map<Integer, ImmutableList<Integer>> map = newHashMap();
		map.put(0, newList(asList(4, 6)));
		map.put(1, newList(asList(6, 8)));
		map.put(2, newList(asList(7, 9)));
		map.put(3, newList(asList(4, 8)));
		map.put(4, newList(asList(3, 9, 0)));
		map.put(5, newList());
		map.put(6, newList(asList(0, 7, 1)));
		map.put(7, newList(asList(2, 6)));
		map.put(8, newList(asList(1, 3)));
		map.put(9, newList(asList(2, 4)));
		return newMap(map);
	}

	public static void main(String[] args) {
		System.out.println(recursive(1, 2));// 2
		System.out.println(recursive(1, 3));// 5
		System.out.println(recursive(9, 13));// 19520
		System.out.println(recursive(2, 4));// 10
		System.out.println(recursive(2, 8));// 272
		System.out.println(recursive(2, 12));// 7456
		System.out.println(recursive(2, 16));// 204416
	}
}
