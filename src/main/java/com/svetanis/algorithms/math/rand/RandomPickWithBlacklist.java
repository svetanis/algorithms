package com.svetanis.algorithms.math.rand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

// 710. Random Pick with Blacklist

// n - B numbers are allowed (B = blacklist size), so draw one of
// 0 .. n - B - 1, the first n - B numbers. Most of them are allowed and
// are returned as they are. Each blacklisted number in that low range is
// sent instead to an allowed number from n - B upward, a different one
// for each -- so every allowed number is reachable from exactly one draw.
//
//   n = 7, blacklist {2, 3, 5}: draw from 0..3; 2 -> 4, 3 -> 6
//   draws 0 1 2 3 give 0 1 4 6

public final class RandomPickWithBlacklist {
	// Time Complexity: O(B) to build, O(1) per pick
	// Space Complexity: O(B) for the remap

	private final int threshold;
	private final Random random = new Random();
	private final Map<Integer, Integer> map = new HashMap<>();

	public RandomPickWithBlacklist(int n, int[] blacklist) {
		this.threshold = n - blacklist.length;
		Set<Integer> set = Arrays.stream(blacklist).boxed().collect(Collectors.toSet());
		int next = threshold;
		for (int curr : blacklist) {
			if (curr < threshold) {
				// never runs past n: at and above threshold there are exactly as
				// many allowed numbers as blacklisted numbers below it
				while (set.contains(next)) {
					next++;
				}
				map.put(curr, next++);
			}
		}
	}

	public int pick() {
		int rand = random.nextInt(threshold);
		return map.getOrDefault(rand, rand);
	}

	public static void main(String[] args) {
		int[] a = { 2, 3, 5 };
		RandomPickWithBlacklist rpb = new RandomPickWithBlacklist(7, a);
		for (int i = 0; i < 7; i++) {
			System.out.println(rpb.pick()); // each one of 0, 1, 4, 6
		}
	}
}
