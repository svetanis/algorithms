package com.svetanis.algorithms.search.binary.invariant;

// 911. Online Election

public final class TopVotedCandidate {

	private int[] leads;
	private int[] times;

	public TopVotedCandidate(int[] candidates, int[] times) {
		this.times = times;
		this.leads = leaders(candidates);
	}

	private int[] leaders(int[] candidates) {
		int n = candidates.length;
		int max = 0;
		int leader = 0;
		int[] count = new int[n];
		int[] leaders = new int[n];
		for (int i = 0; i < n; i++) {
			int candidate = candidates[i];
			count[candidate]++;
			if (count[candidate] >= max) {
				leader = candidate;
				max = count[candidate];
			}
			leaders[i] = leader;
		}
		return leaders;
	}

	public int query(int time) {
		int left = 0;
		int right = leads.length - 1;
		while (left < right) {
			int mid = left + (right - left + 1) / 2;
			if (times[mid] <= time) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return leads[left];
	}

	public static void main(String[] args) {
		int[] people = { 0, 1, 1, 0, 0, 1, 0 };
		int[] times = { 0, 5, 10, 15, 20, 25, 30 };
		TopVotedCandidate tvc = new TopVotedCandidate(people, times);
		System.out.println(tvc.query(3)); // 0
		System.out.println(tvc.query(12)); // 1
		System.out.println(tvc.query(25)); // 1
		System.out.println(tvc.query(15)); // 0
		System.out.println(tvc.query(24)); // 0
		System.out.println(tvc.query(8)); // 1
	}
}