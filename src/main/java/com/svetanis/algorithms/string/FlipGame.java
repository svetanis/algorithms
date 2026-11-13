package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;

// 293. Flip Game
//
// Input: a string s of '+' and '-'. A move turns two neighbouring "++" into "--". Returns every
// string s can become after one move, in order of the move's position from the left; no move
// possible gives an empty list. On LeetCode: 1 <= s.length <= 500.
//
// The one idea: one move is one position i where s[i] and s[i + 1] are both '+'. So walk the
// pairs of neighbours, and for each pair that is "++" write the string with those two characters
// changed. The moves do not affect each other: each result starts again from the original s.
//
// Time: O(n^2) -- up to n - 1 moves, and each result is a new string of n characters.
// Space: O(n^2) -- the returned list holds up to n - 1 strings of n characters.
//
// Siblings: flip builds each result from the text before the pair, "--" and the text after it;
// flip2 changes the two characters in an array, copies the array to a string, then changes them
// back.

public final class FlipGame {

	public static List<String> flip(String s) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i < s.length(); i++) { // WALK: i is the right half of the pair (i - 1, i)
			char prev = s.charAt(i - 1);
			char curr = s.charAt(i);
			if (prev == '+' && curr == '+') { // MOVE: a "++" pair
				String prefix = s.substring(0, i - 1);
				String suffix = i == s.length() - 1 ? "" : s.substring(i + 1);
				String joined = String.join("", prefix, "--", suffix); // RECORD: before + "--" + after
				list.add(joined);
			}
		}
		return list;
	}

	public static List<String> flip2(String s) {
		char[] chars = s.toCharArray();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < s.length() - 1; i++) { // WALK: i is the left half of the pair (i, i + 1)
			char curr = chars[i];
			char next = chars[i + 1];
			if (next == '+' && curr == '+') { // MOVE: a "++" pair
				chars[i] = '-';
				chars[i + 1] = '-';
				list.add(new String(chars)); // RECORD: the flipped array as a string
				chars[i] = '+'; // UNDO: so the next move starts from the original
				chars[i + 1] = '+';
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(flip2("++++")); // [--++, +--+, ++--]
		System.out.println(flip2("+++-++")); // [--+-++, +---++, +++---]
		System.out.println(flip2("++--+++-+")); // [----+++-+, ++----+-+, ++--+---+]
	}
}
