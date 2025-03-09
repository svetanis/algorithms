package com.svetanis.algorithms.permutations;

// 60. Permutation Sequence

public final class PermutaionSequence {

	public static String kPermutation(int n, int k) {
		int[] a = init(n);
		if (k == 1) {
			return build(a);
		}
		int count = 1;
		while (count < k && nextPermutation(a)) {
			count++;
			if (count == k) {
				return build(a);
			}
		}
		return build(a);
	}

	private static int[] init(int n) {
		int[] a = new int[n];
		for (int i = 1; i <= n; i++) {
			a[i - 1] = i;
		}
		return a;
	}

	private static String build(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int num : a) {
			sb.append(num);
		}
		return sb.toString();
	}

	public static boolean nextPermutation(int[] a) {
		int len = a.length;
		int pivot = len - 2;
		// find digit that is less than
		// its immediate right digit
		while (pivot >= 0 && a[pivot] >= a[pivot + 1]) {
			pivot--;
		}
		if (pivot < 0) {
			reverse(a, 0, len - 1);
			return false;
		}
		// find digit that is greater
		// than the digit found above
		int rightMostSuccessor = len - 1;
		while (a[pivot] >= a[rightMostSuccessor]) {
			rightMostSuccessor--;
		}
		swap(a, pivot, rightMostSuccessor);
		reverse(a, pivot + 1, len - 1);
		return true;
	}

	private static void reverse(int[] a, int start, int end) {
		while (start < end) {
			swap(a, start, end);
			start++;
			end--;
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		System.out.println(kPermutation(3, 3)); // 213
		System.out.println(kPermutation(4, 9)); // 2314
		System.out.println(kPermutation(3, 1)); // 123
	}
}
