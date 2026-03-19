package com.svetanis.algorithms.twopointers;

// 1055. Shortest Way to Form String

public final class ShortestWayToFormStr {
	// Time Complexity: O(n * m)
	// Space Complexity: O(1)

	private String src;
	private String dst;

	public int shortestWay(String src, String dst) {
		this.src = src;
		this.dst = dst;
		int j = 0;
		int count = 0;
		while (j < dst.length()) {
			int next = match(0, j);
			if (next == j) {
				return -1;
			}
			j = next;
			count += 1;
		}
		return count;
	}

	private int match(int start, int j) {
		while (start < src.length() && j < dst.length()) {
			if (src.charAt(start) == dst.charAt(j)) {
				j += 1;
			}
			start += 1;
		}
		return j;
	}

	public static void main(String[] args) {
		ShortestWayToFormStr sws = new ShortestWayToFormStr();
		System.out.println(sws.shortestWay("abc", "abcbc")); // 2
		System.out.println(sws.shortestWay("abc", "acdbc")); // -1
		System.out.println(sws.shortestWay("xyz", "xzyxz")); // 3
	}
}
