package com.svetanis.algorithms.matrix;

// 1570. Dot Product of Two Sparse Vectors
//
// Input: SparseVector(a) stores the vector a, mostly zeros. dotProduct(sv) returns the dot product
// of this vector and sv, the same length: a[0] * b[0] + a[1] * b[1] + ... . On LeetCode:
// 1 <= n <= 10^5, 0 <= a[i], b[i] <= 100, so the total is at most 10^9 and an int holds it.
//
// The one idea: one position i holds one number in each vector; the dot product multiplies the two
// numbers at the same position and adds the products. This file keeps the whole array, zeros
// included, and multiplies every position. A zero adds nothing, so the answer is right, but the
// work follows the vector's length, not its number of non-zero entries.
//
// Time: O(n), n = the vector length -- every position is multiplied, zeros included.
// Space: O(1) beyond the vectors themselves -- the constructor keeps the caller's array, not a copy.
//
// Siblings, problem 1570:
//   matrix/SparseVector  the whole array, every position multiplied: O(n) (this file)
//   todo/SparseVector    only the non-zero entries, in a map, walked from the smaller map:
//                        O(min(k, l)) per product, k and l = the non-zero entries of the two
//                        vectors. The two classes share one name in different packages.

public final class SparseVector {

	public SparseVector(int[] a) {
		this.a = a; // KEEP: the caller's array, zeros included
	}

	private int[] a;

	public int dotProduct(SparseVector sv) {
		int result = 0;
		for (int i = 0; i < sv.a.length; i++) { // WALK: every position, zeros too
			result += sv.a[i] * a[i]; // MULTIPLY: the two numbers at position i
		}
		return result;
	}

	public static void main(String[] args) {
		SparseVector sv1 = new SparseVector(new int[] { 1, 0, 0, 2, 0 });
		SparseVector sv2 = new SparseVector(new int[] { 0, 3, 0, 4, 0 });
		System.out.println(sv1.dotProduct(sv2)); // 8
	}
}
