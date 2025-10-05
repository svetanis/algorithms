package com.svetanis.algorithms.string;

import java.util.HashMap;
import java.util.Map;

// 2284. Sender With Largest Word Count

public final class SenderWithLargestWordCount {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n)

	public static String largestWordCount(String[] messages, String[] senders) {
		Map<String, Integer> map = counts(messages, senders);
		String mws = senders[0];
		for (String key : map.keySet()) {
			int mwc = map.get(mws);
			int cwc = map.get(key);
			boolean equals = mwc == cwc && mws.compareTo(key) < 0;
			if (mwc < cwc || equals) {
				mws = key;
			}
		}
		return mws;
	}

	private static Map<String, Integer> counts(String[] messages, String[] senders) {
		int n = senders.length;
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int count = 1;
			for (int j = 0; j < messages[i].length(); j++) {
				if (messages[i].charAt(j) == ' ') {
					count++;
				}
			}
			map.merge(senders[i], count, Integer::sum);
		}
		return map;
	}

	public static void main(String[] args) {
		String[] m1 = { "Hello userTwooo", "Hi userThree", "Wonderful day Alice", "Nice day userThree" };
		String[] s1 = { "Alice", "userTwo", "userThree", "Alice" };
		System.out.println(largestWordCount(m1, s1)); // Alice

		String[] m2 = { "How is leetcode for everyone", "Leetcode is useful for practice" };
		String[] s2 = { "Bob", "Charlie" };
		System.out.println(largestWordCount(m2, s2)); // Charlie
	}
}
