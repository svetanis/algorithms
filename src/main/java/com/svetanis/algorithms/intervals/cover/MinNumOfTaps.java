package com.svetanis.algorithms.intervals.cover;

// 1326. Minimum Number of Taps to Open to Water a Garden
//
// The garden is the line from 0 to n. ranges has n + 1 entries: the tap at point i waters
// [i - ranges[i], i + ranges[i]]. Returns the fewest taps that together water the whole line from
// 0 to n, or -1 if even all of them leave a gap. The garden is a continuous stretch: taps that
// water only the points 0, 1, 2, 3 still leave the land between them dry. A tap with range 0
// waters a single point, which never closes a gap, so it is skipped -- a tidiness: admitting it
// changes no answer.
//
// First turn the taps into a table: last[p] is the furthest point any single tap starting at p
// reaches (a tap reaching left of 0 is counted as starting at 0). Then walk p from 0 towards n.
// prev is how far the taps opened so far reach, and curr is the furthest any tap starting at or
// before p reaches. When the walk arrives at prev, the opened taps run out there, so open one
// more tap: the one that reaches curr, the furthest possible. Choosing the furthest is safe: every
// other tap that could be opened here also starts at or before p and stops sooner, so it waters
// nothing the chosen one misses. Choosing the one that ends first instead -- the rule that is right
// for keeping the most non-overlapping intervals -- opens too many: n = 2, ranges = [1, 3, 3]
// needs 1 tap, and it opens 2. If curr is at or below p, nothing reaches past p and the garden
// cannot be covered.
//
// ranges[i] <= 100 keeps i + ranges[i] far inside int. Without that bound the sum can overflow to
// a negative end and the method answers -1 silently; Math.min(n, i + ranges[i]) does not help, since
// the sum overflows first -- clamp with ranges[i] >= n - i ? n : i + ranges[i], or use long.

public final class MinNumOfTaps {
  // Time Complexity: O(n), one pass to build the table and one walk over it; no sort
  // Space Complexity: O(n), the table

  public static int minTaps(int n, int[] ranges) {
    int[] last = intervals(ranges);
    int curr = 0; // the furthest reach of any tap starting at or before i
    int prev = 0; // the land is watered up to here by the taps opened so far
    int taps = 0;
    for (int i = 0; i < n; i++) { // WALK: the last step is n - 1 -> n
      curr = Math.max(curr, last[i]); // NOTE the best tap starting here
      if (curr <= i) { // HOLE: nothing reaches past i, the stretch just after i stays dry
        return -1;
      }
      if (i == prev) { // EDGE: '==', a question -- the next step is onto dry land
        taps++; // OPEN the tap reaching curr, the furthest of every tap seen so far
        prev = curr;
      } // otherwise still on watered land: walk on, deciding nothing
    }
    return taps;
  }

  // CLIP: last[p] = the furthest point reached by a tap whose watered stretch starts at p
  private static int[] intervals(int[] ranges) {
    int n = ranges.length;
    int[] a = new int[n + 1];
    for (int i = 0; i < n; i++) {
      if (ranges[i] > 0) { // range 0 waters one point and never closes a gap
        int left = Math.max(0, i - ranges[i]); // the garden begins at 0
        int right = i + ranges[i]; // past n is harmless
        a[left] = Math.max(a[left], right); // taps with one start differ only in reach: keep the furthest
      }
    }
    return a;
  }

  public static void main(String[] args) {
    System.out.println(minTaps(5, new int[] { 3, 4, 1, 1, 0, 0 })); // 1: tap 1 waters [0, 5] alone
    System.out.println(minTaps(3, new int[] { 0, 0, 0, 0 })); // -1: every point wet, the land between dry
    System.out.println(minTaps(2, new int[] { 1, 3, 3 })); // 1: the input that catches "ends first"
    System.out.println(minTaps(5, new int[] { 1, 0, 2, 0, 1, 0 })); // 2: [0, 4] then [3, 5]; [0, 1] is never opened
  }
}
