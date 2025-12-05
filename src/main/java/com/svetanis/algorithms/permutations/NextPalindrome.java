package com.svetanis.algorithms.permutations;

// 1842. Next Palindrome Using Same Digits
// not correct
public final class NextPalindrome {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String nextPalindrome(String s) {
		char[] chars = s.toCharArray();
		if (!nextPermutation(chars)) {
			return "";
		}
		int n = s.length();
		for (int i = 0; i < n / 2; ++i) {
			chars[n - 1 - i] = chars[i];
		}
		return new String(chars);
	}

	private static boolean nextPermutation(char[] a) {
		int n = a.length;
		int mid = n / 2;
		int pivot = mid - 2;
		// find digit that is less than
		// its immediate right digit
		while (pivot >= 0 && a[pivot] >= a[pivot + 1]) {
			pivot--;
		}
		if (pivot < 0) {
			return false;
		}
		// find digit that is greater
		// than the digit found above
		// rms = rightMostSuccessor
		int rms = mid - 1;
		while (rms >= 0 && a[pivot] >= a[rms]) {
			rms--;
		}
		swap(a, pivot, rms);
		reverse(a, pivot + 1, mid - 1);
		return true;
	}

	private static void reverse(char[] a, int start, int end) {
		while (start < end) {
			swap(a, start, end);
			start++;
			end--;
		}
	}

	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		System.out.println(nextPalindrome("12521")); // 15251
		System.out.println(nextPalindrome("23143034132")); // 23314041332
	}
}
