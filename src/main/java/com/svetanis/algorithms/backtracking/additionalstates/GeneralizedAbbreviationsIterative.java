package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 320. Generalized Abbreviation
//
// Input: a word. Replace any number of non-overlapping, non-adjacent blocks of its letters with
// the length of the block; "word" becomes "w2" or "1o1d" or "4" and so on. Returns every
// abbreviation, each once, in any order. On LeetCode: 1 <= word.length <= 15, lowercase letters.
//
// The one idea: every letter is either kept or abbreviated, so the abbreviations are exactly the
// 2^n choices of which letters to keep. One queue entry is a half-built answer: sb is what is
// written so far, start the next letter to decide, count the number of abbreviated letters
// waiting before it, not yet written. Taking an entry out of the queue decides the letter at
// start and puts back two entries: it is abbreviated (count grows by one, nothing is written), or
// kept (the waiting count is written when it is not 0, then the letter, and count is 0 again).
// An entry whose start is past the last letter is finished: the waiting count is written and the
// answer is recorded.
//
// Time: O(n * 2^n) -- 2^n answers, and every entry copies its StringBuilder, O(n).
// Space: O(n * 2^n) -- the queue ends up holding all 2^n finished entries, each a builder of up to
// n characters, besides the answers.
//
// Siblings: GeneralizeAbbreviationsRecursive is this version with recursion instead of a queue;
// GeneralizeAbbreviationsBackTracking picks a whole block at a time.

public final class GeneralizedAbbreviationsIterative {

	public static List<String> generate(String s) {
		List<String> list = new ArrayList<>();
		Queue<Abbreviation> queue = new LinkedList<>();
		queue.add(new Abbreviation(new StringBuilder(), 0, 0)); // START: nothing written, nothing waiting
		while (!queue.isEmpty()) {
			Abbreviation abb = queue.poll();
			if (abb.start == s.length()) { // BASE: every letter is decided
				if (abb.count != 0) {
					abb.sb.append(abb.count); // WRITE: the number still waiting at the end
				}
				list.add(abb.sb.toString());
			} else {
				// continue abbreviation
				StringBuilder sbc = new StringBuilder(abb.sb);
				int start = abb.start + 1;
				int count = abb.count + 1;
				queue.add(new Abbreviation(sbc, start, count)); // ABBREVIATE: this letter joins the waiting count

				// restart abbreviation
				if (abb.count != 0) {
					abb.sb.append(abb.count); // WRITE: the waiting count comes out before a kept letter
				}
				StringBuilder sbr = new StringBuilder(abb.sb);
				sbr.append(s.charAt(abb.start)); // KEEP: this letter; the count starts again from 0
				int start2 = abb.start + 1;
				queue.add(new Abbreviation(sbr, start2, 0));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(generate("BAT")); // [3, 2T, 1A1, 1AT, B2, B1T, BA1, BAT]
		System.out.println(generate("code")); // [4, 3e, 2d1, 2de, 1o2, 1o1e, 1od1, 1ode, c3, c2e, c1d1, c1de, co2, co1e, cod1, code]
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