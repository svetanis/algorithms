package com.svetanis.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 1152. Analyze User Website Visit Pattern

public final class UserWebsiteVisitsSubmit {

	public static List<String> visitPattern(String[] names, int[] timestamps, String[] websites) {
		Map<String, List<Visit>> visits = visits(names, timestamps, websites);
		Map<String, Integer> patternCount = patternCount(visits.values());
		int maxCount = 0;
		String mfp = "";
		for (String pattern : patternCount.keySet()) {
			int count = patternCount.get(pattern);
			if (count > maxCount || count == maxCount && pattern.compareTo(mfp) < 0) {
				maxCount = count;
				mfp = pattern;
			}
		}
		return Arrays.asList(mfp.split(","));
	}

	private static Map<String, Integer> patternCount(Collection<List<Visit>> list) {
		Map<String, Integer> map = new HashMap<>();
		for (List<Visit> visits : list) {
			int count = visits.size();
			Set<String> uniquePatterns = new HashSet<>();
			if (count > 2) {
				Collections.sort(visits, (a, b) -> a.timestamp - b.timestamp);
				for (int i = 0; i < count - 2; i++) {
					String w1 = visits.get(i).website;
					for (int j = i + 1; j < count - 1; j++) {
						String w2 = visits.get(j).website;
						for (int k = j + 1; k < count; k++) {
							String w3 = visits.get(k).website;
							String pattern = w1 + "," + w2 + "," + w3;
							uniquePatterns.add(pattern);
						}
					}
				}
			}
			for (String pattern : uniquePatterns) {
				map.put(pattern, map.getOrDefault(pattern, 0) + 1);
			}
		}
		return map;
	}

	private static Map<String, List<Visit>> visits(String[] names, int[] timestamps, String[] websites) {
		Map<String, List<Visit>> map = new HashMap<>();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			String website = websites[i];
			int timestamp = timestamps[i];
			map.computeIfAbsent(name, k -> new ArrayList<>()).add(new Visit(timestamp, name, website));
		}
		return map;
	}

	public static void main(String[] args) {
		String[] names = { "Jane", "Jane", "Jane", "Alex", "Alex", "Alex" };
		int[] timestamps = { 1, 2, 3, 4, 5, 6 };
		String[] websites = { "A", "B", "C", "A", "B", "D" };
		System.out.println(visitPattern(names, timestamps, websites));
	}

	public static class Visit {

		public int timestamp;
		public String name;
		public String website;

		public Visit(int timestamp, String name, String website) {
			this.timestamp = timestamp;
			this.name = name;
			this.website = website;
		}
	}
}
