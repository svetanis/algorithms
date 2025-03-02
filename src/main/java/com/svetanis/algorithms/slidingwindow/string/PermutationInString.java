package com.svetanis.algorithms.slidingwindow.string;

// 567. Permutation in String

public final class PermutationInString {
	// Time Complexity: O(n)
	
	public static boolean checkInclusion(String s1, String s2) {
		int k = s1.length();
		if (k > s2.length()) {
			return false;
		}
		int[] chars = chars(s1, k);
		int[] sw = chars(s2, k);
		for (int i = k; i < s2.length(); i++) {
			if (matches(chars, sw)) {
				return true;
			}
			char right = s2.charAt(i);
			sw[right - 'a']++;
			char left = s2.charAt(i - k);
			sw[left - 'a']--;
		}
		return matches(chars, sw);
	}

	private static boolean matches(int[] a1, int[] a2) {
		for (int i = 0; i < 26; i++) {
			if (a1[i] != a2[i]) {
				return false;
			}
		}
		return true;
	}

	private static int[] chars(String s, int k) {
		int[] a = new int[26];
		for (int i = 0; i < k; i++) {
			a[s.charAt(i) - 'a']++;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(checkInclusion("ab", "eidbaooo")); // true
		System.out.println(checkInclusion("ab", "eidboaoo")); // false
		System.out.println(checkInclusion("adc", "dcda")); // true
	}
}
