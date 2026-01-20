package com.svetanis.algorithms.dp.common.scs;

import java.util.HashMap;
import java.util.Map;

// 1092. Shortest Common Supersequence

public final class ShortestCommonSuperSeqLcsTopDown {
	// Time Complexity: O(n * m)

	private Map<String, String> dp;

	public String scs(String x, String y) {
		this.dp = new HashMap<>();
		String lcs = lcs(x, y, 0, 0);
		return scs(x, y, lcs);
	}

	private String scs(String x, String y, String lcs) {
		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder();
		for (char c : lcs.toCharArray()) {
			while (x.charAt(i) != c) {
				sb.append(x.charAt(i++));
			}
			while (y.charAt(j) != c) {
				sb.append(y.charAt(j++));
			}
			sb.append(c);
			i += 1;
			j += 1;
		}
		sb.append(x.substring(i));
		sb.append(y.substring(j));
		return sb.toString();
	}

	private String lcs(String x, String y, int i, int j) {
		if (i == x.length() || j == y.length()) {
			return "";
		}
		String key = i + "," + j;
		if(dp.containsKey(key)) {
			return dp.get(key);
		}
		String s;
		if (x.charAt(i) == y.charAt(j)) {
			s = x.charAt(i) + lcs(x, y, i + 1, j + 1);
		} else {
			String left = lcs(x, y, i + 1, j);
			String right = lcs(x, y, i, j + 1);
			s = left.length() > right.length() ? left : right;
		}
		dp.put(key, s);
		return s;
	}

	public static void main(String[] args) {
		ShortestCommonSuperSeqLcsTopDown scss = new ShortestCommonSuperSeqLcsTopDown();
		System.out.println(scss.scs("geek", "eke")); // gekek
		System.out.println(scss.scs("AGGTAB", "GXTXAYB")); // AGXGTXAYB
		System.out.println(scss.scs("ABCBDAB", "BDCABA")); // ABDCABDAB
		System.out.println(scss.scs("abac", "cab")); // cabac
		System.out.println(scss.scs("aaaaaaaa", "aaaaaaaa")); // aaaaaaaa
	}

}
