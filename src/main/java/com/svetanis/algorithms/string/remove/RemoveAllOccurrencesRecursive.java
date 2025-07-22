package com.svetanis.algorithms.string.remove;

// 1910. Remove All Occurrences of a Substring

public final class RemoveAllOccurrencesRecursive {

	public static String removeOccurrences(String s, String p) {
		if(!s.contains(p)) {
			return s;
		}
		s = s.replaceFirst(p, "");
		return removeOccurrences(s, p);
	}

	public static void main(String[] args) {
		System.out.println(removeOccurrences("daabcbaabcbc", "abc")); // dab
		System.out.println(removeOccurrences("axxxxyyyyb", "xy")); // ab
		System.out.println(removeOccurrences("aabababa", "aba")); // ba
	}
}
