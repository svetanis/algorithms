package com.svetanis.algorithms.slidingwindow.array;

// 1493. Longest Subarray of 1's After Deleting One Element

// delete exactly one element, then return the length of the
// longest run of 1s in what remains. the deletion is mandatory,
// even when the array is all 1s.

// a block can become all 1s after one deletion iff it holds AT MOST
// ONE 0 -- delete the 0, or delete any 1 if there is no 0. two 0s
// cannot both go. that is the window rule.

// two solutions: slidingWindow names the block it KEEPS, by its two
// endpoints; prefixSuffix names the element it DELETES, by its index.
// they count the same surviving elements. worked through, with a
// trace, in leetcode/sliding-window.md.

public final class LongestSubArrAfterDeletingOneElement {

  // the canonical sliding window for this pattern:
  // longest window containing at most one 0.
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int slidingWindow(int[] a) {
    int left = 0;
    int zeros = 0;
    int max = 0;
    for (int right = 0; right < a.length; right++) {
      if (a[right] == 0) {
        zeros++;
      }
      while (zeros > 1) {
        if (a[left] == 0) {
          zeros--;
        }
        left++;
      }
      // 'right - left', NOT 'right - left + 1'. the window is the
      // block we keep, and exactly one of its elements is deleted --
      // the 0 if there is one, otherwise a 1. dropping the +1 pays
      // for that deletion, so the all-1s case needs no special case.
      max = Math.max(max, right - left);
    }
    return max;
  }

  // Time Complexity: O(n)
  // Space Complexity: O(n) -- two arrays, which is the only reason
  // to prefer the window above when space is asked about

  public static int prefixSuffix(int[] a) {
    int n = a.length;
    int[] prefix = prefix(a);
    int[] suffix = suffix(a);
    int max = 0;
    for (int i = 0; i < n; i++) {
      // deleting a[i] joins the run ending before it to the run
      // starting after it. a[i] itself is never counted, which is
      // how this form pays for the mandatory deletion.
      max = Math.max(max, prefix[i] + suffix[i]);
    }
    return max;
  }

  // count consecutive ones from left to right
  private static int[] prefix(int[] a) {
    int n = a.length;
    int[] prefix = new int[n];
    for (int i = 1; i < n; i++) {
      if (a[i - 1] == 1) {
        prefix[i] = prefix[i - 1] + 1;
      }
    }
    return prefix;
  }

  // count consecutive ones from right to left
  private static int[] suffix(int[] a) {
    int n = a.length;
    int[] suffix = new int[n];
    for (int i = n - 2; i >= 0; i--) {
      if (a[i + 1] == 1) {
        suffix[i] = suffix[i + 1] + 1;
      }
    }
    return suffix;
  }

  public static void main(String args[]) {
    int[] a1 = { 1, 1, 0, 1 };
    System.out.println(slidingWindow(a1) + " " + prefixSuffix(a1)); // 3 3

    int[] a2 = { 0, 1, 1, 1, 0, 1, 1, 0, 1 };
    System.out.println(slidingWindow(a2) + " " + prefixSuffix(a2)); // 5 5

    int[] a3 = { 1, 1, 1 };
    System.out.println(slidingWindow(a3) + " " + prefixSuffix(a3)); // 2 2

    int[] a4 = { 0, 0, 0 };
    System.out.println(slidingWindow(a4) + " " + prefixSuffix(a4)); // 0 0

    int[] a5 = { 1 };
    System.out.println(slidingWindow(a5) + " " + prefixSuffix(a5)); // 0 0
  }
}
