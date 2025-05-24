package com.svetanis.algorithms.math.rand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

// 710. Random Pick with Blacklist

public final class RandomPickWithBlacklist {

	private int threshold;
	private Random random = new Random();
	private Map<Integer, Integer> map = new HashMap<>();

	public RandomPickWithBlacklist(int n, int[] blacklist) {
		this.threshold = n - blacklist.length;
		Set<Integer> set = Arrays.stream(blacklist).boxed().collect(Collectors.toSet());
		int next = threshold;
		for (int curr : blacklist) {
			if (curr < threshold) {
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
		System.out.println(rpb.pick());
		System.out.println(rpb.pick());
		System.out.println(rpb.pick());
		System.out.println(rpb.pick());
		System.out.println(rpb.pick());
		System.out.println(rpb.pick());
		System.out.println(rpb.pick());
	}
}