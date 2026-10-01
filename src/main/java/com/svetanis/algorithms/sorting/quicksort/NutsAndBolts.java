package com.svetanis.algorithms.sorting.quicksort;

import java.util.Arrays;
import java.util.Random;

// Nuts and Bolts: n distinct nuts and n matching bolts. A nut can be compared only with a
// bolt, never with another nut, and the same for bolts. Pair them up.
//
// Quicksort on both arrays at once. A random bolt splits the nuts; the nut that fits it
// lands at the split, and that nut then splits the bolts the same way, so its bolt lands
// at the same index. Both halves are then matched the same way.

public final class NutsAndBolts {
  // Time Complexity: O(n log n) average
  // Space Complexity: O(log n) average, the recursion

  private static final Random GENERATOR = new Random();

  public static void match(char[] nuts, char[] bolts) {
    match(nuts, bolts, 0, nuts.length - 1);
  }

  public static void match(char[] nuts, char[] bolts, int left, int right) {
    if (left < right) {
      int random = randomIndex(left, right);
      int split = partition(nuts, left, right, bolts[random]); // nuts split by a bolt
      partition(bolts, left, right, nuts[split]); // bolts split by its nut: same split
      match(nuts, bolts, left, split - 1);
      match(nuts, bolts, split + 1, right);
    }
  }

  // values smaller than pivot to the left, the one equal to it placed after them, larger
  // ones to the right; returns where the equal one landed
  private static int partition(char[] a, int left, int right, char pivot) {
    int i = left; // where the next smaller value goes
    for (int j = left; j < right; ++j) {
      if (a[j] < pivot) {
        swap(a, i, j);
        i++;
      } else if (a[j] == pivot) {
        swap(a, j, right); // park the match at the right end
        j--; // the value swapped in from the right end is unread: look at j again
      }
    }
    swap(a, i, right); // the match to its final place
    return i;
  }

  private static int randomIndex(int left, int right) {
    return GENERATOR.nextInt(right - left + 1) + left; // right included
  }

  private static void swap(char[] a, int i, int j) {
    char temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    char[] nuts = { '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };
    char[] bolts = { '$', '%', '*', '(', ')', '&', '^', '~', '!', '@', '#' };
    match(nuts, bolts);
    System.out.println(Arrays.toString(nuts)); // [!, #, $, %, &, (, ), *, @, ^, ~]
    System.out.println(Arrays.toString(bolts)); // [!, #, $, %, &, (, ), *, @, ^, ~]
  }
}
