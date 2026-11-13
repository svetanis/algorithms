package com.svetanis.algorithms.string.reorder;

import java.util.Arrays;

// 833. Find And Replace in String
//
// Input: a string s and k replacement tests as three parallel arrays: the test i looks for
// sources[i] in s starting at indices[i], and if it is there, swaps it for targets[i]. Every test
// is judged against the original s, and no two tests touch overlapping pieces of it. Returns the
// string after all the tests that matched have been applied. On LeetCode: 1 <= s.length <= 1000,
// 1 <= k <= 100, 0 <= indices[i] < s.length, 1 <= sources[i].length, targets[i].length <= 50,
// lowercase letters.
//
// The one idea: judge every test against the original string, then write the answer in one pass.
// replacements has one cell per position of the ORIGINAL s; cell p holds the number of the test
// that matched starting at p, or -1 if none did. Because the tests never overlap, a position has
// at most one test. The answer is then built left to right: at a position with a test, append that
// test's target and skip over the source's length in s; anywhere else, copy one character. A
// target of a different length never moves another test, because no position is ever looked up
// in the answer being built -- only in the original.
//
// Time: O(n + S + T) -- n = s.length, S = total length of the sources (one startsWith per test
// compares at most its source), T = total length of the targets (each appended once).
// Space: O(n + T) -- the replacements array and the answer.

public final class FindAndReplaceInString {

	public static String replace(String s, int[] indices, String[] sources, String[] targets) {
		int[] replacements = replacements(s, indices, sources);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length();) {
			int index = replacements[i];
			if (index >= 0) {
				sb.append(targets[index]); // SWAP: the matched test replaces its source
				i += sources[index].length(); // SKIP: past the source in the original
			} else {
				sb.append(s.charAt(i++)); // COPY: no test starts here
			}
		}
		return sb.toString();
	}

	private static int[] replacements(String s, int[] indices, String[] sources) {
		int[] replacements = new int[s.length()];
		Arrays.fill(replacements, -1); // BASE: no test starts anywhere yet
		for (int i = 0; i < indices.length; i++) {
			int index = indices[i];
			String src = sources[i];
			if (s.startsWith(src, index)) { // MATCH: judged against the original s
				replacements[index] = i; // RECORD: test i starts at this position
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
