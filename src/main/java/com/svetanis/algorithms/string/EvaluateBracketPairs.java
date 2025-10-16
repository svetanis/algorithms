package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 1807. Evaluate the Bracket Pairs of a String

public final class EvaluateBracketPairs {
	// Time Complexity: O(n + k)
	// Space Complexity: O(n + m)

	public static String evaluate(String s, List<List<String>> knowledge) {
		Map<String, String> map = knowledge.stream()
				.collect(Collectors.toMap(k -> k.get(0), v -> v.get(1)));
		StringBuilder sb = new StringBuilder();
		for (int left = 0; left < s.length(); left++) {
			char c = s.charAt(left);
			if (c == '(') {
				int right = s.indexOf(')', left + 1);
				String key = s.substring(left + 1, right);
				sb.append(map.getOrDefault(key, "?"));
				left = right;
			} else {
				sb.append(c);
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
