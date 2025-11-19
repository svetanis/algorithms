package com.svetanis.algorithms.string.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 2840. Check if Strings Can be Made Equal With Operations II

public final class CheckStrings {

	public static boolean checkStringsSimple(String s1, String s2) {
		int[][] fdiff = new int[2][26];
		for (int i = 0; i < s1.length(); i++) {
			int parityIndex = i & 1;
			int index1 = s1.charAt(i) - 'a';
			int index2 = s2.charAt(i) - 'a';
			fdiff[parityIndex][index1]++;
			fdiff[parityIndex][index2]--;
		}
		for (int index = 0; index < 26; index++) {
			if (fdiff[0][index] != 0) {
				return false;
			}
			if (fdiff[1][index] != 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkStrings(String s1, String s2) {
		List<Character> even1 = chars(s1, 0);
		List<Character> even2 = chars(s2, 0);
		List<Character> odd1 = chars(s1, 1);
		List<Character> odd2 = chars(s2, 1);
		for (int i = 0; i < even1.size(); i++) {
			if (even1.get(i) != even2.get(i)) {
				return false;
			}
		}
		for (int i = 0; i < odd1.size(); i++) {
			if (odd1.get(i) != odd2.get(i)) {
				return false;
			}
		}
		return true;
	}

	private static List<Character> chars(String s, int start) {
		List<Character> list = new ArrayList<>();
		for (int i = start; i < s.length(); i += 2) {
			list.add(s.charAt(i));
		}
		Collections.sort(list);
		return list;
	}

	public static void main(String[] args) {
		System.out.println(checkStringsSimple("abcdba", "cabdab")); // true
		System.out.println(checkStringsSimple("abe", "bea")); // false
	}
}
