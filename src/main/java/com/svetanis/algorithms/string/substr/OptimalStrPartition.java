package com.svetanis.algorithms.string.substr;

import java.util.Arrays;

// 2405. Optimal Partition of String
//
// Input: a string s. Cut s into the fewest pieces so that no letter appears twice inside one
// piece; every letter of s goes into exactly one piece, in order. Returns the number of pieces. On
// LeetCode: 1 <= s.length <= 10^5, lowercase letters.
//
// The one idea: be greedy -- let the current piece grow until the next letter is already in it,
// and cut only then. Cutting later is never worse: any piece that has no repeated letter still
// has none after a letter is taken off its end, so whatever cuts a shorter piece allows, the
// longer piece allows too. A piece starts at start; lastSeen holds, for each letter, the index
// where it was last read. A letter whose lastSeen is at or after start is already inside the
// current piece: count a new piece and start it at this index.
//
// Time: O(n) -- one pass; the second method also clears 26 counts at each cut, which is still O(26 n).
// Space: O(1) -- 26 counters.
//
// Siblings: partitionStr keeps a last-seen index per letter; partitionStr2 keeps a count per
// letter and clears all 26 at every cut.

public final class OptimalStrPartition {

	public static int partitionStr(String s) {
		int[] lastSeen = new int[26]; // lastSeen[c] -- the index where letter c was last read
		Arrays.fill(lastSeen, -1);
		int start = 0; // the index where the current piece starts
		int partitions = 1;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (lastSeen[index] >= start) { // REPEAT: the letter is already inside the current piece
				partitions += 1; // CUT: a new piece starts here
				start = i;
			}
			lastSeen[index] = i; // RECORD: where this letter was last read
		}
		return partitions;
	}

	public static int partitionStr2(String s) {
		int[] counts = new int[26]; // counts[c] -- how many times letter c is in the current piece
		int partitions = 1;
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			if (counts[index] > 0) { // REPEAT: the letter is already inside the current piece
				partitions += 1; // CUT: a new piece starts here
				Arrays.fill(counts, 0);
			}
			counts[index] += 1; // RECORD: the letter is in the current piece
		}
		return partitions;
	}

	public static void main(String[] args) {
		System.out.println(partitionStr("abacaba")); // 4
		System.out.println(partitionStr("ssssss")); // 6
		System.out.println(partitionStr("hdklqkcssgxlvehva")); // 4
		System.out.println(partitionStr2("abacaba")); // 4
		System.out.println(partitionStr2("ssssss")); // 6
		System.out.println(partitionStr2("hdklqkcssgxlvehva")); // 4
	}
}
