package com.svetanis.algorithms.string;

// 1165. Single-Row Keyboard
//
// Input: a keyboard string that is a permutation of the 26 lowercase letters, one per key, laid
// out in one row; and a word. The finger starts on index 0. Typing a letter moves the finger to
// that letter's key, which costs the distance between the two indexes. Returns the total cost of
// typing the word. On LeetCode: keyboard.length == 26, 1 <= word.length <= 10^4, lowercase letters.
//
// The one idea: look up where each letter sits once -- indexes[c - 'a'] is the position of letter c
// on the keyboard. Then the cost of each letter is just the distance from where the finger is to
// that position, added up while the finger follows the word.
//
// Time: O(26 + w) -- build the 26 positions, then one step per letter of the word (w).
// Space: O(1) -- an array of 26.

public final class SingleRowKeyboard {

	public static int srk(String keyboard, String word) {
		int[] indexes = indexes(keyboard);
		int curr = 0; // where the finger is
		int total = 0;
		for (char c : word.toCharArray()) { // WALK: letter by letter
			int target = indexes[c - 'a'];
			total += Math.abs(curr - target); // TAKE: the move costs the distance
			curr = target; // MOVE the finger
		}
		return total;
	}

	private static int[] indexes(String keyboard) {
		int[] indexes = new int[26];
		for (int i = 0; i < keyboard.length(); i++) {
			indexes[keyboard.charAt(i) - 'a'] = i; // RECORD: the position of this letter
		}
		return indexes;
	}

	public static void main(String[] args) {
		System.out.println(srk("pqrstuvwxyzabcdefghijklmno", "code")); // 37
		System.out.println(srk("abcdefghijklmnopqrstuvwxyz", "cba")); // 4
	}
}
