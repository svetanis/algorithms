package com.svetanis.algorithms.dp.countways.decode;

import static java.lang.Integer.parseInt;
 
// Number of Ways to Decode a Message
// https://algo.monster/problems/decode_ways

// given a message consisting
// of digits 0-9 to decode
// letters are encoded to digits
// by their positions in the alphabet
// how may ways are there to decode it?

public final class CountWaysToDecodeDigits {
	// Time Complexity: O(2^n)

	public static int count(String s) {
		return dfs(0, s);
	}

	private static int dfs(int index, String s) {
		if (index == s.length()) {
			return 1;
		}
		int count = 0;
		for (int i = index; i < s.length(); i++) {
			String prefix = s.substring(index, i + 1);
			if (valid(prefix)) {
				count += dfs(index + prefix.length(), s);
			}
		}
		return count;
	}

	private static boolean valid(String s) {
		// a leading zero is never decodable: the encoding is not
		// zero-padded, so "01" is not a way of writing "A".
		// parseInt("01") == 1, so parseInt alone is NOT a validator.
		if (s.startsWith("0")) {
			return false;
		}
		int num = parseInt(s);
		return num > 0 && num <= 26;
	}

	public static void main(String args[]) {
		System.out.println(count("18")); // 2
		System.out.println(count("123")); // 3
		System.out.println(count("02")); // 0
		System.out.println(count("1221")); // 5
		System.out.println(count("101")); // 1 -- only "10"+"1" = JA; "1"+"01" is not a decoding
	}
}