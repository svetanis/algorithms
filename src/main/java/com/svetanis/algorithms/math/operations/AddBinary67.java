package com.svetanis.algorithms.math.operations;

// 67. Add Binary

public final class AddBinary67 {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static String addBinary(String s1, String s2) {
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int i = s1.length() - 1;
    int j = s2.length() - 1;
    while (i >= 0 || j >= 0 || carry > 0) {
      if (i >= 0) {
        carry += s1.charAt(i) - '0';
        i--;
      }
      if (j >= 0) {
        carry += s2.charAt(j) - '0';
        j--;
      }
      sb.append(carry % 2);
      carry /= 2;
    }
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.out.println(addBinary("11", "1")); // 100
    System.out.println(addBinary("1010", "1011")); // 10101
  }
}