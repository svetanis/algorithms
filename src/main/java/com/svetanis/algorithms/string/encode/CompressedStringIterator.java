package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

import java.util.ArrayList;
import java.util.List;

// 604. Design Compressed String Iterator
//
// Input: a compressed string, each letter followed by how many times it repeats ("L1e2" is
// "Lee"). next() returns the next letter of the uncompressed string, or ' ' when none is left;
// hasNext() says whether one is left. On LeetCode: 1 <= compressedString.length <= 1000, lower- and
// upper-case letters and digits, each count in [1, 10^9], at most 100 calls to next and hasNext.
//
// The one idea: never write the uncompressed string out -- a count of 10^9 would not fit in
// memory. The constructor reads the string once into a list of nodes; one node is one letter and
// how many copies of it are still to be returned. index points at the node being used up. next()
// returns that node's letter and lowers its count; at zero, index moves to the next node.
//
// Time: O(n) for the constructor -- each character is read once; O(1) per next() and hasNext().
// Space: O(k) -- one node per letter-and-count pair, k <= n / 2.
//
// Sibling: CompressedStringIteratorSimple keeps no list: it reads the next letter and its count
// from the string only when the current run is used up.

public final class CompressedStringIterator {

	public CompressedStringIterator(String s) {
		this.list = compressed(s);
	}

	private int index; // the node being used up
	private List<Node> list;

	private List<Node> compressed(String s) {
		int index = 0;
		int len = s.length();
		List<Node> list = new ArrayList<>();
		while (index < len) { // WALK: one letter-and-count pair per pass
			char c = s.charAt(index);
			int count = 0;
			while (++index < len && isDigit(s.charAt(index))) { // READ: the count's digits, left to right
				count = count * 10 + (s.charAt(index) - '0');
			}
			list.add(new Node(c, count));
		}
		return list;
	}

	public char next() {
		if (!hasNext()) {
			return ' '; // BASE: nothing left
		}
		Node node = list.get(index);
		char c = node.c;
		node.count--; // TAKE: one copy of this letter
		if (node.count == 0) {
			index++; // NEXT NODE: this letter is used up
		}
		return c;
	}

	public boolean hasNext() {
		return index < list.size() && list.get(index).count > 0; // LEFT: a node with copies still to give
	}

	public static void main(String[] args) {
		CompressedStringIterator csi = new CompressedStringIterator("a2b1c5");
		while (csi.hasNext()) {
			System.out.println(csi.next()); // a a b c c c c c, one per line
		}
		CompressedStringIterator lc = new CompressedStringIterator("L1e2t1C1o1d1e1");
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

	private static class Node {
		private char c; // the letter
		private int count; // copies of it still to return

		public Node(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}
}
