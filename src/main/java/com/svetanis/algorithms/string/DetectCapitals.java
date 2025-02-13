package com.svetanis.algorithms.string;

// 520. Detect Capital

public final class DetectCapitals {
	// Time Complexity: O(n)

	public static boolean detectCapitals(String word) {
		int len = word.length();
		int capitals = capitals(word);
		boolean one = len == capitals;
		boolean two = capitals == 0;
		boolean three = capitals == 1 && Character.isUpperCase(word.charAt(0));
		return one || two || three;
	}

	private static int capitals(String word) {
		int count = 0;
		for (char c : word.toCharArray()) {
			if (Character.isUpperCase(c)) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(detectCapitals("USA")); // true
		System.out.println(detectCapitals("FlaG")); // false
		System.out.println(detectCapitals("g")); // true
		System.out.println(detectCapitals("ggg")); // true
		System.out.println(detectCapitals("ffffffffffffffffffffF")); // false

	}
}
