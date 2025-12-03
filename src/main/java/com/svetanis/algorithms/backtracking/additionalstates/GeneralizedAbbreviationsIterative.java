package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 320. Generalized Abbreviation

// given a word, generate all of its
// unique generalized abbreviations

public final class GeneralizedAbbreviationsIterative {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static List<String> generate(String s) {
		List<String> list = new ArrayList<>();
		Queue<Abbreviation> queue = new LinkedList<>();
		queue.add(new Abbreviation(new StringBuilder(), 0, 0));
		while (!queue.isEmpty()) {
			Abbreviation abb = queue.poll();
			if (abb.start == s.length()) {
				if (abb.count != 0) {
					abb.sb.append(abb.count);
				}
				list.add(abb.sb.toString());
			} else {
				// continue abbreviation
				StringBuilder sbc = new StringBuilder(abb.sb);
				int start = abb.start + 1;
				int count = abb.count + 1;
				queue.add(new Abbreviation(sbc, start, count));

				// restart abbreviation
				if (abb.count != 0) {
					abb.sb.append(abb.count);
				}
				StringBuilder sbr = new StringBuilder(abb.sb);
				sbr.append(s.charAt(abb.start));
				int start2 = abb.start + 1;
				queue.add(new Abbreviation(sbr, start2, 0));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(generate("BAT"));
		System.out.println(generate("code"));
	}

	private static final class Abbreviation {
		private StringBuilder sb;
		private int start;
		private int count;

		public Abbreviation(StringBuilder sb, int start, int count) {
			this.sb = sb;
			this.start = start;
			this.count = count;
		}
	}
}