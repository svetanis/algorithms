package com.svetanis.algorithms.string;

// 1598. Crawler Log Folder

public final class CrawlerLogFolder {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minOperations(String[] logs) {
		int count = 0;
		for (String log : logs) {
			if (log.equals("../")) {
				count = Math.max(0, count - 1);
			} else if (!log.equals("./")) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String[] logs1 = { "d1/", "d2/", "../", "d21/", "./" };
		System.out.println(minOperations(logs1)); // 2
		String[] logs2 = { "d1/", "d2/", "./", "d3/", "../", "d31/" };
		System.out.println(minOperations(logs2)); // 3
		String[] logs3 = { "d1/", "../", "../", "../" };
		System.out.println(minOperations(logs3)); // 0
		String[] logs4 = { "./", "wz4/", "../", "mj2/", "../", "../", "ik0/", "il7/" };
		System.out.println(minOperations(logs4)); // 2

	}
}
