package com.svetanis.algorithms.string.encode;

// 604. Design Compressed String Iterator

public final class CompressedStringIteratorSimple {

	public CompressedStringIteratorSimple(String s) {
		this.c = ' ';
		this.str = s;
		this.index = 0;
		this.count = 0;
	}

	private char c;
	private int index;
	private int count;
	private String str;

	public char next() {
		if (!hasNext()) {
			return ' ';
		}
		if (count == 0) {
			c = str.charAt(index++);
			while (index < str.length() && Character.isDigit(str.charAt(index))) {
				count = count * 10 + str.charAt(index) - '0';
				index += 1;
			}
		}
		count -= 1;
		return c;
	}

	public boolean hasNext() {
		return index != str.length() || count != 0;
	}

	public static void main(String[] args) {
		CompressedStringIteratorSimple csi = new CompressedStringIteratorSimple("a2b1c5");
		while (csi.hasNext()) {
			System.out.println(csi.next());
		}
	}
}
