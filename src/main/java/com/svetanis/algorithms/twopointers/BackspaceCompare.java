package com.svetanis.algorithms.twopointers;

// 844. Backspace String Compare

// given two strings containing backspaces 
// (identified by the char #)
// check if the two strings are equal

public final class BackspaceCompare {
	// Time Complexity: O(n + m)
	// Space Complexity: O(1)

	public static boolean compare(String s, String t) {
		int i = s.length() - 1;
		int j = t.length() - 1;
		while (i >= 0 || j >= 0) {
			i = backspace(s, i);
			j = backspace(t, j);
			if (i >= 0 && j >= 0) {
				if (s.charAt(i) != t.charAt(j)) {
					return false;
				}
			} else if (i >= 0 || j >= 0) {
				return false;
			}
			i--;
			j--;
		}
		return true;
	}

	private static int backspace(String s, int index) {
		int skip = 0;
		while (index >= 0) {
			if (s.charAt(index) == '#') {
				skip++;
				index--;
			} else if (skip > 0) {
				skip--;
				index--;
			} else {
				break;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		System.out.println(compare("ab#c", "ad#c")); // true
		System.out.println(compare("ab##", "c#d#")); // true
		System.out.println(compare("a#c", "b")); // false
		System.out.println(compare("bxj##tw", "bxj###tw")); // false
		System.out.println(compare("a##c", "#a#c")); // false

		System.out.println(compare("xy#z", "xzz#")); // true
		System.out.println(compare("xy#z", "xyz#")); // false
		System.out.println(compare("xp#", "xyz##")); // true
		System.out.println(compare("xywrrmp", "xywrrmu#p")); // true
	}
}
