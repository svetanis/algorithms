package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// Group red, green and blue balls
//
// Input: a line of n balls, each one red, green or blue, as the chars 'R', 'G', 'B'.
// Return: nothing -- the array is rearranged in place so that every 'R' comes first,
// then every 'G', then every 'B'. Any char that is not 'R' or 'G' is treated as 'B'.
//
// The one idea: LC 75 Sort Colors with colours for 0, 1, 2. Four regions behind three
// pointers: [0, low) reds, [low, mid) greens, [mid, high] not looked at, (high, n-1]
// blues. A blue is swapped to high and only high moves: what comes back from high has
// not been looked at yet.
//
// Siblings -- the same loop on 0, 1, 2:
//   twopointers.segregate.DutchNationalFlag and twopointers.segregate.SegregateZerosOnesTwos
//
// Time: O(n) -- every pass shrinks [mid, high] by one, so at most n passes.
// Space: O(1) -- swaps in place.

public final class RGBs {

  public static void sort(char[] a) {
    int low = 0;                // START: [0, low) holds the reds
    int mid = 0;                // START: the reader; [mid, high] is unread
    int high = a.length - 1;    // START: (high, n-1] holds the blues

    while (mid <= high) {       // STOP: nothing left to read
      if (a[mid] == 'R') {
        swap(a, low, mid);      // SWAP the red to the edge of the reds
        low++;                  // MOVE both: what came back from low is a green
        mid++;
      } else if (a[mid] == 'G') {
        mid++;                  // MOVE mid: already in the greens
      } else {
        swap(a, mid, high);     // SWAP the blue to the edge of the blues
        high--;                 // MOVE high only: mid reads the newcomer
      }
    }
  }

  private static void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }

  public static void main(String[] args) {
    char[] a = { 'G', 'G', 'B', 'B', 'R', 'R' };
    sort(a);
    System.out.println(Arrays.toString(a)); // [R, R, G, G, B, B]

    char[] a1 = { 'R', 'G', 'B' };
    sort(a1);
    System.out.println(Arrays.toString(a1)); // [R, G, B]

    char[] a2 = { 'B', 'G', 'R' };
    sort(a2);
    System.out.println(Arrays.toString(a2)); // [R, G, B]
  }
}
