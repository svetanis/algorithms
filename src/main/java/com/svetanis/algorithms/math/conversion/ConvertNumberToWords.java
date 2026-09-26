package com.svetanis.algorithms.math.conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 273. Integer to English Words

// Read the number in groups of three digits, from the billions down:
// 1,234,567 is "One Million", "Two Hundred Thirty Four Thousand",
// "Five Hundred Sixty Seven". Each group is said the same way, followed
// by its scale word. Every word goes into one list, and the list is
// joined with single spaces -- so no piece has to remember its own space.

public final class ConvertNumberToWords {
	// Time Complexity: O(1) -- at most four groups of three digits
	// Space Complexity: O(1)

	private static final Map<Integer, String> DIGITS = numberToWords();

	public static String convert(int n) {
		if (n == 0) {
			return "Zero";
		}
		List<String> words = new ArrayList<>();
		// i is the scale: billion, million, thousand
		for (int i = 1_000_000_000; i >= 1000; i /= 1000) {
			if (n >= i) {
				words.add(threeDigits(n / i));
				words.add(DIGITS.get(i));
				n %= i;
			}
		}
		if (n > 0) {
			words.add(threeDigits(n));
		}
		return String.join(" ", words);
	}

	// 1..999 in words; each part is added with a space in front, and the
	// first space is cut off at the end
	private static String threeDigits(int n) {
		StringBuilder sb = new StringBuilder();
		// convert hundreds place
		if (n >= 100) {
			sb.append(" ").append(DIGITS.get(n / 100)).append(" ").append(DIGITS.get(100));
			n %= 100;
		}
		if (n > 0) {
			if (n < 20 || n % 10 == 0) {
				sb.append(" ").append(DIGITS.get(n));
			} else {
				sb.append(" ").append(DIGITS.get(n / 10 * 10)).append(" ").append(DIGITS.get(n % 10));
			}
		}
		return sb.substring(1);
	}

	public static void main(String[] args) {
		System.out.println(convert(123)); // One Hundred Twenty Three
		System.out.println(convert(12345)); // Twelve Thousand Three Hundred Forty Five
		// One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven
		System.out.println(convert(1234567));

		System.out.println(convert(9923)); // Nine Thousand Nine Hundred Twenty Three
		System.out.println(convert(1000010)); // One Million Ten
		System.out.println(convert(0)); // Zero
	}

	private static Map<Integer, String> numberToWords() {
		Map<Integer, String> map = new HashMap<>();
		// single digit mapping
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		map.put(4, "Four");
		map.put(5, "Five");
		map.put(6, "Six");
		map.put(7, "Seven");
		map.put(8, "Eight");
		map.put(9, "Nine");
		// teen mappings
		map.put(10, "Ten");
		map.put(11, "Eleven");
		map.put(12, "Twelve");
		map.put(13, "Thirteen");
		map.put(14, "Fourteen");
		map.put(15, "Fifteen");
		map.put(16, "Sixteen");
		map.put(17, "Seventeen");
		map.put(18, "Eighteen");
		map.put(19, "Nineteen");
		// tens place mappings
		map.put(20, "Twenty");
		map.put(30, "Thirty");
		map.put(40, "Forty");
		map.put(50, "Fifty");
		map.put(60, "Sixty");
		map.put(70, "Seventy");
		map.put(80, "Eighty");
		map.put(90, "Ninety");
		// scale mappings
		map.put(100, "Hundred");
		map.put(1000, "Thousand");
		map.put(1000000, "Million");
		map.put(1000000000, "Billion");
		return map;
	}
}