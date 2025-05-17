package com.svetanis.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

// 93. Restore IP Addresses

public final class RestoreIPAddresses {

	public static List<String> restoreIpAddresses(String s) {
		List<String> list = new ArrayList<>();
		List<String> segment = new ArrayList<>();
		dfs(s, 0, segment, list);
		return list;
	}

	private static void dfs(String s, int index, List<String> segment, List<String> list) {
		int n = s.length();
		if (index >= n && segment.size() == 4) {
			list.add(String.join(".", segment));
			return;
		}
		if (index >= n || segment.size() >= 4) {
			return;
		}
		int val = 0;
		for (int i = index; i < Math.min(index + 3, n); i++) {
			val = val * 10 + s.charAt(i) - '0';
			boolean leadingZero = s.charAt(index) == '0' && index != i;
			if (val > 255 || leadingZero) {
				break;
			}
			segment.add(s.substring(index, i + 1));
			dfs(s, i + 1, segment, list);
			segment.remove(segment.size() - 1);
		}
	}

	public static void main(String[] args) {
		// [255.255.11.135, 255.255.111.35]
		System.out.println(restoreIpAddresses("25525511135"));
		System.out.println(restoreIpAddresses("0000")); // [0.0.0.0]
		// [1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]
		System.out.println(restoreIpAddresses("101023"));
	}
}
