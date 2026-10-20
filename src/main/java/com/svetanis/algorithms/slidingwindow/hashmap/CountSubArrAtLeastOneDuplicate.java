package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.HashMap;
import java.util.Map;

// Subarrays with at least one repeated value
//
// Input: an integer array a of n values.
// Return: how many contiguous subarrays hold some value at least twice.
//
// The one idea: count the opposite. A subarray either has a repeat or has all its
// values different, never both, so the answer is all subarrays minus the subarrays
// with no repeat. There are n(n+1)/2 subarrays, one per pair of start <= end.
//
// countUnique counts the subarrays with no repeat. left is the position just before
// the window, -1 at the start, so the window is left + 1 .. i and holds no repeat.
// Every start from left + 1 to i gives a subarray ending at i with no repeat: i - left
// of them, each counted once, at its end i. When a[i] is already in the window, left
// walks forward to the earlier copy of a[i], taking each value it passes out of the
// map, and stops on that copy -- so the window starts just after it and again holds
// no repeat.
//
// Time: O(n) -- left only moves forward, at most n steps over the whole run, so the
// inner while runs at most n times in total.
// Space: O(d) -- one map entry per different value seen; counts drop to 0 but entries
// are never removed.
// The counts are ints: n * (n + 1) overflows int once n is above 46340.
//
// Siblings: CountDistinctSubArrs counts subarrays holding every different value of the
// array, with a window that grows. LongestDistinctSubStrLen (slidingwindow/string) is
// the same no-repeat window measuring its longest length instead of counting.
// CountSubArraysKDistinct also counts by subtracting one count from another.

public final class CountSubArrAtLeastOneDuplicate {

  private static int count(int[] a) {
    int n = a.length;
    int total = n * (n + 1) / 2; // ALL: one subarray per start <= end
    int unique = countUnique(a); // NO REPEAT: all values different
    return total - unique; // RETURN: the rest have a repeat
  }

  private static int countUnique(int[] a) {
    int n = a.length;
    int count = 0;
    int left = -1;
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      int freq = map.getOrDefault(a[i], 0);
      map.put(a[i], freq + 1); // ADD

      if (map.get(a[i]) >= 2) { // REPEAT: an earlier copy of a[i] is in the window
        left++;
        while (a[left] != a[i]) { // SHRINK until left reaches that earlier copy
          map.put(a[left], map.get(a[left]) - 1); // OUT
          left++;
        }
        map.put(a[left], map.get(a[left]) - 1); // OUT: the earlier copy; left now sits on it
      }
      count += i - left; // COUNT: starts left + 1 .. i, all ending at i
    }
    return count; // RETURN
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3 };
    System.out.println(count(a)); // expected 0

    int[] a1 = { 4, 3, 4, 3 };
    System.out.println(count(a1)); // expected 3

    int[] a2 = { 1, 1, 1 };
    System.out.println(count(a2)); // expected 3

    int[] a3 = {};
    System.out.println(count(a3)); // expected 0
  }
}
