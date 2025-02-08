package com.svetanis.algorithms.math.conversion;

import java.util.HashMap;
import java.util.Map;

public final class ConvertNumberToWords {

	private static final Map<Integer, String> DIGITS = numberToWords();

	public static String convert(int n) {
		if (n == 0) {
			return "Zero";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1000000000; i >= 1000; i /= 1000) {
			if (n >= i) {
				sb.append(threeDigits(n / i)).append(" ").append(DIGITS.get(i));
				n %= i;
			}
		}
		if (n > 0) {
			sb.append(threeDigits(n));
		}
		return sb.toString();
	}

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
		System.out.println(convert(9923));
		System.out.println(convert(523));
		System.out.println(convert(89));

		System.out.println(convert(123));
		System.out.println(convert(12345));
		System.out.println(convert(1234567));
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