package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.java.base.utils.Arrays.sum;
import static com.svetanis.java.base.utils.Nums.isOdd;

// 416. Partition Equal Subset Sum

// Partition problem is to determine 
// whether a given set can be partitioned  
// into two subsets such that the sum 
// of elements in both subsets is same. 

public final class BalancedPartitionRecursive {
	// Time Complexity: O(2^n)
	// Space Complexity: O(n)

	public static boolean canPartition(int[] a) {
		int sum = sum(a);
		// if sum is odd there can't be
		// two subsets with equal sum
		if (isOdd(sum)) {
			return false;
		}
		// find if there is a subset with
		// sum equal to half of total sum
		return isSum(a, sum / 2);
	}

	private static boolean isSum(int[] a, int sum) {
		return isSum(a, a.length - 1, sum);
	}

	private static boolean isSum(int[] a, int n, int sum) {
		if (sum == 0) {
			return true;
		}
		if (n < 0 || sum < 0) {
			return false;
		}
		// 1. include last element
		boolean incl = isSum(a, n - 1, sum - a[n]);
		// 2. exclude last element
		boolean excl = isSum(a, n - 1, sum);
		return incl || excl;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, 5, 9, 12 };
		System.out.println(canPartition(a1)); // true
		int[] a2 = { 1, 5, 11, 5 };
		System.out.println(canPartition(a2)); // true
		int[] a3 = { 1, 2, 3, 5 };
		System.out.println(canPartition(a3)); // false
	}
}
