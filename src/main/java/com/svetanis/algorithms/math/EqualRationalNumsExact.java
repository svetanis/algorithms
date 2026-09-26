package com.svetanis.algorithms.math;

import java.util.Arrays;

// 972. Equal Rational Numbers

// a repeating decimal is exactly a fraction, so both strings reduce to
// fractions and compare as whole numbers -- no double, no tolerance.
//
// expanding the repeating run into a double needs a tolerance, and no
// tolerance works: the parts run to 4 digits, so a denominator reaches
// 10^4 * 9999, and two different values with denominators that size can
// sit about 10^-16 apart, closer than a double can tell them apart.
// 0.3804(5645) and 0.380(456) are 8.1e-10 apart and are not equal

public final class EqualRationalNumsExact {

	public static boolean isRationalEqual(String s, String t) {
		return Arrays.equals(fraction(s), fraction(t));
	}

	// I.N(R) = I + (N * (10^r - 1) + R) / (10^n * (10^r - 1)),
	// where n and r are the lengths of N and R.
	//
	// the denominator is what the repeating run is worth: one repeat of R
	// over as many 9s as R has digits is the classic 0.(3) = 3/9, and the
	// leading 10^n shifts that past the non-repeating digits. so 0.5(25)
	// is (5 * 99 + 25) / (10 * 99) = 520/990, which reduces to 52/99 --
	// the same fraction 0.(52) reduces to.
	//
	// returns { numerator, denominator }, already in lowest terms, so two
	// values are equal exactly when both entries match. comparing by
	// cross-multiplying instead would overflow: a numerator reaches about
	// 10^12 and a denominator 10^8, and their product does not fit a long
	private static long[] fraction(String s) {
		int dot = s.indexOf('.');
		// "12" carries no fractional part at all, only an integer one
		if (dot < 0) {
			return reduce(Long.parseLong(s), 1);
		}
		long integerPart = Long.parseLong(s.substring(0, dot));
		String rest = s.substring(dot + 1);
		int open = rest.indexOf('(');
		String nonRepeating = open < 0 ? rest : rest.substring(0, open);
		String repeating = open < 0 ? "" : rest.substring(open + 1, rest.length() - 1);
		long shift = powerOfTen(nonRepeating.length());
		if (repeating.isEmpty()) {
			return reduce(integerPart * shift + digits(nonRepeating), shift);
		}
		long nines = powerOfTen(repeating.length()) - 1;
		long denominator = shift * nines;
		long numerator = digits(nonRepeating) * nines + digits(repeating);
		return reduce(integerPart * denominator + numerator, denominator);
	}

	// reducing on the way out is what removes every special case. 0.9(9)
	// arrives as 9/9 plus the carried integer part and leaves as 1/1, the
	// same pair "1." leaves as, so the all-nines tail needs no handling
	private static long[] reduce(long numerator, long denominator) {
		// a numerator of 0 would make the divisor 0 and the division blow
		// up, and 0 is the same value whatever sits underneath it
		if (numerator == 0) {
			return new long[] { 0, 1 };
		}
		long divisor = gcd(numerator, denominator);
		return new long[] { numerator / divisor, denominator / divisor };
	}

	// an empty run of digits is worth 0, which is what "1." and "0.(9)"
	// need -- each leaves one of the two parts with nothing in it
	private static long digits(String s) {
		long value = 0;
		for (int i = 0; i < s.length(); i++) {
			value = value * 10 + (s.charAt(i) - '0');
		}
		return value;
	}

	private static long powerOfTen(int exponent) {
		long power = 1;
		for (int i = 0; i < exponent; i++) {
			power *= 10;
		}
		return power;
	}

	private static long gcd(long a, long b) {
		while (b != 0) {
			long rem = a % b;
			a = b;
			b = rem;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(isRationalEqual("0.(52)", "0.5(25)")); // true
		System.out.println(isRationalEqual("0.1666(6)", "0.166(66)")); // true
		System.out.println(isRationalEqual("0.9(9)", "1.")); // true
		System.out.println(isRationalEqual("0.1(6)", "0.1666(6)")); // true
		System.out.println(isRationalEqual("12", "12.")); // true
		System.out.println(isRationalEqual("0.(0)", "0.")); // true

		// 8.1e-10 apart, so every tolerance a double can police calls
		// these equal. as fractions they are 38041841/99990000 and
		// 31673/83250, which are plainly not the same
		System.out.println(isRationalEqual("0.3804(5645)", "0.380(456)")); // false
	}
}
