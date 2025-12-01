package com.svetanis.algorithms.matrix;

// 1570. Dot Product of Two Sparse Vectors

public final class SparseVector {
	// Time Complexity: O(min(k, l))
	// k and l are number of non-zero elements

	public SparseVector(int[] a) {
		this.a = a;
	}

	private int[] a;

	public int dotProduct(SparseVector sv) {
		int result = 0;
		for (int i = 0; i < sv.a.length; i++) {
			result += sv.a[i] * a[i];
		}
		return result;
	}

	public static void main(String[] args) {
		SparseVector sv1 = new SparseVector(new int[] { 1, 0, 0, 2, 0 });
		SparseVector sv2 = new SparseVector(new int[] { 0, 3, 0, 4, 0 });
		System.out.println(sv1.dotProduct(sv2)); // 8
	}
}
