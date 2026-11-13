package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 2288. Apply Discount to Prices
//
// Input: a sentence of words separated by single spaces, and a discount in percent. A word is a
// price if it is '$' followed by one or more digits and nothing else. Returns the sentence with
// every price lowered by the discount and written with exactly two decimals, e.g. "$0.50".
// On LeetCode: 1 <= sentence.length <= 10^5, 0 <= discount <= 100, a price has at most 10
// digits.
//
// The one idea: handle the sentence one word at a time. A word is a price only if what follows
// the '$' parses as a whole number (a word like "5$", "$" or "$1a" is left as it is). For a
// price, multiply by (1 - discount / 100) and print with two decimals. Because the price is a
// whole number of dollars, the result is a whole number of cents, so the two-decimal rounding
// never has a real choice to make; and at most 10 digits fit in a long and in a double's 53
// bits of precision.
//
// Time: O(n) -- each character is read a constant number of times.
// Space: O(n) -- the list of words and the result.

public final class PriceDiscount {

	public static String discountPrices(String sentence, int discount) {
		String[] words = sentence.split(" ");
		List<String> list = new ArrayList<>();
		for (String word : words) { // WALK: one word at a time
			Optional<Long> optional = price(word);
			if (optional.isPresent()) { // PRICE: lower it and write two decimals
				double price = optional.get() * (1 - discount / 100.0);
				String formatted = String.format("$%.2f", price);
				list.add(formatted);
			} else { // SKIP: not a price, keep the word as it is
				list.add(word);
			}
		}
		return String.join(" ", list);
	}

	private static Optional<Long> price(String s) {
		if (!s.startsWith("$")) {
			return Optional.empty();
		}
		String num = s.substring(1);
		try {
			long val = Long.parseLong(num); // PARSE: fails on "", "5a" and the like
			return Optional.of(val);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static void main(String[] args) {
		String s1 = "there are $1 $2 and 5$ candies in the shop";
		String s2 = "1 2 $3 4 $5 $6 7 8$ $9 $10$";
		String s3 = "706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6";
		System.out.println(discountPrices(s1, 50)); // there are $0.50 $1.00 and 5$ candies in the shop
		System.out.println(discountPrices(s2, 100)); // 1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$
		System.out.println(discountPrices(s3, 28)); // 706hzu76jjh7yufr5x9ot60v149k5 $5509377493.92 pw2o $4.32
	}
}
