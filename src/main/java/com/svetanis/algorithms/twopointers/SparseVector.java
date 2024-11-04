package com.svetanis.algorithms.twopointers;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// given a sorted list of numbers,
// remove duplicates and return
// the new length. do it in-place
// without using extra memory

public final class SparseVector {

	public SparseVector(List<Integer> list) {
		this.map = build(list);
	}

	private Map<Integer, Integer> map;

	private Map<Integer, Integer> build(List<Integer> list) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			int curr = list.get(i);
			if (curr != 0) {
				map.put(i, curr);
			}
		}
		return map;
	}

	public int dotProduct(SparseVector sv) {
		Map<Integer, Integer> smaller = map;
		Map<Integer, Integer> larger = sv.map;
		if (larger.size() < smaller.size()) {
			Map<Integer, Integer> temp = smaller;
			smaller = larger;
			larger = temp;
		}
		return dotProduct(smaller, larger);
	}

	private int dotProduct(Map<Integer, Integer> smaller, Map<Integer, Integer> larger) {
		int total = 0;
		for (int index : smaller.keySet()) {
			int first = smaller.get(index);
			int second = larger.getOrDefault(index, 0);
			total += first * second;
		}
		return total;
	}

	public static void main(String[] args) {
		SparseVector sv1 = new SparseVector(asList(1, 0, 0, 2, 0));
		SparseVector sv2 = new SparseVector(asList(0, 3, 0, 4, 0));
		System.out.println(sv1.dotProduct(sv2)); // 8
	}
}
