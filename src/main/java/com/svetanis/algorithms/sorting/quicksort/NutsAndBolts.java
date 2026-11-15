package com.svetanis.algorithms.sorting.quicksort;

import java.util.Arrays;
import java.util.Random;

// Nuts and Bolts -- not a LeetCode submission (no LeetCode twin).
//
// Input: nuts and bolts, two char arrays of one length n; every nut has exactly one bolt of its
// size and no two nuts have the same size. A nut can be compared only with a bolt, never with
// another nut, and the same for bolts. Rearranges both arrays so that nuts[i] fits bolts[i] for
// every i; both end up in ascending order of size.
//
// The one idea: quicksort on both arrays at once, because a bolt can split the nuts but a nut
// cannot split the nuts. Pick a random bolt. Compare it with every nut: the smaller nuts go left,
// the larger go right, and the one nut that fits lands between them, at its final index. That nut
// now splits the bolts the same way, so its bolt lands at the same index. Everything left of that
// index is smaller than both, everything right is larger than both, so the two sides are separate
// smaller problems, matched the same way.
//
// Time: O(n log n) on average -- the pivot bolt is random, so the sides are balanced on average;
// the worst case is O(n^2).
// Space: O(log n) on average -- the recursion.
//
// Siblings: quicksort.impl.QuickSortRandomized is the same split with one array, the pivot a
// random element of it.

public final class NutsAndBolts {

  private static final Random GENERATOR = new Random();

  public static void match(char[] nuts, char[] bolts) {
    match(nuts, bolts, 0, nuts.length - 1);
  }

  public static void match(char[] nuts, char[] bolts, int left, int right) {
    if (left < right) {
      int random = randomIndex(left, right);
      int split = partition(nuts, left, right, bolts[random]); // nuts split by a bolt
      partition(bolts, left, right, nuts[split]); // bolts split by its nut: same split
      match(nuts, bolts, left, split - 1); // LEFT: the smaller nuts and bolts
      match(nuts, bolts, split + 1, right); // RIGHT: the larger ones
    }
  }

  // values smaller than pivot to the left, the one equal to it placed after them, larger
  // ones to the right; returns where the equal one landed
  private static int partition(char[] a, int left, int right, char pivot) {
    int i = left; // where the next smaller value goes
    for (int j = left; j < right; ++j) {
      if (a[j] < pivot) {
        swap(a, i, j); // SMALLER: goes to the left block
        i++;
      } else if (a[j] == pivot) {
        swap(a, j, right); // PARK: the match at the right end
        j--; // the value swapped in from the right end is unread: look at j again
      }
    }
    swap(a, i, right); // PLACE: the match to its final place
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
