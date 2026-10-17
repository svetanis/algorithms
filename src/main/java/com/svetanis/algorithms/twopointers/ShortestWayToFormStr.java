package com.svetanis.algorithms.twopointers;

// 1055. Shortest Way to Form String
//
// Input: two strings, source and target.
// Return: the fewest subsequences of source that, joined end to end, spell target;
// -1 if that cannot be done, which happens exactly when target holds a letter source
// does not. (A subsequence keeps some of the letters, in their order, skipping others.)
//
// The one idea: greedy. Each pass walks source once from its start and matches as many
// of target's next letters as it can, in order. The longest piece is never worse than a
// shorter one: whatever a shorter first piece leaves, the longer one has already covered
// at least as much of target. A pass that matches nothing means target's next letter is
// not in source at all.
//
// Time: O(n * m) -- at most m passes (one per target letter in the worst case), each
//   walking source's n letters. n = source length, m = target length.
// Space: O(1).

public final class ShortestWayToFormStr {

	private String src;
	private String dst;

	public int shortestWay(String src, String dst) {
		this.src = src;
		this.dst = dst;
		int j = 0;                      // START: target's next unmatched letter
		int count = 0;
		while (j < dst.length()) {      // STOP: all of target is spelled
			int next = match(0, j);     // ONE PASS over source
			if (next == j) {
				return -1;              // STUCK: target's letter at j is not in source
			}
			j = next;                   // MOVE past what this pass matched
			count += 1;                 // COUNT the piece
		}
		return count;
	}

	private int match(int start, int j) {
		while (start < src.length() && j < dst.length()) {
			if (src.charAt(start) == dst.charAt(j)) {
				j += 1;                 // MATCH: this letter of source spells the next of target
			}
			start += 1;                 // MOVE along source either way
		}
		return j;                       // RETURN how far target got
	}

	public static void main(String[] args) {
		ShortestWayToFormStr sws = new ShortestWayToFormStr();
		System.out.println(sws.shortestWay("abc", "abcbc")); // 2
		System.out.println(sws.shortestWay("abc", "acdbc")); // -1
		System.out.println(sws.shortestWay("xyz", "xzyxz")); // 3
	}
}
