package com.svetanis.algorithms.string;

// 299. Bulls and Cows
//
// Input: digit strings secret and guess of the same length. A bull is a position where the two
// hold the same digit. A cow is a digit of guess that also occurs in secret at a different
// position, each digit of secret used for at most one cow. Returns the hint "xAyB": x bulls, y
// cows. On LeetCode: 1 <= secret.length, guess.length <= 1000, digits only.
//
// The one idea: bulls are settled position by position. Every position that is NOT a bull leaves
// one digit of secret and one digit of guess unused; count how often each digit is left over on
// each side. A digit d that is left over a times in secret and b times in guess can form
// min(a, b) cows, because each cow pairs one leftover of secret with one leftover of guess.
// Bull positions must stay out of the counts, or a digit would be counted as both a bull and a cow.
//
// Time: O(n) -- one pass over the strings, then 10 digits
// Space: O(1) -- two arrays of 10 counts

public final class BullsAndCows {

	public static String hint(String secret, String guess) {
		int[] scount = new int[10]; // LEFTOVER: digit d of secret not matched as a bull
		int[] gcount = new int[10]; // LEFTOVER: digit d of guess not matched as a bull
		int bulls = 0;
		int cows = 0;
		for (int i = 0; i < secret.length(); i++) {
			int sdigit = secret.charAt(i) - '0';
			int gdigit = guess.charAt(i) - '0';
			if (sdigit == gdigit) {
				bulls++; // BULL: same digit, same position
			} else {
				scount[sdigit]++; // KEEP: both digits stay available for cows
				gcount[gdigit]++;
			}
		}
		for (int i = 0; i < scount.length; i++) {
			cows += Math.min(scount[i], gcount[i]); // COW: each pair uses one leftover from each side
		}
		return String.format("%dA%dB", bulls, cows);
	}

	public static void main(String[] args) {
		System.out.println(hint("1807", "7810")); // 1A3B
		System.out.println(hint("1123", "0111")); // 1A1B
	}
}
