package com.svetanis.algorithms.string;

// 3019. Number of Changing Keys

public final class CountChangingKeys {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int countKeyChanges(String s) {
		int count = 0;
		for(int i = 0; i < s.length() - 1; i++) {
			char curr = Character.toLowerCase(s.charAt(i));
			char next = Character.toLowerCase(s.charAt(i + 1));
			if(curr != next) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countKeyChanges("aAbBcC")); // 2
		System.out.println(countKeyChanges("AaAaAaaA")); // 0
	}
}
