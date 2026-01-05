package com.svetanis.algorithms.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

// 346. Moving Average from Data Stream

public final class MovingAverage {

	private int maxSize;
	private double sum;
	private Deque<Integer> dq;

	public MovingAverage(int size) {
		this.maxSize = size;
		this.dq = new ArrayDeque<>();
	}

	public double next(int val) {
		if (dq.size() == maxSize) {
			sum -= dq.removeFirst();
		}
		sum += val;
		dq.addLast(val);
		double average = sum / dq.size();
		return average;
	}

	public static void main(String[] args) {
		MovingAverage mva = new MovingAverage(3);
		System.out.println(mva.next(5)); // 5
		System.out.println(mva.next(10)); // 7.5
		System.out.println(mva.next(15)); // 10
		System.out.println(mva.next(20)); // 15
	}
}