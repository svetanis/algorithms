package com.svetanis.algorithms.bits;

// swap every pair of neighbouring bits: slot 0 with 1, 2 with 3, ...
// "even" and "odd" count positions from 1, as GeeksforGeeks does:
// 0xAAAAAAAA holds slots 1, 3, 5, ... when counted from 0

public final class SwapEvenOddBits {
  // Time Complexity: O(1)
  
  public static int swap(int x) {
    // get all even bits of x
    int even = x & 0xAAAAAAAA;

    // get all odd bits of x
    int odd = x & 0x55555555;

    // right shift even bits -- >>> fills slot 31 with 0; slot 31 is
    // one of the slots moving down
    even = even >>> 1;

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

