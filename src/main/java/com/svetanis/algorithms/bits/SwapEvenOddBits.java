package com.svetanis.algorithms.bits;

public final class SwapEvenOddBits {
  
  public static int swap(int x) {
    // get all even bits of x
    int even = x & 0xAAAAAAAA;

    // get all odd bits of x
    int odd = x & 0x55555555;

    // right shift even bits
    even = even >> 1;

    // left shift odd bits
    odd = odd << 1;

    // combine even and odd bits
    return even | odd;
  }

  public static void main(String args[]) {
    int n = 23; // 00010111
    // output is 43 (00101011)
    System.out.println(swap(n));
  }
}

