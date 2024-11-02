package com.svetanis.algorithms.bits.xor;

// 461. Hamming Distance

public final class HammingDistance {

  public static int hammingDist(int a, int b) {
  	return Integer.bitCount(a ^ b);
  }

  public static void main(String[] args) {
    System.out.println(hammingDist(5, 11));
  }
}

