package com.svetanis.algorithms.string.encode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 271. Encode and Decode Strings

public final class Codec {
	// Time Complexity: O(n*k)
	// Space Complexity: O(n*k)

	public static String encode(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			char len = (char) s.length();
			sb.append(len).append(s);
		}
		return sb.toString();
	}

	public static List<String> decode(String s) {
		List<String> list = new ArrayList<>();
		int n = s.length();
		int index = 0;
		while (index < n) {
			int len = s.charAt(index++);
			String substr = s.substring(index, index + len);
			list.add(substr);
			index += len;
		}

		return list;
	}

	public static void main(String[] args) {
		List<String> list = Arrays.asList("hello", "world", "leetcode", "example");
		String encoded = encode(list);
		System.out.println(encoded);
		List<String> decoded = decode(encoded);
		System.out.println(decoded);
	}
}
