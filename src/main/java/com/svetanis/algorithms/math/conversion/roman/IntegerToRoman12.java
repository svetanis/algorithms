package com.svetanis.algorithms.math.conversion.roman;

// 12. Integer to Roman

public final class IntegerToRoman12 {
  // Time Complexity: O(1) -- at most 15 symbols for any n up to 3999
  // Space Complexity: O(1)

  // the subtractive pairs (900 = CM, 4 = IV, ...) sit in the table beside
  // the plain symbols, so taking the largest value that fits each time
  // writes them without any special case

  private static final int[] DECIMAL = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
  private static final String[] ROMAN = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

  public static String intToRoman(int decimal) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ROMAN.length; i++) {
      while (decimal >= DECIMAL[i]) {
        decimal -= DECIMAL[i];
        sb.append(ROMAN[i]);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(intToRoman(3749)); // MMMDCCXLIX
    System.out.println(intToRoman(58)); // LVIII
    System.out.println(intToRoman(1994)); // MCMXCIV
  }
}