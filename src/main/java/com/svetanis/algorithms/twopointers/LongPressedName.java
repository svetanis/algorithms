package com.svetanis.algorithms.twopointers;

// 925. Long Pressed Name

public final class LongPressedName {
	// Time Complexity: O(n + m)
	// Space Complexity: O(1)

	public static boolean isLongPressedName(String name, String typed) {
		int n = name.length();
		int m = typed.length();
		int nindex = 0, tindex = 0;
		while (nindex < n && tindex < m) {
			char c = name.charAt(nindex);
			char t = typed.charAt(tindex);
			if (c != t) {
				return false;
			}
			int nend = nindex + 1;
			while (nend < n && name.charAt(nend) == c) {
				nend += 1;
			}
			int tend = tindex + 1;
			while (tend < m && typed.charAt(tend) == t) {
				tend += 1;
			}
			if (nend - nindex > tend - tindex) {
				return false;
			}
			nindex = nend;
			tindex = tend;
		}
		return nindex == n && tindex == m;
	}

	public static void main(String[] args) {
		System.out.println(isLongPressedName("alex", "aaleex")); // true
		System.out.println(isLongPressedName("saeed", "ssaaedd")); // false
	}
}
