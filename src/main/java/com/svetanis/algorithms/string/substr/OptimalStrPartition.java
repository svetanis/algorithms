package com.svetanis.algorithms.string.substr;

import java.util.Arrays;

// 2405. Optimal Partition of String

public final class OptimalStrPartition {
	// Time Complexity: O(n)

	public static int partitionStr(String s) {
		int[] lastSeen = new int[26];
		Arrays.fill(lastSeen, -1);
		int start = 0;
		int partitions = 1;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (lastSeen[index] >= start) {
				partitions += 1;
				start = i;
			}
			lastSeen[index] = i;
		}
		return partitions;
	}

	public static int partitionStr2(String s) {
		int[] counts = new int[26];
		int partitions = 1;
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			if (counts[index] > 0) {
				partitions += 1;
				Arrays.fill(counts, 0);
			}
			counts[index] += 1;
		}
		return partitions;
	}

	public static void main(String[] args) {
		System.out.println(partitionStr("abacaba")); // 4
		System.out.println(partitionStr("ssssss")); // 6
		System.out.println(partitionStr("hdklqkcssgxlvehva")); // 4
	}
}
