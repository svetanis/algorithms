package com.svetanis.algorithms.string;

// 2047. Number of Valid Words in a Sentence

public final class CountValidWords {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int countValidWords(String s) {
		int count = 0;
		for (String token : s.split(" ")) {
			if (valid(token)) {
				count++;
			}
		}
		return count;
	}

	private static boolean valid(String s) {
		int len = s.length();
		if(len == 0) {
			return false;
		}
		boolean hasHyphen = false;
		for(int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if(Character.isDigit(c)) {
				return false;
			}
			boolean pc = c == '!' || c == '.' || c == ',';
			if(i < len - 1 && pc) {
				return false;
			}
			if(c == '-') {
				if(hasHyphen || i == 0 || i == len - 1 
						|| !Character.isLetter(s.charAt(i - 1)) 
						|| !Character.isLetter(s.charAt(i + 1))) {
					return false;
				}
				hasHyphen = true;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(countValidWords("cat and  dog")); // 3
		System.out.println(countValidWords("!this  1-s b8d!")); // 0
		System.out.println(countValidWords("alice and  bob are playing stone-game10")); // 5
	}
}
