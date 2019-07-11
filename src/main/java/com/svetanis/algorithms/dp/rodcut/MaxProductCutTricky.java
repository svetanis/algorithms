package com.svetanis.algorithms.dp.rodcut;

// The maximum product can be obtained be repeatedly 
// cutting parts of size 3 while size is greater than 4, 
// keeping the last part as size of 2 or 3 or 4.
// For example, n = 10, the maximum product is obtained by 3, 3, 4. 
// For n = 11, the maximum product is obtained by 3, 3, 3, 2. 

public final class MaxProductCutTricky {

  public static int maxProduct(int n) {
    // n equals to 2 or 3
    // must be handled explicitly
    if (n == 2 || n == 3) {
      return n - 1;
    }
    // keep removing parts of size 3
    // while n is greater than 4
    int res = 1;
    while (n > 4) {
      n -= 3;
      res *= 3; // keep multiplying 3 to res
    }
    // the last part multiplied by previous parts
    return n * res;
  }

  public static void main(String[] args) {
    System.out.println(maxProduct(10));
  }
}