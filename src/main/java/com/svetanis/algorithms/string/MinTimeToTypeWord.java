package com.svetanis.algorithms.string;

// 1974. Minimum Time to Type Word Using Special Typewriter
//
// Input: a word of lowercase letters. The typewriter's 26 letters sit on a circle, a to z; a
// pointer starts at 'a'. One second moves the pointer one place either way round, and one second
// types the letter under it. Returns the least time to type the word. On LeetCode:
// 1 <= word.length <= 100.
//
// The one idea: the letters must be typed in order, so the pointer's path is forced; only the
// way round between two letters is a choice. Between letters at positions prev and curr, going
// one way takes |curr - prev| seconds and the other way takes 26 minus that, so each step costs
// the smaller of the two. Add one second per letter typed.
//
// Time: O(n) -- one step per letter.
// Space: O(1).

public final class MinTimeToTypeWord {

	public static int minTimeToType(String word) {
		int total = word.length(); // TYPE: one second per letter
		int prev = 0; // prev: where the pointer is, 0 = 'a'
		for (char c : word.toCharArray()) {
			int curr = c - 'a';
			int clockwise = Math.abs(curr - prev);
			int counterclockwise = 26 - clockwise; // the other way round the circle
			total += Math.min(counterclockwise, clockwise); // MOVE: the shorter way
			prev = curr;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(minTimeToType("abc")); // 5
		System.out.println(minTimeToType("bza")); // 7
		System.out.println(minTimeToType("zjpc")); // 34
	}
}
