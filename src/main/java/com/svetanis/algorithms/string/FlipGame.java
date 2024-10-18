package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

// 293. Flip Game

public final class FlipGame {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static List<String> flip(String s) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i < s.length(); i++) {
			char prev = s.charAt(i - 1);
			char curr = s.charAt(i);
			if (prev == '+' && curr == '+') {
				String prefix = s.substring(0, i - 1);
				String suffix = i == s.length() - 1 ? "" : s.substring(i + 1);
				String joined = Joiner.on("").join(prefix, "--", suffix);
				list.add(joined);
			}
		}
		return list;
	}

	public static List<String> flip2(String s) {
		char[] chars = s.toCharArray();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < s.length() - 1; i++) {
			char curr = chars[i];
			char next = chars[i + 1];
			if (next == '+' && curr == '+') {
				chars[i] = '-';
				chars[i + 1] = '-';
				list.add(new String(chars));
				chars[i] = '+';
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
