package com.svetanis.algorithms.string.encode;

// 900. RLE Iterator

public final class RleIteratorOnTheFly {
	// Time Complexity: O(k) - number of calls
	// Space Complexity: O(n)

	public RleIteratorOnTheFly(int[] encoding) {
		this.index = 0;
		this.count = 0;
		this.encoded = encoding;
	}

	private int index;
	private int count;
	private int[] encoded;

	public int next(int n) {
		while (index < encoded.length) {
			if (count + n > encoded[index]) {
				n -= encoded[index] - count;
				index += 2;
				count = 0;
			} else {
				count += n;
				return encoded[index + 1];
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a = { 3, 8, 0, 9, 2, 5 };
		RleIteratorOnTheFly rle = new RleIteratorOnTheFly(a);
		System.out.println(rle.next(2)); // 8
		System.out.println(rle.next(1)); // 8
		System.out.println(rle.next(1)); // 5
		System.out.println(rle.next(2)); // -1
	}
}
