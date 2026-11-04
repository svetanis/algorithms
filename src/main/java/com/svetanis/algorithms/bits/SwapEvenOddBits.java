package com.svetanis.algorithms.bits;

// Swap all odd and even bits (GeeksforGeeks; no LeetCode problem)
//
// Input: an int x, read as 32 slots. Returns x with each pair of neighbouring slots swapped: slot 0
// with slot 1, slot 2 with slot 3, ..., slot 30 with slot 31. Right for every int, negatives
// included.
//
// The one idea: 0xAAAAAAAA is 1010...10 in binary -- a 1 in slots 1, 3, 5, ..., 31 -- and
// 0x55555555 is 0101...01 -- a 1 in slots 0, 2, 4, ..., 30. & with each mask splits x into its two
// halves. Every slot of the first half moves down one place and every slot of the second half moves
// up one place, and | puts the halves back together. The shift down is >>>, which fills slot 31
// with 0: slot 31 has just moved down to slot 30, and slot 30 moving up will fill it.
// "Even" and "odd" count positions from 1, as GeeksforGeeks does: 0xAAAAAAAA holds the "even"
// positions 2, 4, 6, ..., which are slots 1, 3, 5, ... when counted from 0.
//
// Time: O(1) -- six operations.
// Space: O(1) -- two ints.

public final class SwapEvenOddBits {
  // Time Complexity: O(1)

  public static int swap(int x) {
    // get all even bits of x
    int even = x & 0xAAAAAAAA; // SPLIT: slots 1, 3, 5, ..., 31

    // get all odd bits of x
    int odd = x & 0x55555555; // SPLIT: slots 0, 2, 4, ..., 30

    // right shift even bits -- >>> fills slot 31 with 0; slot 31 is
    // one of the slots moving down
    even = even >>> 1; // MOVE DOWN one slot

    // left shift odd bits
    odd = odd << 1; // MOVE UP one slot

    // combine even and odd bits
    return even | odd; // JOIN: the two halves never share a slot
  }

  public static void main(String args[]) {
    int n = 23; // 00010111
    // output is 43 (00101011)
    System.out.println(swap(n)); // 43
    System.out.println(swap(2)); // 1
    System.out.println(swap(0xAAAAAAAA)); // 1431655765, which is 0x55555555
  }
}

