package com.svetanis.algorithms.string.remove;

import java.util.ArrayDeque;
import java.util.Deque;

// 1209. Remove All Adjacent Duplicates In String II
//
// Input: a string s and an integer k. Remove k equal neighbouring letters, again and again, until
// no k in a row are left. Returns the final string (it is the same whatever order the groups
// are removed in). On LeetCode: 1 <= s.length <= 10^5, 2 <= k <= 10^4, s is lowercase English
// letters.
//
// The one idea: the stack holds one entry per run of equal letters kept so far: the letter and how
// many of it are in a row, always fewer than k. Read s left to right. If the new letter equals
// the top entry's letter, add 1 to its count, and when the count reaches k the whole run cancels,
// so pop the entry (the count is kept mod k, so reaching k shows up as 0). Otherwise push a new
// entry with count 1. A pop can leave an older run of the same letter on top, and the next
// letter then extends it, exactly as in the one-letter case.
//
// Time: O(n) -- each letter changes one count or pushes one entry; each entry is popped once.
// The final loops write at most n letters in total.
// Space: O(n) -- the stack holds at most one entry per letter, and the builder holds the answer.
//
// Siblings: RemoveAllAdjacentDuplicates is the same with k = 2, where no count is needed.

public final class RemoveAllAdjacentDuplicates1209 {

	public static String remove(String s, int k) {
		Deque<int[]> dq = new ArrayDeque<>(); // ENTRY: { letter index, count in a row }
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			if (!dq.isEmpty() && dq.peek()[0] == index) { // SAME letter as the top run
				dq.peek()[1] = (dq.peek()[1] + 1) % k; // COUNT: mod k, so k in a row becomes 0
				if (dq.peek()[1] == 0) {
					dq.pop(); // CANCEL: k equal letters in a row
				}
			} else {
				dq.push(new int[] { index, 1 }); // START: a new run
			}
		}
		return build(dq);
	}

	private static String build(Deque<int[]> dq) {
		StringBuilder sb = new StringBuilder();
		for (int[] element : dq) { // WALK: top of the stack first, so the string comes out backwards
			char c = (char) (element[0] + 'a');
			for (int i = 0; i < element[1]; i++) {
				sb.append(c);
			}
		}
		sb.reverse(); // REVERSE: back to the original order
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(remove("abcd", 2)); // abcd
		System.out.println(remove("deeedbbcccbdaa", 3)); // aa
		System.out.println(remove("pbbcggttciiippooaais", 2)); // ps
	}
}
