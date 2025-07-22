package com.svetanis.algorithms.string.remove;

// 1910. Remove All Occurrences of a Substring

public final class RemoveAllOccurrences {

	public static String removeOccurrencesSimple(String s, String p) {
		while (s.contains(p)) {
			s = s.replaceFirst(p, "");
		}
		return s;
	}

	public static String removeOccurrences(String s, String p) {
		StringBuilder sb = new StringBuilder(s);
		int index = sb.indexOf(p);
		while (index >= 0) {
			sb.replace(index, index + p.length(), "");
			index = sb.indexOf(p);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(removeOccurrencesSimple("daabcbaabcbc", "abc")); // dab
		System.out.println(removeOccurrencesSimple("axxxxyyyyb", "xy")); // ab
		System.out.println(removeOccurrencesSimple("aabababa", "aba")); // ba

		System.out.println(removeOccurrences("daabcbaabcbc", "abc")); // dab
		System.out.println(removeOccurrences("axxxxyyyyb", "xy")); // ab
		System.out.println(removeOccurrences("aabababa", "aba")); // ba
	}
}
