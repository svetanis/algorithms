package com.svetanis.algorithms.matrix;

// 1570. Dot Product of Two Sparse Vectors

public final class SparseVector {
	// Time Complexity: O(n), n = the vector length -- every position is
	// multiplied, zeros included. storing only the non-zero entries is
	// what makes it O(non-zeros); see todo/SparseVector.java
	// Space Complexity: O(1) beyond the vectors themselves

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
