package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

import java.util.ArrayList;
import java.util.List;

// 604. Design Compressed String Iterator

public final class CompressedStringIterator {

	public CompressedStringIterator(String s) {
		this.list = compressed(s);
	}

	private int index;
	private List<Node> list;

	private List<Node> compressed(String s) {
		int index = 0;
		int len = s.length();
		List<Node> list = new ArrayList<>();
		while (index < len) {
			char c = s.charAt(index);
			int count = 0;
			while (++index < len && isDigit(s.charAt(index))) {
				count = count * 10 + (s.charAt(index) - '0');
			}
			list.add(new Node(c, count));
		}
		return list;
	}

	public char next() {
		if (!hasNext()) {
			return ' ';
		}
		Node node = list.get(index);
		char c = node.c;
		// decrement the count of the character
		node.count--;
		if (node.count == 0) {
			index++;
		}
		return c;
	}

	public boolean hasNext() {
		return index < list.size() && list.get(index).count > 0;
	}

	public static void main(String[] args) {
		CompressedStringIterator csi = new CompressedStringIterator("a2b1c5");
		while (csi.hasNext()) {
			System.out.println(csi.next());
		}
	}

	private static class Node {
		private char c;
		private int count;

		public Node(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}
}
