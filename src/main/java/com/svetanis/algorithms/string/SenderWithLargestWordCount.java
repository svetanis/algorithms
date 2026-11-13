package com.svetanis.algorithms.string;

import java.util.HashMap;
import java.util.Map;

// 2284. Sender With Largest Word Count
//
// Input: messages[i] -- a message of words separated by single spaces, and senders[i] -- who sent
// it. A sender's word count is the number of words in all the messages they sent. Returns the
// sender with the largest word count; if several tie, the one whose name is lexicographically
// largest (uppercase letters sort before lowercase). On LeetCode: 1 <= messages.length <= 10^4,
// 1 <= messages[i].length <= 100, 1 <= senders[i].length <= 10.
//
// The one idea: one map entry holds one sender's total number of words. A message of w words has
// w - 1 spaces, so its words are the spaces plus one. Add that to the sender's entry, then scan
// the entries once, keeping the best sender: a larger count wins, and at an equal count the
// larger name wins.
//
// Time: O(L + k) -- L the total length of the messages, k the number of distinct senders.
// Space: O(k) -- the map.

public final class SenderWithLargestWordCount {

	public static String largestWordCount(String[] messages, String[] senders) {
		Map<String, Integer> map = counts(messages, senders);
		String mws = senders[0]; // mws: the best sender so far
		for (String key : map.keySet()) { // WALK: every sender
			int mwc = map.get(mws);
			int cwc = map.get(key);
			boolean equals = mwc == cwc && mws.compareTo(key) < 0; // TIE: the larger name wins
			if (mwc < cwc || equals) {
				mws = key; // TAKE: key beats the best so far
			}
		}
		return mws;
	}

	private static Map<String, Integer> counts(String[] messages, String[] senders) {
		int n = senders.length;
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int count = 1; // a message with no space is one word
			for (int j = 0; j < messages[i].length(); j++) {
				if (messages[i].charAt(j) == ' ') { // each space starts one more word
					count++;
				}
			}
			map.merge(senders[i], count, Integer::sum); // ADD: to this sender's total
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
