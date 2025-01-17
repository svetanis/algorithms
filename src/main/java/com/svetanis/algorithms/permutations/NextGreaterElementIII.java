package com.svetanis.algorithms.permutations;

// 556. Next Greater Element

public final class NextGreaterElementIII {
	// Time Complexity: O(n)

	public static int nextGreater(int n) {
		String s = String.valueOf(n);
		String next = nextGreater(s);
		if (s.equals(next)) {
			return -1;
		}
		long result = Long.parseLong(next);
		return result > Integer.MAX_VALUE ? -1 : (int) result;

	}

	public static String nextGreater(String s) {
		char[] chars = s.toCharArray();
		int len = chars.length;
		int pivot = len - 2;
		// find digit that is less than
		// its immediate right digit
		while (pivot >= 0 && chars[pivot] >= chars[pivot + 1]) {
			pivot--;
		}
		if (pivot < 0) {
			return s;
		}
		// find digit that is greater
		// than the digit found above
		int rightMostSuccessor = len - 1;
		while (chars[pivot] >= chars[rightMostSuccessor]) {
			rightMostSuccessor--;
		}
		swap(chars, pivot, rightMostSuccessor);
		reverse(chars, pivot + 1, len - 1);
		return new String(chars);
	}

	private static void reverse(char[] chars, int start, int end) {
		while (start < end) {
			swap(chars, start, end);
			start++;
			end--;
		}
	}

	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	public static void main(String[] args) {
		System.out.println(nextGreater(12)); // 21
		System.out.println(nextGreater(21)); // -1
	}
}
