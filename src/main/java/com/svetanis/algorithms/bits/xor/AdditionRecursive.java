package com.svetanis.algorithms.bits.xor;

// 371. Sum of Two Integers

public final class AdditionRecursive {

  public static int add(int a, int b) {
    if (b == 0) {
      return a;
    }
    int sum = a ^ b; // add without carrying
    int carry = (a & b) << 1; // carry but don't add
    return add(sum, carry); // recurse
  }

  public static void main(String[] args) {
    System.out.println(add(5, 11));
  }
}

