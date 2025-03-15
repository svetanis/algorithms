package com.svetanis.algorithms.string;

// 299. Bulls and Cows

public final class BullsAndCows {
	// Time Complexity: O(n)

	public static String hint(String secret, String guess) {
		int[] scount = new int[10];
		int[] gcount = new int[10];
		int bulls = 0;
		int cows = 0;
		for (int i = 0; i < secret.length(); i++) {
			int sdigit = secret.charAt(i) - '0';
			int gdigit = guess.charAt(i) - '0';
			if (sdigit == gdigit) {
				bulls++;
			} else {
				scount[sdigit]++;
				gcount[gdigit]++;
			}
		}
		for (int i = 0; i < scount.length; i++) {
			cows += Math.min(scount[i], gcount[i]);
		}
		return String.format("%dA%dB", bulls, cows);
	}

	public static void main(String[] args) {
		System.out.println(hint("1807", "7810")); // 1A3B
		System.out.println(hint("1123", "0111")); // 1A1B
	}
}
