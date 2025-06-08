package com.svetanis.algorithms.twopointers;

import static java.util.Arrays.asList;

import java.util.List;

public final class TeleporterArrays {

	private static final int MOD = (int) 1e9 + 7;

	public static int maxScore(List<Integer> list1, List<Integer> list2) {
		int n1 = list1.size();
		int n2 = list2.size();
		long sum1 = 0;
		long sum2 = 0;
		long maxScore = 0;
		int left = 0, right = 0;
		while (left < n1 || right < n2) {
			if (left < n1 && right < n2 && list1.get(left).equals(list2.get(right))) {
				maxScore += Math.max(sum1, sum2) + list1.get(left);
				maxScore %= MOD;
				sum1 = 0;
				sum2 = 0;
				left++;
				right++;
				continue;
			}
			if (left == n1 || (right != n2 && list1.get(left) > list2.get(right))) {
				sum2 += list2.get(right);
				right++;
			} else {
				sum1 += list1.get(left);
				left++;
			}
		}
		maxScore += Math.max(sum1, sum2);
		return (int) (maxScore % MOD);
	}

	public static void main(String[] args) {
		System.out.println(maxScore(asList(2, 4, 5, 8, 10), asList(4, 6, 8, 9))); // 30
		System.out.println(maxScore(asList(1, 4, 5, 8, 9), asList(2, 3, 6, 7, 10))); // 28
	}
}
