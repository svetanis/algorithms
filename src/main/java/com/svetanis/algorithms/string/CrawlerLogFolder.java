package com.svetanis.algorithms.string;

// 1598. Crawler Log Folder
//
// Input: an array of log strings, each one move of a file system crawler that starts in the main
// folder: "../" goes up one folder (a move up from the main folder stays in the main folder),
// "./" stays in place, "x/" goes down into the child folder x. Returns the fewest operations
// needed to get back to the main folder. On LeetCode: 1 <= logs.length <= 10^3, 2 <= logs[i].length
// <= 10; a folder name is lowercase letters and digits; a move down always names a folder that exists.
//
// The one idea: the only thing that matters is how deep the crawler is, so keep one number, the
// depth, with the main folder at 0. "x/" makes it 1 deeper, "../" makes it 1 shallower but never
// below 0, "./" changes nothing. Going up that many times is the fewest operations, one per level,
// so the final depth is the answer.
//
// Time: O(n) -- one pass over the logs
// Space: O(1)

public final class CrawlerLogFolder {

	public static int minOperations(String[] logs) {
		int count = 0; // DEPTH: folders below the main folder
		for (String log : logs) {
			if (log.equals("../")) {
				count = Math.max(0, count - 1); // UP: but never above the main folder
			} else if (!log.equals("./")) {
				count++; // DOWN: into a child folder
			}
		}
		return count; // RETURN: one "../" per level to climb back
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
