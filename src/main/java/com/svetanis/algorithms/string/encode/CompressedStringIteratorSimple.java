package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

// 604. Design Compressed String Iterator

public final class CompressedStringIteratorSimple {

	public CompressedStringIteratorSimple(String s) {
		this.sb = compressed(s);
	}

	private int index;
	private StringBuilder sb = new StringBuilder();

	private StringBuilder compressed(String s) {
		int index = 0;
		int len = s.length();
		while (index < len) {
			char c = s.charAt(index);
			int count = 0;
			while (++index < len && isDigit(s.charAt(index))) {
				count = count * 10 + (s.charAt(index) - '0');
			}
			for (int j = 0; j < count; j++) {
				sb.append(c);
			}
		}
		return sb;
	}

	public char next() {
		return !hasNext() ? ' ' : sb.charAt(index++);
	}

	public boolean hasNext() {
		return index < sb.length();
	}

	public static void main(String[] args) {
		CompressedStringIteratorSimple csi = new CompressedStringIteratorSimple("a2b1c5");
		while (csi.hasNext()) {
			System.out.println(csi.next());
		}
	}
}
