package com.svetanis.algorithms.math.operations;

// 415. Add Strings

public final class AddStrings {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static String add(String num1, String num2) {
  	int carry = 0;
  	int i = num1.length() - 1;
  	int j = num2.length() - 1;
  	StringBuilder sb = new StringBuilder();
  	while(i >= 0 || j >= 0 || carry > 0) {
      if (i >= 0) {
        carry += (num1.charAt(i) - '0');
        i--;
      }
      if (j >= 0) {
        carry += (num2.charAt(j) - '0');
        j--;
      }
      sb.append(carry % 10);
      carry /= 10;  	
  	}
  	return sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.out.println(add("11", "123")); // 134
    System.out.println(add("456", "77")); // 533
    System.out.println(add("0", "0")); // 0
  }
}