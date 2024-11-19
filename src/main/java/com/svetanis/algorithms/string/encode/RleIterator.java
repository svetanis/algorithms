package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Queue;

// 900. RLE Iterator

public final class RleIterator {

	public RleIterator(int[] encoding) {
		this.queue = decode(encoding);
	}

	private Queue<Integer> queue;

	private Queue<Integer> decode(int[] encoding) {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i < encoding.length; i += 2) {
			int count = encoding[i - 1];
			int value = encoding[i];
			while (count > 0) {
				queue.offer(value);
				count--;
			}
		}
		return queue;
	}

	public int next(int n) {
		if (n > queue.size()) {
			return -1;
		}
		int value = -1;
		while (!queue.isEmpty() && n > 0) {
			value = queue.poll();
			n--;
		}
		return value;
	}

	public static void main(String[] args) {
		int[] a = { 3, 8, 0, 9, 2, 5 };
		RleIterator rle = new RleIterator(a);
		System.out.println(rle.next(2)); // 8
		System.out.println(rle.next(1)); // 8
		System.out.println(rle.next(1)); // 5
		System.out.println(rle.next(2)); // -1
	}
}
