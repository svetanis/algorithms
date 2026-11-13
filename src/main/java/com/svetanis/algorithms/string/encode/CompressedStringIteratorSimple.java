package com.svetanis.algorithms.string.encode;

// 604. Design Compressed String Iterator
//
// Input: a compressed string, each letter followed by how many times it repeats ("L1e2" is
// "Lee"). next() returns the next letter of the uncompressed string, or ' ' when none is left;
// hasNext() says whether one is left. On LeetCode: 1 <= compressedString.length <= 1000, lower- and
// upper-case letters and digits, each count in [1, 10^9], at most 100 calls to next and hasNext.
//
// The one idea: keep only the current letter c and how many copies of it are still to be
// returned (count), plus index, the position in the string where the next letter-and-count pair
// starts. When count reaches 0, next() reads the next pair from the string. Letters are left while
// either part remains: copies of c (count > 0) or unread pairs (index < length).
//
// Time: O(1) per next() and hasNext(), apart from reading each pair's digits once.
// Space: O(1) -- three fields besides the string itself.
//
// Sibling: CompressedStringIterator reads every pair into a list in its constructor, then walks
// the list.

public final class CompressedStringIteratorSimple {

	public CompressedStringIteratorSimple(String s) {
		this.c = ' ';
		this.str = s;
		this.index = 0;
		this.count = 0;
	}

	private char c; // the current letter
	private int index; // where the next unread pair starts
	private int count; // copies of c still to return
	private String str;

	public char next() {
		if (!hasNext()) {
			return ' '; // BASE: nothing left
		}
		if (count == 0) { // READ: the current letter is used up, so take the next pair
			c = str.charAt(index++);
			while (index < str.length() && Character.isDigit(str.charAt(index))) {
				count = count * 10 + str.charAt(index) - '0'; // its count, digit by digit
				index += 1;
			}
		}
		count -= 1; // TAKE: one copy of c
		return c;
	}

	public boolean hasNext() {
		return index != str.length() || count != 0; // LEFT: an unread pair, or copies of c
	}

	public static void main(String[] args) {
		CompressedStringIteratorSimple csi = new CompressedStringIteratorSimple("a2b1c5");
		while (csi.hasNext()) {
			System.out.println(csi.next()); // a a b c c c c c, one per line
		}
		CompressedStringIteratorSimple lc = new CompressedStringIteratorSimple("L1e2t1C1o1d1e1");
		System.out.println(lc.next()); // L
		System.out.println(lc.next()); // e
		System.out.println(lc.next()); // e
		System.out.println(lc.next()); // t
		System.out.println(lc.next()); // C
		System.out.println(lc.next()); // o
		System.out.println(lc.hasNext()); // true
		System.out.println(lc.next()); // d
		System.out.println(lc.hasNext()); // true
	}
}
