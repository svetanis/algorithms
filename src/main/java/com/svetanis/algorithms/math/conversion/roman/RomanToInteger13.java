package com.svetanis.algorithms.math.conversion.roman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 13. Roman to Integer
// main.java.numeric.conversions.roman

public final class RomanToInteger13 {
  // Time Complexity: O(n)

  private Map<Character, Integer> map;

  public RomanToInteger13() {
    map = init();
  }

  public int romanToInt(String roman) {
    int prev = 0;
    int total = 0;
    int n = roman.length();
    for (int i = n - 1; i >= 0; i--) {
      char c = roman.charAt(i);
      int curr = map.get(c);
      if (curr < prev) {
        total -= curr;
      } else {
        total += curr;
      }
      prev = curr;
    }
    return total;
  }

  private Map<Character, Integer> init() {
    String s = "IVXLCDM";
    List<Integer> list = Arrays.asList(1, 5, 10, 50, 100, 500, 1000);
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < list.size(); i++) {
      map.put(s.charAt(i), list.get(i));
    }
    return map;
  }

  public static void main(String[] args) {
    RomanToInteger13 num = new RomanToInteger13();
    System.out.println(num.romanToInt("III")); // 3
    System.out.println(num.romanToInt("LVIII")); // 58
    System.out.println(num.romanToInt("MCMXCIV")); // 1994
    
    System.out.println(num.romanToInt("MMXIII")); // 2013
    System.out.println(num.romanToInt("XIII")); // 13
    System.out.println(num.romanToInt("CCVII")); // 207
    System.out.println(num.romanToInt("MLXVI")); // 1066
    System.out.println(num.romanToInt("XL")); /// 40
    System.out.println(num.romanToInt("XC")); // 90
    System.out.println(num.romanToInt("CD")); // 400
    System.out.println(num.romanToInt("CM")); /// 900
    System.out.println(num.romanToInt("MCMIV")); // 1904
    System.out.println(num.romanToInt("MCMLIV")); // 1954
    System.out.println(num.romanToInt("MCMXC")); /// 1990
    System.out.println(num.romanToInt("MMVIII")); // 2008

  }
}