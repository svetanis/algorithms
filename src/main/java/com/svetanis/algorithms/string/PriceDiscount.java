package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 2288. Apply Discount to Prices

public final class PriceDiscount {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String discountPrices(String sentence, int discount) {
		String[] words = sentence.split(" ");
		List<String> list = new ArrayList<>();
		for (String word : words) {
			Optional<Long> optional = price(word);
			if (optional.isPresent()) {
				double price = optional.get() * (1 - discount / 100.0);
				String formatted = String.format("$%.2f", price);
				list.add(formatted);
			} else {
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
			long val = Long.parseLong(num);
			return Optional.of(val);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static void main(String[] args) {
		String s1 = "there are $1 $2 and 5$ candies in the shop";
		String s2 = "1 2 $3 4 $5 $6 7 8$ $9 $10$";
		String s3 = "706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6";
		System.out.println(discountPrices(s1, 50));
		System.out.println(discountPrices(s2, 100));
		System.out.println(discountPrices(s3, 28));
	}
}
