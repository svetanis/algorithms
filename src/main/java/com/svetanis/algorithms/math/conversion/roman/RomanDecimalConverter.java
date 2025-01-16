package com.svetanis.algorithms.math.conversion.roman;

import static com.google.common.collect.ImmutableBiMap.copyOf;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.Preconditions.checkNotNull;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Integer.MIN_VALUE;
import static java.util.regex.Pattern.compile;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Converter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class RomanDecimalConverter {

	private static final BiMap<Integer, String> bimap = build();
	private static final Converter<Integer, String> CONVERTER = new RomanConverter(bimap);

	public static Converter<Integer, String> converter() {
		return CONVERTER;
	}

	public static String intToRoman(int num) {
		return converter().convert(num);
	}

	public static int romanToInt(String roman) {
		if (isValid(roman)) {
			return converter().reverse().convert(roman);
		} else {
			return MIN_VALUE;
		}
	}

	private static boolean isValid(String roman) {
		String one = "^M{0,4}(CM|CD|D?C{0,3})";
		String two = "(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
		String pattern = one + two;
		Pattern pat = compile(pattern);
		Matcher matcher = pat.matcher(roman);
		return matcher.matches();
	}

	public static class RomanConverter extends Converter<Integer, String> {

		public RomanConverter(BiMap<Integer, String> bimap) {
			this.bimap = checkNotNull(bimap, "bimap");
		}

		private final BiMap<Integer, String> bimap;

		@Override
		public String doForward(Integer input) {
			String roman = new String();
			for (int i : Lists.reverse(sort(bimap.keySet()))) {
				while (input >= i) {
					roman += bimap.get(i);
					input -= i;
				}
			}
			return roman;
		}

		@Override
		public Integer doBackward(String input) {
			int result = 0;
			int prev = 0;
			int n = input.length();
			for (int i = n - 1; i >= 0; i--) {
				String c = Character.toString(input.charAt(i));
				int current = bimap.inverse().get(c);
				if (current < prev) {
					result -= current;
				} else {
					result += current;
				}
				prev = current;
			}
			return result;
		}
	}

	private static ImmutableBiMap<Integer, String> build() {
		BiMap<Integer, String> bimap = HashBiMap.create();
		bimap.put(1, "I");
		bimap.put(4, "IV");
		bimap.put(5, "V");
		bimap.put(9, "IX");
		bimap.put(10, "X");
		bimap.put(40, "XL");
		bimap.put(50, "L");
		bimap.put(90, "XC");
		bimap.put(100, "C");
		bimap.put(400, "CD");
		bimap.put(500, "D");
		bimap.put(900, "CM");
		bimap.put(1000, "M");
		return copyOf(bimap);
	}

	public static void main(String[] args) {
		System.out.println(intToRoman(3));
		System.out.println(intToRoman(4));
		System.out.println(intToRoman(5));
		System.out.println(intToRoman(9));
		System.out.println(intToRoman(10));
		System.out.println(intToRoman(11));
		System.out.println(intToRoman(40));
		System.out.println(intToRoman(1904));

		System.out.println();

		List<String> list = getList();
		for (String s : list) {
			System.out.println(romanToInt(s));
		}

	}

	private static ImmutableList<String> getList() {
		List<String> list = newArrayList();
		list.add("MMMMCMLXXXVIII"); // 4988
		list.add("MMMDCCLXXXVII"); // 3787
		list.add("MMMMCMLXXXVIIIV"); // not valid
		list.add("MMMDCCLXLXXVII"); // not valid
		list.add("DMCLXII"); // not valid
		list.add("MDCLXVIII"); // 1668
		list.add("CDLXXXIX"); // 489
		list.add("MDCCCLXXIII"); // 1873
		list.add("MMCCCLXXVI"); // 2376
		list.add("MDCICXLIX"); // not valid
		list.add("MMXIII"); // 2013
		list.add("XIII"); // 13
		list.add("CCVII"); // 207
		list.add("MLXVI");// 1066
		list.add("XL");// 40
		list.add("XC");// 90
		list.add("CD");// 400
		list.add("CM");// 900
		list.add("MCMIV");// 1904
		list.add("MCMLIV");// 1954
		list.add("MCMXC");// 1990
		list.add("MMVIII");// 2008
		return copyOf(list);
	}
}