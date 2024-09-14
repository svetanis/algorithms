package com.svetanis.algorithms.slidingwindow.string.anagram;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Maps.freqMap;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class CountAnagrams {

	public static int count(String str, String pat) {
		// Time complexity: O(n)

		int n = str.length();
		int left = 0;
		int count = 0;
		int matched = 0;
		Map<Character, Integer> map = freqMap(pat.toCharArray());

		for (int right = 0; right < n; right++) {
			char end = str.charAt(right);
			if (map.containsKey(end)) {
				map.put(end, map.get(end) - 1);
				if (map.get(end) == 0) {
					matched++;
				}
			}

			if (matched == map.size()) {
				count++;
			}

			if (right >= pat.length() - 1) {
				char start = str.charAt(left++);
				if (map.containsKey(start)) {
					if (map.get(start) == 0) {
						matched--;
					}
					map.put(start, map.get(start) + 1);
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		ImmutableList<Pair<String, String>> pairs = pairs();
		for (Pair<String, String> pair : pairs) {
			System.out.println(count(pair.getLeft(), pair.getRight()));
		}
	}

	private static ImmutableList<Pair<String, String>> pairs() {
		List<Pair<String, String>> list = newArrayList();
		list.add(Pair.build("forxxorfxdorf", "for")); // 3
		list.add(Pair.build("aabaabaa", "aaba")); // 4
		return newList(list);
	}
}
