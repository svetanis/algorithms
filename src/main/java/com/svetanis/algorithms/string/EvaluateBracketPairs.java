package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 1807. Evaluate the Bracket Pairs of a String
//
// Input: a string s with bracket pairs, each "(key)" holding a non-empty key, and a list
// knowledge of [key, value] pairs. Replaces each bracket pair, brackets included, by the value of
// its key, or by "?" if the key is not in knowledge. Returns the new string. On LeetCode:
// 1 <= s.length <= 10^5, 0 <= knowledge.length <= 10^5, every key in knowledge is different;
// s holds lowercase letters and round brackets, brackets are never nested, and each bracket
// pair holds a key of lowercase letters.
//
// The one idea: put the knowledge into a map from key to value, then walk s once. A letter is copied
// as it is. An opening bracket at left starts a pair: its key runs from left + 1 up to the next
// ')', because brackets never nest, so the first ')' after it is its partner. Look the key up
// (with "?" as the answer for a missing key), append the value, and jump left onto the ')' so the
// loop continues after the pair.
//
// Time: O(n + k) -- k pairs go into the map, and every character of s is read once
// Space: O(n + k) -- the map and the result

public final class EvaluateBracketPairs {

	public static String evaluate(String s, List<List<String>> knowledge) {
		Map<String, String> map = knowledge.stream()
				.collect(Collectors.toMap(k -> k.get(0), v -> v.get(1))); // MAP: key -> value
		StringBuilder sb = new StringBuilder();
		for (int left = 0; left < s.length(); left++) {
			char c = s.charAt(left);
			if (c == '(') {
				int right = s.indexOf(')', left + 1); // PARTNER: the first ')' after it, since brackets never nest
				String key = s.substring(left + 1, right);
				sb.append(map.getOrDefault(key, "?")); // REPLACE: the value, or "?" for an unknown key
				left = right; // SKIP: past the whole pair
			} else {
				sb.append(c); // COPY: an ordinary letter
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s1 = "(name)is(age)yearsold";
		List<List<String>> list1 = new ArrayList<>();
		list1.add(Arrays.asList("name", "bob"));
		list1.add(Arrays.asList("age", "two"));
		System.out.println(evaluate(s1, list1)); // bobistwoyearsold

		String s2 = "hi(name)";
		List<List<String>> list2 = new ArrayList<>();
		list2.add(Arrays.asList("a", "b"));
		System.out.println(evaluate(s2, list2)); // hi?

		String s3 = "(a)(a)(a)aaa";
		List<List<String>> list3 = new ArrayList<>();
		list3.add(Arrays.asList("a", "yes"));
		System.out.println(evaluate(s3, list3)); // yesyesyesaaa
	}
}
