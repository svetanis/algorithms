package com.svetanis.algorithms.bits.xor;

// 1863. Sum of All Subset XOR Totals

// visit every subset once, carrying its XOR down the recursion

public final class SumAllSubsetXOR {
	// Time Complexity: O(2^n)
	// Space Complexity: O(n), the recursion

	private int total;
	
	public int subsetXorSum(int[] a) {
		total = 0; // the total belongs to this call only
		dfs(a, 0, 0);
		return total;
	}
	
	private void dfs(int[] a, int index, int xor) {
		total += xor; // every call is one subset
		for(int i = index; i < a.length; i++) {
			xor ^= a[i]; // choose a[i]
			dfs(a, i + 1, xor);
			xor ^= a[i]; // unchoose: ^ undoes itself
		}
	}

	public static void main(String[] args) {
		SumAllSubsetXOR sas = new SumAllSubsetXOR();
		int[] a1 = { 1, 3 };
		System.out.println(sas.subsetXorSum(a1)); // 6
		int[] a2 = { 5, 1, 6 };
		System.out.println(sas.subsetXorSum(a2)); // 28
		int[] a3 = { 3, 4, 5, 6, 7, 8 };
		System.out.println(sas.subsetXorSum(a3)); // 480
	}
}
