package com.svetanis.algorithms.string.encode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 271. Encode and Decode Strings
//
// encode turns a list of strings into one string; decode turns that string back into the same
// list. A string may contain any of the 256 ASCII characters, so no character is safe to put
// between them as a separator: whichever is chosen can also appear inside a string.
//
// Each string is written as its length in decimal digits, then '#', then the string itself.
// The decoder reads the digits up to the first '#'. That '#' is always the one after the length,
// because the length comes first and digits contain no '#'. The number then says exactly how
// many characters follow, so the string's own characters, '#' and digits included, are never
// looked at.
//
// Why the '#' is needed at all: a length in digits has no fixed width (5 is one digit, 12 is
// two), and a string may itself start with a digit. Without a marker, ["1x"] would encode as
// "21x" and the decoder would read the length 21.
//
// The follow-up (any characters, not only ASCII): nothing here depends on which characters may
// appear, because length() and substring() both count Java chars. It breaks only if the two
// sides count different units - a receiver counting UTF-8 bytes, where "é" is one char but two
// bytes. Then the length must be written in the receiver's unit.
//
// encodeCharLength and decodeCharLength need no '#': the length is written as ONE char whose
// stored number is the length - (char) 12 for a string of length 12, an invisible character, not
// the digits '1' and '2' (stored as 49 and 50). The decoder reads charAt(i) as an int, gets 12,
// and knows the string starts at i + 1, because the length is always exactly one char wide.
// The price: a char only holds 0 .. 65,535, so a string of 65,536 characters or more breaks the
// decode (StringIndexOutOfBoundsException). LeetCode's limit is 200.

public final class Codec {
	// Time Complexity: O(total characters), each character written once and read once
	// Space Complexity: O(total characters), the encoded string or the decoded list

	public static String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder(); // BUILD
		for (String s : strs) {
			sb.append(s.length()).append('#'); // LENGTH, then the marker that ends it
			sb.append(s); // STRING: any characters at all
		}
		return sb.toString();
	}

	public static List<String> decode(String s) {
		List<String> strs = new ArrayList<>(); // LIST
		int i = 0; // where the next piece starts
		while (i < s.length()) {
			int hash = s.indexOf('#', i); // MARK: the first '#' from a piece's start ends its length
			int len = Integer.parseInt(s.substring(i, hash)); // LENGTH: the digits from i up to the '#'
			int start = hash + 1;
			strs.add(s.substring(start, start + len)); // CUT: exactly len characters, whatever they are
			i = start + len; // MOVE: the next piece starts right after them
		}
		return strs;
	}

	public static String encodeCharLength(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append((char) s.length()); // LENGTH as ONE char whose stored number is the length
			sb.append(s); // STRING
		}
		return sb.toString();
	}

	public static List<String> decodeCharLength(String s) {
		List<String> strs = new ArrayList<>();
		int i = 0; // where the next length char is
		while (i < s.length()) {
			int len = s.charAt(i); // LENGTH: a char read as an int gives its stored number
			int start = i + 1; // the length is always one char wide, so no marker is needed
			strs.add(s.substring(start, start + len)); // CUT: exactly len characters
			i = start + len; // MOVE: the next length char
		}
		return strs;
	}

	public static void main(String[] args) {
		List<String> words = Arrays.asList("Hello", "World");
		System.out.println(encode(words)); // 5#Hello5#World
		System.out.println(decode(encode(words))); // [Hello, World]

		List<String> empty = Arrays.asList("");
		System.out.println(encode(empty)); // 0#
		System.out.println(decode(encode(empty)).size()); // 1: the one empty string comes back

		List<String> tricky = Arrays.asList("3#ab", "", "##12");
		System.out.println(decode(encode(tricky))); // [3#ab, , ##12]
		System.out.println(decodeCharLength(encodeCharLength(tricky))); // [3#ab, , ##12]
	}
}
