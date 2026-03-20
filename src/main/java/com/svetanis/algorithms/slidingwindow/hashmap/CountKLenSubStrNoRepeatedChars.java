package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.HashMap;
import java.util.Map;

// 1100. Find K-Length Substrings With No Repeated Characters

public final class CountKLenSubStrNoRepeatedChars {
	// Time Complexity: O(n)
	// Space Complexity: O(m)

	public static int countSubStrNoRepeats(String s, int k) {
		if (k > s.length()) {
			return 0;
		}
		char[] freq = new char[26];
		int left = 0;
		int count = 0;
		for (int right = 0; right < s.length(); right++) {
			int index = s.charAt(right) - 'a';
			freq[index] += 1;
			while (freq[index] > 1) {
				freq[s.charAt(left) - 'a'] -= 1;
				left += 1;
			}
			if (right - left + 1 == k) {
				count += 1;
				freq[s.charAt(left) - 'a'] -= 1;
				left += 1;
			}
		}
		return count;
	}

	public static int countSubStrNoRepeats2(String s, int k) {
		if (k > s.length()) {
			return 0;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < k; i++) {
			map.merge(s.charAt(i), 1, Integer::sum);
		}
		int count = map.size() == k ? 1 : 0;
		for (int right = k; right < s.length(); right++) {
			map.merge(s.charAt(right), 1, Integer::sum);
			char out = s.charAt(right - k);
			int f = map.merge(out, -1, Integer::sum);
			if (f == 0) {
				map.remove(out);
			}
			if (map.size() == k) {
				count += 1;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countSubStrNoRepeats("havefunonleetcode", 5)); // 6
		System.out.println(countSubStrNoRepeats("home", 5)); // 0
		System.out.println(countSubStrNoRepeats2("havefunonleetcode", 5)); // 6
		System.out.println(countSubStrNoRepeats2("home", 5)); // 0
	}
}
