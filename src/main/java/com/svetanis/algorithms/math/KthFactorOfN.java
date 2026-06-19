package com.svetanis.algorithms.math;

// 1492. The kth Factor of n

public final class KthFactorOfN {

	public static int kthFactor(int n, int k) {
		int factor = 0;
		for(int i = 1; i <= n; i++) {
			if(n % i == 0 && k > 0) {
				factor = i;
				k--;
			}
			if(k == 0) {
				return factor;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(kthFactor(12, 3)); // 3
		System.out.println(kthFactor(7, 2)); // 7
		System.out.println(kthFactor(4, 4)); // -1
	}
}