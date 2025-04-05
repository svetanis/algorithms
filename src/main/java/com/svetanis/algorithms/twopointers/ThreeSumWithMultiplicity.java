package com.svetanis.algorithms.twopointers;

// 923. 3Sum With Multiplicity

public final class ThreeSumWithMultiplicity {
	// Time complexity: O(n^2)

	private static final int MOD = 1000000007;

	public static int triplets(int[] a, int target) {
		int[] freq = frequencies(a);
		int count = 0;
		for (int i = 0; i < a.length; ++i) {
			int second = a[i];
			freq[second]--;
			for (int j = 0; j < i; j++) {
				int first = a[j];
				int third = target - first - second;
				if (third >= 0 && third <= 100) {
					count = (count + freq[third]) % MOD;
				}
			}
		}
		return count;
	}

	private static int[] frequencies(int[] a) {
		int[] count = new int[101];
		for (int num : a) {
			count[num]++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 };
		System.out.println(triplets(a, 8)); // 20

		int[] a1 = { 1, 1, 2, 2, 2, 2 };
		System.out.println(triplets(a1, 5)); // 12

		int[] a2 = { 2, 1, 3 };
		System.out.println(triplets(a2, 6)); // 1
	}
}