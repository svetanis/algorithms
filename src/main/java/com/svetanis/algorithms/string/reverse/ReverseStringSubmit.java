package com.svetanis.algorithms.string.reverse;

// 344. Reverse String

public final class ReverseStringSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static void reverseStr(char[] chars) {
		reverse(chars, 0, chars.length - 1);
	}
	
	private static void reverse(char[] chars, int left, int right) {
		if(left >= right) {
			return;
		}
		char temp = chars[left];
		chars[left] = chars[right];
		chars[right] = temp;
		reverse(chars, left + 1, right - 1);
	}
	
	public static void reverse(char[] chars) {
		int n = chars.length;
		int left = 0;
		int right = n - 1;
		while (left < right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}
	}

	public static void main(String[] args) {
		String s1 = "hello";
		reverse(s1.toCharArray());
		String s2 = "Hannah";
		reverse(s2.toCharArray());
	}
}