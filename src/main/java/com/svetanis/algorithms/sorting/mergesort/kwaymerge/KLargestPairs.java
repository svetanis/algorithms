package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// given two sorted arrays in descending order,
// find k pairs with the largest sum where 
// each pair consists of numbers from both arrays

public final class KLargestPairs {
	// Time Complexity: O(n * m * log k)
	// Space Complexity: O(k)

	public static ImmutableList<Pair<Integer, Integer>> kLargestPairs(int[] a1, int[] a2, int k) {
		Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>(pc());
		for(int i = 0; i < a1.length && i < k; i++) {
			for(int j = 0; j < a2.length && j < k; j++) {
				if(pq.size() < k) {
					pq.offer(Pair.build(a1[i], a2[j]));
				} else {
				    int sum = a1[i] + a2[j];
				    Pair<Integer, Integer> top = pq.peek();
				    int tps = top.getLeft() + top.getRight();
				    if(sum > tps) {
				    	pq.poll();
				    	pq.offer(Pair.build(a1[i], a2[j]));
				    }
				}
			}
		}
		return newList(pq.stream().collect(toList()));
	}
	
	private static Comparator<Pair<Integer, Integer>> pc(){
		return (p1, p2) -> (p1.getLeft() + p1.getRight()) - (p2.getLeft() + p2.getRight());
	}

	public static void main(String[] args) {
		int[] a1 = {9, 8, 2};
		int[] a2 = {6, 3, 1};
		System.out.println(kLargestPairs(a1, a2, 3));
	}
}