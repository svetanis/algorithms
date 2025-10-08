package com.svetanis.algorithms.string;

// 1165. Single-Row Keyboard

public final class SingleRowKeyboard {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n)

	public static int srk(String keyboard, String word) {
		int[] indexes = indexes(keyboard);
		int curr = 0;
		int total = 0;
		for (char c : word.toCharArray()) {
			int target = indexes[c - 'a'];
			total += Math.abs(curr - target);
			curr = target;
		}
		return total;
	}

	private static int[] indexes(String keyboard) {
		int[] indexes = new int[26];
		for (int i = 0; i < keyboard.length(); i++) {
			indexes[keyboard.charAt(i) - 'a'] = i;
		}
		return indexes;
	}

	public static void main(String[] args) {
		System.out.println(srk("pqrstuvwxyzabcdefghijklmno", "code")); // 38
		System.out.println(srk("abcdefghijklmnopqrstuvwxyz", "cba")); // 4
	}
}
