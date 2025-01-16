package com.svetanis.algorithms.math.conversion.roman;

// 12. Integer to Roman
// main.java.numeric.conversions.roman

public final class IntegerToRoman12 {
  // Time Complexity: O(1)

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