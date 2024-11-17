package com.svetanis.algorithms.string;

import java.util.Arrays;

// 833. Find And Replace in String

public final class FindAndReplaceInString {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n + t)

	public static String replace(String s, int[] indices, String[] sources, String[] targets) {
		int[] replacements = replacements(s, indices, sources);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length();) {
			int index = replacements[i];
			if (index >= 0) {
				sb.append(targets[index]);
				i += sources[index].length();
			} else {
				sb.append(s.charAt(i++));
			}
		}
		return sb.toString();
	}

	private static int[] replacements(String s, int[] indices, String[] sources) {
		int[] replacements = new int[s.length()];
		Arrays.fill(replacements, -1);
		for (int i = 0; i < indices.length; i++) {
			int index = indices[i];
			String src = sources[i];
			if (s.startsWith(src, index)) {
				replacements[index] = i;
			}
		}
		return replacements;
	}

	public static void main(String[] args) {
		int[] i1 = { 0, 2 };
		String[] s1 = { "a", "cd" };
		String[] s2 = { "ab", "ec" };
		String[] t1 = { "eee", "ffff" };
		System.out.println(replace("abcd", i1, s1, t1)); // eeebffff
		System.out.println(replace("abcd", i1, s2, t1)); // eeecd

		int[] i3 = { 3, 5, 1 };
		String[] s3 = { "kg", "ggq", "mo" };
		String[] t3 = { "s", "so", "bfr" };
		System.out.println(replace("vmokgggqzp", i3, s3, t3)); // vbfrssozp

		int[] i4 = { 14, 12, 10, 5, 0, 18 };
		String[] s4 = { "rxv", "dh", "ui", "ttv", "wreor", "vo" };
		String[] t4 = { "frs", "c", "ql", "qpir", "gwbeve", "n" };
		System.out.println(replace("wreorttvosuidhrxvmvo", i4, s4, t4)); // gwbeveqpirosqlcfrsmn
	}
}
