package com.svetanis.algorithms.sorting;

import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.sort;
import static com.svetanis.java.base.collect.Maps.newMap;
import static com.svetanis.java.base.collect.Sets.newSet;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

// 1152. Analyze User Website Visit Pattern

public final class UserWebsiteVisits {

	public static List<String> visitPattern(List<String> names, List<Integer> timestamps, List<String> websites) {
		List<Visit> visits = visits(names, timestamps, websites);
		// List<Visit> sorted = visits.stream().sorted(Comparator.comparing(v ->
		// v.getTimestamp())).collect(Collectors.toList());
		List<Visit> sorted = sort(visits, v -> v.getTimestamp());
		Map<String, List<String>> map = usersToWebsites(sorted);
		Set<String> set = sequences(map);
		Map<String, Integer> frequences = frequencies(set);
		Queue<Pattern> pq = patterns(frequences);
		String key = pq.peek().getKey();
		// return Splitters.split(',', key);
		return Arrays.asList(key.split(","));
	}

	private static ImmutableList<Visit> visits(List<String> names, List<Integer> timestamps, List<String> websites) {
		List<Visit> list = new ArrayList<>();
		for (int i = 0; i < names.size(); i++) {
			String name = names.get(i);
			String website = websites.get(i);
			int timestamp = timestamps.get(i);
			list.add(new Visit(timestamp, name, website));
		}
		return newList(list);
	}

	private static ImmutableMap<String, List<String>> usersToWebsites(List<Visit> visits) {
		Map<String, List<String>> map = new HashMap<>();
		for (Visit visit : visits) {
			String key = visit.getName();
			map.computeIfAbsent(key, k -> new ArrayList<>()).add(visit.getWebsite());
		}
		return newMap(map);
	}

	private static ImmutableSet<String> sequences(Map<String, List<String>> map) {
		Set<String> set = new HashSet<>();
		for (String key : map.keySet()) {
			if (map.get(key).size() > 2) {
				set.add(String.join(",", map.get(key)));
			}
		}
		return newSet(set);
	}

	private static ImmutableMap<String, Integer> frequencies(Set<String> sequences) {
		Map<String, Integer> map = new HashMap<>();
		for (String s : sequences) {
			map.put(s, map.getOrDefault(s, 0) + 1);
		}
		return newMap(map);
	}

	private static Queue<Pattern> patterns(Map<String, Integer> map) {
		Queue<Pattern> pq = new PriorityQueue<>();
		for (String key : map.keySet()) {
			pq.add(new Pattern(key, map.get(key)));
		}
		return pq;
	}

	public static void main(String[] args) {
		List<String> names = asList("Jane", "Jane", "Jane", "Alex", "Alex", "Alex");
		List<Integer> timestamps = asList(1, 2, 3, 4, 5, 6);
		List<String> websites = asList("A", "B", "C", "A", "B", "D");
		System.out.println(visitPattern(names, timestamps, websites));
	}
}
